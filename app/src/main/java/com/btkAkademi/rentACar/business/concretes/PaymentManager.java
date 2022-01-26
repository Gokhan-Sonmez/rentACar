package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceItemService;
import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.PromoCodeService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.dtos.CarDto;
import com.btkAkademi.rentACar.business.dtos.PaymentDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.dtos.RentalDto;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.RentalRequest.CalculateTotalPriceRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequest.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequest.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.adapters.abstracts.FakePosSystemService;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PaymentDao;

import com.btkAkademi.rentACar.entities.concretes.Card;
import com.btkAkademi.rentACar.entities.concretes.Payment;

@Service
public class PaymentManager implements PaymentService {

	private PaymentDao paymentDao;
	private ModelMapperService modelMapperService;
	private AdditionalServiceService additionalServiceService;
	private RentalService rentalService;
	private CarService carService;
	private FakePosSystemService fakePosSystemService;
	private PromoCodeService promoCodeService;
	private AdditionalServiceItemService additionalServiceItemService;

	@Autowired
	public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService, RentalService rentalService,
			AdditionalServiceService additionalServiceService, CarService carService,
			FakePosSystemService fakePosSystemService, PromoCodeService promoCodeService,
			AdditionalServiceItemService additionalServiceItemService

	) {
		super();
		this.paymentDao = paymentDao;
		this.modelMapperService = modelMapperService;
		this.additionalServiceService = additionalServiceService;
		this.rentalService = rentalService;
		this.carService = carService;
		this.fakePosSystemService = fakePosSystemService;
		this.promoCodeService = promoCodeService;
		this.additionalServiceItemService = additionalServiceItemService;

	}

	@Override
	public DataResult<List<PaymentListDto>> getAll() {
		List<Payment> paymentList = this.paymentDao.findAll();
		List<PaymentListDto> response = paymentList.stream()
				.map(payment -> modelMapperService.forDto().map(payment, PaymentListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<PaymentListDto>>(response, Messages.paymentListed);

	}

	@Override
	public DataResult<PaymentDto> getById(int id) {

		Payment payment = this.paymentDao.getById(id);
		PaymentDto response = this.modelMapperService.forDto().map(payment, PaymentDto.class);

		return new SuccessDataResult<PaymentDto>(response, Messages.paymentListed);
	}

	@Override
	public Result add(CreatePaymentRequest createPaymentRequest) {

	
		Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);

		int rentalId = createPaymentRequest.getRentalId();

		RentalDto rental = rentalService.getById(rentalId).getData();

		// calculates total amount
		double totalPrice = totalPriceCalculator(rental, createPaymentRequest.getReturnDate());

		payment.setMoneyPaid(totalPrice);
		payment.setId(0);
		payment.setPaymentDate(LocalDate.now());
		this.paymentDao.save(payment);

		return new SuccessResult(Messages.paymentAdded);
	}

	@Override
	public Result update(UpdatePaymentRequest updatePaymentRequest) {
		Result result = BusinessRules.run(checkIfPaymentIdExist(updatePaymentRequest.getId()));

		if (result != null) {

			return result;
		}

		Payment payment = this.modelMapperService.forRequest().map(updatePaymentRequest, Payment.class);

		this.paymentDao.save(payment);
		return new SuccessResult(Messages.paymentUpdated);
	}

	@Override
	public Result delete(int id) {
		Result result = BusinessRules.run(checkIfPaymentIdExist(id));

		if (result != null) {

			return result;
		}

		this.paymentDao.deleteById(id);

		return new SuccessResult(Messages.paymentDeleted);
	}

	@Override
	public DataResult<List<PaymentListDto>> getAllByRentalId(int id) {

		List<Payment> paymentList = this.paymentDao.getAllByRentalId(id);
		List<PaymentListDto> response = paymentList.stream()
				.map(payment -> modelMapperService.forDto().map(payment, PaymentListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<PaymentListDto>>(response);
	}

	@Override
	public DataResult<Double> calculateTotalPriceForDisplay(CalculateTotalPriceRequest calculateTotalPriceRequest) {

		RentalDto rental = rentalService.getById(calculateTotalPriceRequest.getRentalId()).getData();
		System.out.println(rental.getRentDate());
		Double price = this.totalPriceCalculator(rental, calculateTotalPriceRequest.getReturnDate());
		System.out.println(price);
		return new SuccessDataResult<Double>(price);
	}

	// valid

	private Result checkIfPaymentIdExist(int id) {
		if (!paymentDao.existsById(id)) {
			return new ErrorResult();
		}

		return new SuccessResult();
	}

	private Result checkIfCreditCardIsValid(Card card) {
		var result = this.fakePosSystemService.checkIfCreditCardIsValid(card);

		return result;
	}

	// To calculate total price
	private double totalPriceCalculator(RentalDto rental, LocalDate returnDate) {

		double totalPrice = 0.0;
		System.out.println(rental.getRentDate() + " " + returnDate);
		// finds usage day
		long days = ChronoUnit.DAYS.between(rental.getRentDate(), returnDate);

		// if return date and rent date are equal than we charge one day
		if (days == 0)
			days = 1;
		// calculates total usage price by day
		totalPrice += days * carService.getCarById(rental.getCarId()).getData().getDailyPrice();

		// discount
		// discount
		if (rental.getPromoCodeId() != 0) {
			double discountRate = 0;
			discountRate = promoCodeService.getById(rental.getPromoCodeId()).getData().getDiscountRate();
			totalPrice = totalPrice - (totalPrice * discountRate);
		}
		// calculates total additional service price
		List<AdditionalServiceListDto> services = additionalServiceService.getAllByRentalId(rental.getId()).getData();
		for (AdditionalServiceListDto additionalService : services) {
			double additionalServiceItemPrice = additionalServiceItemService
					.getById(additionalService.getAdditionalServiceItemId()).getData().getPrice();
			totalPrice += additionalServiceItemPrice;
		}

		return totalPrice;
	}

	/*
	 * private Payment paymentCalculate(Payment payment, int rentalId) {
	 * 
	 * RentalDto rental = rentalService.getById(rentalId).getData();
	 * 
	 * // date difference long days = ChronoUnit.DAYS.between(rental.getRentDate(),
	 * rental.getReturnDate());
	 * 
	 * 
	 * CarDto carDto = carService.getCarById(rental.getCarId()).getData();
	 * List<AdditionalServiceListDto> additionalService =
	 * additionalServiceService.getAllByRentalId(rentalId).getData();
	 * 
	 * double serviceTotalPrice = 0;
	 * 
	 * double totalPrice = 0;
	 * 
	 * for (AdditionalServiceListDto additional : additionalService) {
	 * 
	 * double additionalServiceItemPrice =
	 * additionalServiceItemService.getById(additional.getAdditionalServiceItemId())
	 * .getData().getPrice();
	 * 
	 * serviceTotalPrice += additionalServiceItemPrice;
	 * 
	 * }
	 * 
	 * totalPrice = carDto.getDailyPrice();
	 * 
	 * // discount if (rental.getPromoCodeId() != 0) { double discountRate = 0;
	 * discountRate =
	 * promoCodeService.getById(rental.getPromoCodeId()).getData().getDiscountRate()
	 * ; totalPrice = totalPrice - (totalPrice * discountRate); }
	 * 
	 * // calculate Double total = (totalPrice * days) + serviceTotalPrice;
	 * 
	 * if (!rental.getReturnDate().equals(LocalDate.now())) {
	 * payment.setMoneyPaid(total); }
	 * 
	 * return payment;
	 * 
	 * }
	 */

}

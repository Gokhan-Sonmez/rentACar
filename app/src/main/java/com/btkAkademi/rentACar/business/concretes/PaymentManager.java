package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CardService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarDto;
import com.btkAkademi.rentACar.business.dtos.PaymentDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.dtos.RentalDto;
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
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
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


	@Autowired
	public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService, RentalService rentalService,
			AdditionalServiceService additionalServiceService, CarService carService,
			FakePosSystemService fakePosSystemService

	) {
		super();
		this.paymentDao = paymentDao;
		this.modelMapperService = modelMapperService;
		this.additionalServiceService = additionalServiceService;
		this.rentalService = rentalService;
		this.carService = carService;
		this.fakePosSystemService = fakePosSystemService;
	

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

		Result result = BusinessRules.run(checkIfCreditCardIsValid(createPaymentRequest.getCard()));

		if (result != null) {

			return result;
		}

		Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);

		int rentalId = createPaymentRequest.getRentalId();

		payment = paymentCalculate(payment, rentalId);
		payment.setId(0);
		this.paymentDao.save(payment);

		return new SuccessResult(Messages.paymentAdded);
	}

	private Payment paymentCalculate(Payment payment, int rentalId) {

		RentalDto rental = rentalService.getById(rentalId).getData();

		// date difference
		long days = ChronoUnit.DAYS.between(rental.getRentDate(), rental.getReturnDate());

		// dependency
		CarDto carDto = carService.getCarById(rental.getCarId()).getData();
		List<AdditionalService> additionalService = additionalServiceService.getAllRentalId(rentalId).getData();

		double serviceTotalPrice = 0;

		for (AdditionalService additional : additionalService) {

			serviceTotalPrice += additional.getPrice();

		}

		// calculate
		Double total = (carDto.getDailyPrice() * days) + serviceTotalPrice;

		if (!rental.getReturnDate().equals(LocalDate.now())) {
			payment.setMoneyPaid(total);
		}

		return payment;

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

}

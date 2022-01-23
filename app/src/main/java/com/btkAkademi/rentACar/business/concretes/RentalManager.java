package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.abstracts.CorparateCustomerService;
import com.btkAkademi.rentACar.business.abstracts.CustomerService;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.RentalDto;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.RentalRequest.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.RentalRequest.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.adapters.abstracts.FindeksCheckService;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.RentalDao;
import com.btkAkademi.rentACar.entities.concretes.Rental;

@Service

public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CustomerService customerService;
	private CarMaintenanceService carMaintananceService;
	private CityService cityService;
	private FindeksCheckService findeksCheckService;
	private IndividualCustomerService individualCustomerService;
	private CorparateCustomerService corparateCustomerService;
	private CarService carService;

	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CustomerService customerService,
			CarMaintenanceService carMaintananceService, CityService cityService,
			FindeksCheckService findeksCheckService, IndividualCustomerService individualCustomerService,
			CorparateCustomerService corparateCustomerService, CarService carService) {

		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.customerService = customerService;
		this.carMaintananceService = carMaintananceService;
		this.cityService = cityService;
		this.findeksCheckService = findeksCheckService;
		this.individualCustomerService = individualCustomerService;
		this.corparateCustomerService = corparateCustomerService;
		this.carService = carService;
	}

	// IndividiualCustomer

	@Override
	public Result addForIndividiualCustomer(CreateRentalRequest createRentalRequest) {

		Result result = BusinessRules.run(checkIfCustomerIdExists(createRentalRequest.getCustomerId()),
				checkIfCarInMaintenance(createRentalRequest.getCarId()),
				checkIfCityExist(createRentalRequest.getPickUpCityId()),
				checkIfCityExist(createRentalRequest.getReturnCityId()),
				checkIfIsCarAlreadyRented(createRentalRequest.getCarId()),
				checkIfCustomerAgeIsEnough(createRentalRequest.getCustomerId(), createRentalRequest.getCarId()),
				checkIfIndividualCustomerHasEnoughCreditScore(
						individualCustomerService.getById(createRentalRequest.getCustomerId()).getData().getNationalNumber(),
						carService.getCarById(createRentalRequest.getCarId()).getData().getFindexScore()));

		if (result != null) {

			return result;
		}

		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		// checkAvailableCarsByCarClass
		var car = this.carService.getCarById(createRentalRequest.getCarId()).getData();
		if (!checkIfCarInMaintenance(car.getId()).isSuccess() || !checkIfIsCarAlreadyRented(car.getId()).isSuccess()) {

			var newCarToRent = this.carService.getAvailableCarsByCarClassId(car.getCarClassId());
			if (!newCarToRent.isSuccess()) {
				return new ErrorResult();
			}
			rental.setCar(newCarToRent.getData());
		}
		//
		this.rentalDao.save(rental);

		return new SuccessResult(Messages.rentalAdded);
	}

	@Override
	public Result updateForIndividiualCustomer(UpdateRentalRequest updateRentalRequest) {

		Result result = BusinessRules.run(checkIfRentalIdExist(updateRentalRequest.getId()),
				checkIfCustomerIdExists(updateRentalRequest.getCustomerId()),
				checkIfCityExist(updateRentalRequest.getPickUpCityId()),
				checkIfCityExist(updateRentalRequest.getReturnCityId()),
				checkIfIsCarAlreadyRented(updateRentalRequest.getCarId()),
				checkIfDatesCorrect(updateRentalRequest.getRentDate(), updateRentalRequest.getReturnDate()),
				checkIfKilometerCorrect(updateRentalRequest.getRentedKilometer(),
						(updateRentalRequest.getReturnKilometer())));

		if (result != null) {

			return result;
		}
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		this.rentalDao.save(rental);

		return new SuccessResult(Messages.rentalUpdated);
	}

	@Override
	public Result deleteForIndividiualCustomer(int id) {

		Result result = BusinessRules.run(checkIfRentalIdExist(id));
		if (result != null) {

			return result;
		}
		this.rentalDao.deleteById(id);

		return new SuccessResult();
	}

	// CorparateCustomer

	@Override
	public Result addForCorparateCustomer(CreateRentalRequest createRentalRequest) {

		Result result = BusinessRules.run(checkIfCustomerIdExists(createRentalRequest.getCustomerId()),
				checkIfCarInMaintenance(createRentalRequest.getCarId()),
				checkIfCityExist(createRentalRequest.getPickUpCityId()),
				checkIfCityExist(createRentalRequest.getReturnCityId()),
				checkIfIsCarAlreadyRented(createRentalRequest.getCarId()),
				checkIfCorporateCustomerHasEnoughCreditScore(
						corparateCustomerService.getById(createRentalRequest.getCustomerId()).getData().getTaxtNumber(),
						carService.getCarById(createRentalRequest.getCarId()).getData().getFindexScore())

		);

		if (result != null) {

			return result;
		}

		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		this.rentalDao.save(rental);

		return new SuccessResult(Messages.rentalAdded);
	}

	@Override
	public Result updateForCorparateCustomer(UpdateRentalRequest updateRentalRequest) {

		Result result = BusinessRules.run(checkIfRentalIdExist(updateRentalRequest.getId()),
				checkIfCustomerIdExists(updateRentalRequest.getCustomerId()),
				checkIfCityExist(updateRentalRequest.getPickUpCityId()),
				checkIfCityExist(updateRentalRequest.getReturnCityId()),
				checkIfIsCarAlreadyRented(updateRentalRequest.getCarId()),
				checkIfDatesCorrect(updateRentalRequest.getRentDate(), updateRentalRequest.getReturnDate()),
				checkIfKilometerCorrect(updateRentalRequest.getRentedKilometer(),
						(updateRentalRequest.getReturnKilometer())

				));

		if (result != null) {

			return result;
		}
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		this.rentalDao.save(rental);

		return new SuccessResult(Messages.rentalUpdated);
	}

	@Override
	public Result deleteForCorparateCustomer(int id) {

		Result result = BusinessRules.run(checkIfRentalIdExist(id));
		if (result != null) {

			return result;
		}
		this.rentalDao.deleteById(id);

		return new SuccessResult();
	}

	@Override
	public DataResult<List<RentalListDto>> getAll() {

		List<Rental> rentalList = this.rentalDao.findAll();

		List<RentalListDto> response = rentalList.stream()
				.map(rental -> modelMapperService.forDto().map(rental, RentalListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<RentalListDto>>(response, Messages.rentalListed);
	}

	@Override
	public boolean isCarRented(int carId) {
		if (rentalDao.findByCarIdAndReturnDateIsNull(carId) != null) {
			return true;
		} else
			return false;
	}

	@Override
	public DataResult<RentalDto> getById(int id) {
		Rental rental = this.rentalDao.findById(id).get();
		RentalDto response = modelMapperService.forDto().map(rental, RentalDto.class);

		return new SuccessDataResult<RentalDto>(response, Messages.rentalListed);
	}

	@Override
	public DataResult<Rental> getByCarId(int id) {
		Rental rental = this.rentalDao.findByCarId(id);
		return new SuccessDataResult<Rental>(rental, Messages.carListed);
	}

	// validation

	private Result checkIfCustomerIdExists(int id) {

		var result = this.customerService.getByCustomerId(id);

		if (!result.isSuccess()) {
			return new ErrorResult(Messages.customerIdNotExists);

		}

		return new SuccessResult();
	}

	// boolean isAfter(LocalDate other) – Checks if given date is after the other
	// date.
	private Result checkIfDatesCorrect(LocalDate rentDate, LocalDate returnDate) {
		if (!returnDate.isAfter(rentDate)) {
			return new ErrorResult(Messages.notCorrectReturnDate);

		}

		return new SuccessResult();
	}

	private Result checkIfKilometerCorrect(int rentedKilometer, int returnedKilometer) {
		if (rentedKilometer > returnedKilometer) {
			return new ErrorResult(Messages.notCorrectKilometer);
		}

		return new SuccessResult();
	}

	private Result checkIfCarInMaintenance(int carId) {
		if (carMaintananceService.isCarInMaintenance(carId)) {
			return new ErrorResult(Messages.carInMaintenance);
		}
		return new SuccessResult();
	}

	private Result checkIfCityExist(int cityId) {
		if (!cityService.getCityById(cityId).isSuccess()) {
			return new ErrorResult(Messages.cityExist);
		}
		return new SuccessResult();
	}

	private Result checkIfIsCarAlreadyRented(int carId) {
		if (isCarRented(carId)) {
			return new ErrorResult(Messages.carRented);
		}
		return new SuccessResult();
	}

	private Result checkIfRentalIdExist(int id) {
		if (!rentalDao.existsById(id)) {
			return new ErrorResult();
		}

		return new SuccessResult(Messages.rentalIdExist);
	}

	private Result checkIfIndividualCustomerHasEnoughCreditScore(String nationalityId, int minCreditScore) {
		System.out.println("min :" + minCreditScore);
		if (findeksCheckService.checkFindeksForIndividualCustomer(nationalityId).getData() >= minCreditScore) {
			return new SuccessResult();
		} else
			return new ErrorResult();

	}

	private Result checkIfCorporateCustomerHasEnoughCreditScore(String taxNumber, int minCreditScore) {
		System.out.println("min :" + minCreditScore);
		if (findeksCheckService.checkFindeksForIndividualCustomer(taxNumber).getData() >= minCreditScore) {
			return new SuccessResult();
		} else
			return new ErrorResult();

	}

	private Result checkIfCustomerAgeIsEnough(int customerId, int carId) {

		int age = Period
				.between(individualCustomerService.getById(customerId).getData().getBirthDate(), LocalDate.now())
				.getYears();
		int minAge = this.carService.getCarById(carId).getData().getMinAge();
		if (age < minAge) {
			return new ErrorResult(Messages.ageIsNotEnough);
		}
		return new SuccessResult();

	}

	/*
	 * private Result checkIfCarClassExist(String carClass) {
	 * 
	 * DataResult<CarDto> car = this.carService.getCarByCarClass(carClass);
	 * 
	 * if(car!= null) {
	 * 
	 * return new ErrorResult(Messages.carClassExists); } return new
	 * SuccessResult(); }
	 */

}

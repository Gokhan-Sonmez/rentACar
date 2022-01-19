package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarDto;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequest.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequest.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDao;
import com.btkAkademi.rentACar.entities.concretes.Car;

@Service
public class CarManager implements CarService {

	private CarDao carDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {

		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<CarListDto>> getAll() {
		List<Car> carList = this.carDao.findAll();
		List<CarListDto> response = carList.stream().map(car -> modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<CarListDto>>(response);
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {

		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);

		return new SuccessResult(Messages.carAdded);
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {

		Result result = BusinessRules.run(checkIfCarIdExists(updateCarRequest.getId()));

		if (result != null) {

			return result;
		}

		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);

		this.carDao.save(car);
		return new SuccessResult(Messages.carUpdated);
	}

	@Override
	public DataResult<CarDto> getCarById(int carId) {

		Car car = this.carDao.getById(carId);
		CarDto response = this.modelMapperService.forDto().map(car, CarDto.class);

		return new SuccessDataResult<CarDto>(response);
	}

	@Override
	public DataResult<List<CarListDto>> getAll(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		List<Car> carList = this.carDao.findAll(pageable).getContent();
		List<CarListDto> response = carList.stream().map(car -> modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<CarListDto>>(response, Messages.carListed);
	}

	@Override
	public Result delete(int id) {
		if (carDao.existsById(id)) {
			carDao.deleteById(id);
			return new SuccessResult(Messages.carDeleted);
		}

		return new ErrorResult(Messages.carIdNotExists);
	}

	@Override
	public DataResult<List<CarListDto>> getCarByColorId(int colorId) {

		List<Car> cars = this.carDao.getByColor_Id(colorId);

		List<CarListDto> response = cars.stream().map(car -> modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<CarListDto>>(response, Messages.carListed);
	}

	@Override
	public  DataResult<List<CarListDto>> getCarByBrandId(int brandId) {
		List<Car> cars = this.carDao.getByBrand_Id(brandId);
		List<CarListDto> response = cars.stream().map(car -> modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<CarListDto>>(response, Messages.carListed);
	}



	@Override
	public DataResult<Car> getAvailableCarsByCarClassId(int carClassId) {
		var newCar = this.carDao.getAvailableCarByCarClassId(carClassId);
		if (newCar.isEmpty()) {
			return new ErrorDataResult<Car>();
		}

		var car = this.carDao.getById(newCar.get(carClassId));
		return new SuccessDataResult<Car>(car);
	}

	private Result checkIfCarIdExists(int id) {
		if (this.carDao.existsById(id)) {

			return new ErrorResult(Messages.carIdNotExists);
		}
		return new SuccessResult();
	}



	/*
	 * private Result checkIfCarClassExist(String carClass) {
	 * 
	 * Car car = this.carDao.getByCarClass(carClass);
	 * 
	 * if(car!= null) {
	 * 
	 * return new ErrorResult(Messages.carClassExists); } return new
	 * SuccessResult(); }
	 */

}

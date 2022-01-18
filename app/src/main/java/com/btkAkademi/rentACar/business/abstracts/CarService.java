package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CarDto;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequest.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequest.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Car;


public interface CarService {

	
	DataResult<List<CarListDto>> getAll();
	DataResult<List<CarListDto>> getAll(int pageNo,int pageSize);
	
	DataResult<CarDto> getCarById(int carId);
	DataResult<CarDto> getCarByCarClass(String carClass);
	

	DataResult<Car> getAvailableCarsByCarClassId(int carClassId);
	
	
	
	Result add(CreateCarRequest createCarRequest);
	Result update(UpdateCarRequest updateCarRequest);
	Result delete (int id);
}

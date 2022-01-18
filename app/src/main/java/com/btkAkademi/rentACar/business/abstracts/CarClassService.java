package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CarClassDto;
import com.btkAkademi.rentACar.business.dtos.CarClassListDto;
import com.btkAkademi.rentACar.business.requests.carClass.CreateCarClassRequest;
import com.btkAkademi.rentACar.business.requests.carClass.UpdateCarClassRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;


public interface CarClassService {
	
	DataResult<List<CarClassListDto>> getAll();
	DataResult<CarClassDto> getById(int id);
	
	Result add(CreateCarClassRequest createCarClassRequest);
	Result update(UpdateCarClassRequest updateCarClassRequest);
	Result delete(int id);

}

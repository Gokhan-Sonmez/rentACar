package com.btkAkademi.rentACar.business.concretes;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarDamageService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.requests.carDamageRequest.CreateCarDamageRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDamageDao;
import com.btkAkademi.rentACar.entities.concretes.CarDamage;

@Service
public class CarDamageManager implements CarDamageService {

	private CarDamageDao carDamageDao;
	private ModelMapperService modelMapperService;
	
	public CarDamageManager(CarDamageDao carDamageDao,ModelMapperService modelMapperService) {
		super();
		this.carDamageDao = carDamageDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCarDamageRequest createCarDamageRequest) {
		
		CarDamage carDamage = this.modelMapperService.forRequest().map(createCarDamageRequest,CarDamage.class);
		this.carDamageDao.save(carDamage);
		
		return new SuccessResult(Messages.carDamageAdded);
	}

}

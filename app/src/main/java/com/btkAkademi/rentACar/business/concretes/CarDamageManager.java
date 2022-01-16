package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarDamageService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarDamageDto;
import com.btkAkademi.rentACar.business.dtos.CarDamageListDto;
import com.btkAkademi.rentACar.business.requests.carDamageRequest.CreateCarDamageRequest;
import com.btkAkademi.rentACar.business.requests.carDamageRequest.UpdateCarDamageRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDamageDao;
import com.btkAkademi.rentACar.entities.concretes.CarDamage;

@Service
public class CarDamageManager implements CarDamageService {

	private CarDamageDao carDamageDao;
	private ModelMapperService modelMapperService;

	public CarDamageManager(CarDamageDao carDamageDao, ModelMapperService modelMapperService) {
		super();
		this.carDamageDao = carDamageDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCarDamageRequest createCarDamageRequest) {

		CarDamage carDamage = this.modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);
		this.carDamageDao.save(carDamage);

		return new SuccessResult(Messages.carDamageAdded);
	}
	
	@Override
	public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
		Result result = BusinessRules.run(checkCarDamageIdExists(updateCarDamageRequest.getId()));
		
		if(result != null)
		{
			
			return result;
		}
		
		CarDamage carDamage = this.modelMapperService.forRequest().map(updateCarDamageRequest, CarDamage.class);
		
		this.carDamageDao.save(carDamage);

		return new SuccessResult(Messages.carDamageUpdated);
	}

	@Override
	public Result delete(int id) {
		
		Result result = BusinessRules.run(checkCarDamageIdExists(id));
		if(result != null)
		{
			return result;
		}
		
		this.carDamageDao.deleteById(id);
		return new SuccessResult(Messages.carDamageDeleted);
	}

	@Override
	public DataResult<List<CarDamageListDto>> getAll() {

		List<CarDamage> carDamagesList = this.carDamageDao.findAll();
		List<CarDamageListDto> response = carDamagesList.stream()
				.map(carDamages -> modelMapperService.forDto().map(carDamages, CarDamageListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarDamageListDto>>(response,Messages.carDamagesListed);
	}

	@Override
	public DataResult<CarDamageDto> getById(int id) {
		
		CarDamage carDamage = this.carDamageDao.getById(id);
		CarDamageDto response = this.modelMapperService.forDto().map(carDamage, CarDamageDto.class);
		return new SuccessDataResult<CarDamageDto>(response,Messages.carDamagesListed);
	}
	
	private Result checkCarDamageIdExists(int id)
	{
		
		if(!carDamageDao.existsById(id))
		{
			return new ErrorResult(Messages.carDamageIdNotExists);
		}
		
		return new SuccessResult();
	}

	

}

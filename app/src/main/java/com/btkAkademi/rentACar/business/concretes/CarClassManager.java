package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarClassService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarClassDto;
import com.btkAkademi.rentACar.business.dtos.CarClassListDto;
import com.btkAkademi.rentACar.business.requests.carClass.CreateCarClassRequest;
import com.btkAkademi.rentACar.business.requests.carClass.UpdateCarClassRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarClassDao;
import com.btkAkademi.rentACar.entities.concretes.CarClass;
@Service
public class CarClassManager implements CarClassService {

	private CarClassDao carClassDao;
	private ModelMapperService modelMapperService;
	
	public CarClassManager(CarClassDao carClassDao,ModelMapperService modelMapperService) {
		super();
		this.carClassDao = carClassDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<CarClassListDto>> getAll() {
		
		List<CarClass> carClassList = this.carClassDao.findAll();
		List<CarClassListDto> response = carClassList.stream().map(carClass -> this.modelMapperService.forDto().map(carClassList, CarClassListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarClassListDto>>(response);
	}

	@Override
	public DataResult<CarClassDto> getById(int id) {
		
		CarClass carClass = this.carClassDao.getById(id);
		CarClassDto response = this.modelMapperService.forDto().map(carClass, CarClassDto.class);
		
		return new SuccessDataResult<CarClassDto>(response);
	}

	@Override
	public Result add(CreateCarClassRequest createCarClassRequest) {
		CarClass carClass = this.modelMapperService.forRequest().map(createCarClassRequest, CarClass.class);
		this.carClassDao.save(carClass);
		
		return new SuccessResult(Messages.carClassAdded);
	}

	@Override
	public Result update(UpdateCarClassRequest updateCarClassRequest) {
		
		Result result = BusinessRules.run(checkIfCarClassIdExist(updateCarClassRequest.getId()));
		if(result != null)
		{
			return result;
		}
		CarClass carClass = this.modelMapperService.forRequest().map(updateCarClassRequest, CarClass.class);
		this.carClassDao.save(carClass);
		
		return new SuccessResult(Messages.carClassUpdated);
	}

	@Override
	public Result delete(int id) {
		Result result = BusinessRules.run(checkIfCarClassIdExist(id));
		if(result != null)
		{
			return result;
		}
	
		this.carClassDao.deleteById(id);
		
		return new SuccessResult(Messages.carClassDeleted);
	}
	
	// valid
	
	Result checkIfCarClassIdExist(int id)
	{
		if(!carClassDao.existsById(id))
		{
			return new ErrorResult();
			
		}
		
		return new SuccessResult();
	}

}

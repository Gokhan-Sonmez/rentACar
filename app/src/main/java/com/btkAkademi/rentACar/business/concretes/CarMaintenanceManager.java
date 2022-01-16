package com.btkAkademi.rentACar.business.concretes;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceDto;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequest.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequest.UpdateCarMaintenanceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarMaintenanceDao;

import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService{

	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
	private CarService carService;

	@Autowired
	public CarMaintenanceManager(
			CarMaintenanceDao carMaintenanceDao,
			ModelMapperService modelMapperService,
			CarService carService,
			@Lazy RentalService rentalService
			) {
		super();
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService =rentalService;
		this.carService = carService;
		
	}

	@Override
	public DataResult<List<CarMaintenanceListDto>> getAll() {
	
		List<CarMaintenance> carMaintenanceList = this.carMaintenanceDao.findAll();
		List<CarMaintenanceListDto> response = carMaintenanceList.stream()
				.map(carMaintanance->modelMapperService.forDto()
				.map(carMaintanance, CarMaintenanceListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarMaintenanceListDto>>(response, Messages.carMaintenanceListed);
	}

	
	@Override
	public DataResult<CarMaintenanceDto> getById(int id) {
		
		CarMaintenance carMaintenance = this.carMaintenanceDao.getById(id);
		CarMaintenanceDto response = this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceDto.class);
		
		return new SuccessDataResult<CarMaintenanceDto>(response, Messages.carMaintenanceListed);
	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		
		Result result = BusinessRules.run(				
				checkIfCarIsExists(createCarMaintenanceRequest.getCarId()),
				checkIfCarIsRented(createCarMaintenanceRequest.getCarId()),
				checkIfCarIsAlreadyInMaintanance(createCarMaintenanceRequest.getCarId())
				)	;		
		if(result!=null) {			
			return result;
		}
		
		
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest,CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult(Messages.carMaintenanceAdded);
	}
	
	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		
		Result result = BusinessRules.run(	
				checkIfCarMaintenanceIdExists(updateCarMaintenanceRequest.getId()),
				checkIfCarIsExists(updateCarMaintenanceRequest.getCarId()),
				checkIfCarIsRented(updateCarMaintenanceRequest.getCarId()),
				checkIfCarIsAlreadyInMaintanance(updateCarMaintenanceRequest.getCarId())
				)	;		
		if(result!=null) {			
			return result;
		}
		
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest,CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult(Messages.carMaintenanceUpdated);
	}

	@Override
	public Result delete(int id) {
		
		Result result = BusinessRules.run(	
				checkIfCarMaintenanceIdExists(id)
				)	;		
		if(result!=null) {			
			return result;
		}
		this.carMaintenanceDao.deleteById(id);
		
		return new SuccessResult(Messages.carMaintenanceDeleted);
	}
	
	@Override
	public boolean isCarInMaintenance(int carId) {
		if(carMaintenanceDao.findByCarIdAndMaintenanceEndIsNull(carId)!=null) {
			return true;
		}
		else return false;
	}

	

	// valid
	
	private Result checkIfCarIsExists(int carId) {
		if(!carService.getCarById(carId).isSuccess()) {
			return new ErrorResult(Messages.carIdNotExists);
		}
		else return new SuccessResult();
	}
	
	
	private Result checkIfCarIsRented(int id)
	{
		if(rentalService.isCarRented(id)) {
			
			return new ErrorResult(Messages.carRented);
		}
		else return new SuccessResult();
	}

	private Result checkIfCarMaintenanceIdExists(int id)
	{
		
		if(carMaintenanceDao.existsById(id))
		{
			return new ErrorResult();
		}
		
		return new SuccessResult();
			
	}
	
	
	private Result checkIfCarIsAlreadyInMaintanance(int carId) {
		if(isCarInMaintenance(carId)) {
			return new ErrorResult(Messages.carInMaintenance);
		}
		else return new SuccessResult();
	}

	
	

}

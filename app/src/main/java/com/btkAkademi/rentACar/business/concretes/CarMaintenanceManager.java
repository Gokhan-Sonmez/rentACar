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

import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequest.CreateCarMaintenanceRequest;
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
		
		return new SuccessDataResult<List<CarMaintenanceListDto>>(response);
	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		
		Result result = BusinessRules.run(				
				checkIfCarIsExists(createCarMaintenanceRequest.getCarId()),
				checkIfCarIsRented(createCarMaintenanceRequest.getCarId())
				)	;		
		if(result!=null) {			
			return result;
		}
		
		
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest,CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult(Messages.carMaintenanceAdded);
	}
	
	private Result checkIfCarIsExists(int carId) {
		if(!carService.getByCarId(carId).isSuccess()) {
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

	@Override
	public boolean checkIfCarIsInMaintenance(int carId) {
		if(carMaintenanceDao.findByCarIdAndMaintenanceEndIsNull(carId)!=null) {
			return true;
		}
		else return false;
	}
	
	
	
	

}
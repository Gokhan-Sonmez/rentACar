package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerDto;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequest.CreateIndividualCustomeRequest;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequest.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

	
	private IndividualCustomerDao individualCustomerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao,ModelMapperService modelMapperService) {
		
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapperService = modelMapperService;
	}


	@Override
	public Result add(CreateIndividualCustomeRequest createIndividualCustomeRequest) {
		
		Result result = BusinessRules.run(
				checkIfEmailExists(createIndividualCustomeRequest.getEmail()),
				checkIfMoreThean18(createIndividualCustomeRequest.getBirthDate()));
		
		if(result!=null) {
			
			return result;
		}
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createIndividualCustomeRequest,IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		
		return new SuccessResult(Messages.individualCustomerAdded);
	}
	
	



@Override
public Result update(UpdateIndividualCustomerRequest updateIndividualCustomeRequest) {

	Result result = BusinessRules.run(
			checkIfIndividualCustomerIdExist(updateIndividualCustomeRequest.getId()),
			checkIfEmailExists(updateIndividualCustomeRequest.getEmail()),
			checkIfMoreThean18(updateIndividualCustomeRequest.getBirthDate()));
	
	if(result!=null) {
		
		return result;
	}
	
	IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(updateIndividualCustomeRequest,IndividualCustomer.class);
	this.individualCustomerDao.save(individualCustomer);
	
	return new SuccessResult(Messages.individualCustomerUpdated);
}


@Override
public Result delete(int id) {
	Result result = BusinessRules.run(
			checkIfIndividualCustomerIdExist(id));
	
	if(result!=null) {
		
		return result;
	}
	this.individualCustomerDao.deleteById(id);
	
	return new SuccessResult(Messages.individualCustomerDeleted);
}


@Override
public DataResult<List<IndividualCustomerListDto>> getAll() {
	
	List<IndividualCustomer> individualCustomerList = this.individualCustomerDao.findAll();
	List<IndividualCustomerListDto> response = individualCustomerList.stream()
			.map(individualCustomer -> this.modelMapperService.forDto()
					.map(individualCustomer, IndividualCustomerListDto.class)).collect(Collectors.toList());
	return new SuccessDataResult<List<IndividualCustomerListDto>>(response,Messages.individualCustomerListed);
}


@Override
public DataResult<IndividualCustomerDto> getById(int id) {
	
	IndividualCustomer individualCustomer = this.individualCustomerDao.getById(id);
	IndividualCustomerDto response = this.modelMapperService.forDto().map(individualCustomer, IndividualCustomerDto.class);
	
	return new SuccessDataResult<IndividualCustomerDto>(response,Messages.individualCustomerListed);
}


private Result checkIfEmailExists(String email)
{
	 
	  
	  if(individualCustomerDao.getByEmail(email)!=null) {
		  return new ErrorResult(Messages.emailExists);
	  }
	  return new SuccessResult();
	  
}

private Result checkIfMoreThean18(LocalDate birthdate)
{
	  
	  
	 var Age= Period.between(birthdate, LocalDate.now()).getYears();
	  
	  
	  if(Age<18) {
		  return new ErrorResult(Messages.birthdateNotEnough);
	  }
	  return new SuccessResult();
	  
}

private Result checkIfIndividualCustomerIdExist(int id)
{
	if(!individualCustomerDao.existsById(id))
	{
		return new ErrorResult();
	}
	
	return new SuccessResult();
}
	

}

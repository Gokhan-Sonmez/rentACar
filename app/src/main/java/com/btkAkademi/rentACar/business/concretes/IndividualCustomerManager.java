package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequest.CreateIndividualCustomeRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;

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
				checkIfEmailExists(createIndividualCustomeRequest.getEmail()),checkIfMoreThean18(createIndividualCustomeRequest.getBirtdate()));
		
		if(result!=null) {
			
			return result;
		}
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createIndividualCustomeRequest,IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		
		return new SuccessResult(Messages.individualCustomerAdded);
	}
	
	
  private Result checkIfEmailExists(String email)
  {
	  var Result = this.individualCustomerDao.getByEmail(email);
	  
	  if(Result!=null) {
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
	
	

}
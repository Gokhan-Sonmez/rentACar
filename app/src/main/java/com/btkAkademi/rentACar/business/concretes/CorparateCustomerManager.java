package com.btkAkademi.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CorparateCustomerService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.requests.corparateCustomerRequest.CreateCorparateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorparateCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.CorpareteCustomer;

@Service
public class CorparateCustomerManager implements CorparateCustomerService{

	private CorparateCustomerDao corparateCustomerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CorparateCustomerManager(CorparateCustomerDao corparateCustomerDao,
			ModelMapperService modelMapperService) {
		
		this.corparateCustomerDao = corparateCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCorparateCustomerRequest createCorparateCustomeRequest) {
		Result result = BusinessRules.run(
				checkIfCompanyNameExists(createCorparateCustomeRequest.getCompanyName()));
		
		if(result!=null) {
			
			return result;
		}
		
		CorpareteCustomer corparateCustomer = this.modelMapperService.forRequest().map(createCorparateCustomeRequest,CorpareteCustomer.class);
		this.corparateCustomerDao.save(corparateCustomer);
		
		return new SuccessResult(Messages.corparateCustomerAdded);
	}

 private Result checkIfCompanyNameExists(String companyName)
 {
	 
	 var Result = corparateCustomerDao.getByCompanyName(companyName);
	  
	  if(Result!=null) {
		  return new ErrorResult(Messages.companyNameExists);
	  }
	 return new SuccessResult();
 }


}

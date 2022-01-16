package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.IndividualCustomerDto;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequest.CreateIndividualCustomeRequest;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequest.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface IndividualCustomerService {

	
	Result add(CreateIndividualCustomeRequest createIndividualCustomeRequest);
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomeRequest);
	Result delete(int id);
	
	DataResult<List<IndividualCustomerListDto>> getAll();
	DataResult<IndividualCustomerDto> getById(int id);
}

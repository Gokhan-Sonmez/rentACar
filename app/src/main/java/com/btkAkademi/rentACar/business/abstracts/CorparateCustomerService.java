package com.btkAkademi.rentACar.business.abstracts;


import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CorparateCustomerDto;
import com.btkAkademi.rentACar.business.dtos.CorparateCustomerListDto;
import com.btkAkademi.rentACar.business.requests.corparateCustomerRequest.CreateCorparateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.corparateCustomerRequest.UpdateCorparateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CorparateCustomerService {

	Result add(CreateCorparateCustomerRequest createCorparateCustomeRequest);
	Result update(UpdateCorparateCustomerRequest updateCorparateCustomeRequest);
	Result delete(int id);
	
	DataResult<List<CorparateCustomerListDto>> getAll();
	DataResult<CorparateCustomerDto> getById(int id);
	
	
}

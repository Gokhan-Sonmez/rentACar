package com.btkAkademi.rentACar.business.abstracts;


import com.btkAkademi.rentACar.business.requests.corparateCustomerRequest.CreateCorparateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CorparateCustomerService {

	Result add(CreateCorparateCustomerRequest createCorparateCustomeRequest);
}
package com.btkAkademi.rentACar.core.utilities.adapters.abstracts;

import com.btkAkademi.rentACar.core.utilities.results.DataResult;


public interface FindeksCheckService {
	
	DataResult<Integer> checkFindeksForIndividualCustomer(String nationalNumber);
	DataResult<Integer> checkFindeksForCorparateCustomer(String taxtNumber);
}

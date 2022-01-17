package com.btkAkademi.rentACar.core.utilities.adapters.concretes;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.core.utilities.adapters.abstracts.FindeksCheckService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;

import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;

import com.btkAkademi.rentACar.core.utilities.services.Findeks;
@Service
public class FindeksCheckAdapterManager implements FindeksCheckService {

	@Override
	public DataResult<Integer> checkFindeksForIndividualCustomer(String nationalNumber) {
		Findeks findeks = new Findeks();
		
		var result = findeks.getScoreOfIndividualCustomer(nationalNumber);
		return new SuccessDataResult<Integer>(result);
	}

	@Override
	public DataResult<Integer> checkFindeksForCorparateCustomer(String taxtNumber) {
		
		Findeks findeks = new Findeks();
		var result = findeks.getScoreOfCorporateCustomer(taxtNumber);
		return new SuccessDataResult<Integer>(result);
	}

}

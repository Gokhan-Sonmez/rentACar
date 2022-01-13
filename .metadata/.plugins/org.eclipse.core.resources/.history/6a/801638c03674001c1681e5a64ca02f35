package com.btkAkademi.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CustomerService;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CustomerDao;

@Service
public class CustomerManager implements CustomerService {

	private CustomerDao customerdao;
	@Autowired
	public CustomerManager(CustomerDao customerdao) {
		
		this.customerdao = customerdao;
	}

	@Override
	public Result getByCustomerId(int id) {
		this.customerdao.getById(id);
		 return new SuccessResult();
	}

	
}

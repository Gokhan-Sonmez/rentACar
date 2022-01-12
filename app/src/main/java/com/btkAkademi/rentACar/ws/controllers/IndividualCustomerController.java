package com.btkAkademi.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequest.CreateIndividualCustomeRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/individualcustomer")
public class IndividualCustomerController {

	private IndividualCustomerService individualCustomerService;
	
@PostMapping("add")
	
	public Result add(@RequestBody @Valid CreateIndividualCustomeRequest individualCustomerRequest) {
		
		return this.individualCustomerService.add(individualCustomerRequest);
	}
}
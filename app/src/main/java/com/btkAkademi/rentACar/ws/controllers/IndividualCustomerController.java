package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerDto;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequest.CreateIndividualCustomeRequest;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequest.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/individualcustomer")
public class IndividualCustomerController {

	private IndividualCustomerService individualCustomerService;

	public IndividualCustomerController(IndividualCustomerService individualCustomerService) {
		super();
		this.individualCustomerService = individualCustomerService;
	}

	@PostMapping("add")

	public Result add(@RequestBody @Valid CreateIndividualCustomeRequest createIndividualCustomerRequest) {

		return this.individualCustomerService.add(createIndividualCustomerRequest);
	}

	@PostMapping("update")

	public Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {

		return this.individualCustomerService.update(updateIndividualCustomerRequest);
	}

	@PostMapping("delete/{id}")

	public Result delete(@PathVariable @Valid int id) {

		return this.individualCustomerService.delete(id);
	}

	@GetMapping("getall")

	public DataResult<List<IndividualCustomerListDto>> getAll() {

		return this.individualCustomerService.getAll();
	}

	@GetMapping("get-by-id/{id}")

	public DataResult<IndividualCustomerDto> getById(int id) {

		return this.individualCustomerService.getById(id);
	}

}

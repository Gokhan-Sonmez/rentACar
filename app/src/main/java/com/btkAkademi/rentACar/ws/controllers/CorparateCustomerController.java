package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CorparateCustomerService;
import com.btkAkademi.rentACar.business.dtos.CorparateCustomerDto;
import com.btkAkademi.rentACar.business.dtos.CorparateCustomerListDto;
import com.btkAkademi.rentACar.business.requests.corparateCustomerRequest.CreateCorparateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.corparateCustomerRequest.UpdateCorparateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/corparatecustomer")
@CrossOrigin
public class CorparateCustomerController {

	private CorparateCustomerService corparateCustomerService;

	public CorparateCustomerController(CorparateCustomerService corparateCustomerService) {
		
		this.corparateCustomerService = corparateCustomerService;
	}

	@PostMapping("add")

	public Result add(@RequestBody @Valid CreateCorparateCustomerRequest createCorparateCustomerRequest) {

		return this.corparateCustomerService.add(createCorparateCustomerRequest);
	}
	
	@PostMapping("update")

	public Result update(@RequestBody @Valid UpdateCorparateCustomerRequest updateCorparateCustomerRequest) {

		return this.corparateCustomerService.update(updateCorparateCustomerRequest);
	}
	
	@DeleteMapping("delete/{id}")

	public Result delete(@PathVariable @Valid int id) {

		return this.corparateCustomerService.delete(id);
	}
	
	@GetMapping("getall")
	public DataResult<List<CorparateCustomerListDto>> getAll()
	{
		return this.corparateCustomerService.getAll();
	}
	@GetMapping("get-by-id/{id}")
	public DataResult<CorparateCustomerDto> getById(int id)
	{
		return this.corparateCustomerService.getById(id);
	}

}

package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.requests.additionalService.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.additionalService.UpdateAdditionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalservices")
public class AdditionalServiceController {

	private AdditionalServiceService additionalServiceService;

	public AdditionalServiceController(AdditionalServiceService additionalServiceService) {
	
		this.additionalServiceService = additionalServiceService;
	}
	
	
	@GetMapping("getall")
	public DataResult<List<AdditionalServiceListDto>> getAll()
	{
		return this.additionalServiceService.getAll();
	}
	
	@GetMapping("get-by-id/{id}")
	public Result getById(@PathVariable int id) {
		return additionalServiceService.getById(id);
		
	}
	
	
	@GetMapping("get-all-by-rental-id/{id}")
	public Result getAllByRentalId(@PathVariable int id) {
		return additionalServiceService.getAllRentalId(id);
		
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		
		
		return this.additionalServiceService.add(createAdditionalServiceRequest);
		
	}
	
	@PostMapping("updated")
	public Result add(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		
		
		return this.additionalServiceService.update(updateAdditionalServiceRequest);
		
	}
	
	@DeleteMapping("delete/{id}")
	public Result update(@PathVariable int id) {
		return this.additionalServiceService.delete(id);
	}
	
	
}

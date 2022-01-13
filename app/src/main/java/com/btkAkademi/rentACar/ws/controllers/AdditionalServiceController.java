package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.requests.additionalService.CreateAdditionalServiceRequest;
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
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		
		
		return this.additionalServiceService.add(createAdditionalServiceRequest);
		
	}
	
	
}

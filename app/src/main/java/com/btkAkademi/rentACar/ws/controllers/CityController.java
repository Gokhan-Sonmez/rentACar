package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CityService;

import com.btkAkademi.rentACar.business.dtos.CityListDto;

import com.btkAkademi.rentACar.business.requests.cityRequest.CreateCityRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cities")
public class CityController {
	private CityService cityService;

	public CityController(CityService cityService) {
		super();
		this.cityService = cityService;
	}
	@GetMapping("getall")
	public  DataResult<List<CityListDto>> getall(){
		
		return this.cityService.getAll();
	}

	
@PostMapping("add")
	
	public Result add(@RequestBody @Valid CreateCityRequest createCityRequest) {
		
		return this.cityService.add(createCityRequest);
	}
	

}
package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarClassService;
import com.btkAkademi.rentACar.business.dtos.CarClassDto;
import com.btkAkademi.rentACar.business.dtos.CarClassListDto;
import com.btkAkademi.rentACar.business.requests.carClass.CreateCarClassRequest;
import com.btkAkademi.rentACar.business.requests.carClass.UpdateCarClassRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/carClasses")
public class CarClassController {

	private CarClassService carClassService;

	public CarClassController(CarClassService carClassService) {
		super();
		this.carClassService = carClassService;
	}
	
	@GetMapping("getall")
	DataResult<List<CarClassListDto>> getAll()
	{
		
		return this.carClassService.getAll();
	}
	
	@GetMapping("getById/{id}")
	DataResult<CarClassDto> getById(int id)
	{
		return this.getById(id);
	}
	
	Result add(@RequestBody CreateCarClassRequest createCarClassRequest)
	{
		return this.add(createCarClassRequest);
	}
	Result update(@RequestBody UpdateCarClassRequest updateCarClassRequest)
	{
		return this.update(updateCarClassRequest);
	}
	Result delete(int id)
	{
		return this.delete(id);
	}
}

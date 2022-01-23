package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@CrossOrigin
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
	
	@GetMapping("getById/{carClassId}")
	DataResult<CarClassDto> getById(int carClassId)
	{
		return this.carClassService.getById(carClassId);
	}
	
	@PostMapping("add")
	Result add(@RequestBody CreateCarClassRequest createCarClassRequest)
	{
		return this.carClassService.add(createCarClassRequest);
	}
	@PostMapping("update")
	Result update(@RequestBody UpdateCarClassRequest updateCarClassRequest)
	{
		return this.carClassService.update(updateCarClassRequest);
	}
	@PostMapping("delete")
	Result delete(int id)
	{
		return this.delete(id);
	}
}

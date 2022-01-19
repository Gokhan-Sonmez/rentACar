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

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceDto;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequest.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequest.UpdateCarMaintenanceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/carmaintenances")
@CrossOrigin
public class CarMaintenanceController {

	private CarMaintenanceService carMaintenanceService;

	public CarMaintenanceController(CarMaintenanceService carMaintenanceService) {
		super();
		this.carMaintenanceService = carMaintenanceService;
	}

	@GetMapping("getall")
	public DataResult<List<CarMaintenanceListDto>> getall() {
		return this.carMaintenanceService.getAll();
	}

	@GetMapping("get-by-id/{id}")
	public DataResult<CarMaintenanceDto> getById(@PathVariable int id) {
		return this.carMaintenanceService.getById(id);
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		return this.carMaintenanceService.add(createCarMaintenanceRequest);
	}

	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		return this.carMaintenanceService.update(updateCarMaintenanceRequest);
	}

	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable @Valid int id) {
		return this.carMaintenanceService.delete(id);
	}

}

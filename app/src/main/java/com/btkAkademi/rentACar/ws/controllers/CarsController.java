package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.dtos.CarDto;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequest.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequest.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
	private CarService carService;

	public CarsController(CarService carService) {
		super();
		this.carService = carService;
	}

	@GetMapping("getall")
	public DataResult<List<CarListDto>> getall() {

		return this.carService.getAll();
	}

	@GetMapping("get-by-id/{id}")
	public DataResult<CarDto> getById(@PathVariable int id) {

		return this.carService.getAllCarById(id);
	}

	@GetMapping("getallbypage")
	DataResult<List<CarListDto>> getAll(@RequestParam int pageNo, @RequestParam(defaultValue = "10") int pageSize) {

		return this.carService.getAll(pageNo, pageSize);

	}

	@PostMapping("add")

	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {

		return this.carService.add(createCarRequest);
	}

	@PostMapping("update")

	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {

		return this.carService.update(updateCarRequest);
	}

	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable int id) {

		return this.carService.delete(id);
	}
}

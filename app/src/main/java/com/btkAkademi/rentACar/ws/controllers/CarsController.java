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
@CrossOrigin
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

	@GetMapping("getById")
	public DataResult<CarDto> getById(int carId) {

		return this.carService.getCarById(carId);
	}
	
	@GetMapping("getCarByBrandId")
	public DataResult<List<CarListDto>> getCarByBrandId(int brandId) {

		return this.carService.getCarByBrandId(brandId);
	}
	
	@GetMapping("getCarByColorId")
	public DataResult<List<CarListDto>> getCarByColorId(int colorId) {
		return this.carService.getCarByColorId(colorId);
	}
	
	@GetMapping("getCarByCityId")
	public DataResult<List<CarListDto>> getCarByCityId(int cityId) {
		return this.carService.getCarByCityId(cityId);
	}
	
	@GetMapping("getCarBrandIdAndColorId")
	public DataResult<List<CarListDto>> getCarByBrandIdAndColorId(int brandId,int colorId)
	{
		return this.carService.getCarByBrandIdAndColorId(brandId,colorId);
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

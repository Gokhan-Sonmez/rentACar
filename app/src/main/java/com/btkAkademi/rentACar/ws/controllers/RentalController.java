package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.RentalDto;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.RentalRequest.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.RentalRequest.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

	private RentalService rentalService;

	public RentalController(RentalService rentalService) {

		this.rentalService = rentalService;
	}

	@PostMapping("add")

	public Result add(@RequestBody CreateRentalRequest createRentalRequest) {
		return this.rentalService.add(createRentalRequest);
	}

	@PostMapping("update")

	public Result update(@RequestBody UpdateRentalRequest updateRentalRequest) {
		return this.rentalService.update(updateRentalRequest);
	}

	@DeleteMapping("delete/{id}")

	public Result delete(@PathVariable int id) {
		return this.rentalService.delete(id);
	}

	@GetMapping("getall")

	public DataResult<List<RentalListDto>> getall() {

		return this.rentalService.getAll();
	}

	@GetMapping("get-by-id/{id}")

	public DataResult<RentalDto> getById(int id) {

		return this.rentalService.getById(id);
	}

}

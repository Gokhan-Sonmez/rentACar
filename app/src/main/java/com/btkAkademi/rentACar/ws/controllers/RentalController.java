package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class RentalController {

	private RentalService rentalService;

	public RentalController(RentalService rentalService) {

		this.rentalService = rentalService;
	}

	//IndividiualCustomer
	@PostMapping("addforindividiualcustomer")

	public Result addForIndividiualCustomer(@RequestBody CreateRentalRequest createRentalRequest) {
		return this.rentalService.addForIndividiualCustomer(createRentalRequest);
	}

	@PostMapping("updateforindividiualcustomer")

	public Result updateForIndividiualCustomer(@RequestBody UpdateRentalRequest updateRentalRequest) {
		return this.rentalService.updateForIndividiualCustomer(updateRentalRequest);
	}

	@DeleteMapping("deleteforindividiualcustomer/{id}")

	public Result delete(@PathVariable int id) {
		return this.rentalService.deleteForIndividiualCustomer(id);
	}
	
	//CorparateCustomer
	@PostMapping("addforcorparatecustomer")

	public Result addForCorparateCustomer(@RequestBody CreateRentalRequest createRentalRequest) {
		return this.rentalService.addForCorparateCustomer(createRentalRequest);
	}

	@PostMapping("updateforcorparatecustomer")

	public Result updateForCorparateCustomer(@RequestBody UpdateRentalRequest updateRentalRequest) {
		return this.rentalService.updateForCorparateCustomer(updateRentalRequest);
	}

	@DeleteMapping("deleteforcorparatecustomer/{id}")

	public Result deleteForCorparateCustomer(@PathVariable int id) {
		return this.rentalService.deleteForCorparateCustomer(id);
	}

	@GetMapping("getall")

	public DataResult<List<RentalListDto>> getall() {

		return this.rentalService.getAll();
	}

	@GetMapping("getByRentalId/{rentalId}")

	public DataResult<RentalDto> getById(int rentalId) {

		return this.rentalService.getById(rentalId);
	}

}

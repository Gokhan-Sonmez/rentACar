package com.btkAkademi.rentACar.ws.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.requests.RentalRequest.CreateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
	
	private RentalService rentalService;

	public RentalController(RentalService rentalService) {
	
		this.rentalService = rentalService;	
	}
	
	@PostMapping("add")

	public Result add(CreateRentalRequest createRentalRequest)
	{
		return this.rentalService.add(createRentalRequest);
	}

   }



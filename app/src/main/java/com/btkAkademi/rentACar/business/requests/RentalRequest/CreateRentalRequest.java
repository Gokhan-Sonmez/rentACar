package com.btkAkademi.rentACar.business.requests.RentalRequest;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

	private int customerId;
	
	private int carId;

	private LocalDate rentDate;
	

	private LocalDate returnDate;
	

	private int rentedKilometer;
	

	private int returnKilometer;
	
	private int pickUpCityId;
	
	private int returnCityId;
	
	
}

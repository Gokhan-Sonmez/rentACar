package com.btkAkademi.rentACar.business.requests.carDamageRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {

	

	private int carId;
	private String description;
	
}

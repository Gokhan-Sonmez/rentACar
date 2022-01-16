package com.btkAkademi.rentACar.business.requests.carDamageRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarDamageRequest {

	
    private int id;
	private int carId;
	private String description;
	
}
package com.btkAkademi.rentACar.business.requests.cityRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCityRequest {
	

	private String name; 
	
	private int rentalId;

}

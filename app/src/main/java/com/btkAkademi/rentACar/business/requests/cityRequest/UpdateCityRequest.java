package com.btkAkademi.rentACar.business.requests.cityRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCityRequest {

	private int id;
	private String name;

	private int rentalId;
}

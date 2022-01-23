package com.btkAkademi.rentACar.business.requests.additionalServiceItemRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdditionalServiceItemRequest {

	
	private String name;
	private double price;
}

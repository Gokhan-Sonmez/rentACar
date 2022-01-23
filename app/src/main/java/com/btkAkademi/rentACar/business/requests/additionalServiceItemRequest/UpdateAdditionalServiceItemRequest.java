package com.btkAkademi.rentACar.business.requests.additionalServiceItemRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAdditionalServiceItemRequest {

	private int id;
	private String name;
	private double price;
}

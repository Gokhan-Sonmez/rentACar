package com.btkAkademi.rentACar.business.requests.additionalService;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalServiceRequest {

	private int rentalId;
	private int additionalServiceItemId;
}

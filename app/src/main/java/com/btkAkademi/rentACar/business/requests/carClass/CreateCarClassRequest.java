package com.btkAkademi.rentACar.business.requests.carClass;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarClassRequest {


	private int carId;
	private String carClass;
	
	
}

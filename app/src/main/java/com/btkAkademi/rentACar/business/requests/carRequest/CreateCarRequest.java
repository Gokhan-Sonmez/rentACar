package com.btkAkademi.rentACar.business.requests.carRequest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	private int brandId;
	private int colorId;
	private int carClassId;
	private double dailyPrice;
	private int modelYear;
	private int findexScore;
	private int kilometer;
	private String description;
	private int minAge;
	
	
}

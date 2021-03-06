package com.btkAkademi.rentACar.business.requests.carRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
	private int id;
	private int brandId;
	private int colorId;
	private int cityId;
	private String carName;
	private int carClassId;
	private double dailyPrice;
	private int modelYear;
	private int findexScore;
	private int kilometer;
	private String description;
	private int minAge;
	private String imagePath;
	
}
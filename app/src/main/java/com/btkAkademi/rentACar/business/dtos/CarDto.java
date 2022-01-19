package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
	private int id;
	private double dailyPrice;
	private int modelYear;
	private String description;
	private int findexScore;
	private int kilometer;
	private String brandName;
	private String colorName;
	private String carName;
	private int minAge;
	private int cityId;
	private int carClassId;
}

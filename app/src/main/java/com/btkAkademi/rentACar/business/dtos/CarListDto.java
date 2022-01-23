package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListDto {
	private int id;
	private String carClassCarClass;
	private int carClassId;
	private String cityName;
	private String imagePath;
	private double dailyPrice;
	private int modelYear;
	private String description;
	private int findexScore;
	private int kilometer;
	private String brandName;
	private String colorName;
	private String carName;
	private int minAge;
	
}

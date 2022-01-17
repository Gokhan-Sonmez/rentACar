package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListDto {
    private int id;
	private int brandId;
	private int colorId;
	private double dailyPrice;
	private int modelYear;
	private int findexScore;
	private int kilometer;
	private String description;
	private int minAge;
}

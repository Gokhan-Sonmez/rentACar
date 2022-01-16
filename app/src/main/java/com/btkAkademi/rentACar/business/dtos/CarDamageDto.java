package com.btkAkademi.rentACar.business.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDamageDto {

	private int carId;
	private String description;
}

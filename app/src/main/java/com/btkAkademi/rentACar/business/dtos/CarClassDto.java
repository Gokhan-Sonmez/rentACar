package com.btkAkademi.rentACar.business.dtos;

import com.btkAkademi.rentACar.business.requests.carDamageRequest.CreateCarDamageRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarClassDto {

	private int id;
	private int carId;
	private String carClass;
}

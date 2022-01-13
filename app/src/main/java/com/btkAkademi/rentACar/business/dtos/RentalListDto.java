package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalListDto {


	
    private int id;
    
    private int customerId;
    
	private int carId;

	private LocalDate rentDate;
	

	private LocalDate returnDate;
	

	private int rentedKilometer;
	

	private int returnKilometer;
}

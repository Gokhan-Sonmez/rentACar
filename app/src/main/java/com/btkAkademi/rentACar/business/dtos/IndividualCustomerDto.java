package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerDto {

	private int id;
	private String nationalNumber;
	private String email;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;	
}

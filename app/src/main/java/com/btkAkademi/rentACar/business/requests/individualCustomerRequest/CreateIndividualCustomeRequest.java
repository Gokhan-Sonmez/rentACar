package com.btkAkademi.rentACar.business.requests.individualCustomerRequest;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomeRequest {

	private String firstName;
	private String lastName;
    private String email;
	private LocalDate birthDate;
}

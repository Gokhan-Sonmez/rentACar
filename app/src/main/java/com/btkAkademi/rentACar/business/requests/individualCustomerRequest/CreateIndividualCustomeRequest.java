package com.btkAkademi.rentACar.business.requests.individualCustomerRequest;

import java.time.LocalDate;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomeRequest {
	@Size(min=11,max=11)
	private String nationalNumber;
	private String firstName;
	private String lastName;
    private String email;
	private LocalDate birthDate;
}

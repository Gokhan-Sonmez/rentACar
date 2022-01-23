package com.btkAkademi.rentACar.business.requests.RentalRequest;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

	private int customerId;

	private int carId;

	private int promoCodeId;

	private LocalDate rentDate;



}

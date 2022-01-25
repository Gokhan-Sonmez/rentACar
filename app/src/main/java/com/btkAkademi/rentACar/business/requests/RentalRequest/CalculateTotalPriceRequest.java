package com.btkAkademi.rentACar.business.requests.RentalRequest;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateTotalPriceRequest {
	private int rentalId;
	private LocalDate returnDate;
}
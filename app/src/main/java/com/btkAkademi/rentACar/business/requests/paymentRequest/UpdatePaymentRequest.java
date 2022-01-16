package com.btkAkademi.rentACar.business.requests.paymentRequest;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest {

	private int id;
	private int rentalId;
	private LocalDate paymentDate;
	private double moneyPaid; 
}

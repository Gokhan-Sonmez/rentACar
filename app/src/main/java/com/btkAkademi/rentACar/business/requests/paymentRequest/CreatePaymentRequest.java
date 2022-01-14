package com.btkAkademi.rentACar.business.requests.paymentRequest;

import java.time.LocalDate;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {

	
	private int rentalId;
	private LocalDate paymentDate;
	private double moneyPaid; 
}

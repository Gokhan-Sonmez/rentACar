package com.btkAkademi.rentACar.business.requests.paymentRequest;

import java.time.LocalDate;

import com.btkAkademi.rentACar.entities.concretes.Card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {

	
	private int rentalId;
	private LocalDate returnDate;
	private LocalDate paymentDate;
	private String cardNumber;
	private String nameOnTheCard;
	private String month;
	private String year;
	private int cvv;
}

package com.btkAkademi.rentACar.business.requests.cardRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCardRequest {

	private int id;

	private int customerId;
	private String cardNumber;

	private String nameOnTheCard;

	private String month;
	private String year;

	private int cvv;
}

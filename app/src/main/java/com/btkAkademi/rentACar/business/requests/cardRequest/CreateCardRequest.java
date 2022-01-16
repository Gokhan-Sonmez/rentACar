package com.btkAkademi.rentACar.business.requests.cardRequest;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardRequest {

	private int customerId;
	private String cardNumber;

	private String nameOnTheCard;

	private String expirationDate;

	private int cvv;
}

package com.btkAkademi.rentACar.business.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CardListDto {
	private int id;

	private int customerId;
	private String cardNumber;

	private String nameOnTheCard;

	private String month;
	private String year;

	private int cvv;

}

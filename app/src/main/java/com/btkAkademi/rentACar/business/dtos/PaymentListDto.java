package com.btkAkademi.rentACar.business.dtos;



import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentListDto {

	private int id;
    private int rentalId;
    private LocalDate paymentDate;
	private double moneyPaid; 
}

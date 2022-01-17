package com.btkAkademi.rentACar.business.requests.invoiceRequest;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {

	
	private int id;
	private LocalDate createdDate;
	private double amount;

}

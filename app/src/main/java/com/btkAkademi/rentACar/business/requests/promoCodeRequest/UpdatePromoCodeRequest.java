package com.btkAkademi.rentACar.business.requests.promoCodeRequest;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePromoCodeRequest {

	private int id;
	private int discounntRate;

	private LocalDate startDate;

	private LocalDate finishDate;
}

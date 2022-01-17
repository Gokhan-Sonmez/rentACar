package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromoCodeListDto {



	private int id;
	

	private String code;
	

	private int discountRate;
	

	private LocalDate startDate;
	

	private LocalDate finishDate;
}

package com.btkAkademi.rentACar.business.concretes;

import java.util.List;

import com.btkAkademi.rentACar.business.abstracts.CardService;
import com.btkAkademi.rentACar.business.dtos.CardListDto;
import com.btkAkademi.rentACar.business.requests.cardRequest.CreateCardRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public class CardManager implements CardService {

	@Override
	public DataResult<List<CardListDto>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result add(CreateCardRequest createCardRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}

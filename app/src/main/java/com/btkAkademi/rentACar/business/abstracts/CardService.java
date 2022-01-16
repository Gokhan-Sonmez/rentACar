package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CardDto;
import com.btkAkademi.rentACar.business.dtos.CardListDto;
import com.btkAkademi.rentACar.business.requests.cardRequest.CreateCardRequest;
import com.btkAkademi.rentACar.business.requests.cardRequest.UpdateCardRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CardService {

	DataResult<List<CardListDto>> getAll();
	DataResult<CardDto> getById(int id);
	
	Result add(CreateCardRequest createCardRequest);
	
	Result update (UpdateCardRequest updateCardRequest);
	
	Result delete (int id);
	
}

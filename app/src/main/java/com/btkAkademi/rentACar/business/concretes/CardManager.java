package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CardService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CardDto;
import com.btkAkademi.rentACar.business.dtos.CardListDto;
import com.btkAkademi.rentACar.business.requests.cardRequest.CreateCardRequest;
import com.btkAkademi.rentACar.business.requests.cardRequest.UpdateCardRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CardDao;
import com.btkAkademi.rentACar.entities.concretes.Card;

@Service
public class CardManager implements CardService {

	private CardDao cardDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CardManager(CardDao cardDao, ModelMapperService modelMapperService) {
		super();
		this.cardDao = cardDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<CardListDto>> getAll() {

		List<Card> cardList = this.cardDao.findAll();
		List<CardListDto> response = cardList.stream()
				.map(card -> modelMapperService.forDto().map(card, CardListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CardListDto>>(response, Messages.cardListed);

	}

	@Override
	public DataResult<CardDto> getById(int id) {
		
		Card card = this.cardDao.getById(id);
		CardDto response = this.modelMapperService.forDto().map(card, CardDto.class);
		
		return new SuccessDataResult<CardDto>(response, Messages.cardListed);
	}

	@Override
	public Result add(CreateCardRequest createCardRequest) {
		
		
		Card card = this.modelMapperService.forRequest().map(createCardRequest, Card.class);
		
		this.cardDao.save(card);
		return new SuccessResult(Messages.cardAdded);
	}

	@Override
	public Result update(UpdateCardRequest updateCardRequest) {
		
		Result result = BusinessRules.run(checkIfCardIdExist(updateCardRequest.getId()));
		
		if(result != null)
		{
			return result;
		}
		
		Card card = this.modelMapperService.forRequest().map(updateCardRequest, Card.class);
		this.cardDao.save(card);
		
       return new SuccessResult(Messages.cardUpdated);
	}

	@Override
	public Result delete(int id) {
		
		Result result = BusinessRules.run(checkIfCardIdExist(id));
		if(result != null) {
			return result;
		}
		this.cardDao.deleteById(id);
		
		return new SuccessResult(Messages.cardDeleted);
	}
	
	
	private Result checkIfCardIdExist(int id)
	{
		if(!cardDao.existsById(id))		
		{
			return new ErrorResult(Messages.cardIdNotExists);
		}
			
		return new SuccessResult();
	}

}

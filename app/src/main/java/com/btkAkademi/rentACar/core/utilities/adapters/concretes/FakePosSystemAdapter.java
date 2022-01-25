package com.btkAkademi.rentACar.core.utilities.adapters.concretes;



import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.adapters.abstracts.FakePosSystemService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.core.utilities.services.IsBank;
import com.btkAkademi.rentACar.entities.concretes.Card;

@Service
public class FakePosSystemAdapter implements FakePosSystemService {

	@Override
	public Result checkIfCreditCardIsValid(Card card) {
		
		IsBank isBank = new IsBank();
		if( isBank.isLimitExists(card.getCardNumber(),card.getNameOnTheCard(),card.getMonth(),card.getYear(),card.getCvv())) {
			return new SuccessResult();
		}else {	
			return new ErrorResult(Messages.limitNotEnough);
			}
	}

}

package com.btkAkademi.rentACar.core.utilities.adapters.abstracts;

import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Card;

public interface FakePosSystemService {
	
	Result checkIfCreditCardIsValid(Card card);

	
}

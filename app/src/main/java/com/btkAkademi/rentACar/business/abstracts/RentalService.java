package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.requests.RentalRequest.CreateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface RentalService {
	
	Result add(CreateRentalRequest createRentalRequest);

}



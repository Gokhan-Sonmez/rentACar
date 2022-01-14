package com.btkAkademi.rentACar.core.utilities.adapters;

import com.btkAkademi.rentACar.core.utilities.services.IsBankPosService;

public class IsBankPosAdapter implements IsBankPosService {

	@Override
	public boolean checkIfLimitIsEnough(String cardNo, int cvv) {
		// TODO Auto-generated method stub
		return true;
	}

}

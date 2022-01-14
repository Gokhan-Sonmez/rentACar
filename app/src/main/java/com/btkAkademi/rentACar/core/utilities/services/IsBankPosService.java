package com.btkAkademi.rentACar.core.utilities.services;

public interface IsBankPosService {

	boolean checkIfLimitIsEnough(String cardNo,int cvv);
}

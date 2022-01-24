package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.RentalDto;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.RentalRequest.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.RentalRequest.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Rental;

public interface RentalService {

	Result addForIndividiualCustomer(CreateRentalRequest createRentalRequest);
	Result updateForIndividiualCustomer(UpdateRentalRequest updateRentalRequest);
	Result deleteForIndividiualCustomer(int id);
	
	
	Result addForCorparateCustomer(CreateRentalRequest createRentalRequest);
	Result updateForCorparateCustomer(UpdateRentalRequest updateRentalRequest);
	Result deleteForCorparateCustomer(int id);


	DataResult<List<RentalListDto>> getAll();

	DataResult<RentalDto> getById(int id);

	DataResult<RentalDto> getByCarId(int id);

	boolean isCarRented(int carId);
}

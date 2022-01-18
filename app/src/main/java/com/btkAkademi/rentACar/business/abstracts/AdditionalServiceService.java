package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.AdditionalServiceDto;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.requests.additionalService.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.additionalService.UpdateAdditionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;


public interface AdditionalServiceService {


    DataResult<List<AdditionalServiceListDto>> getAll();
    DataResult<AdditionalServiceDto> getById(int id);
	
    DataResult<List<AdditionalServiceDto>> getAllByRentalId(int rentalId);

	
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);
	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest);
	Result delete(int id);

}

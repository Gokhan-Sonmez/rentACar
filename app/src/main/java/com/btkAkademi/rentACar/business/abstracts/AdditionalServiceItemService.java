package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.AdditionalServiceItemListDto;
import com.btkAkademi.rentACar.business.requests.additionalServiceItemRequest.CreateAdditionalServiceItemRequest;
import com.btkAkademi.rentACar.business.requests.additionalServiceItemRequest.UpdateAdditionalServiceItemRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface AdditionalServiceItemService {

	DataResult<List<AdditionalServiceItemListDto>> getAll();
	DataResult<AdditionalServiceItemListDto> getById(int id);
	
	Result add(CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest);

	Result update(UpdateAdditionalServiceItemRequest updateAdditionalServiceItemRequest);

	Result delete(int id);
}

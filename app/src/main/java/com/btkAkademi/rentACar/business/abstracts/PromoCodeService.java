package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.PromoCodeDto;
import com.btkAkademi.rentACar.business.dtos.PromoCodeListDto;
import com.btkAkademi.rentACar.business.requests.promoCodeRequest.CreatePromoCodeRequest;
import com.btkAkademi.rentACar.business.requests.promoCodeRequest.UpdatePromoCodeRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface PromoCodeService {

	DataResult<List<PromoCodeListDto>> getAll();
	DataResult<PromoCodeDto> getById(int id);
	
	Result add(CreatePromoCodeRequest createPromoCodeRequest);
	Result update(UpdatePromoCodeRequest updatePromoCodeRequest);
	Result delete(int id);
}

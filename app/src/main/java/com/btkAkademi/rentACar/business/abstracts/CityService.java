package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CityDto;
import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.business.requests.cityRequest.CreateCityRequest;
import com.btkAkademi.rentACar.business.requests.cityRequest.UpdateCityRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;


public interface CityService {

	
	Result add(CreateCityRequest createCityRequest);
	Result update(UpdateCityRequest udpateCityRequest);
	Result delete(int id);
	
	DataResult<List<CityListDto>> getAll(); 
	DataResult<CityDto> getCityById(int id);
	
}

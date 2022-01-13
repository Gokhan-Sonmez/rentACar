package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.business.requests.cityRequest.CreateCityRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.City;

public interface CityService {

	
	Result add(CreateCityRequest createCityRequest);
	DataResult<List<CityListDto>> getAll(); 
	DataResult<City> getCityById(int id);
	
}

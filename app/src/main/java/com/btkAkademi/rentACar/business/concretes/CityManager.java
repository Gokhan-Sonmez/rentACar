package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.business.requests.cityRequest.CreateCityRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CityDao;
import com.btkAkademi.rentACar.entities.concretes.City;

@Service
public class CityManager implements CityService{

	private CityDao cityDao;
	private ModelMapperService modelMapperService;
	
	@Override
	public Result add(CreateCityRequest createCityRequest) {
		
		Result result = BusinessRules.run(checkIfCityNameExists(createCityRequest.getName()));
		
		if (result != null) {
			return result;
		}
		
		City city = modelMapperService.forRequest().map(createCityRequest, City.class);
		this.cityDao.save(city);
		return new SuccessResult(Messages.cityAdded);
		
	}

	@Override
	public DataResult<List<CityListDto>> getAll() {
		List<City> cityList = this.cityDao.findAll();
		List<CityListDto> response = cityList.stream()
				.map(city->modelMapperService.forDto()
				.map(city, CityListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CityListDto>>(response);
	}

	@Override
	public DataResult<City> getCityById(int id) {
		if(cityDao.existsById(id)) {
			return new SuccessDataResult<City>(cityDao.getById(id));
		}
		else return new ErrorDataResult<City>();
	}
	
	private Result checkIfCityNameExists(String name) {
		if(cityDao.getByName(name)!=null)
				{
			return new ErrorResult(Messages.cityNameExists);
		         }
		return new SuccessResult();
	}

}

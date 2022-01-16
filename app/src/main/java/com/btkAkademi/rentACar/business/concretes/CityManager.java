package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CityDto;
import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.business.requests.cityRequest.CreateCityRequest;
import com.btkAkademi.rentACar.business.requests.cityRequest.UpdateCityRequest;
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
public class CityManager implements CityService {

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
	public Result update(UpdateCityRequest udpateCityRequest) {
		Result result = BusinessRules.run(checkIfCityIdExist(udpateCityRequest.getId()));

		if (result != null) {
			return result;
		}
		
		City city = modelMapperService.forRequest().map(udpateCityRequest, City.class);
		this.cityDao.save(city);
		return new SuccessResult(Messages.cityUpdated);
	}

	@Override
	public Result delete(int id) {
		Result result = BusinessRules.run(checkIfCityIdExist(id));

		if (result != null) {
			return result;
		}
		this.cityDao.deleteById(id);
		return new SuccessResult(Messages.cityDeleted);
	}

	@Override
	public DataResult<List<CityListDto>> getAll() {
		List<City> cityList = this.cityDao.findAll();
		List<CityListDto> response = cityList.stream()
				.map(city -> modelMapperService.forDto().map(city, CityListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CityListDto>>(response,Messages.cityListed);
	}

	@Override
	public DataResult<CityDto> getCityById(int id) {
		
		City city = this.cityDao.getById(id);
		CityDto response = this.modelMapperService.forDto().map(city, CityDto.class);
		
	 return new SuccessDataResult<CityDto>(response,Messages.cityListed);
		
	}

	private Result checkIfCityNameExists(String name) {
		if (cityDao.getByName(name) != null) {
			return new ErrorResult(Messages.cityNameExists);
		}
		return new SuccessResult();
	}

	private Result checkIfCityIdExist(int id) {
		if (!cityDao.existsById(id)) {
			return new ErrorResult();
		}
		return new SuccessResult();
	}

}

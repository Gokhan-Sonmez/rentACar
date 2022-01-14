package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.requests.additionalService.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.AdditionalServiceDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
@Service
public class AdditionalServiceManager implements AdditionalServiceService {

	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public AdditionalServiceManager(
			AdditionalServiceDao additionalServiceDao,
			ModelMapperService modelMapperService
			) 
	{

		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<AdditionalServiceListDto>> getAll() {
		
		List<AdditionalService> AdditionalServiceList = additionalServiceDao.findAll();
		List<AdditionalServiceListDto> response = AdditionalServiceList.stream()
				.map(additionalServer -> modelMapperService.forDto()
			    .map(additionalServer,AdditionalServiceListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<AdditionalServiceListDto>>(response);
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		
		AdditionalService additionalService = modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
		
		additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.additionalServiceAdded);
	}

	@Override
	public DataResult<AdditionalService> getByRentalId(int rentalId) {
		AdditionalService additionalService = this.additionalServiceDao.getById(rentalId);
		return new SuccessDataResult<AdditionalService>(additionalService);
	}

	@Override
	public DataResult<List<AdditionalService>> getAllRentalId(int rentalId) {
		List<AdditionalService> additionalService = this.additionalServiceDao.findByRentalId(rentalId);
		return new SuccessDataResult<List<AdditionalService>>(additionalService);
	}

}

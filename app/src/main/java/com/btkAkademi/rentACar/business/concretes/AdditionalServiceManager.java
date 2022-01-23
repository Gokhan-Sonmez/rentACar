package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceDto;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.requests.additionalService.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.additionalService.UpdateAdditionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
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
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService) {

		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<AdditionalServiceListDto>> getAll() {

		List<AdditionalService> additionalServiceList = additionalServiceDao.findAll();
		List<AdditionalServiceListDto> response = additionalServiceList.stream().map(
				additionalServer -> modelMapperService.forDto().map(additionalServer, AdditionalServiceListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<AdditionalServiceListDto>>(response,Messages.additionalServiceListed);
	}
	
	@Override
	public DataResult<AdditionalServiceDto> getById(int id) {
		AdditionalService additionalService = this.additionalServiceDao.getById(id);
		AdditionalServiceDto response = this.modelMapperService.forDto().map(additionalService, AdditionalServiceDto.class);
		
		return new SuccessDataResult<AdditionalServiceDto>(response,Messages.additionalServiceListed);
	}
	
	@Override
	public DataResult<List<AdditionalServiceListDto>> getAllByRentalId(int rentalId) {
		List<AdditionalService> additionalServiceList = this.additionalServiceDao.getByRentalId(rentalId);
		List<AdditionalServiceListDto> response = additionalServiceList.stream().map(
				additionalServer -> modelMapperService.forDto().map(additionalServer, AdditionalServiceListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<AdditionalServiceListDto>>(response,Messages.additionalServiceListed);
		
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {

		AdditionalService additionalService = modelMapperService.forRequest().map(createAdditionalServiceRequest,
				AdditionalService.class);

		additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.additionalServiceAdded);
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {

		Result result = BusinessRules.run(checkIfAdditionalServiceIdExist(updateAdditionalServiceRequest.getId()));

		if (result != null) {
			return result;
		}

		AdditionalService additionalService = modelMapperService.forRequest().map(updateAdditionalServiceRequest,
				AdditionalService.class);

		additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.additionalServiceUpdated);
	}

	@Override
	public Result delete(int id) {
		Result result = BusinessRules.run(checkIfAdditionalServiceIdExist(id));

		if (result != null) {
			return result;
		}

		additionalServiceDao.deleteById(id);
		return new SuccessResult(Messages.additionalServiceDeleted);

	}



	private Result checkIfAdditionalServiceIdExist(int id) {
		if (!additionalServiceDao.existsById(id)) {
			return new ErrorResult(Messages.additionalServiceNotExist);
		}

		return new SuccessResult();

	}

	

	

}

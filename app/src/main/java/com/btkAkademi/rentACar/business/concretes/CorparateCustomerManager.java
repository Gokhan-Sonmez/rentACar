package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CorparateCustomerService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CorparateCustomerDto;
import com.btkAkademi.rentACar.business.dtos.CorparateCustomerListDto;
import com.btkAkademi.rentACar.business.requests.corparateCustomerRequest.CreateCorparateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.corparateCustomerRequest.UpdateCorparateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorparateCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.CorpareteCustomer;

@Service
public class CorparateCustomerManager implements CorparateCustomerService {

	private CorparateCustomerDao corparateCustomerDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CorparateCustomerManager(CorparateCustomerDao corparateCustomerDao, ModelMapperService modelMapperService) {

		this.corparateCustomerDao = corparateCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCorparateCustomerRequest createCorparateCustomeRequest) {
		Result result = BusinessRules.run(checkIfCompanyNameExists(createCorparateCustomeRequest.getCompanyName()));

		if (result != null) {

			return result;
		}

		CorpareteCustomer corparateCustomer = this.modelMapperService.forRequest().map(createCorparateCustomeRequest,
				CorpareteCustomer.class);
		this.corparateCustomerDao.save(corparateCustomer);

		return new SuccessResult(Messages.corparateCustomerAdded);
	}


	@Override
	public Result update(UpdateCorparateCustomerRequest updateCorparateCustomeRequest) {
		Result result = BusinessRules.run(checkIfCorparateCustomerIdExist(updateCorparateCustomeRequest.getId()),
				checkIfCompanyNameExists(updateCorparateCustomeRequest.getCompanyName())
				
				);

		if (result != null) {

			return result;
		}
		
		
		CorpareteCustomer corparateCustomer = this.modelMapperService.forRequest().map(updateCorparateCustomeRequest,
				CorpareteCustomer.class);
		this.corparateCustomerDao.save(corparateCustomer);

		return new SuccessResult(Messages.corparateCustomerUpdated);
	}

	@Override
	public Result delete(int id) {
		Result result = BusinessRules.run(checkIfCorparateCustomerIdExist(id));

		if (result != null) {

			return result;
		}

		this.corparateCustomerDao.deleteById(id);

		return new SuccessResult(Messages.corparateCustomerDeletes);
	}

	@Override
	public DataResult<List<CorparateCustomerListDto>> getAll() {

		List<CorpareteCustomer> corpareteCustomerList = this.corparateCustomerDao.findAll();
		List<CorparateCustomerListDto> response = corpareteCustomerList.stream()
				.map(corparateCustomer -> this.modelMapperService.forDto().map(corparateCustomer,
						CorparateCustomerListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<CorparateCustomerListDto>>(response);
	}

	@Override
	public DataResult<CorparateCustomerDto> getById(int id) {

		CorpareteCustomer corpareteCustomer = this.corparateCustomerDao.getById(id);
		CorparateCustomerDto response = this.modelMapperService.forDto().map(corpareteCustomer,
				CorparateCustomerDto.class);

		return new SuccessDataResult<CorparateCustomerDto>(response);
	}

	
	private Result checkIfCompanyNameExists(String companyName) {

		var Result = corparateCustomerDao.getByCompanyName(companyName);

		if (Result != null) {
			return new ErrorResult(Messages.companyNameExists);
		}
		return new SuccessResult();
	}
	
	private Result checkIfCorparateCustomerIdExist(int id) {
		if (!corparateCustomerDao.existsById(id)) {
			return new ErrorResult();
		}

		return new SuccessResult();
	}

}

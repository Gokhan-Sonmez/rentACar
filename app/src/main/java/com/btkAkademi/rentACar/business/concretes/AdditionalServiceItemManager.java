package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceItemService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceItemListDto;
import com.btkAkademi.rentACar.business.requests.additionalServiceItemRequest.CreateAdditionalServiceItemRequest;
import com.btkAkademi.rentACar.business.requests.additionalServiceItemRequest.UpdateAdditionalServiceItemRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.AdditionalServiceItemDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalServiceItem;

@Service
public class AdditionalServiceItemManager implements AdditionalServiceItemService {

	private AdditionalServiceItemDao additionalServiceItemDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public AdditionalServiceItemManager(AdditionalServiceItemDao additionalServiceItemDao,
			ModelMapperService modelMapperService) {
		super();
		this.additionalServiceItemDao = additionalServiceItemDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<AdditionalServiceItemListDto>> getAll() {
		List<AdditionalServiceItem> additionalServiceItems = this.additionalServiceItemDao.findAll();

		List<AdditionalServiceItemListDto> response = additionalServiceItems.stream()
				.map(additionalServiceItem -> modelMapperService.forDto().map(additionalServiceItem,
						AdditionalServiceItemListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<AdditionalServiceItemListDto>>(response);
	}

	@Override
	public DataResult<AdditionalServiceItemListDto> getById(int id) {

		AdditionalServiceItem additionalServiceItems = this.additionalServiceItemDao.getById(id);

		AdditionalServiceItemListDto response = modelMapperService.forDto().map(additionalServiceItems,
				AdditionalServiceItemListDto.class);

		return new SuccessDataResult<AdditionalServiceItemListDto>(response);
	}

	@Override
	public Result add(CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest) {

		AdditionalServiceItem additionalServiceItem = this.modelMapperService.forRequest()
				.map(createAdditionalServiceItemRequest, AdditionalServiceItem.class);

		this.additionalServiceItemDao.save(additionalServiceItem);

		return new SuccessResult();
	}

	@Override
	public Result update(UpdateAdditionalServiceItemRequest updateAdditionalServiceItemRequest) {
		AdditionalServiceItem additionalServiceItem = this.modelMapperService.forRequest()
				.map(updateAdditionalServiceItemRequest, AdditionalServiceItem.class);

		this.additionalServiceItemDao.save(additionalServiceItem);

		return new SuccessResult();
	}

	@Override
	public Result delete(int id) {

		this.additionalServiceItemDao.deleteById(id);
		

		return new SuccessResult();
	}

}

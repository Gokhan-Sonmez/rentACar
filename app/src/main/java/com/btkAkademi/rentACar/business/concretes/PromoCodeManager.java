package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.PromoCodeService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.PromoCodeDto;
import com.btkAkademi.rentACar.business.dtos.PromoCodeListDto;
import com.btkAkademi.rentACar.business.requests.promoCodeRequest.CreatePromoCodeRequest;
import com.btkAkademi.rentACar.business.requests.promoCodeRequest.UpdatePromoCodeRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PromoCodeDao;
import com.btkAkademi.rentACar.entities.concretes.PromoCode;

@Service
public class PromoCodeManager implements PromoCodeService {

	private PromoCodeDao promoCodeDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public PromoCodeManager(PromoCodeDao promoCodeDao, ModelMapperService modelMapperService) {
		super();
		this.promoCodeDao = promoCodeDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<PromoCodeListDto>> getAll() {
		List<PromoCode> promoCodeList = this.promoCodeDao.findAll();
		List<PromoCodeListDto> response = promoCodeList.stream()
				.map(promoCode -> this.modelMapperService.forDto().map(promoCodeList, PromoCodeListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<PromoCodeListDto>>(response, Messages.promoCodeListed);
	}

	@Override
	public DataResult<PromoCodeDto> getById(int id) {

		PromoCode promoCode = this.promoCodeDao.getById(id);
		PromoCodeDto response = this.modelMapperService.forDto().map(promoCode, PromoCodeDto.class);

		return new SuccessDataResult<PromoCodeDto>(response, Messages.promoCodeListed);
	}

	@Override
	public Result add(CreatePromoCodeRequest createPromoCodeRequest) {

		PromoCode promoCode = this.modelMapperService.forRequest().map(createPromoCodeRequest, PromoCode.class);

		this.promoCodeDao.save(promoCode);

		return new SuccessResult(Messages.promoCodeAdded);
	}

	@Override
	public Result update(UpdatePromoCodeRequest updatePromoCodeRequest) {

		Result result = BusinessRules.run(checkIfPromoCodeIdExist(updatePromoCodeRequest.getId()));

		if (result != null) {
			return result;
		}

		PromoCode promoCode = this.modelMapperService.forRequest().map(updatePromoCodeRequest, PromoCode.class);

		this.promoCodeDao.save(promoCode);

		return new SuccessResult(Messages.promoCodeUpdated);
	}

	@Override
	public Result delete(int id) {
		
		Result result = BusinessRules.run(checkIfPromoCodeIdExist(id));

		if (result != null) {
			return result;
		}
		
		this.promoCodeDao.deleteById(id);
		return new SuccessResult(Messages.promoCodeDeleted);
	}

	private Result checkIfPromoCodeIdExist(int id) {

		if (!promoCodeDao.existsById(id)) {
			return new ErrorResult();

		}
		return new SuccessResult();

	}

}

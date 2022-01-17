package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.PromoCodeService;
import com.btkAkademi.rentACar.business.dtos.PromoCodeDto;
import com.btkAkademi.rentACar.business.dtos.PromoCodeListDto;
import com.btkAkademi.rentACar.business.requests.promoCodeRequest.CreatePromoCodeRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/promocodes")
public class PromoCodeController {
	
	private PromoCodeService promoCodeService;

	public PromoCodeController(PromoCodeService promoCodeService) {
		super();
		this.promoCodeService = promoCodeService;
	}
	
	@GetMapping("getall")
	
	public DataResult<List<PromoCodeListDto>> getAll()
	{
		
		return this.promoCodeService.getAll();
	}
	
	@GetMapping("get-by-id/{id}")
	public DataResult<PromoCodeDto> getById(@PathVariable int id)
	{
		
		return this.getById(id);
	}
	
	@PostMapping("add")
	public Result add(@RequestBody CreatePromoCodeRequest createPromoCodeRequest)
	{
		return this.promoCodeService.add(createPromoCodeRequest);
	}
	

}

package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceItemService;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceItemListDto;
import com.btkAkademi.rentACar.business.requests.additionalServiceItemRequest.CreateAdditionalServiceItemRequest;
import com.btkAkademi.rentACar.business.requests.additionalServiceItemRequest.UpdateAdditionalServiceItemRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalserviceitems")
@CrossOrigin
public class AdditionalServiceItemController {

	private AdditionalServiceItemService additionalServiceItemService;

	public AdditionalServiceItemController(AdditionalServiceItemService additionalServiceItemService) {
		super();
		this.additionalServiceItemService = additionalServiceItemService;
	}

	@GetMapping("getall")
	public DataResult<List<AdditionalServiceItemListDto>> getAll() {
		return this.additionalServiceItemService.getAll();
	}

	@GetMapping("getById/{id}")
	public Result getById(@PathVariable int id) {
		return additionalServiceItemService.getById(id);

	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateAdditionalServiceItemRequest createAdditionalServiceRequest) {

		return this.additionalServiceItemService.add(createAdditionalServiceRequest);

	}

	@PostMapping("updated")
	public Result add(@RequestBody @Valid UpdateAdditionalServiceItemRequest updateAdditionalServiceRequest) {

		return this.additionalServiceItemService.update(updateAdditionalServiceRequest);

	}

	@DeleteMapping("delete/{id}")
	public Result update(@PathVariable int id) {
		return this.additionalServiceItemService.delete(id);
	}

}

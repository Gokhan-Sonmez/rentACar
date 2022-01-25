package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CardService;
import com.btkAkademi.rentACar.business.dtos.CardDto;
import com.btkAkademi.rentACar.business.dtos.CardListDto;
import com.btkAkademi.rentACar.business.requests.cardRequest.CreateCardRequest;
import com.btkAkademi.rentACar.business.requests.cardRequest.UpdateCardRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin
public class CardController {

	private CardService cardService;

	public CardController(CardService cardService) {
		super();
		this.cardService = cardService;
	}

	@GetMapping("getall")
	public DataResult<List<CardListDto>> getAll() {

		return this.cardService.getAll();

	}

	@GetMapping("getById/{cardId}")
	public DataResult<CardDto> getById(@PathVariable int cardId) {

		return this.cardService.getById(cardId);
	}

	@PostMapping("add")
	public Result add(@RequestBody CreateCardRequest createCardRequest) {
		return this.cardService.add(createCardRequest);
	}

	@PostMapping("update")
	public Result update(@RequestBody UpdateCardRequest updateCardRequest) {
		
		return this.cardService.update(updateCardRequest);
		
	}
	
	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable int id)
	{
		return this.cardService.delete(id);
	}

}

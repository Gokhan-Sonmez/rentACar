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

import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.dtos.PaymentDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.RentalRequest.CalculateTotalPriceRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequest.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequest.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentController {
	
	private PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}

	
	@GetMapping("getall")
	DataResult<List<PaymentListDto>> getAll(){
		
		return this.paymentService.getAll();
	}
	
	@GetMapping("getById/{paymentId}")
	DataResult<PaymentDto> getById(@PathVariable int paymentId){
		
		return this.paymentService.getById(paymentId);
	}
	
	@PostMapping("add")
	Result add (@RequestBody CreatePaymentRequest createPaymentRequest) {
		
		return this.paymentService.add(createPaymentRequest);
	}
	
	@PostMapping("update")
	Result update (@RequestBody UpdatePaymentRequest updatePaymentRequest) {
		
		return this.paymentService.update(updatePaymentRequest);
	}
	
	@DeleteMapping("delete/{id}")
	Result delete (@PathVariable int id) {
		
		return this.paymentService.delete(id);
	}
	
	@PostMapping("getTotalPrice")
	public DataResult<Double> calculateTotalPrice(@RequestBody CalculateTotalPriceRequest calculateTotalPriceRequest) {
		System.out.println(calculateTotalPriceRequest.getReturnDate());
		return this.paymentService.calculateTotalPriceForDisplay(calculateTotalPriceRequest);
	}
}

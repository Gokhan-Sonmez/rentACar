package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.PaymentDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.RentalRequest.CalculateTotalPriceRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequest.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequest.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface PaymentService {
	
	DataResult<List<PaymentListDto>> getAll();
	DataResult<PaymentDto> getById(int id);
	DataResult<List<PaymentListDto>> getAllByRentalId(int id);
	
	DataResult<Double> calculateTotalPriceForDisplay(CalculateTotalPriceRequest calculateTotalPriceRequest);
	
	
	Result add (CreatePaymentRequest createPaymentRequest);
	Result update (UpdatePaymentRequest updatePaymentRequest);
	Result delete (int id);

}

package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.CorparateCustomerService;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceDto;

import com.btkAkademi.rentACar.business.dtos.IndividualCustomerDto;

import com.btkAkademi.rentACar.business.dtos.InvoiceCorporateCustomerDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceIndividualCustomerDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceListDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.dtos.RentalDto;

import com.btkAkademi.rentACar.business.requests.invoiceRequest.CreateInvoiceRequest;
import com.btkAkademi.rentACar.business.requests.invoiceRequest.UpdateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.InvoiceDao;
import com.btkAkademi.rentACar.entities.concretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService {

	private InvoiceDao invoiceDao;
	private ModelMapperService modelMapperService;
	private IndividualCustomerService individualCustomerService;
	private CorparateCustomerService corparateCustomerService;
	private RentalService rentalService;
	private PaymentService paymentService;
	private AdditionalServiceService additionalServiceService;

	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao,
			ModelMapperService modelMapperService,
			IndividualCustomerService individualCustomerService,
			CorparateCustomerService corporateCustomerService,
			RentalService rentalService, PaymentService paymentService,
			AdditionalServiceService additionalServiceService) {
		super();
		this.invoiceDao = invoiceDao;
		this.modelMapperService = modelMapperService;
		this.individualCustomerService = individualCustomerService;
		this.corparateCustomerService = corporateCustomerService;

		this.rentalService = rentalService;
		this.paymentService = paymentService;
		this.additionalServiceService = additionalServiceService;
	}



	@Override
	public DataResult<InvoiceIndividualCustomerDto> getInvoiceForIndividualCustomer(int rentalId) {
		
		Invoice invoice = invoiceDao.findByRentalId(rentalId);
		RentalDto rental = rentalService.getById(rentalId).getData();
		IndividualCustomerDto customer = individualCustomerService.getById(rental.getCustomerId()).getData();
		List<AdditionalServiceDto> additionalServices = additionalServiceService.getAllByRentalId(rentalId)
				.getData();
		List<PaymentListDto> payments = paymentService.getAllByRentalId(rentalId).getData();
		double totalPrice = 0;
		for (PaymentListDto payment : payments) {
			totalPrice += payment.getMoneyPaid();
		}

		InvoiceIndividualCustomerDto responseCustomerDto = InvoiceIndividualCustomerDto.builder().id(invoice.getId())
				.firstName(customer.getFirstName()).lastName(customer.getLastName())
				.nationalityId(customer.getNationalityId()).email(customer.getEmail()).amount(totalPrice)
				.rentDate(rental.getRentDate()).returnedDate(rental.getReturnDate())
				.creationDate(invoice.getCreatedDate())
						.additonalServices(additionalServices).build();
		return new SuccessDataResult<InvoiceIndividualCustomerDto>(responseCustomerDto);
	}

	@Override
	public DataResult<InvoiceCorporateCustomerDto> getInvoiceForCorporateCustomer(int rentalId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);

		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.invoiceAdded);
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {

		Result result = BusinessRules.run(checkIfInvoiceIdExist(updateInvoiceRequest.getId()));

		if (result != null) {
			return result;
		}
		
		Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);

		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.invoiceUpdated);
	}

	@Override
	public Result delete(int id) {
		Result result = BusinessRules.run(checkIfInvoiceIdExist(id));

		if (result != null) {
			return result;
		}

		this.invoiceDao.deleteById(id);
		return new SuccessResult(Messages.invoiceDeleted);
	}
	
	@Override
	public DataResult<List<InvoiceListDto>> getAll() {
		List<Invoice> invoiceList = this.invoiceDao.findAll();
		List<InvoiceListDto> response = invoiceList.stream()
				.map(invoice -> this.modelMapperService.forDto().map(invoiceList, InvoiceListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<InvoiceListDto>>(response,Messages.invoiceListed);
	}

	@Override
	public DataResult<InvoiceDto> getById(int id) {
		Invoice invoice = this.invoiceDao.getById(id);
		InvoiceDto response = this.modelMapperService.forDto().map(invoice, InvoiceDto.class);

		return new SuccessDataResult<InvoiceDto>(response,Messages.invoiceListed);

	}

	Result checkIfInvoiceIdExist(int id) {
		if (!invoiceDao.existsById(id)) {
			return new ErrorResult();
		}
		return new SuccessResult();
	}

}

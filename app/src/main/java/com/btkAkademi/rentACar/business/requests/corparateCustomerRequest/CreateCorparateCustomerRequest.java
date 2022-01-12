package com.btkAkademi.rentACar.business.requests.corparateCustomerRequest;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorparateCustomerRequest {

	
	
	private String companyName;

	private String taxtNumber;
}

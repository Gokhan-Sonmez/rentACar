package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.CorpareteCustomer;

public interface CorparateCustomerDao extends JpaRepository<CorpareteCustomer, Integer>{

	CorpareteCustomer getByCompanyName (String companyName);
	
}

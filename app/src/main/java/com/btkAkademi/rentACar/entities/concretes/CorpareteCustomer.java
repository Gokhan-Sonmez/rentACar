package com.btkAkademi.rentACar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="corparate_customer")
@PrimaryKeyJoinColumn(name="customer_id")
public class CorpareteCustomer extends Customer{
	
	    @Column(name="company_name")
		private String companyName;
	    @Column(name="taxt_number")
		private String taxtNumber;

}

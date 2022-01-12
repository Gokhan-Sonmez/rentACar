package com.btkAkademi.rentACar.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="rentals")
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="rent_date")
	private LocalDate rentDate;
	
	@Column(name="return_date")
	private LocalDate returnDate;
	
	@Column(name="rented_kilometer")
	private int rentedKilometer;
	
	@Column(name="return_kilometer")
	private int returnKilometer;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
}
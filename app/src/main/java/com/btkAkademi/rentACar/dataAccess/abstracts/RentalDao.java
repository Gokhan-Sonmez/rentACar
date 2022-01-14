package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Rental;

public interface RentalDao extends JpaRepository<Rental, Integer>{

	Rental findByCarId(int carId);
	Rental findByCarIdAndReturnDateIsNull(int carId);
}

package com.btkAkademi.rentACar.dataAccess.abstracts;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;

public interface CarMaintenanceDao extends JpaRepository<CarMaintenance, Integer> {
	
	List<CarMaintenance> findAllByCarId(int carId);
	
	CarMaintenance findByCarIdAndMaintenanceEndIsNull(int CarId);
	
	
}

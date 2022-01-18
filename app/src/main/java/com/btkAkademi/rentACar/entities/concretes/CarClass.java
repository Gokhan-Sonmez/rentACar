package com.btkAkademi.rentACar.entities.concretes;



import java.util.List;

import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="car_classes")
public class CarClass {

	
	private int id;
	private String carClass;
	
	@OneToMany(mappedBy = "carsClass")
	private List<Car> cars;
}

package com.btkAkademi.rentACar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cars")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="car_name")
	private String carName;
	
	@Column(name="image_path")
	private String imagePath; 
	
	@Column(name="daily_price")
	private double dailyPrice;
	
	@Column(name="model_year")
	private int modelYear;
	
	@Column(name="description")
	private String description;
	
	@Column(name="findex_score")
	private int findexScore;
	
	@Column(name="kilometer")
	private int kilometer;
	
	@Column(name="min_age")
	private int minAge;
	
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@OneToMany(mappedBy ="car" )
	private List<CarMaintenance> carMaintenances;
	
	@OneToMany(mappedBy ="car" )
	private List<Rental> rentals;
	
	@ManyToOne
	@JoinColumn(name="car_classes_id")
	private CarClass carClass;
	
	
	

}

package com.btkAkademi.rentACar.entities.concretes;



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
@Table(name="cards")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private int id;
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="name_on_the_card")
	private String nameOnTheCard;
	
	@Column(name="month")
	private String month;
	
	@Column(name="year")
	private String year;
	
	@Column(name="cvv")
	private int cvv ;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
}

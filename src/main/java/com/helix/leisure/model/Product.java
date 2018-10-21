package com.helix.leisure.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Lob
	private String name;

	private int quantity;

	private double sale_amount;

	@ManyToOne(cascade = CascadeType.ALL)
	private Event event;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSale_amount() {
		return sale_amount;
	}

	public void setSale_amount(double saleAmount) {
		this.sale_amount = saleAmount;
	}

	@JsonIgnore
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Product() {
	}

	public Product(String name, int quantity, double saleAmount) {
		this.name = name;
		this.quantity = quantity;
		this.sale_amount = saleAmount;
	}

}

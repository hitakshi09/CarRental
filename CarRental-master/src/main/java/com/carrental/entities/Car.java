package com.carrental.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "cars")
public class Car implements Serializable {

	private static final long serialVersionUID = -5175608523574439852L;

	public static final String ATTRIBUTE_ID = "id";
	public static final String ATTRIBUTE_MODEL = "model";

	public static final String ATTRIBUTE_MAKE = "make";
	public static final String ATTRIBUTE_CAR_CLASS = "car_class";
	public static final String ATTRIBUTE_COST = "cost";
	public static final String ATTRIBUTE_STATUS = "status";

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "model")
	private String model;

	@Column(name = "make")
	private String make;

	@Column(name = "car_class")
	private String carClass;

	@Column(name = "cost")
	private int cost;
	
	@Column(name = "status")
	private String status;
	
	
	public Car() {

	}

	public Car(String model, String make, String carClass, int cost, String status) {
		this.model = model;
		this.make = make;
		this.carClass = carClass;
		this.cost = cost;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getCarClass() {
		return carClass;
	}

	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", model=" + model + ", make=" + make + ", carClass=" + carClass + ", cost=" + cost + ", status=" + status
				+ "]";
	}

}

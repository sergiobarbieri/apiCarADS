package br.com.drummond.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car {

	@Id
	@GeneratedValue
	private Integer carId;
	private String model;
	private Integer year;

	public Car() {

	}

	public Integer getCarId() {
		return carId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	

}

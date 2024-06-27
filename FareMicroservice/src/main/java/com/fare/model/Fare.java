package com.fare.model;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="fare")
public class Fare {
	
	@Id
	private String flightId;
	
	private BigDecimal amount;

	public Fare(String flightId, BigDecimal amount) {
		super();
		this.flightId = flightId;
		this.amount = amount;
	}

	public Fare() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Fare [FlightId=" + flightId + ", amount=" + amount + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(flightId, amount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fare other = (Fare) obj;
		return Objects.equals(flightId, other.flightId) && Objects.equals(amount, other.amount);
	}
	
	
	
	

}

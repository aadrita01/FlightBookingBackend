package com.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.model.Flights;

public interface FlightsRepository extends JpaRepository<Flights,String> {

}

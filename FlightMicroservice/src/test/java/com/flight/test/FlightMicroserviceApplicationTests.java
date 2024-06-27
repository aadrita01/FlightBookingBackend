package com.flight.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.flight.exception.InvalidIdException;
import com.flight.model.Flights;
import com.flight.repository.FlightsRepository;
import com.flight.service.FlightServiceImpl;

@SpringBootTest
@ComponentScan(basePackages = "com.flight")
class FlightMicroserviceApplicationTests {
	
	 @Mock
	    private FlightsRepository flightsRepository;

	    @InjectMocks
	    private FlightServiceImpl flightService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
	
	@Test
    void getAllFlights() {
        Flights flight1 = new Flights();
        Flights flight2 = new Flights();
        List<Flights> expectedFlights = Arrays.asList(flight1, flight2);

        when(flightsRepository.findAll()).thenReturn(expectedFlights);

        List<Flights> actualFlights = flightService.getAllFlights();

        assertEquals(expectedFlights, actualFlights);
        verify(flightsRepository, times(1)).findAll();
    }
	
	@Test
	void getFlightById_invalidId() {
	    String invalidFlightId = "InvalidId";
	    when(flightsRepository.existsById(invalidFlightId)).thenReturn(false);

	    assertThrows(InvalidIdException.class, () -> flightService.getFlightById(invalidFlightId));
	    verify(flightsRepository, times(1)).existsById(invalidFlightId);
	    verify(flightsRepository, never()).findById(any());
	}
	
	@Test
	void addFlight_duplicateFlightId() {
	    Flights existingFlight = new Flights();
	    existingFlight.setFlightId("ExistingFlightId");
	    when(flightsRepository.existsById(existingFlight.getFlightId())).thenReturn(true);

	    assertThrows(InvalidIdException.class, () -> flightService.addFlight(existingFlight));
	    verify(flightsRepository, times(1)).existsById(existingFlight.getFlightId());
	    verify(flightsRepository, never()).save(any());
	}

}

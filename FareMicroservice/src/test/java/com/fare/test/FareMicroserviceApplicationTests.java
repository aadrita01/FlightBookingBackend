package com.fare.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fare.exception.FlightIdNotFoundException;
import com.fare.model.Fare;
import com.fare.repository.FareRepository;
import com.fare.service.FareServiceImpl;

@SpringBootTest
class FareServiceImplTest {

    @Mock
    private FareRepository fareRepository;

    @InjectMocks
    private FareServiceImpl fareService;

    @Test
    void testGetFareByFlightId() {
        // Arrange
        String flightId = "ML4301";
        Fare mockFare = new Fare(flightId, new BigDecimal(2000));
        when(fareRepository.findByFlightId(flightId)).thenReturn(mockFare);

        // Act
        Fare result = fareService.getFareByFlightId(flightId);

        // Assert
        assertNotNull(result);
        assertEquals(mockFare, result);
    }

    @Test
    void testDefaultFare() {
        // Arrange
        Fare mockFare = new Fare("ML4301", new BigDecimal(2000));
        when(fareRepository.save(any())).thenReturn(mockFare);

        // Act
        fareService.defaultFare();

        // Assert
        verify(fareRepository, times(1)).save(any());
    }

    @Test
    void testAddFare() {
        // Arrange
        Fare mockFare = new Fare("ML4301", new BigDecimal(2000));
        when(fareRepository.save(mockFare)).thenReturn(mockFare);

        // Act
        Fare result = fareService.addFare(mockFare);

        // Assert
        assertNotNull(result);
        assertEquals(mockFare, result);
    }

    @Test
    void testUpdateFare() throws FlightIdNotFoundException {
        // Arrange
        String flightId = "ML4301";
        BigDecimal newFareAmount = new BigDecimal(2500);
        Fare existingFare = new Fare(flightId, new BigDecimal(2000));
        when(fareRepository.findById(flightId)).thenReturn(Optional.of(existingFare));
        when(fareRepository.save(existingFare)).thenReturn(existingFare);

        // Act
        Fare result = fareService.updateFare(flightId, newFareAmount);

        // Assert
        assertNotNull(result);
        assertEquals(newFareAmount, result.getAmount());
    }

    @Test
    void testUpdateFareFlightIdNotFoundException() {
        // Arrange
        String flightId = "NonexistentFlight";
        BigDecimal newFareAmount = new BigDecimal(2500);
        when(fareRepository.findById(flightId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(FlightIdNotFoundException.class, () -> {
            fareService.updateFare(flightId, newFareAmount);
        });
    }

    @Test
    void testDeleteFare() throws FlightIdNotFoundException {
        // Arrange
        String flightId = "ML4301";
        when(fareRepository.existsById(flightId)).thenReturn(true);

        // Act
        fareService.deleteFare(flightId);

        // Assert
        verify(fareRepository, times(1)).deleteById(flightId);
    }

    @Test
    void testDeleteFareFlightIdNotFoundException() {
        // Arrange
        String flightId = "NonexistentFlight";
        when(fareRepository.existsById(flightId)).thenReturn(false);

        // Act and Assert
        assertThrows(FlightIdNotFoundException.class, () -> {
            fareService.deleteFare(flightId);
        });
    }
}

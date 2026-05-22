package com.axity.dinosaurpark;

import com.axity.dinosaurpark.model.*;
import com.axity.dinosaurpark.persistence.csvWriter;
import com.axity.dinosaurpark.simulation.ParkState;
import com.axity.dinosaurpark.zone.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ParkStateTest {

    private ParkState parkState;

    private List<Tourist> tourists;
    private List<Dinosaur> dinosaurs;
    private List<Worker> workers;

    @BeforeEach
    void setUp() {

        tourists = new ArrayList<>();
        dinosaurs = new ArrayList<>();
        workers = new ArrayList<>();

        ArrivalZone arrivalZone = new ArrivalZone();
        CentralHub centralHub = new CentralHub();
        BathroomZone bathroomZone = new BathroomZone();
        PowerPlant powerPlant = new PowerPlant();

        List<ObservationEnclosure> enclosures = new ArrayList<>();

        csvWriter writer = new csvWriter();

        Random rng = new Random();

        parkState = new ParkState(
                tourists,
                dinosaurs,
                workers,
                arrivalZone,
                centralHub,
                bathroomZone,
                powerPlant,
                enclosures,
                writer,
                rng
        );
    }

    @Test
    void shouldIncrementStep() {

        int initialStep = parkState.getCurrentStep();

        parkState.incrementStep();

        assertEquals(initialStep + 1, parkState.getCurrentStep());
    }

    @Test
    void shouldAddRevenueCorrectly() {

        parkState.addRevenue(100.0);
        parkState.addRevenue(50.0);

        assertEquals(150.0, parkState.getTotalRevenue());
    }

    @Test
    void shouldAddExpensesCorrectly() {

        parkState.addExpense(40.0);
        parkState.addExpense(10.0);

        assertEquals(50.0, parkState.getTotalExpenses());
    }

    @Test
    void shouldCountActiveTourists() {

        Tourist tourist1 = new Tourist(1, "Brayan");
        tourist1.setStatus(TouristStatus.IN_PARK);

        Tourist tourist2 = new Tourist(2, "Dani");
        tourist2.setStatus(TouristStatus.EXITED);

        tourists.add(tourist1);
        tourists.add(tourist2);

        long result = parkState.countActiveTourists();

        assertEquals(1, result);
    }

    @Test
    void shouldCountDinosaursInEnclosure() {

        CarnivoreDinosaur dinosaur1 =
                new CarnivoreDinosaur(1, "Rex", "T-Rex");

        CarnivoreDinosaur dinosaur2 =
                new CarnivoreDinosaur(2, "Blue", "Velociraptor");

        dinosaur2.escape();

        dinosaurs.add(dinosaur1);
        dinosaurs.add(dinosaur2);

        long result = parkState.countDinosaursInEnclosure();

        assertEquals(1, result);
    }

    @Test
    void shouldReturnDinosaursList() {
        assertEquals(dinosaurs, parkState.getDinosaurs());
    }

    @Test
    void shouldReturnWorkersList() {
        assertEquals(workers, parkState.getWorkers());
    }

    @Test
    void shouldStartWithZeroRevenueAndExpenses() {

        assertEquals(0.0, parkState.getTotalRevenue());
        assertEquals(0.0, parkState.getTotalExpenses());
    }
}
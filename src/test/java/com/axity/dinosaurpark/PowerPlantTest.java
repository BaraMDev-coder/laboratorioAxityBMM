package com.axity.dinosaurpark;

import com.axity.dinosaurpark.zone.PowerPlant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PowerPlantTest {

    @Test
    void testInitiallyOperational() {
        //Verifica si se creo la planta
        PowerPlant plant = new PowerPlant();
        assertTrue(plant.isOperational());
    }

    @Test
    void testTriggerFailure() {
        //En caso de ser falso
        PowerPlant plant = new PowerPlant();
        plant.triggerFailure();
        assertFalse(plant.isOperational());
    }

    @Test
    void testRepair() {
        // proceso de reparacion de la planta
        PowerPlant plant = new PowerPlant();
        plant.triggerFailure();
        plant.repair();
        assertTrue(plant.isOperational());
    }

    @Test
    void testInitialEnergy() {
        //Verifica la enrgia de la planta
        PowerPlant plant = new PowerPlant();
        assertEquals(100.0, plant.getEnergy());
    }

    @Test
    void testRepairCost() {
        // Verifica el costo de la reparcion
        PowerPlant plant = new PowerPlant();
        assertEquals(500.0, plant.getRepairCost());
    }
}
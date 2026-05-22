package com.axity.dinosaurpark;

import com.axity.dinosaurpark.model.*;
import com.axity.dinosaurpark.zone.PowerPlant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerTest {

    @Test
    void testGuardRole() {
        //Verifica que el rol del guardia
        Guard guard = new Guard(1, "Brayan", 150.0);
        assertEquals("GUARD", guard.getRole());
    }

    @Test
    void testGuardName() {
        //Verifica que el nombre
        Guard guard = new Guard(1, "Brayan", 150.0);
        assertEquals("Brayan", guard.getName());
    }

    @Test
    void testGuardSalary() {
        //Verifica que el salario diario sea 150.0
        Guard guard = new Guard(1, "Brayan", 150.0);
        assertEquals(150.0, guard.getDailySalary());
    }

    @Test
    void testGuardRecaptureEscapedDinosaur() {
        //Verifica si el guardi capturo al dinossaurio
        Guard guard = new Guard(1, "Brayan", 150.0);
        CarnivoreDinosaur dino = new CarnivoreDinosaur(1, "Rex", "T-Rex");
        dino.escape();
        List<Dinosaur> dinosaurs = new ArrayList<>();
        dinosaurs.add(dino);
        guard.recaptureEscapedDinosaurs(dinosaurs);
        assertEquals(DinosaurStatus.IN_ENCLOSURE, dino.getStatus());
    }

    @Test
    void testTechnicianRole() {
        //Verifica que el rol del técnico
        Technician tech = new Technician(1, "Brayan", 200.0);
        assertEquals("TECHNICIAN", tech.getRole());
    }

    @Test
    void testTechnicianRepairIfNeeded() {
        //Verifica el flujo real del técnico
        Technician tech = new Technician(1, "Brayan", 200.0);
        PowerPlant plant = new PowerPlant();
        plant.triggerFailure();
        tech.repairIfNeeded(plant);
        assertTrue(plant.isOperational());
    }
}
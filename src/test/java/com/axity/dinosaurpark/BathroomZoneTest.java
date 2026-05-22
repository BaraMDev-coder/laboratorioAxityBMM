package com.axity.dinosaurpark;

import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.zone.BathroomZone;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class BathroomZoneTest {

    @Test
    void testBathroomZoneName() {
        //Verifica que el nombre de la zona
        BathroomZone zone = new BathroomZone();
        assertEquals("BathroomZone", zone.getName());
    }

    @Test
    void testInitialOccupancy() {
        //Verifica que los baños empiecen con 0 ocupantes
        BathroomZone zone = new BathroomZone();
        assertEquals(0, zone.getCurrentOccupancy());
    }

    @Test
    void testHasCapacity() {
        //Verifica que los baños recién creados sí tienen capacidad disponible
        BathroomZone zone = new BathroomZone();
        assertTrue(zone.hasCapacity());
    }

    @Test
    void testTryEnterAddsOccupant() {
        //Verifica que cuando el turista entra al baño
        BathroomZone zone = new BathroomZone();
        Tourist tourist = new Tourist(1, "Brayan");
        zone.tryEnter(tourist, new Random(42));
        assertEquals(1, zone.getCurrentOccupancy());
    }

    @Test
    void testTryEnterRecordsVisit() {
        //Verifica cuando un turista entra al baño
        BathroomZone zone = new BathroomZone();
        Tourist tourist = new Tourist(1, "Brayan");
        zone.tryEnter(tourist, new Random(42));
        assertTrue(tourist.getVisitedZones().contains("BathroomZone"));
    }

    @Test
    void testTickReducesOccupancy() {
        //Verfica "el tiempo" en el baño del turista
        BathroomZone zone = new BathroomZone();
        Tourist tourist = new Tourist(1, "Brayan");
        zone.tryEnter(tourist, new Random(42));
        zone.tick();
        zone.tick();
        zone.tick();
        assertEquals(0, zone.getCurrentOccupancy());
    }

    @Test
    void testEnterAndExit() {
        //Verifica el flujo básico de entrada y salida
        BathroomZone zone = new BathroomZone();
        Tourist tourist = new Tourist(1, "Juan");
        zone.enter(tourist);
        assertEquals(1, zone.getCurrentOccupancy());
        zone.exit(tourist);
        assertEquals(0, zone.getCurrentOccupancy());
    }
}
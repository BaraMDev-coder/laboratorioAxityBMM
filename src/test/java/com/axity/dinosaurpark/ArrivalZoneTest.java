package com.axity.dinosaurpark;

import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.model.TouristStatus;
import com.axity.dinosaurpark.zone.ArrivalZone;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrivalZoneTest {

    @Test
    void testArrivalZoneName() {
        //Verifica que el nombre de la zona
        ArrivalZone zone = new ArrivalZone();
        assertEquals("ArrivalZone", zone.getName());
    }

    @Test
    void testInitialOccupancy() {
        //Verifica que cuando creas la zona
        ArrivalZone zone = new ArrivalZone();
        assertEquals(0, zone.getCurrentOccupancy());
    }

    @Test
    void testHasCapacity() {
        //Verifica que una zona recién creada
        ArrivalZone zone = new ArrivalZone();
        assertTrue(zone.hasCapacity());
    }

    @Test
    void testAddToQueue() {
        //Verifica que cuando agregas un turista a la cola
        ArrivalZone zone = new ArrivalZone();
        Tourist tourist = new Tourist(1, "Juan");
        zone.addToQueue(tourist);
        assertEquals(1, zone.getWaitingQueue().size());
    }

    @Test
    void testProcessBatch() {
        //Verifica que después de procesar el lote
        ArrivalZone zone = new ArrivalZone();
        Tourist tourist = new Tourist(1, "Juan");
        zone.addToQueue(tourist);
        zone.processBatch(1);
        assertEquals(TouristStatus.IN_PARK, tourist.getStatus());
    }

    @Test
    void testProcessBatchReducesQueue() {
        //Agrega a dos turistas
        ArrivalZone zone = new ArrivalZone();
        zone.addToQueue(new Tourist(1, "Brayan"));
        zone.addToQueue(new Tourist(2, "Fer"));
        zone.processBatch(1);
        assertEquals(1, zone.getWaitingQueue().size());
    }

    @Test
    void testEnterAndExit() {
        //Verifica el flujo completo
        ArrivalZone zone = new ArrivalZone();
        Tourist tourist = new Tourist(1, "Juan");
        zone.enter(tourist);
        assertEquals(1, zone.getCurrentOccupancy());
        zone.exit(tourist);
        assertEquals(0, zone.getCurrentOccupancy());
    }
}
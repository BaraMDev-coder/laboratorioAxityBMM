package com.axity.dinosaurpark;

import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.model.TouristStatus;
import com.axity.dinosaurpark.zone.CentralHub;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class CentralHubTest {

    @Test
    void testCentralHubName() {
        //Verifica que el nombre de la zona
        CentralHub hub = new CentralHub();
        assertEquals("CentralHub", hub.getName());
    }

    @Test
    void testInitialOccupancy() {
        //Verifica que cuando creas el hub empieza con 0 turistas adentro.
        CentralHub hub = new CentralHub();
        assertEquals(0, hub.getCurrentOccupancy());
    }

    @Test
    void testHasCapacity() {
        //Verifica que una zona recién creada sí tiene capacidad disponible
        CentralHub hub = new CentralHub();
        assertTrue(hub.hasCapacity());
    }

    @Test
    void testEnterAndExit() {
        //Verifica el flujo completo de entrada y salida del turista
        CentralHub hub = new CentralHub();
        Tourist tourist = new Tourist(1, "Brayan");
        hub.enter(tourist);
        assertEquals(1, hub.getCurrentOccupancy());
        hub.exit(tourist);
        assertEquals(0, hub.getCurrentOccupancy());
    }

    @Test
    void testVisitRecordsZone() {
        //Verifica el registro de CentralHub
        CentralHub hub = new CentralHub();
        Tourist tourist = new Tourist(1, "Brayan");
        tourist.setStatus(TouristStatus.IN_PARK);
        hub.visit(tourist, new Random(42));
        assertTrue(tourist.getVisitedZones().contains("CentralHub"));
    }

    @Test
    void testGetSouvenirPrice() {
        //Verifica que el precio del souvenir sea 15.0
        CentralHub hub = new CentralHub();
        assertEquals(15.0, hub.getSouvenirPrice());
    }
}
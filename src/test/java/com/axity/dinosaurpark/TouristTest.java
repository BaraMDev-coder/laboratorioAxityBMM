package com.axity.dinosaurpark;

import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.model.TouristStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TouristTest {

    @Test
    void testTouristInitialStatus() {
        Tourist tourist = new Tourist(1, "Brayan");
        assertEquals(TouristStatus.WAITING, tourist.getStatus());
    }

    @Test
    void testTouristSpend() {
        Tourist tourist = new Tourist(1, "Brayan");
        tourist.spend(25.0);
        assertEquals(25.0, tourist.getMoneySpent());
    }

    @Test
    void testTouristRecordVisit() {
        Tourist tourist = new Tourist(1, "Brayan");
        tourist.recordVisit("ArrivalZone");
        assertEquals(1, tourist.getVisitedZones().size());
        assertEquals("ArrivalZone", tourist.getVisitedZones().get(0));
    }

    @Test
    void testTouristSetStatus() {
        Tourist tourist = new Tourist(1, "Brayan");
        tourist.setStatus(TouristStatus.IN_PARK);
        assertEquals(TouristStatus.IN_PARK, tourist.getStatus());
    }
}
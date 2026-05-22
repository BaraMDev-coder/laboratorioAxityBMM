package com.axity.dinosaurpark;

import com.axity.dinosaurpark.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DinosaurTest {

    @Test
    void testCarnivoreDinosaurDiet() {
        CarnivoreDinosaur dino = new CarnivoreDinosaur(1, "Rex", "T-Rex");
        assertEquals("CARNIVORE", dino.getDiet());
    }

    @Test
    void testCarnivoreDangerLevel() {
        CarnivoreDinosaur dino = new CarnivoreDinosaur(1, "Rex", "T-Rex");
        assertEquals(0.9, dino.getDangerLevel());
    }

    @Test
    void testHerbivoreDinosaurDiet() {
        HerbivoreDinosaur dino = new HerbivoreDinosaur(2, "Toro", "Triceratops");
        assertEquals("HERBIVORE", dino.getDiet());
    }

    @Test
    void testHerbivoreDangerLevel() {
        HerbivoreDinosaur dino = new HerbivoreDinosaur(2, "Toro", "Triceratops");
        assertEquals(0.2, dino.getDangerLevel());
    }

    @Test
    void testDinosaurEscape() {
        CarnivoreDinosaur dino = new CarnivoreDinosaur(1, "Rex", "T-Rex");
        dino.escape();
        assertEquals(DinosaurStatus.ESCAPED, dino.getStatus());
    }

    @Test
    void testDinosaurReturnToEnclosure() {
        CarnivoreDinosaur dino = new CarnivoreDinosaur(1, "Rex", "T-Rex");
        dino.escape();
        dino.returnToEnclosure();
        assertEquals(DinosaurStatus.IN_ENCLOSURE, dino.getStatus());
    }
}
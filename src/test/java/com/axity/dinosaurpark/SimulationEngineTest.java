package com.axity.dinosaurpark;

import com.axity.dinosaurpark.simulation.SimulationEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SimulationEngineTest {

    @Test
    void shouldCreateSimulationEngine() {

        SimulationEngine engine = new SimulationEngine();

        assertNotNull(engine);
    }

    @Test
    void shouldRunSimulationWithoutExceptions() {

        SimulationEngine engine = new SimulationEngine();

        assertDoesNotThrow(engine::run);
    }
}
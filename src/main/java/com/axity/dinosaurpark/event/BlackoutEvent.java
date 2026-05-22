package com.axity.dinosaurpark.event;

import com.axity.dinosaurpark.persistence.EventRecord;
import com.axity.dinosaurpark.simulation.ParkState;

import java.time.LocalDateTime;
import java.util.Random;

public class BlackoutEvent implements SimulationEvent {

    private String description = "";

    @Override
    public String getName() {
        return "APAGON_MASIVO";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute(ParkState state, Random rng) {
        double costo = 2000.0;
        state.addExpense(costo);
        description = "Apagón!!! Costo de reparación: $" + costo;
    }

    @Override
    public EventRecord toRecord(long step) {
        return new EventRecord(step, getName(), description, "planta electrica", LocalDateTime.now());
    }
}
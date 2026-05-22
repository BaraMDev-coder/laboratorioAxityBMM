package com.axity.dinosaurpark.event;

import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.model.TouristStatus;
import com.axity.dinosaurpark.persistence.EventRecord;
import com.axity.dinosaurpark.simulation.ParkState;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class StormEvent implements SimulationEvent {

    private String description = "";

    @Override
    public String getName() {
        return "TORMENTA_TORRENCIAL";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute(ParkState state, Random rng) {
        // Se registra la salida de los turistas
        List<Tourist> activos = state.getTourists().stream()
                .filter(t -> t.getStatus() == TouristStatus.IN_PARK)
                .toList();

        for (Tourist t : activos) {
            t.recordVisit("Evacuación");
        }

        // Costo
        double costo = 500.0;
        state.addExpense(costo);
        description = "Tormenta!!! Evacuación de " + activos.size()
                + " turistas. Costo: $" + costo;
    }

    @Override
    public EventRecord toRecord(long step) {
        return new EventRecord(step, getName(), description, "todos los turistas", LocalDateTime.now());
    }
}
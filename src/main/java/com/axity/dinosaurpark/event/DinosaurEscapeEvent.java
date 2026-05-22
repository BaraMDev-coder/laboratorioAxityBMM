package com.axity.dinosaurpark.event;

import com.axity.dinosaurpark.model.Dinosaur;
import com.axity.dinosaurpark.model.DinosaurStatus;
import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.model.TouristStatus;
import com.axity.dinosaurpark.persistence.EventRecord;
import com.axity.dinosaurpark.simulation.ParkState;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class DinosaurEscapeEvent implements SimulationEvent {

    private String description = "";

    @Override
    public String getName() {
        return "ESCAPE_DINOSAURIO";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute(ParkState state, Random rng) {
        // Elige un dinosaurio al azar
        List<Dinosaur> enEnclosure = state.getDinosaurs().stream()
                .filter(d -> d.getStatus() == DinosaurStatus.IN_ENCLOSURE)
                .toList();

        if (enEnclosure.isEmpty()) return;

        Dinosaur dinosaur = enEnclosure.get(rng.nextInt(enEnclosure.size()));

        // Espacapa un dinosaurio
        dinosaur.escape();
        description = "Dinosaurio " + dinosaur.getName() + " escapó!";

        // Ataca a un turista al azar
        List<Tourist> activos = state.getTourists().stream()
                .filter(t -> t.getStatus() == TouristStatus.IN_PARK)
                .toList();

        if (!activos.isEmpty() && rng.nextDouble() < dinosaur.getDangerLevel()) {
            Tourist attacked = activos.get(rng.nextInt(activos.size()));
            attacked.setStatus(TouristStatus.ATTACKED);
            description += " Turista " + attacked.getName() + " fue atacado!";
        }
    }

    @Override
    public EventRecord toRecord(long step) {
        return new EventRecord(step, getName(), description, "dinosaurios", LocalDateTime.now());
    }
}
package com.axity.dinosaurpark.simulation;

import com.axity.dinosaurpark.event.DinosaurEscapeEvent;
import com.axity.dinosaurpark.event.BlackoutEvent;
import com.axity.dinosaurpark.event.StormEvent;
import com.axity.dinosaurpark.event.SimulationEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class EventScheduler {

    private final Map<Integer, SimulationEvent> scheduledEvents;

    public EventScheduler(long seed, int totalSteps) {
        scheduledEvents = new HashMap<>();
        Random rng = new Random(seed);

        // Se crean tres eventos
        SimulationEvent escape  = new DinosaurEscapeEvent();
        SimulationEvent blackout = new BlackoutEvent();
        SimulationEvent storm   = new StormEvent();

        // Asigna un step(un paso o tick simulado) aleatorio a cada evento
        scheduledEvents.put(rng.nextInt(totalSteps), escape);
        scheduledEvents.put(rng.nextInt(totalSteps), blackout);
        scheduledEvents.put(rng.nextInt(totalSteps), storm);
    }

    public Optional<SimulationEvent> checkForEvent(int step) {
        return Optional.ofNullable(scheduledEvents.get(step));
    }
}
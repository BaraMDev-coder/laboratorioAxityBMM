package com.axity.dinosaurpark.event;

import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.model.TouristStatus;

import java.util.List;

public class StormEvent implements SimulationEvent {

    private final List<Tourist> tourists;
    private String description;

    public StormEvent(List<Tourist> tourists) {
        this.tourists = tourists;
        this.description = "";
    }

    @Override
    public String getType() {
        return "STORM";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute() {
        int evacuated = 0;
        for (Tourist t : tourists) {
            if (t.getStatus() == TouristStatus.IN_PARK) {
                t.setStatus(TouristStatus.EXITED);
                evacuated++;
            }
        }
        description = "¡Tormenta!" + evacuated + " turistas evacuados.";
    }
}
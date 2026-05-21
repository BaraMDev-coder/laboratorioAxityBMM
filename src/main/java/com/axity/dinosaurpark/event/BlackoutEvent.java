package com.axity.dinosaurpark.event;

import com.axity.dinosaurpark.zone.PowerPlant;

public class BlackoutEvent implements ParkEvent {

    private final PowerPlant powerPlant;
    private String description;
    private double cost;

    public BlackoutEvent(PowerPlant powerPlant) {
        this.powerPlant = powerPlant;
        this.description = "";
        this.cost = 0.0;
    }

    @Override
    public String getType() {
        return "BLACKOUT";
    }

    @Override
    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public void execute() {
        cost = powerPlant.triggerFailure();
        description = "Costo de la reparación: " + cost;
        powerPlant.repair();
    }
}
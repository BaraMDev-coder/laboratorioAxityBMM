package com.axity.dinosaurpark.zone;

import com.axity.dinosaurpark.config.ParkConfig;
import com.axity.dinosaurpark.model.Tourist;

import java.util.Random;

public class PowerPlant implements ParkZone {

    private double energy;
    private final double consumptionPerStep;
    private final double failureProbability;
    private final double maintenanceCost;
    private final double repairCost;
    private boolean operational;

    public PowerPlant() {
        ParkConfig config = ParkConfig.getInstance();
        this.energy = config.getDouble("powerplant.initialEnergy", 100.0);
        this.consumptionPerStep = config.getDouble("powerplant.consumptionPerStep", 1.5);
        this.failureProbability = config.getDouble("powerplant.failureProbability", 0.05);
        this.maintenanceCost = config.getDouble("powerplant.maintenanceCost", 200.0);
        this.repairCost = config.getDouble("powerplant.repairCost", 500.0);
        this.operational = true;
    }

    public double tick(Random random) {
        energy -= consumptionPerStep;
        if (energy < 0) energy = 0;
        if (random.nextDouble() < failureProbability) {
            operational = false;
        }
        return maintenanceCost;
    }

    public double triggerFailure() {
        operational = false;
        return repairCost;
    }

    public void repair() {
        operational = true;
    }

    public boolean isOperational() {
        return operational;
    }
    public double getEnergy() {
        return energy;
    }
    public double getRepairCost() {
        return repairCost;
    }

    @Override
    public String getName() {
        return "PowerPlant";
    }

    @Override
    public boolean hasCapacity() {
        return true;
    }

    @Override
    public int getCurrentOccupancy() {
        return 0;
    }

    @Override
    public int getMaxCapacity() {
        return 0;
    }

    @Override
    public void enter(Tourist tourist) {

    }

    @Override
    public void exit(Tourist tourist) {

    }
}
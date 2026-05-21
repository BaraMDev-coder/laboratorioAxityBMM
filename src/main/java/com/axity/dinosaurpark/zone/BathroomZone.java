package com.axity.dinosaurpark.zone;

import com.axity.dinosaurpark.config.ParkConfig;
import com.axity.dinosaurpark.model.Tourist;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BathroomZone implements ParkZone {

    private final int maxCapacity;
    private final int useDurationSteps;
    private final double spaPrice;
    private final double spaPurchaseProbability;
    private final Map<Tourist, Integer> occupants;

    public BathroomZone() {
        ParkConfig config = ParkConfig.getInstance();
        this.maxCapacity = config.getInt("bathroom.maxCapacity", 10);
        this.useDurationSteps = config.getInt("bathroom.useDurationSteps", 3);
        this.spaPrice = config.getDouble("bathroom.spaPrice", 20.0);
        this.spaPurchaseProbability = config.getDouble("bathroom.spaPurchaseProbability", 0.2);
        this.occupants = new HashMap<>();
    }

    public double tryEnter(Tourist tourist, Random random) {
        double income = 0.0;
        if (hasCapacity()) {
            occupants.put(tourist, useDurationSteps);
            tourist.recordVisit("BathroomZone");
            if (random.nextDouble() < spaPurchaseProbability) {
                tourist.spend(spaPrice);
                income = spaPrice;
            }
        }
        return income;
    }

    public void tick() {
        occupants.replaceAll((t, steps) -> steps - 1);
        occupants.entrySet().removeIf(e -> e.getValue() <= 0);
    }

    @Override
    public String getName() {
        return "BathroomZone";
    }

    @Override
    public boolean hasCapacity() {
        return occupants.size() < maxCapacity;
    }

    @Override
    public int getCurrentOccupancy() {
        return occupants.size();
    }

    @Override
    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public void enter(Tourist tourist) {
        occupants.put(tourist, useDurationSteps);
    }

    @Override
    public void exit(Tourist tourist) {
        occupants.remove(tourist);
    }
}
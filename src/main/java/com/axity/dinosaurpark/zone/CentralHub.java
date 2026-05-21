package com.axity.dinosaurpark.zone;

import com.axity.dinosaurpark.config.ParkConfig;
import com.axity.dinosaurpark.model.Tourist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CentralHub implements ParkZone {

    private final int maxCapacity;
    private final double souvenirPrice;
    private final double souvenirPurchaseProbability;
    private final List<Tourist> inside;

    public CentralHub() {
        ParkConfig config = ParkConfig.getInstance();
        this.maxCapacity = config.getInt("arrival.maxCapacity", 30);
        this.souvenirPrice = config.getDouble("hub.souvenirPrice", 15.0);
        this.souvenirPurchaseProbability = config.getDouble("hub.souvenirPurchaseProbability", 0.4);
        this.inside = new ArrayList<>();
    }

    public double visit(Tourist tourist, Random random) {
        double income = 0.0;
        tourist.recordVisit("CentralHub");
        if (random.nextDouble() < souvenirPurchaseProbability) {
            tourist.spend(souvenirPrice);
            income = souvenirPrice;
        }
        return income;
    }

    //Metodos
    @Override
    public String getName() {
        return "CentralHub";
    }

    @Override
    public boolean hasCapacity() {
        return inside.size() < maxCapacity;
    }

    @Override
    public int getCurrentOccupancy() {
        return inside.size();
    }

    @Override
    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public void enter(Tourist tourist) {
        inside.add(tourist);
    }

    @Override
    public void exit(Tourist tourist) {
        inside.remove(tourist);
    }
    // Getter
    public double getSouvenirPrice() {
        return souvenirPrice;
    }
}

package com.axity.dinosaurpark.zone;

import com.axity.dinosaurpark.config.ParkConfig;
import com.axity.dinosaurpark.model.SatisfactionSurvey;
import com.axity.dinosaurpark.model.Tourist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObservationEnclosure implements ParkZone {

    private final String name;
    private final ExperienceType type;
    private final int maxVisitors;
    private final double entryFee;
    private final int minScore;
    private final int maxScore;
    private final List<Tourist> inside;

    public ObservationEnclosure(String name, ExperienceType type) {
        ParkConfig config = ParkConfig.getInstance();
        this.name = name;
        this.type = type;
        this.inside = new ArrayList<>();

        switch (type) {
            case BASIC:
                this.maxVisitors = config.getInt("enclosure.basic.maxVisitors", 20);
                this.entryFee = config.getDouble("enclosure.basic.entryFee", 10.0);
                this.minScore = 1; this.maxScore = 3;
                break;
            case PREMIUM:
                this.maxVisitors = config.getInt("enclosure.premium.maxVisitors", 12);
                this.entryFee = config.getDouble("enclosure.premium.entryFee", 30.0);
                this.minScore = 2; this.maxScore = 4;
                break;
            case VIP:
                this.maxVisitors = config.getInt("enclosure.vip.maxVisitors", 5);
                this.entryFee = config.getDouble("enclosure.vip.entryFee", 75.0);
                this.minScore = 3; this.maxScore = 5;
                break;
            default:
                this.maxVisitors = 20;
                this.entryFee = 10.0;
                this.minScore = 1; this.maxScore = 3;
        }
    }

    public double visit(Tourist tourist, Random random) {
        tourist.spend(entryFee);
        tourist.recordVisit(name);
        inside.add(tourist);
        return entryFee;
    }

    public SatisfactionSurvey conductSurvey(Tourist tourist, Random random) {
        int score = minScore + random.nextInt(maxScore - minScore + 1);
        return new SatisfactionSurvey(tourist.getId(), name, score);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasCapacity() {
        return inside.size() < maxVisitors;
    }

    @Override
    public int getCurrentOccupancy() {
        return inside.size();
    }

    @Override
    public int getMaxCapacity() {
        return maxVisitors;
    }

    @Override
    public void enter(Tourist tourist) {
        inside.add(tourist);
    }

    @Override
    public void exit(Tourist tourist) {
        inside.remove(tourist);
    }

    public double getEntryFee() {
        return entryFee;
    }
    public ExperienceType getType() {
        return type;
    }
}
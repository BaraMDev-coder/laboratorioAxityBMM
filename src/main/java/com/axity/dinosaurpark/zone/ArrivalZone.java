package com.axity.dinosaurpark.zone;

import com.axity.dinosaurpark.config.ParkConfig;
import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.model.TouristStatus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArrivalZone implements ParkZone {

    private final int maxCapacity;
    private final double ticketPrice;
    private final Queue<Tourist> waitingQueue;
    private final List<Tourist> inside;

    public ArrivalZone() {
        ParkConfig config = ParkConfig.getInstance();
        this.maxCapacity = config.getInt("arrival.maxCapacity", 30);
        this.ticketPrice = config.getDouble("arrival.ticketPrice", 25.0);
        this.waitingQueue = new LinkedList<>();
        this.inside = new ArrayList<>();
    }

    public void addToQueue(Tourist tourist) {
        waitingQueue.add(tourist);
    }

    public void processBatch(int batchSize) {
        int processed = 0;
        while (!waitingQueue.isEmpty() && processed < batchSize) {
            Tourist t = waitingQueue.poll();
            t.spend(ticketPrice);
            t.setStatus(TouristStatus.IN_PARK);
            t.recordVisit("ArrivalZone");
            inside.add(t);
            processed++;
        }
    }

    @Override
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public Queue<Tourist> getWaitingQueue() {
        return waitingQueue;
    }

    public List<Tourist> getInside() {
        return inside;
    }

    @Override
    public String getName() {
        return "ArrivalZone";
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
    public void enter(Tourist tourist) {
        inside.add(tourist);
    }

    @Override
    public void exit(Tourist tourist) {
        inside.remove(tourist);
    }
}
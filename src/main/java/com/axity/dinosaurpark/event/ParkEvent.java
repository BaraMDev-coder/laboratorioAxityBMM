package com.axity.dinosaurpark.event;

public interface ParkEvent {
    String getType();
    String getDescription();
    void execute();
}
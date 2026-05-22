package com.axity.dinosaurpark.simulation;

import com.axity.dinosaurpark.model.Dinosaur;
import com.axity.dinosaurpark.model.DinosaurStatus;
import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.model.TouristStatus;
import com.axity.dinosaurpark.model.Worker;
import com.axity.dinosaurpark.persistence.csvWriter;
import com.axity.dinosaurpark.zone.ArrivalZone;
import com.axity.dinosaurpark.zone.BathroomZone;
import com.axity.dinosaurpark.zone.CentralHub;
import com.axity.dinosaurpark.zone.ObservationEnclosure;
import com.axity.dinosaurpark.zone.PowerPlant;

import java.util.List;
import java.util.Random;

public class ParkState {

    // Estado del parque
    private final List<Tourist>             tourists;
    private final List<Dinosaur>            dinosaurs;
    private final List<Worker>              workers;

    // Zonas
    private final ArrivalZone              arrivalZone;
    private final CentralHub               centralHub;
    private final BathroomZone             bathroomZone;
    private final PowerPlant               powerPlant;
    private final List<ObservationEnclosure> enclosures;

    // Persistencia y aleatoriedad
    private final csvWriter  csvWriter;
    private final Random     rng;

    // Total de gastos
    private double totalRevenue  = 0.0;
    private double totalExpenses = 0.0;
    private int currentStep = 0;

    // Constructor
    public ParkState(List<Tourist> tourists,
                     List<Dinosaur> dinosaurs,
                     List<Worker> workers,
                     ArrivalZone arrivalZone,
                     CentralHub centralHub,
                     BathroomZone bathroomZone,
                     PowerPlant powerPlant,
                     List<ObservationEnclosure> enclosures,
                     csvWriter csvWriter,
                     Random rng) {
        this.tourists     = tourists;
        this.dinosaurs    = dinosaurs;
        this.workers      = workers;
        this.arrivalZone  = arrivalZone;
        this.centralHub   = centralHub;
        this.bathroomZone = bathroomZone;
        this.powerPlant   = powerPlant;
        this.enclosures   = enclosures;
        this.csvWriter    = csvWriter;
        this.rng          = rng;
    }

    // Métodos de utilidad
    public long countActiveTourists() {
        return tourists.stream()
                .filter(t -> t.getStatus() == TouristStatus.IN_PARK)
                .count();
    }

    public long countDinosaursInEnclosure() {
        return dinosaurs.stream()
                .filter(d -> d.getStatus() == DinosaurStatus.IN_ENCLOSURE)
                .count();
    }

    public void incrementStep() {
        currentStep++;
    }

    public void addRevenue(double amount) {
        totalRevenue += amount;
    }

    public void addExpense(double amount) {
        totalExpenses += amount;
    }

    // Getters
    public List<Tourist> getTourists() {
        return tourists;
    }
    public List<Dinosaur> getDinosaurs() {
        return dinosaurs;
    }
    public List<Worker> getWorkers() {
        return workers;
    }
    public ArrivalZone getArrivalZone() {
        return arrivalZone;
    }
    public CentralHub getCentralHub() {
        return centralHub;
    }
    public BathroomZone getBathroomZone(){
        return bathroomZone;
    }
    public PowerPlant getPowerPlant() {
        return powerPlant;
    }
    public List<ObservationEnclosure> getEnclosures() {
        return enclosures;
    }
    public csvWriter getCsvWriter() { return csvWriter;
    }
    public Random getRng() {
        return rng;
    }
    public double getTotalRevenue() {
        return totalRevenue;
    }
    public double getTotalExpenses() {
        return totalExpenses;
    }
    public int getCurrentStep() {
        return currentStep;
    }
}
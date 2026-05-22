package com.axity.dinosaurpark.model;

import com.axity.dinosaurpark.zone.PowerPlant;

public class Technician extends Worker {

    public Technician(int id, String name, double dailySalary) {
        super(id, name, dailySalary);
    }


    @Override
    public String getRole() {
        return "TECHNICIAN";
    }

    public void repairIfNeeded(PowerPlant plant) {  // Planta de energia
        if (!plant.isOperational()) {  // Pregunta si la planta esta funcionado
            plant.repair(); //  En caso de no funcionar se tiene que reparar
        }
    }
}

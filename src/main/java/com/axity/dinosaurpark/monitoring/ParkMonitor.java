package com.axity.dinosaurpark.monitoring;

import com.axity.dinosaurpark.simulation.ParkState;

public class ParkMonitor {

    public static void displaySnapshot(ParkState state) {
        System.out.println("========================================");
        System.out.println("PARQUE DE DINOSAURIOS");
        System.out.println("========================================");
        System.out.println("  STEP: " + state.getCurrentStep());
        System.out.println("========================================");
        System.out.println("  Turistas en el parque : " + state.countActiveTourists());
        System.out.println("  Dinosaurios encerrados: " + state.countDinosaursInEnclosure());
        System.out.println("  Energía de la planta  : " +
                String.format("%.1f%%", state.getPowerPlant().getEnergy()));
        System.out.println("  Ingresos acumulados   : $" +
                String.format("%.2f", state.getTotalRevenue()));
        System.out.println("  Gastos acumulados     : $" +
                String.format("%.2f", state.getTotalExpenses()));
        System.out.println("========================================");
    }
}
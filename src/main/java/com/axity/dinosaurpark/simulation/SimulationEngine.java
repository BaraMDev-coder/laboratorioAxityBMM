package com.axity.dinosaurpark.simulation;

import com.axity.dinosaurpark.config.ParkConfig;
import com.axity.dinosaurpark.model.*;
import com.axity.dinosaurpark.monitoring.ParkMonitor;
import com.axity.dinosaurpark.persistence.csvWriter;
import com.axity.dinosaurpark.zone.*;
import com.axity.dinosaurpark.zone.PowerPlant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationEngine {

    private final ParkState state;
    private final EventScheduler scheduler;
    private final int totalSteps;
    private final int batchSize;

    public SimulationEngine() {
        ParkConfig config = ParkConfig.getInstance();

        // Configuración
        totalSteps = config.getInt("simulation.totalSteps", 100);
        batchSize  = config.getInt("simulation.batchSize", 5);
        long seed = (long) config.getInt("simulation.seed", 42);

        Random rng = new Random(seed);

        // Persistencia
        csvWriter csv = new csvWriter();

        // Modelos
        List<Tourist>  tourists  = new ArrayList<>();
        List<Dinosaur> dinosaurs = createDinosaurs(config);
        List<Worker>   workers   = createWorkers(config);

        // Zonas
        ArrivalZone  arrivalZone  = new ArrivalZone();
        CentralHub   centralHub   = new CentralHub();
        BathroomZone bathroomZone = new BathroomZone();
        PowerPlant   powerPlant   = new PowerPlant();
        List<ObservationEnclosure> enclosures = createEnclosures(config);

        // Turistas
        int totalTourists = config.getInt("tourists", 50);
        for (int i = 1; i <= totalTourists; i++) {
            Tourist tourist = new Tourist(i, "Turista " + i);
            tourists.add(tourist);
            arrivalZone.addToQueue(tourist);
        }

        // Estado global
        state = new ParkState(tourists, dinosaurs, workers,
                arrivalZone, centralHub, bathroomZone,
                powerPlant, enclosures, csv, rng);

        // Scheduler
        scheduler = new EventScheduler(seed, totalSteps);
    }

    public void run() {
        for (int step = 0; step < totalSteps; step++) {
            state.incrementStep();

            // A. LLEGADAS
            state.getArrivalZone().processBatch(batchSize);

            // B. MOVIMIENTO DE TURISTAS
            List<Tourist> activos = state.getTourists().stream()
                    .filter(t -> t.getStatus() == TouristStatus.IN_PARK)
                    .toList();

            for (Tourist t : activos) {
                state.getCentralHub().visit(t, state.getRng());
                state.getBathroomZone().tryEnter(t, state.getRng());
                state.getEnclosures().get(t.getId() % state.getEnclosures().size())
                        .visit(t, state.getRng());
            }

            // TICKS DE ZONAS
            state.getBathroomZone().tick();
            state.getPowerPlant().tick(state.getRng());

            // EVENTO
            scheduler.checkForEvent(step)
                    .ifPresent(e -> e.execute(state, state.getRng()));

            // WORKERS
            for (Worker w : state.getWorkers()) {
                if (w instanceof Guard g) {
                    g.recaptureEscapedDinosaurs(state.getDinosaurs());
                } else if (w instanceof Technician t) {
                    t.repairIfNeeded(state.getPowerPlant());
                }
            }

            // Monitoreo
            ParkMonitor.displaySnapshot(state);
        }
    }

    private List<Dinosaur> createDinosaurs(ParkConfig config) {
        List<Dinosaur> list = new ArrayList<>();
        list.add(new CarnivoreDinosaur(1, "Rex", "T-Rex"));
        list.add(new HerbivoreDinosaur(2, "Toro", "Triceratops"));
        return list;
    }

    private List<Worker> createWorkers(ParkConfig config) {
        List<Worker> list = new ArrayList<>();
        list.add(new Guard(1, "Guard1", 300.0));
        list.add(new Technician(1, "Tech1", 400.0));
        return list;
    }

    private List<ObservationEnclosure> createEnclosures(ParkConfig config) {
        List<ObservationEnclosure> list = new ArrayList<>();
        list.add(new ObservationEnclosure("Encierro 1", ExperienceType.BASIC));
        list.add(new ObservationEnclosure("Encierro 2", ExperienceType.PREMIUM));
        list.add(new ObservationEnclosure("Encierro 3", ExperienceType.VIP));
        return list;
    }
}
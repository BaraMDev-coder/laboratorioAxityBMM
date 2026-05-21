package com.axity.dinosaurpark.event;

import com.axity.dinosaurpark.model.Dinosaur;
import com.axity.dinosaurpark.model.DinosaurStatus;
import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.model.TouristStatus;

import java.util.List;
import java.util.Random;

public class DinosaurEscapeEvent implements ParkEvent {

    private final Dinosaur dinosaur;
    private final List<Tourist> tourists;
    private final Random random;
    private String description;

    public DinosaurEscapeEvent(Dinosaur dinosaur, List<Tourist> tourists, Random random) {
        this.dinosaur = dinosaur;
        this.tourists = tourists;
        this.random = random;
        this.description = "";
    }

    @Override
    public String getType() { return "DINOSAUR_ESCAPE"; }

    @Override
    public String getDescription() { return description; }

    @Override
    public void execute() {
        dinosaur.escape();
        description = "Dinosaurio " + dinosaur.getName() + " escapó!";

        // Si es carnívoro puede atacar a un turista
        if (dinosaur.getDiet().equals("CARNIVORE") && !tourists.isEmpty()) {
            if (random.nextDouble() < dinosaur.getDangerLevel()) {
                Tourist attacked = tourists.get(random.nextInt(tourists.size()));
                attacked.setStatus(TouristStatus.ATTACKED);
                description += " Turista " + attacked.getName() + " fue atacado!";
            }
        }

        dinosaur.returnToEnclosure();
    }
}
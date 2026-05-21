package com.axity.dinosaurpark.persistence;

import com.axity.dinosaurpark.config.ParkConfig;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class csvWriter {

    private final String outputDirectory;

    public csvWriter() {
        this.outputDirectory = ParkConfig.getInstance()
                .getString("output.directory", "output");
        createDirectoryIfNotExists();
    }

    private void createDirectoryIfNotExists() {
        try {
            Files.createDirectories(Paths.get(outputDirectory));
        } catch (IOException e) {
            System.err.println("Error al crear directorio: " + e.getMessage());
        }
    }

    public void writeIncome(String category, double amount, String detail) {
        writeToFile("ingresos.csv", category + "," + amount + "," + detail);
    }

    public void writeExpense(String category, double amount, String detail) {
        writeToFile("gastos.csv", category + "," + amount + "," + detail);
    }

    public void writeEvent(String type, String description) {
        writeToFile("eventos.csv", type + "," + description);
    }

    private void writeToFile(String fileName, String content) {
        String filePath = outputDirectory + "/" + fileName;
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(content);
        } catch (IOException e) {
            System.err.println("Error al escribir en " + fileName + ": " + e.getMessage());
        }
    }
}
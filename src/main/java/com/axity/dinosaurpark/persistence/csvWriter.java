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
        initFiles();
    }

    private void createDirectoryIfNotExists() {
        try {
            Files.createDirectories(Paths.get(outputDirectory));
        } catch (IOException e) {
            System.err.println("Error al crear directorio: " + e.getMessage());
        }
    }

    private void initFiles() {
        writeHeader("ingresos.csv", "id,type,amount,touristId,zone,timestamp");
        writeHeader("gastos.csv", "id,type,amount,description,timestamp");
        writeHeader("eventos.csv", "step,eventName,description,affectedEntities,timestamp");
    }

    private void writeHeader(String fileName, String header) {
        String filePath = outputDirectory + "/" + fileName;
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
            writer.println(header);
        } catch (IOException e) {
            System.err.println("Error al escribir header en " + fileName + ": " + e.getMessage());
        }
    }

    public void appendRevenue(RevenueRecord r) {
        writeToFile("ingresos.csv", r.toCsvLine());
    }

    public void appendExpense(ExpenseRecord e) {
        writeToFile("gastos.csv", e.toCsvLine());
    }

    public void appendEvent(EventRecord ev) {
        writeToFile("eventos.csv", ev.toCsvLine());
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
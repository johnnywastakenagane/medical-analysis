package ui;

import domain.MedicalAnalysis;
import domain.Status;
import service.MedicalAnalysisService;

import java.util.List;
import java.util.Scanner;

public class UI {
    private final MedicalAnalysisService service;
    private final Scanner scanner = new Scanner(System.in);

    public UI(MedicalAnalysisService service) {
        this.service = service;
    }

    public void run() {
        while (true) {
            System.out.println("\n1. Add analysis\n2. Show all (sorted by date)\n3. Filter by status\n0. Exit");
            String option = scanner.nextLine().trim();
            try {
                switch (option) {
                    case "1" -> addAnalysis();
                    case "2" -> print(service.getAllSortedByDate());
                    case "3" -> print(service.filterByStatus(readStatus()));
                    case "0" -> { return; }
                    default -> System.out.println("Invalid option!");
                }
            } catch (IllegalArgumentException e) {        // also catches NumberFormatException
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addAnalysis() {
        System.out.print("Type (BMI / BP): ");
        String type = scanner.nextLine().trim().toUpperCase();
        System.out.print("Date (yyyy.mm.dd): ");
        String date = scanner.nextLine().trim();
        Status status = readStatus();

        if (type.equals("BMI")) {
            System.out.print("Value: ");
            double value = Double.parseDouble(scanner.nextLine().trim());
            service.addBMI(date, status, value);
        } else if (type.equals("BP")) {
            System.out.print("Systolic: ");
            int sys = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Diastolic: ");
            int dia = Integer.parseInt(scanner.nextLine().trim());
            service.addBP(date, status, sys, dia);
        } else {
            System.out.println("Unknown type!");
            return;
        }
        System.out.println("Added.");
    }

    private Status readStatus() {
        System.out.print("Status (done / in_progress): ");
        return Status.valueOf(scanner.nextLine().trim().toUpperCase());  // throws IllegalArgumentException if wrong
    }

    private void print(List<MedicalAnalysis> list) {
        if (list.isEmpty()) System.out.println("Nothing to show.");
        for (MedicalAnalysis a : list) System.out.println(a);   // toString() is polymorphic
    }
}

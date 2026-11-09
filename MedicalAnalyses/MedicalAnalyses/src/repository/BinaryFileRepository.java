package repository;

import domain.MedicalAnalysis;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Same repository, but it loads from a binary file at start
// and saves to the file after EVERY modification.
public class BinaryFileRepository<T extends MedicalAnalysis> extends MedicalAnalysisRepository<T> {
    private final String fileName;

    public BinaryFileRepository(String fileName) {
        this.fileName = fileName;
        loadFromFile();
    }

    @Override
    public void add(T item) {
        super.add(item);
        saveToFile();
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) return;                       // first run: nothing to load
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            items = (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load file: " + e.getMessage());
            items = new ArrayList<>();
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(items);                       // ArrayList is Serializable
        } catch (IOException e) {
            System.out.println("Could not save file: " + e.getMessage());
        }
    }
}

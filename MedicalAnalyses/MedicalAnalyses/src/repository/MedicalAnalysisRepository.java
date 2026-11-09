package repository;

import domain.MedicalAnalysis;

import java.util.ArrayList;
import java.util.List;

// GENERIC repository: T can be any subtype of MedicalAnalysis
public class MedicalAnalysisRepository<T extends MedicalAnalysis> {
    protected List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);   // return a copy, so nobody modifies our list from outside
    }
}

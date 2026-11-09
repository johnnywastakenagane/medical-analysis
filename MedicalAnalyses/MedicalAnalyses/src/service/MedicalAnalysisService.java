package service;

import domain.BMI;
import domain.BP;
import domain.MedicalAnalysis;
import domain.Status;
import repository.MedicalAnalysisRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class MedicalAnalysisService {
    private final MedicalAnalysisRepository<MedicalAnalysis> repo;

    public MedicalAnalysisService(MedicalAnalysisRepository<MedicalAnalysis> repo) {
        this.repo = repo;
    }

    // constructors of BMI / BP throw IllegalArgumentException if values are not positive
    public void addBMI(String date, Status status, double value) {
        repo.add(new BMI(date, status, value));
    }

    public void addBP(String date, Status status, int systolic, int diastolic) {
        repo.add(new BP(date, status, systolic, diastolic));
    }

    // Requirement 2: sorted ascending by date (uses Comparable from MedicalAnalysis)
    public List<MedicalAnalysis> getAllSortedByDate() {
        List<MedicalAnalysis> list = repo.getAll();
        Collections.sort(list);
        // Comparator alternative: list.sort(Comparator.comparing(MedicalAnalysis::getDate));
        return list;
    }

    // Requirement 4: generic filter that receives a Predicate
    public List<MedicalAnalysis> filter(Predicate<MedicalAnalysis> predicate) {
        List<MedicalAnalysis> result = new ArrayList<>();
        for (MedicalAnalysis a : repo.getAll())
            if (predicate.test(a))
                result.add(a);
        return result;
        // streams alternative: return repo.getAll().stream().filter(predicate).toList();
    }

    public List<MedicalAnalysis> filterByStatus(Status status) {
        return filter(a -> a.getStatus() == status);
    }

    public boolean isEmpty() {
        return repo.getAll().isEmpty();
    }
}

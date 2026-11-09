package service;

import domain.MedicalAnalysis;
import domain.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.MedicalAnalysisRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// JUnit 5. Uses the in-memory repo, so tests never touch the real binary file.
class MedicalAnalysisServiceTest {
    private MedicalAnalysisService service;

    @BeforeEach
    void setUp() {
        service = new MedicalAnalysisService(new MedicalAnalysisRepository<>());
        service.addBMI("2026.03.15", Status.DONE, 23.5);
        service.addBP("2026.01.10", Status.DONE, 120, 80);
        service.addBP("2026.11.01", Status.IN_PROGRESS, 135, 85);
    }

    @Test
    void filterByStatusDone() {
        List<MedicalAnalysis> result = service.filterByStatus(Status.DONE);
        assertEquals(2, result.size());
        for (MedicalAnalysis a : result)
            assertEquals(Status.DONE, a.getStatus());
    }

    @Test
    void filterByStatusInProgress() {
        List<MedicalAnalysis> result = service.filterByStatus(Status.IN_PROGRESS);
        assertEquals(1, result.size());
        assertEquals("2026.11.01", result.get(0).getDate());
    }

    @Test
    void filterWithCustomPredicate() {
        assertEquals(1, service.filter(a -> a.getDate().startsWith("2026.03")).size());
        assertTrue(service.filter(a -> false).isEmpty());
    }

    @Test
    void negativeValueThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.addBMI("2026.05.05", Status.DONE, -1));
    }
}

import domain.MedicalAnalysis;
import domain.Status;
import repository.BinaryFileRepository;
import repository.MedicalAnalysisRepository;
import service.MedicalAnalysisService;
import ui.UI;

public class Main {
    public static void main(String[] args) {
        MedicalAnalysisRepository<MedicalAnalysis> repo = new BinaryFileRepository<>("analyses.bin");
        MedicalAnalysisService service = new MedicalAnalysisService(repo);

        // at least 3 initial analyses (only on first run, when the file is still empty)
        if (service.isEmpty()) {
            service.addBMI("2026.03.15", Status.DONE, 23.5);
            service.addBP("2026.01.10", Status.DONE, 120, 80);
            service.addBP("2026.11.01", Status.IN_PROGRESS, 135, 85);
        }

        new UI(service).run();
    }
}

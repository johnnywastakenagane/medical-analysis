package domain;

public class BP extends MedicalAnalysis {
    private int systolic;
    private int diastolic;

    public BP(String date, Status status, int systolic, int diastolic) {
        super(date, status);
        if (systolic <= 0 || diastolic <= 0)
            throw new IllegalArgumentException("BP values must be positive!");
        this.systolic = systolic;
        this.diastolic = diastolic;
    }

    public int getSystolic() { return systolic; }
    public int getDiastolic() { return diastolic; }

    @Override
    public String toString() {
        return "BP{" + super.toString() + ", systolic=" + systolic + ", diastolic=" + diastolic + "}";
    }
}

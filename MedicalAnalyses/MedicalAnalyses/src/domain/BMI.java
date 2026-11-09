package domain;

public class BMI extends MedicalAnalysis {
    private double value;

    public BMI(String date, Status status, double value) {
        super(date, status);                 // super(...) must be the first line
        if (value <= 0)
            throw new IllegalArgumentException("BMI value must be positive!");
        this.value = value;
    }

    public double getValue() { return value; }

    @Override
    public String toString() {
        return "BMI{" + super.toString() + ", value=" + value + "}";
    }
}

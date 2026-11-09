package domain;

import java.io.Serializable;

// abstract base class: holds what ALL analyses have in common
// Serializable -> so it can be written to a binary file
// Comparable   -> so a list of analyses can be sorted by date
public abstract class MedicalAnalysis implements Serializable, Comparable<MedicalAnalysis> {
    private static final long serialVersionUID = 1L;

    private String date;     // format "yyyy.mm.dd" -> string comparison == date comparison
    private Status status;

    public MedicalAnalysis(String date, Status status) {
        this.date = date;
        this.status = status;
    }

    public String getDate() { return date; }
    public Status getStatus() { return status; }

    @Override
    public int compareTo(MedicalAnalysis other) {
        return this.date.compareTo(other.date);   // ascending by date
    }

    @Override
    public String toString() {
        return "date=" + date + ", status=" + status;
    }
}

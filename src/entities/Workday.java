package entities;

public class Workday {
    private String date;
    private double totalHoursWorked;

    public Workday(String date, double totalHoursWorked) {
        this.date = date;
        this.totalHoursWorked = totalHoursWorked;
    }

    public String getDate() {
        return date;
    }

    public double getTotalHoursWorked() {
        return totalHoursWorked;
    }

    public void setTotalHoursWorked(double time)
    {
        this.totalHoursWorked = time ;
    }
}

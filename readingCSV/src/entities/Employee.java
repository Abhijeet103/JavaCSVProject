package entities;
import java.util.*;
public class Employee {
    private String id;
    private String name;
    private String position ;
    private List<Workday> workdays;

    HashMap<String , Workday>  map = new HashMap<>();
    public Employee(String id , String name , String position ) {
        this.id = id;
        this.name  =  name ;
        this.position =  position;
        this.workdays = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getPosition()
    {
        return position;
    }

    public void addWorkDay(String date, double totalHoursWorked) {
        if(map.containsKey(date))
        {
            Workday work =  map.get(date);
            double time  =  work.getTotalHoursWorked() +totalHoursWorked;
            work.setTotalHoursWorked(time);
            map.put(date , work);

        }
        else
        {
           Workday work  =  new Workday(date, totalHoursWorked);
           workdays.add(work);
           map.put(date , work);

        }

    }

    public List<Workday> getWorkDays() {
        return workdays;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, position);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(position, employee.position);
    }

}

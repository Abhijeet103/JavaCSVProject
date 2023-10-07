import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import entities.*;

public class Main {
    private static double convertHoursToDecimal(String hours) {
        String[] parts = hours.split(":");
        int hrs = Integer.parseInt(parts[0]);
        int mins = Integer.parseInt(parts[1]);
        return hrs + (mins / 60.0);
    }

    public static long calculateDateDifference(String startDateString, String endDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);

        return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
    }
    public static void main(String[] args) throws IOException {
        String csvFile = "C:\\Users\\disco\\IdeaProjects\\readingCSV\\src\\Assignment_Timecard.xlsx - Sheet1.csv";

        HashMap<String , Employee> map =  new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        String line  = reader.readLine();
        while(line != null)
        {
            line  =  reader.readLine();

            if(line  ==  null)
            {
                break;
            }
            String data[] = line.split(",");
            String id =  data[0];
            String name = data[7];
            String pos =  data[1];
            String date =  data[2].split(" ")[0];
            String timeIn = data[2];
            String timeOut =  data[3];
            String duration =  data[4];
            String arr[] = duration.split(":");
            double workingHours = 0;
            if (arr.length == 2)
            {
                workingHours = convertHoursToDecimal(duration);
            }
            if(map.containsKey(id))
            {
                Employee emp =  map.get(id);
                emp.addWorkDay(date , workingHours);
                map.put(id ,  emp);
            }
            else {
                Employee emp =  new Employee(id , name , pos);
                emp.addWorkDay(date ,workingHours );
                map.put(id , emp);
            }
            //System.out.println(id +" " + timeIn + " " + duration +"" +name);

        }

        List<Employee> QuesA = new ArrayList<>();
        for(String i : map.keySet())
        {
            Employee emp =  map.get(i);
            List<Workday> workdays = emp.getWorkDays();
            System.out.println(emp.getId());
            boolean flag =  true;
            for(int j =0 ;j<workdays.size()-1 ;j++)
            {
                String day1 = workdays.get(j).getDate();
                String day2 = workdays.get(j+1).getDate();
                long diff = calculateDateDifference(day1 , day2);
                if(diff > 1)
                {
                    flag = false;
                    break;

                }
            }

            if(flag)
            {
                System.out.println(emp.getId());
                QuesA.add(emp);
            }

        }

        System.out.println(QuesA.size());









    }
}

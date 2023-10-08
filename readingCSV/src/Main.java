import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
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

    public static String convert12To24(String time12hr) {
        if (time12hr == null)
        {
            return null;
        }
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
            Date date = inputFormat.parse(time12hr);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Handle the exception as needed
        }
    }


    public static long calculateDateDifference(String startDateString, String endDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);

        return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
    }

    public static long manual_hours_diffrence(String date1, String date2) {
        // Extract hours, minutes, and AM/PM from time strings
        int hours1 = Integer.parseInt(date1.substring(11, 13));
        int minutes1 = Integer.parseInt(date1.substring(14, 16));
        String ampm1 = date1.substring(17, 19);

        int hours2 = Integer.parseInt(date2.substring(11, 13));
        int minutes2 = Integer.parseInt(date2.substring(14, 16));
        String ampm2 = date2.substring(17, 19);

        // Convert to 24-hour format
        if (ampm1.equalsIgnoreCase("PM") && hours1 != 12) {
            hours1 += 12;
        }
        if (ampm2.equalsIgnoreCase("PM") && hours2 != 12) {
            hours2 += 12;
        }

        long time1InMinutes = hours1 * 60 + minutes1;
        long time2InMinutes = hours2 * 60 + minutes2;

        long timeDifferenceInMinutes;
        if (time2InMinutes >= time1InMinutes) {
            timeDifferenceInMinutes = time2InMinutes - time1InMinutes;
        } else {
            timeDifferenceInMinutes = (24 * 60 - time1InMinutes) + time2InMinutes;
        }

        return timeDifferenceInMinutes / 60;
    }


    public static void main(String[] args) throws IOException {
        String csvFile = "src/Repository/Assignment_Timecard.xlsx - Sheet1.csv";

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
            String timeIn =  data[2] ;
            String timeOut = data[3];


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
                emp.addWorkDay(date , workingHours , timeIn , timeOut);
                map.put(id ,  emp);
            }
            else {
                Employee emp =  new Employee(id , name , pos);
                emp.addWorkDay(date ,workingHours , timeIn , timeOut );
                map.put(id , emp);
            }
            //System.out.println(id +" " + timeIn + " " + duration +"" +name);

        }

        List<Employee> QuesA = new ArrayList<>();
        for(String i : map.keySet())
        {
            Employee emp =  map.get(i);
            List<Workday> workdays = emp.getWorkDays();
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
                QuesA.add(emp);
            }

        }

        // finding employee whoes diffrence in sfits timing is more than 1 hr and less than 10 hrs

        List<Employee> QuesB =  new ArrayList<>();
        List<Employee> QuesC =  new ArrayList<>();
        for(String i : map.keySet())
        {
            Employee emp =  map.get(i);
            List<Shifts> shift =  emp.getShifts();
            for(int  j = 0 ;j<shift.size() ;j++)
            {

                if(shift.get(j).getTimeWorked()> 14)
                {
                    QuesC.add(emp);
                }

            }
        }

        //Sub SecQuesB
        for(String i : map.keySet())
        {
            Employee emp =  map.get(i);
            List<Shifts>  shifts =  emp.getShifts();


            for(int  j = 0 ;j<shifts.size()-1 ;j++)
            {
                String  date1 =  shifts.get(j).getEndTime();
                String  date2 =  shifts.get(j+1).getStartTime();



                // Get the difference in hours
                long hoursDifference = 0;
                if(date1.length() == 19 && date2.length() == 19) {

                    hoursDifference = manual_hours_diffrence(date1, date2);
                }

                if(hoursDifference < 10 && hoursDifference > 1)
                {
                    QuesB.add(emp);
                }

            }
        }


        System.out.println("Employees who worked for 7  consecutive days or more ");
        System.out.println();
        for(Employee emp : QuesA)
        {
            System.out.println(emp.getName() + " " + emp.getPosition());
        }

        System.out.println("Employee who worked for more than  14 in a single shift ");
        System.out.println();
        for(Employee emp : QuesC)
        {
            System.out.println(emp.getName() + " " + emp.getPosition());
        }

        System.out.println("Employee who have less than 10 hours of time between shifts but greater than 1 hour");
        System.out.println();
        for(Employee emp : QuesB)
        {
            System.out.println(emp.getName() + " " + emp.getPosition());
        }












    }
}

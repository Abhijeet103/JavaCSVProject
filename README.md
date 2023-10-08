# JavaCSVProject
# Employee Data Analyzer

This Java project reads data from a CSV file, processes it, and performs three main tasks:

1. **Programmatically Analyze Data**
   - Print in the console the names and positions of employees who meet the following criteria:
     a) Worked for 7 consecutive days.
     b) Have less than 10 hours of time between shifts but greater than 1 hour.
     c) Worked for more than 14 hours in a single shift.

2. **Code Overview**
   - The project includes code for parsing a CSV file, creating POJOs (Plain Old Java Objects), and implementing the analysis logic.
   - It adheres to clean code principles and includes code comments to explain the logic.

3. **Assumptions**
   - The input CSV file follows a specific format (e.g., columns for employee ID, name, position, date, time in, time out, duration).
   - The time format is in 12-hour format with AM/PM.
   - The data in the CSV file is consistent and free of errors.

4. **Output**
   - The console output of the analysis is saved in `output.txt`.

## Usage

1. **Clone the Repository**
   ```
   git clone https://github.com/your_username/employee-data-analyzer.git
   cd employee-data-analyzer
   ```

2. **Compile and Run**
   ```
   javac Main.java
   java Main
   ```

3. **View Output**
   - The results will be printed in the console and saved in `output.txt`.


```

## Dependencies
- Java 8 or higher

## Contributing
Contributions are welcome! Please open an issue to discuss potential changes or improvements.

## License
[MIT License](LICENSE)

---

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MonthlyReport {


    int year;
    int month;
    String path = "";

    public ArrayList<MonthReportRecord> monthReportRecords = new ArrayList<>();




    public void loadFileMonthsReport(){
        List<String> linesMonthRecord = readFileContents();
        for (int i = 1; i < linesMonthRecord.size(); i++) {
            String line = linesMonthRecord.get(i);
            String[] parts = line.split(",");
            String itemName = parts[0];
            Boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);
            MonthReportRecord month = new MonthReportRecord(itemName, isExpense, quantity, sumOfOne);
            monthReportRecords.add(month);
        }
        this.monthReportRecords = monthReportRecords;
    }


    List<String> readFileContents() {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }


    public MonthlyReport(int year, int month, String path) {
        this.year = year;
        this.month = month;
        this.path = path;
    }
}

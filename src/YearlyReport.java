import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class YearlyReport {

    public ArrayList<YearReportRecord> yearRecord = new ArrayList<>(); //Хэш лист для годового отчета
    int year;
    String path;





    public void loadFileYearReport(){

        List<String> linesYearRecord = readFileContents(path);
        for (int i = 1; i < linesYearRecord.size(); i++) {
            String line = linesYearRecord.get(i);
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            Boolean isExpense = Boolean.parseBoolean(parts[2]);
            YearReportRecord yearReport = new YearReportRecord(month, amount, isExpense);
            yearRecord.add(yearReport);
        }
        this.yearRecord = yearRecord;
    }

    //Чтение файла
    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }


    public YearlyReport(int year, String path) {
        this.year = year;
        this.path = path;
    }
}

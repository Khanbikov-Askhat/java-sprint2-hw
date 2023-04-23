import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MonthlyReport {


    int year;
    int month;

    ArrayList<MonthReportRecord> monthReportRecords = new ArrayList<>();

    public MonthlyReport(int year, int month, ArrayList<MonthReportRecord> monthReportRecords) {
        this.year = year;
        this.month = month;
        this.monthReportRecords = monthReportRecords;
    }






}

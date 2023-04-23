import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YearlyReport {

    ArrayList<YearReportRecord> yearRecord; //Список для годового отчета
    int year;



    public YearlyReport(int year, ArrayList<YearReportRecord> yearRecord) {
        this.year = year;
        this.yearRecord = yearRecord;
    }
}

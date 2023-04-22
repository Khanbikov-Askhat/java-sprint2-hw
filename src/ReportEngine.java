import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;


public class ReportEngine {

    HashMap<Integer, ArrayList<MonthReportRecord>> monthlyReports = new HashMap<>();// Хэш лист для месячных отчетов


    public ArrayList<YearReportRecord> yearRecord = new ArrayList<>(); //Хэш лист для годового отчета


    // Загрузка файла месячного отчета
    public void readMonthFile() {
        String path;
        for (int i = 1; i < 4; i++) {
            path = "resources/m.20210" + i + ".csv";
            loadFileMonthsReport(i, path);
        }
    }


    // Загрузка файла годового отчета
    public void readYearfile() {
        loadFileYearReport( "resources/y.2021.csv");
    }


    // Загрузка файла месячного отчета
    public void loadFileMonthsReport(Integer monthNumber, String path){
        ArrayList<MonthReportRecord> monthRecord = new ArrayList<>();
        List<String> linesMonthRecord = readFileContents(path);
        for (int i = 1; i < linesMonthRecord.size(); i++) {
            String line = linesMonthRecord.get(i);
            String[] parts = line.split(",");
            String itemName = parts[0];
            Boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);
            MonthReportRecord month = new MonthReportRecord(itemName, isExpense, quantity, sumOfOne);
            monthRecord.add(month);
            monthlyReports.put(monthNumber, monthRecord);
        }

    }


// Загрузка файла годового отчета
    public void loadFileYearReport( String path){

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
    }

    //Чтение файла
    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }


    //Информация о годовом отчете
    public void profitForMonth(int yearNumber){

        HashMap<Integer,  ArrayList<Integer>> yearMonths = new HashMap<>();
        HashMap<Integer, Integer> months = new HashMap<>();
        ArrayList<Integer> monthNumberForYear = new ArrayList<>();


        for (YearReportRecord year : yearRecord) {
            if(year.amount == 0) {
                System.out.println("Необходимо считать отчеты по месяцам");
            }
            if (!months.containsKey(year.month)){
                months.put(year.month, 0);
            }
            if (year.isExpense) {
                months.put(year.month, months.get(year.month) - year.amount);
            } else {
                months.put(year.month, months.get(year.month) + year.amount);
            }
            if (!monthNumberForYear.contains(year.month)) {
                monthNumberForYear.add(year.month);
            }
            yearMonths.put(yearNumber, monthNumberForYear);
        }

        for (Integer year: yearMonths.keySet()) {
            int month = 0;

            System.out.println("год: " + year);
            for (int i = 0; i < monthNumberForYear.size(); i++) {
                month = monthNumberForYear.get(i);
                System.out.println("Номер месяца: " + month);
                System.out.println("Прибыль: " + months.get(month));
                System.out.println();
            }
        }
    }


    //Расчет среднего дохода за год
    public void averageIncomeForYear(){
        int sum = 0;
        int monthNumber = 0;
        for (YearReportRecord year : yearRecord) {
            if (!year.isExpense){
                sum = sum + year.amount;
                monthNumber += 1;
            }
        }
        if (monthNumber == 3){
            System.out.println("Средний доход за все месяцы в году: " + (sum / monthNumber));
            System.out.println();
        } else{
            System.out.println();
        }
    }


    //Расчет средней траты за год
    public void averageExpenseForYear(){
        int exp = 0;
        int monthNumber = 0;
        for (YearReportRecord year : yearRecord) {
            if (year.isExpense){
                exp = exp + year.amount;
                monthNumber += 1;
            }
        }
        if (monthNumber == 3){
            System.out.println("Средний расход за все месяцы в году: " + (exp / monthNumber));
            System.out.println();
        } else{
            System.out.println("Для этого необходимо считать отчеты");
        }
    }

    // Расчет дохода за месяц
    public int incomeMonthForYear(Integer number) {
        int incomeForYear = 0;
        for (YearReportRecord year : yearRecord) {
            if (year.month != number) {
                continue;
            }
            if (!year.isExpense) {
                incomeForYear = year.amount;
            }
        }
        return incomeForYear;
    }


    //Метод для поиска дохода за один месяц
    public int expenseMonthForYear(Integer number) {
        int expenseForYear = 0;
        for (YearReportRecord year : yearRecord) {
            if (year.month != number) {
                continue;
            }
            if (year.isExpense) {
                expenseForYear = year.amount;
            }
        }
        return expenseForYear;
    }


    //Метод для получения суммы доходов за месяц
    public int incomeForMonth (Integer Number) {
        int incomeForMonth = 0;
        for (Integer month: monthlyReports.keySet()) {
            if (month != Number) {
                continue;
            }
            for (int i = 0; i < monthlyReports.get(month).size(); i++) {
                if (!monthlyReports.get(month).get(i).isExpense) {
                    incomeForMonth += (monthlyReports.get(month).get(i).quantity * monthlyReports.get(month).get(i).sumOfOne);
                }
            }
        }
        return incomeForMonth;
    }

    //Метод для получения суммы расходов за месяц
    public int expenseForMonth (Integer Number) {
        int expenseForMonth = 0;
        for (Integer month: monthlyReports.keySet()) {

            if (month != Number) {
                continue;
            }
            for (int i = 0; i < monthlyReports.get(month).size(); i++) {
                if (monthlyReports.get(month).get(i).isExpense) {
                    expenseForMonth += (monthlyReports.get(month).get(i).quantity * monthlyReports.get(month).get(i).sumOfOne);
                }
            }

        }
        return expenseForMonth;
    }



    int statistic = 0; /*Я знаю, что это не совсем правильно, но время 7 утра и писать отдельный метод сил нет совсем,
    в методе для сравнения месячных и годовых отчетов я вроде обошёл такой "Хардкод", не знаю насколько верно, но я старался
           */
    public void mostProfitableAndExpenseProduct(Integer Number) {

        int maxProfit = 0;
        int maxExpense = 0;
        String profit = "";
        String expense = "";
        for (Integer month: monthlyReports.keySet()){
            int sum = 0;
            if (!(month == Number)) {
                continue;
            }
            for (int i = 0; i < monthlyReports.get(month).size(); i++) {
                if (!monthlyReports.get(month).get(i).isExpense) {
                    sum = monthlyReports.get(month).get(i).quantity * monthlyReports.get(month).get(i).sumOfOne;
                    if (sum > maxProfit) {
                        maxProfit = sum;
                        profit = monthlyReports.get(month).get(i).itemName;
                    }
                } else {
                    sum = monthlyReports.get(month).get(i).quantity * monthlyReports.get(month).get(i).sumOfOne;
                    if (sum > maxExpense) {
                        maxExpense = sum;
                        expense = monthlyReports.get(month).get(i).itemName;
                    }
                }
            }
            if (Number == 1) {
                System.out.println("Январь");
                System.out.println("Самый прибыльный товар: " + maxProfit + " - " + profit);
                System.out.println("Самая большая трата: " + maxExpense + " - " + expense);
                System.out.println();
            } else if(Number == 2) {
                System.out.println("Февраль");
                System.out.println("Самый прибыльный товар: " + maxProfit + " - " + profit);
                System.out.println("Самая большая трата: " + maxExpense + " - " + expense);
                System.out.println();
            } else if (Number == 3) {
                System.out.println("Март");
                System.out.println("Самый прибыльный товар: " + maxProfit + " - " + profit);
                System.out.println("Самая большая трата: " + maxExpense + " - " + expense);
                System.out.println();
            }
        }
        if (maxProfit== 0  || maxExpense == 0) {
            statistic += 1;
        }
        if (statistic == 3) {
            System.out.println("Сначала необходимо считать отчеты по месяцам");
            statistic = 0;
        }
    }


    //Метод для сравнения доходов
    public boolean dataIncomeReconciliation(int monthIncomeData, int yearIncomeData) {
        boolean result = false;
        if (monthIncomeData == yearIncomeData) {
            result = true;
        }
        return result;
    }


    //Метод для сравнения расходов
    public boolean dataExpenseReconciliation(int monthExpenseData, int yearExpenseData) {
        boolean result = false;
        if (monthExpenseData == yearExpenseData) {
                result = true;
            }
        return result;
    }


    //Вызов сравнения месячных и годовых отчетов по расходам в меню
    public void dataExpenseReconciliationForMenu() {

        int d = 0;
        for (int j = 1; j <= 3; j++) {
            if (expenseForMonth(j) == 0 || expenseForMonth(j) == 0) {
                System.out.println("Для сверки расходов нужно считать отчёты");
                System.out.println();
                break;
            }
            if ((dataExpenseReconciliation(expenseForMonth(j), expenseMonthForYear(j)))) {
                d += 1;
            } else {
                if (j == 1) {
                    System.out.println("В данных по расходам за январь ошибка, возможно вы забыли прочитать отчет");
                } else if (j == 2) {
                    System.out.println("В данных по расходам за февраль ошибка, возможно вы забыли прочитать отчет");
                } else if (j == 3) {
                    System.out.println("В данных по расходам за март ошибка, возможно вы забыли прочитать отчет");
                }
            }
        }
        if (d == 3) {
            System.out.println("Операция завершена успешно, ошибок в расходах нет");
        }

    }


    //Вызов сравнения месячных и годовых отчетов по расходам в меню
    public void dataIncomeReconciliationForMenu() {

        int d = 0;
        for (int j = 1; j <= 3; j++) {
            if (incomeForMonth(j) == 0 || incomeMonthForYear(j) == 0) {
                System.out.println("Для сверки доходов нужно считать отчёты");
                System.out.println();
                break;
            }
            if ((dataIncomeReconciliation(incomeForMonth(j), incomeMonthForYear(j)))) {
                d += 1;
            } else {
                if (j == 1) {
                    System.out.println("В данных по доходам за январь ошибка, возможно вы забыли прочитать отчет");
                } else if (j == 2) {
                    System.out.println("В данных по доходам за февраль ошибка, возможно вы забыли прочитать отчет");
                } else if (j == 3) {
                    System.out.println("В данных по доходам за март ошибка, возможно вы забыли прочитать отчет");
                }
            }
        }
        if (d == 3) {
            System.out.println("Операция завершена успешно, ошибок в доходах нет");
        }

    }

}

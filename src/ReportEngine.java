import java.util.ArrayList;
import java.util.HashMap;

public class ReportEngine {


    // мапа с хранением отчетов
    HashMap<Integer, MonthlyReport> monthReports = new HashMap<>();


    // хранение годового отчета
    YearlyReport yearlyReport;


    //получение месячных отчетов
    public void monthReportReading() {
        String path;
        for (int i = 0; i <= 3; i++) {
            path = "resources/m.20210" + i + ".csv";
            int year = 2021;
            MonthlyReport currentMonth = new MonthlyReport(year, i, path);
            currentMonth.loadFileMonthsReport();
            monthReports.put(i, currentMonth);
        }
    }


    //Получение годового отчета
    public void yearReportReading() {
        String path = "resources/y.2021.csv";
        int year = 2021;
        yearlyReport = new YearlyReport(year, path);
        yearlyReport.loadFileYearReport();
    }


    //МЕСЯЧЫНЕ ОТЧЕТЫ


    // Вывод информации по месячным отчетам
    public void mostProfitableAndExpenseProduct(Integer number) {

        int maxProfit = 0;
        int maxExpense = 0;
        String profit = "";
        String expense = "";
        if(monthReports.get(number) == null || monthReports.isEmpty()) {
            System.out.println("Сначала необходимо считать отчеты по месяцам");
            return;
        }

        for (Integer month: monthReports.keySet()){
            int sum = 0;
            if (!(month == number)) {
                continue;
            }
            for (int i = 0; i < monthReports.get(month).monthReportRecords.size(); i++) {
                if (!monthReports.get(month).monthReportRecords.get(i).isExpense) {
                    sum = monthReports.get(month).monthReportRecords.get(i).quantity * monthReports.get(month).monthReportRecords.get(i).sumOfOne;
                    if (sum > maxProfit) {
                        maxProfit = sum;
                        profit = monthReports.get(month).monthReportRecords.get(i).itemName;
                    }
                } else {
                    sum = monthReports.get(month).monthReportRecords.get(i).quantity * monthReports.get(month).monthReportRecords.get(i).sumOfOne;
                    if (sum > maxExpense) {
                        maxExpense = sum;
                        expense = monthReports.get(month).monthReportRecords.get(i).itemName;
                    }
                }
            }
            if (number == 1) {
                System.out.println("Январь");
                System.out.println("Самый прибыльный товар: " + maxProfit + " - " + profit);
                System.out.println("Самая большая трата: " + maxExpense + " - " + expense);
                System.out.println();
            } else if(number == 2) {
                System.out.println("Февраль");
                System.out.println("Самый прибыльный товар: " + maxProfit + " - " + profit);
                System.out.println("Самая большая трата: " + maxExpense + " - " + expense);
                System.out.println();
            } else if (number == 3) {
                System.out.println("Март");
                System.out.println("Самый прибыльный товар: " + maxProfit + " - " + profit);
                System.out.println("Самая большая трата: " + maxExpense + " - " + expense);
                System.out.println();
            }
        }

    }



    //Метод для получения суммы доходов за месяц
    public int incomeForMonth (Integer Number) {
        int incomeForMonth = 0;
        for (Integer month: monthReports.keySet()) {
            if (month != Number) {
                continue;
            }
            for (int i = 0; i < monthReports.get(month).monthReportRecords.size(); i++) {
                if (!monthReports.get(month).monthReportRecords.get(i).isExpense) {
                    incomeForMonth += (monthReports.get(month).monthReportRecords.get(i).quantity * monthReports.get(month).monthReportRecords.get(i).sumOfOne);
                }
            }
        }
        return incomeForMonth;
    }


    //Метод для получения суммы расходов за месяц
    public int expenseForMonth (Integer Number) {
        int expenseForMonth = 0;
        for (Integer month: monthReports.keySet()) {

            if (month != Number) {
                continue;
            }
            for (int i = 0; i < monthReports.get(month).monthReportRecords.size(); i++) {
                if (monthReports.get(month).monthReportRecords.get(i).isExpense) {
                    expenseForMonth += (monthReports.get(month).monthReportRecords.get(i).quantity * monthReports.get(month).monthReportRecords.get(i).sumOfOne);
                }
            }

        }
        return expenseForMonth;
    }







    //ГОДОВЫЕ ОТЧЕТЫ
    //Информация о годовом отчете
    public void profitForMonth(int yearNumber){

        HashMap<Integer,  ArrayList<Integer>> yearMonths = new HashMap<>();
        HashMap<Integer, Integer> months = new HashMap<>();
        ArrayList<Integer> monthNumberForYear = new ArrayList<>();

        if(yearlyReport == null) {
            System.out.println("Сначала необходимо считать годовой отчет");
            return;
        }
        System.out.println("Прибыль по каждому месяцу:");
        for (int i = 0; i < yearlyReport.yearRecord.size(); i++) {
            if (!months.containsKey(yearlyReport.yearRecord.get(i).month)){
                months.put(yearlyReport.yearRecord.get(i).month, 0);
            }
            if (yearlyReport.yearRecord.get(i).isExpense) {
                months.put(yearlyReport.yearRecord.get(i).month, months.get(yearlyReport.yearRecord.get(i).month) - yearlyReport.yearRecord.get(i).amount);
            } else {
                months.put(yearlyReport.yearRecord.get(i).month, months.get(yearlyReport.yearRecord.get(i).month) + yearlyReport.yearRecord.get(i).amount);
            }
            if (!monthNumberForYear.contains(yearlyReport.yearRecord.get(i).month)) {
                monthNumberForYear.add(yearlyReport.yearRecord.get(i).month);
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

        if(yearlyReport == null) {
            return;
        }

        for (int i = 0; i < yearlyReport.yearRecord.size(); i++) {
            if (!yearlyReport.yearRecord.get(i).isExpense){
                sum = sum + yearlyReport.yearRecord.get(i).amount;
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

        if(yearlyReport == null) {
            return;
        }

        for (int i = 0; i < yearlyReport.yearRecord.size(); i++) {
            if (yearlyReport.yearRecord.get(i).isExpense){
                exp = exp + yearlyReport.yearRecord.get(i).amount;
                monthNumber += 1;
            }
        }
        if (monthNumber == 3){
            System.out.println("Средний расход за все месяцы в году: " + (exp / monthNumber));
            System.out.println();
        }
    }

    // Расчет дохода за месяц
    public int incomeMonthForYear(Integer number) {
        int incomeForYear = 0;
        for (int i = 0; i < yearlyReport.yearRecord.size(); i++) {
            if (yearlyReport.yearRecord.get(i).month != number) {
                continue;
            }
            if (!yearlyReport.yearRecord.get(i).isExpense) {
                incomeForYear = yearlyReport.yearRecord.get(i).amount;
            }
        }
        return incomeForYear;
    }


    //Метод для поиска дохода за один месяц
    public int expenseMonthForYear(Integer number) {
        int expenseForYear = 0;
        for (int i = 0; i < yearlyReport.yearRecord.size(); i++) {
            if (yearlyReport.yearRecord.get(i).month != number) {
                continue;
            }
            if (yearlyReport.yearRecord.get(i).isExpense) {
                expenseForYear = yearlyReport.yearRecord.get(i).amount;
            }
        }
        return expenseForYear;
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

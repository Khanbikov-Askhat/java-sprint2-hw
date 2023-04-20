import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class YearlyReport {

    public static ArrayList<Year> years = new ArrayList<>();


    public YearlyReport(Integer yearNumber, String path) {
        String lineContents  = readFIleContentsOrNull(path);
        String[] lines = lineContents.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            Boolean is_expense = Boolean.parseBoolean(parts[2]);

            Year year = new Year(month, amount, is_expense, yearNumber);
            years.add(year);

        }
    }


    public static void profitForMonth(){

        HashMap<Integer,  ArrayList<Integer>> yearMonths = new HashMap<>();
        HashMap<Integer, Integer> months = new HashMap<>();
        ArrayList<Integer> monthNumberForYear = new ArrayList<>();


        for (Year year : years) {




            if (!months.containsKey(year.month)){
                months.put(year.month, 0);
            }
            if (year.is_expense) {
                months.put(year.month, months.get(year.month) - year.amount);
            } else {
                months.put(year.month, months.get(year.month) + year.amount);
            }
            if (!monthNumberForYear.contains(year.month)) {
                monthNumberForYear.add(year.month);
            }
            yearMonths.put(year.yearNumber, monthNumberForYear);
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


    public static int averageIncomeForYear(){
        int sum = 0;
        int monthNumber = 0;
        HashMap<Integer, Integer> averageIncome = new HashMap<>();
        for (Year year : years) {
            if (!year.is_expense){
                sum = sum + year.amount;
                monthNumber += 1;
            }
        }
        return (sum / monthNumber);
    }


    public static int averageExpenseForYear(){
        int exp = 0;
        int monthNumber = 0;
        HashMap<Integer, Integer> averageIncome = new HashMap<>();
        for (Year year : years) {
            if (year.is_expense){
                exp = exp + year.amount;
                monthNumber += 1;
            }
        }
        return (exp / monthNumber);

    }


    //Метод для поиска дохода за один месяц
    public static int incomeMonthForYear(Integer number) {
        int incomeForYear = 0;
        for (Year year : years) {
            if (year.month != number) {
                continue;
            }
            if (!year.is_expense) {
                incomeForYear = year.amount;
            }
        }
        return incomeForYear;
    }


    //Метод для поиска дохода за один месяц
    public static int expenseMonthForYear(Integer number) {
        int expenseForYear = 0;
        for (Year year : years) {
            if (year.month != number) {
                continue;
            }
            if (year.is_expense) {
                expenseForYear = year.amount;
            }
        }
        return expenseForYear;
    }

    public String readFIleContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. " +
                    "Возможно файл не находится в нужной директории.");
            return null;
        }
    }




}

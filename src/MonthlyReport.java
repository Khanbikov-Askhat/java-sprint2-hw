import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    public MonthlyReport() {
    }

    public static ArrayList<Month> months = new ArrayList<>();

    public static void loadFile(Integer monthNumber, String path){
        String content = readFIleContentsOrNull(path);
        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            String item_name = parts[0];
            Boolean is_expense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sum_of_one = Integer.parseInt(parts[3]);
            Month month = new Month(item_name, is_expense, quantity, sum_of_one, monthNumber);
            months.add(month);
        }
    }


    // Метод для вывода информации о месячных отчетах
    public static void mostProfitableAndExpenseProduct(Integer Number) {
        int maxProfit = 0;
        int maxExpense = 0;
        String profit = "";
        String expense = "";
        for (Month month: months){
            int sum = 0;
            if (!(month.monthNumber == Number)) {
                continue;
            }
            if (!month.is_expense) {
                sum = month.quantity * month.sum_of_one;
                if (sum > maxProfit) {
                    maxProfit = sum;
                    profit = month.item_name;
                } else {
                    sum = month.quantity * month.sum_of_one;
                    if (sum > maxExpense) {
                        maxExpense = sum;
                        expense = month.item_name;
                    }
                }
            }


        }
        if (Number == 1) {
            System.out.println("Январь");
            System.out.println("Самый прибыльный товар: " + maxProfit + " - " + profit);
            System.out.println("Самая больная трата: " + maxExpense + " - " + expense);
            System.out.println();
        } else if(Number == 2) {
            System.out.println("Февраль");
            System.out.println("Самый прибыльный товар: " + maxProfit + " - " + profit);
            System.out.println("Самая больная трата: " + maxExpense + " - " + expense);
            System.out.println();
        } else if (Number == 3) {
            System.out.println("Март");
            System.out.println("Самый прибыльный товар: " + maxProfit + " - " + profit);
            System.out.println("Самая больная трата: " + maxExpense + " - " + expense);
        }
    }


    //Метод для получения суммы доходов за месяц
    public static int incomeForMonth (Integer Number) {
        int incomeForMonth = 0;
        for (Month month : months) {
            int sum = 0;
            if (month.monthNumber != Number) {
                continue;
            }
            if (!month.is_expense) {
                incomeForMonth += (month.quantity * month.sum_of_one);
            }
        }
        return incomeForMonth;
    }

        //Метод для получения суммы расходов за месяц
        public static int expenseForMonth (Integer Number) {
            int expenseForMonth = 0;
            for (Month month : months) {
                int sum = 0;
                if (month.monthNumber != Number) {
                    continue;
                }
                if (month.is_expense) {
                    expenseForMonth += (month.quantity * month.sum_of_one);
                }
            }
            return expenseForMonth;
        }


    //Метод для считывания данных из файла
    public static String readFIleContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. " +
                    "Возможно файл не находится в нужной директории.");
            return null;
        }
    }





}

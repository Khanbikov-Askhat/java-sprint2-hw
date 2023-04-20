import java.util.Scanner;




public class Main {

    public static void main(String[] args) {


        while (true) {
            printMenu();
            Scanner scanner = new Scanner(System.in);


            int i = scanner.nextInt();
            if (i == 1) {
                String path = "";
                MonthlyReport monthlyReport = new MonthlyReport();
                for (int k = 1; k <= 3; k++) {
                    path = "resources/m.20210" + k + ".csv";
                    monthlyReport.loadFile(k, path);
                }
                System.out.println("Месячные отчеты считаны");
                System.out.println();
            } else if (i == 2) {
                YearlyReport yearlyReport = new YearlyReport(2021, "resources/y.2021.csv");
                System.out.println("Месячные отчеты считаны");
                System.out.println();
            } else if (i == 3) {
                for (int j = 1; j <= 3; j ++) {
                    dataIncomeReconciliation(MonthlyReport.incomeForMonth(j), YearlyReport.incomeMonthForYear(j), j);
                    System.out.println();
                    dataExpenseReconciliation(MonthlyReport.expenseForMonth(j), YearlyReport.expenseMonthForYear(j), j);
                    System.out.println();
                }

            } else if (i == 4) {
                System.out.println("Информация по месечным отчетам:" );
                MonthlyReport.mostProfitableAndExpenseProduct(1);
                MonthlyReport.mostProfitableAndExpenseProduct(2);
                MonthlyReport.mostProfitableAndExpenseProduct(3);

            } else if (i == 5) {
                //System.out.println("Рассматриваемый год: " + );
                System.out.println("Прибыль по каждому месяцу:");
                YearlyReport.profitForMonth();
                System.out.println("Средний расход за все месяцы в году: " + YearlyReport.averageExpenseForYear());
                System.out.println();
                System.out.println("Средний доход за все месяцы в году: " + YearlyReport.averageIncomeForYear());
                System.out.println();
            } else if (i == 6) {
                System.out.println("Пока!");
                scanner.close();
            } else {
                System.out.println("Такой команды нет");
            }
        }// Поехали!
    }



    static void printMenu() {
        System.out.println("1 - Считать все месячные отчёты.");
        System.out.println("2 - Считать годовой отчёт.");
        System.out.println("3 - Сверить отчёты.");
        System.out.println("4 - Вывести информацию о всех месячных отчётах.");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - выйти из приложения.");
    }



    //Метод для сравнения доходов
    static void dataIncomeReconciliation(int monthData, int yearData, int month) {
        if (monthData == yearData) {
            if (month == 1) {
                System.out.println("Данные по доходам за январь верны");
            } else if (month == 2) {
                System.out.println("Данные по доходам за февраль верны");
            } else if (month == 3) {
                System.out.println("Данные по доходам за март верны");
            }
        } else {
            if (month == 1) {
                System.out.println("В данных по доходам за январь ошибка");
            } else if (month == 2) {
                System.out.println("В данных по доходам за февраль ошибка");
            } else if (month == 3) {
                System.out.println("В данных по доходам за март ошибка");
            }
        }
    }


    //Метод для сравнения расходов
    static void dataExpenseReconciliation(int monthData, int yearData, int month) {
        if (monthData == yearData) {
            if (month == 1) {
                System.out.println("Данные по расходам за январь верны");
            } else if (month == 2) {
                System.out.println("Данные по расходам за февраль верны");
            } else if (month == 3) {
                System.out.println("Данные по расходам за март верны");
            }
        } else {
            if (month == 1) {
                System.out.println("В данных по расходам за январь ошибка");
            } else if (month == 2) {
                System.out.println("В данных по расходам за февраль ошибка");
            } else if (month == 3) {
                System.out.println("В данных по расходам за март ошибка");
            }
        }
    }

}


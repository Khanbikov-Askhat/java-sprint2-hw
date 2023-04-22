import java.util.Scanner;




public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportEngine reportEngine = new ReportEngine();

        while (true) {
            printMenu();

            int i = scanner.nextInt();
            if (i == 1) {
                reportEngine.readMonthFile();
                System.out.println("Месячные отчеты считаны");
                System.out.println();
            } else if (i == 2) {
                reportEngine.readYearfile();
                System.out.println("Годовой отчет считан");
                System.out.println();
            } else if (i == 3) {
                reportEngine.dataIncomeReconciliationForMenu();
                System.out.println();
                reportEngine.dataExpenseReconciliationForMenu();
                System.out.println();
            } else if (i == 4) {
                System.out.println("Информация по месечным отчетам:");
                for (int a = 1; a <= 3; a++) {
                    reportEngine.mostProfitableAndExpenseProduct(a);
                }
            } else if (i == 5) {
                System.out.println("Прибыль по каждому месяцу:");
                reportEngine.profitForMonth(2021);
                reportEngine.averageExpenseForYear();
                reportEngine.averageIncomeForYear();
            } else if (i == 6) {
            System.out.println("Пока!");
            scanner.close();
            } else {
            System.out.println("Такой команды нет");
            }
        }
    }


    static void printMenu() {
        System.out.println("1 - Считать все месячные отчёты.");
        System.out.println("2 - Считать годовой отчёт.");
        System.out.println("3 - Сверить отчёты.");
        System.out.println("4 - Вывести информацию о всех месячных отчётах.");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - выйти из приложения.");
    }


}

/*Я честно очень старался писать верно и по правкам, надеюсь сделал всё правильно, есть момент "хардкода" наверное (222 строка в ReportEngine и с 278 начиная),
потому что я понятия не имею как обойти дублирование данных, сколько не пытался - не выходило, сделал как мог:)
Спасибо за ревью!
 */
import java.util.Scanner;




public class Main {

    public static void main(String[] args) {
        ReportEngine reportEngine = new ReportEngine();

        while (true) {
            printMenu();
            Scanner scanner = new Scanner(System.in);


            int i = scanner.nextInt();
            if (i == 1) {
                reportEngine.monthReportReading();
                System.out.println("Месячные отчеты считаны");
                System.out.println();
            } else if (i == 2) {
                reportEngine.yearReportReading();
                System.out.println("Годовые отчеты считаны");
                System.out.println();
            } else if (i == 3) {
                reportEngine.dataIncomeReconciliationForMenu();
                System.out.println();
                reportEngine.dataExpenseReconciliationForMenu();
                System.out.println();

            } else if (i == 4) {
                System.out.println("Информация по месечным отчетам:" );
                for (int a = 1; a <= 3; a++) {
                    reportEngine.mostProfitableAndExpenseProduct(a);
                }

            } else if (i == 5) {
                //System.out.println("Рассматриваемый год: " + );
                //System.out.println("Прибыль по каждому месяцу:");
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
        System.out.println("6 - Выйти из приложения.");
    }




}
//Надеюсь теперь я понял всё верноXD я сделал monthlyReport и YearlyReport - классами, которые отвечают только за хранение, было тяжко вснутуть monthlyReport в хешмапу в ReportEngine, я не очень понимаю как сделать это правильно, сделал так как заработало
//При нажатии в меню 1, программа пишет "Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.", хотя отчеты она считывает и записывает и даже верно
//Убрал максимум логики из Main'а
//Плюс убрал тот инт на ранее 222 строке для проверки загруженности отчетов и проверяю на их присутствие через monthReport == null, но из-за этого у меня появляется 3 записи Сначала необходимо считать отчеты по месяцам, потому что метод вызывается 3 раза
//Но зато я плюс-минус разобрался как вообще работать с этими приколами
import java.util.Scanner;

class Main {
    static int READ_MONTH_REPORTS = 1;
    static int READ_YEAR_REPORT = 2;
    static int CHECK_REPORTS = 3;
    static int SHOW_MONTH_REPORTS = 4;
    static int SHOW_YEAR_REPORT = 5;
    static int EXIT = 0;

    public static void main(String[] args) throws Error {
        Scanner scanner = new Scanner(System.in);
        ReadFile readFile01 = new ReadFile("resources/m.202101.csv");
        ReadFile readFile02 = new ReadFile("resources/m.202102.csv");
        ReadFile readFile03 = new ReadFile("resources/m.202103.csv");
        ReadFile readFile2021 = new ReadFile("resources/y.2021.csv");

        MonthlyReport monthlyReport01 = new MonthlyReport(readFile01);
        MonthlyReport monthlyReport02 = new MonthlyReport(readFile02);
        MonthlyReport monthlyReport03 = new MonthlyReport(readFile03);
        YearlyReport yearReport2021 = new YearlyReport(readFile2021);

        ChechReps chechReps = new ChechReps();

        while (true) {
            printMenu();
            int command = -1;
            try {
                command = Integer.parseInt(scanner.nextLine());
                if (command > 5 || command < 0) {
                    throw new Error();
                }
            }
            catch (NumberFormatException nfe) {
                System.out.println("Данный формат команды не поддерживается. Введите число от 0 до 5.");
            }
            catch (Error e) {
                System.out.println("Данная команда не поддерживается. Введите число от 0 до 5.");
            }

            if (command == READ_MONTH_REPORTS) {
                monthlyReport01.monthDataArrayList = monthlyReport01.parseMonthData();
                monthlyReport02.monthDataArrayList = monthlyReport02.parseMonthData();
                monthlyReport03.monthDataArrayList = monthlyReport03.parseMonthData();
                System.out.println("Операция считывания месячных отчетов завершена.");
            } else if (command == READ_YEAR_REPORT) {
                yearReport2021.yearDataArrayList = yearReport2021.parseYearData();
                System.out.println("Операция считывания годового отчета завершена.");
            } else if (command == CHECK_REPORTS) {
                if (!monthlyReport01.isRead || !monthlyReport02.isRead || !monthlyReport03.isRead ||
                    !yearReport2021.isRead) {
                    System.out.println("Операция не может быть выполнена. Сначала необходимо считать файлы с годовым и месячными отчетами.");
                } else if (chechReps.checkReps(monthlyReport01, yearReport2021, "01") *
                           chechReps.checkReps(monthlyReport02, yearReport2021, "02") *
                           chechReps.checkReps(monthlyReport03, yearReport2021, "03") == 1) {
                    System.out.println("Операция сверки завершилась успешно.");
                }
            } else if (command == SHOW_MONTH_REPORTS) {
                if (!monthlyReport01.isRead || !monthlyReport02.isRead || !monthlyReport03.isRead) {
                    System.out.println("Операция не может быть выполнена. Сначала необходимо считать файлы с месячными отчетами.");
                } else {
                    monthlyReport01.showMonthRep("01");
                    monthlyReport02.showMonthRep("02");
                    monthlyReport03.showMonthRep("03");
                }
            } else if (command == SHOW_YEAR_REPORT) {
                if (!yearReport2021.isRead) {
                    System.out.println("Операция не может быть выполнена. Сначала необходимо считать файл с годовым отчетом.");
                } else {
                    yearReport2021.showYearRep("2021");
                }
            } else if (command == EXIT) {
                System.out.println("Выход");
                System.exit(0);
            }
        }
    }

    static void printMenu() {
        System.out.println("\nВыберите действие: \n" +
                "1 - Считать все месячные отчёты \n" +
                "2 - Считать годовой отчёт \n" +
                "3 - Сверить отчёты\n" +
                "4 - Вывести информацию о всех месячных отчётах\n" +
                "5 - Вывести информацию о годовом отчёте\n" +
                "0 - Выход \n");
    }
}


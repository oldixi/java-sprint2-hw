import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    static int READ_MONTH_REPORTS = 1;
    static int READ_YEAR_REPORT = 2;
    static int CHECK_REPORTS = 3;
    static int SHOW_MONTH_REPORTS = 4;
    static int SHOW_YEAR_REPORT = 5;
    static int EXIT = 0;
    static String DIRECTORY = "resources";

    public static void main(String[] args) throws Error {
        Scanner scanner = new Scanner(System.in);
        boolean isReadMonthlyReports = false;
        boolean isReadYearlyReports = false;

        ArrayList<MonthlyReport> listOfMonthlyReport = new ArrayList<>();
        ArrayList<YearlyReport> listOfYearlyReport = new ArrayList<>();
        ChechReps chechReps = new ChechReps();

        ReadFile readFileFolder = new ReadFile();
        File folder = new File(DIRECTORY);
        List<String> listOfFilesInFolder = new ArrayList<>();
        listOfFilesInFolder = readFileFolder.listFilesForFolder(folder);

        beforeReadReports(listOfFilesInFolder, listOfMonthlyReport, listOfYearlyReport);

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
                for (MonthlyReport monthlyReportFromArray : listOfMonthlyReport) {
                    monthlyReportFromArray.monthDataArrayList = monthlyReportFromArray.parseMonthData();
                }
                isReadMonthlyReports = true;
                System.out.println("Операция считывания месячных отчетов завершена.");
            } else if (command == READ_YEAR_REPORT) {
                for (YearlyReport yearlyReportFromArray : listOfYearlyReport) {
                    yearlyReportFromArray.yearDataArrayList = yearlyReportFromArray.parseYearData();
                }
                isReadYearlyReports = true;
                System.out.println("Операция считывания годовых отчетов завершена.");
            } else if (command == CHECK_REPORTS) {
                if (!isReadMonthlyReports || !isReadYearlyReports) {
                    System.out.println("Операция не может быть выполнена. Сначала необходимо считать файлы с годовыми и месячными отчетами.");
                }
                chechReps.checkReps(listOfMonthlyReport, listOfYearlyReport);
            } else if (command == SHOW_MONTH_REPORTS) {
                if (!isReadMonthlyReports) {
                    System.out.println("Операция не может быть выполнена. Сначала необходимо считать файлы с месячными отчетами.");
                } else {
                    for (MonthlyReport monthlyReportFromArray : listOfMonthlyReport) {
                        monthlyReportFromArray.showMonthRep(monthlyReportFromArray.month);
                    }
                }
            } else if (command == SHOW_YEAR_REPORT) {
                if (!isReadYearlyReports) {
                    System.out.println("Операция не может быть выполнена. Сначала необходимо считать файл с годовым отчетом.");
                } else {
                    for (YearlyReport yearlyReportFromArray : listOfYearlyReport) {
                        yearlyReportFromArray.showYearRep(yearlyReportFromArray.year);
                    }
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

    static void beforeReadReports(List<String> listOfFilesInFolder, ArrayList<MonthlyReport> listOfMonthlyReport, ArrayList<YearlyReport> listOfYearlyReport) {
        for (String fileName : listOfFilesInFolder) {
            if (fileName.substring(0, 1).toLowerCase().equals("m")) {
                ReadFile readFile = new ReadFile(DIRECTORY + "/" + fileName);
                MonthlyReport monthlyReport = new MonthlyReport(readFile);
                listOfMonthlyReport.add(monthlyReport);
            } else if (fileName.substring(0, 1).toLowerCase().equals("y")) {
                ReadFile readFile = new ReadFile(DIRECTORY + "/" + fileName);
                YearlyReport yearlyReport = new YearlyReport(readFile);
                listOfYearlyReport.add(yearlyReport);
            }
        }
    }

}


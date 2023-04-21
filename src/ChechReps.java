import java.util.ArrayList;

public class ChechReps {

    ChechReps() {
    }

    public int checkReps(MonthlyReport monthlyReport, YearlyReport yearlyReport, String month) {
        Integer monthNum;
        if (yearlyReport.sumOfMonth(true, month) != monthlyReport.sumOfMonth(true) ||
                yearlyReport.sumOfMonth(false, month) != monthlyReport.sumOfMonth(false)) {
                monthNum = Integer.parseInt(month);
                System.out.println("Обнаружено несоответствие при сверке данных за " + UsefulTools.getMonthName(monthNum) + ".");
                return 0;
        }
        return 1;
    }

    public void checkReps(ArrayList<MonthlyReport> listOfMonthlyReport, ArrayList<YearlyReport> listOfYearlyReport) {
        Integer monthNum;
        boolean isSuccess = true;

        for (YearlyReport yearlyReportFromArray : listOfYearlyReport) {
            for (MonthlyReport monthlyReportFromArray : listOfMonthlyReport) {
                if (yearlyReportFromArray.year.equals(monthlyReportFromArray.year)) {
                    if (monthlyReportFromArray.sumOfMonth(true) !=
                        yearlyReportFromArray.sumOfMonth(true, monthlyReportFromArray.month) ||
                        monthlyReportFromArray.sumOfMonth(false) !=
                        yearlyReportFromArray.sumOfMonth(false, monthlyReportFromArray.month)) {

                        monthNum = Integer.parseInt(monthlyReportFromArray.month);
                        System.out.println("Обнаружено несоответствие при сверке данных за " + UsefulTools.getMonthName(monthNum) +
                                " " + yearlyReportFromArray.year + " года.");
                        isSuccess = false;
                    }
                }
            }
        }
        if (isSuccess) {
            System.out.println("Операция сверки завершилась успешно.");
        }
    }

}

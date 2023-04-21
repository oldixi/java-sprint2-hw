import java.util.ArrayList;

public class ChechReps {

    public void checkReps(ArrayList<MonthlyReport> listOfMonthlyReport, ArrayList<YearlyReport> listOfYearlyReport) {
        int monthNum;
        boolean isSuccess = true;

        for (YearlyReport yearlyReportFromArray : listOfYearlyReport) {
            for (MonthlyReport monthlyReportFromArray : listOfMonthlyReport) {
                if (yearlyReportFromArray.year.equals(monthlyReportFromArray.year)) {
                    if (monthlyReportFromArray.sumOfMonth(true) !=
                        yearlyReportFromArray.sumOfMonth(true, monthlyReportFromArray.month) ||
                        monthlyReportFromArray.sumOfMonth(false) !=
                        yearlyReportFromArray.sumOfMonth(false, monthlyReportFromArray.month)) {

                        monthNum = Integer.parseInt(monthlyReportFromArray.month);
                        System.out.println("Обнаружено несоответствие при сверке данных за " + FormatForReports.getMonthName(monthNum) +
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

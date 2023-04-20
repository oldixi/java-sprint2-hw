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

}

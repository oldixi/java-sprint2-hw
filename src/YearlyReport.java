import java.util.List;
import java.util.ArrayList;

public class YearlyReport {
    ReadFile file;
    String year;
    Integer monthCont = 0;
    ArrayList<YearlyData> yearlyDataArrayList = new ArrayList<>();

    YearlyReport(ReadFile file) {
        this.file = file;
        year = file.path.substring(file.path.lastIndexOf('y') + 2, file.path.lastIndexOf('y') + 6);
    }

    public ArrayList<YearlyData> parseYearData() {
        List<String> yearList = file.readFileContents();
        String[] lineContents;
        ArrayList<YearlyData> yearlyDataList = new ArrayList();
        for (int i = 1; i < yearList.size(); i++) {
            YearlyData yearlyData = new YearlyData();
            lineContents = yearList.get(i).split(",");
            yearlyData.month = lineContents[0];
            yearlyData.amount = Double.parseDouble(lineContents[1]);
            yearlyData.is_expense = Boolean.parseBoolean(lineContents[2]);
            yearlyDataList.add(yearlyData);
        }
        monthCont = yearList.size() / 2;
        return yearlyDataList;
    }

    public double sumOfMonth(boolean is_expense, String month) {
        double sumOfMonth = 0;
        for (YearlyData yearlyData : yearlyDataArrayList) {
            if (yearlyData.is_expense == is_expense && yearlyData.month.equals(month)) {
                sumOfMonth = yearlyData.amount;
            }
        }
        return sumOfMonth;
    }

    public double avgOfYear(boolean is_expense) {
        double sumOfYear = 0;
        for (YearlyData yearlyData : yearlyDataArrayList) {
            if (yearlyData.is_expense == is_expense) {
                sumOfYear += yearlyData.amount;
            }
        }
        return sumOfYear / monthCont;
    }

    public void showYearRep(String year) {
        Integer monthNum;
        System.out.println("\nОтчет за " + year + ".");
        for (int i = 1; i <= monthCont; i++) {
            System.out.println("Прибыль за " + FormatForReports.getMonthName(i) +
                    ": " + FormatForReports.moneyFormat(sumOfMonth(false, "0"+i) - sumOfMonth(true, "0"+i)) + ".");
        }
        System.out.println("Средний доход за отчетные месяцы: " + FormatForReports.moneyFormat(avgOfYear(false)) + ".");
        System.out.println("Средний расход за отчетные месяцы: " + FormatForReports.moneyFormat(avgOfYear(true)) + ".");
    }

}
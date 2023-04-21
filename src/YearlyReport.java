import java.util.List;
import java.util.ArrayList;

public class YearlyReport {
    ReadFile file;
    String year;
    Integer monthCont = 0;
    ArrayList<YearData> yearDataArrayList = new ArrayList<>();

    YearlyReport(ReadFile file) {
        this.file = file;
        year = file.path.substring(file.path.lastIndexOf('y') + 2, file.path.lastIndexOf('y') + 6);
    }

    public ArrayList<YearData> parseYearData() {
        List<String> yearList = file.readFileContents();
        String[] lineContents;
        ArrayList<YearData> yearDataList = new ArrayList();
        for (int i = 1; i < yearList.size(); i++) {
            YearData yearData = new YearData();
            lineContents = yearList.get(i).split(",");
            yearData.month = lineContents[0];
            yearData.amount = Double.parseDouble(lineContents[1]);
            yearData.is_expense = Boolean.parseBoolean(lineContents[2]);
            yearDataList.add(yearData);
        }
        monthCont = yearList.size() / 2;
        return yearDataList;
    }

    public double sumOfMonth(boolean is_expense, String month) {
        double sumOfMonth = 0;
        for (YearData yearData : yearDataArrayList) {
            if (yearData.is_expense == is_expense && yearData.month.equals(month)) {
                sumOfMonth = yearData.amount;
            }
        }
        return sumOfMonth;
    }

    public double avgOfYear(boolean is_expense) {
        double sumOfYear = 0;
        for (YearData yearData : yearDataArrayList) {
            if (yearData.is_expense == is_expense) {
                sumOfYear += yearData.amount;
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
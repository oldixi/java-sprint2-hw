import java.util.List;
import java.util.ArrayList;

public class MonthlyReport {
    ReadFile file;
    String month;
    String year;
    ArrayList<MonthlyData> monthlyDataArrayList = new ArrayList<>();

    MonthlyReport(ReadFile file) {
        this.file = file;
        year = file.path.substring(file.path.lastIndexOf('m') + 2, file.path.lastIndexOf('m') + 6);
        month = file.path.substring(file.path.lastIndexOf('m') + 6, file.path.lastIndexOf('m') + 8);
    }

    public ArrayList<MonthlyData> parseMonthData() {
        List<String> monthList = file.readFileContents();
        String[] lineContents;
        ArrayList<MonthlyData> monthlyDataList = new ArrayList();
        for (int i = 1; i < monthList.size(); i++) {
            MonthlyData monthlyData = new MonthlyData();
            lineContents = monthList.get(i).split(",");
            monthlyData.item_name = lineContents[0];
            monthlyData.is_expense = Boolean.parseBoolean(lineContents[1]);
            monthlyData.quantity = Integer.parseInt(lineContents[2]);
            monthlyData.sum_of_one = Double.parseDouble(lineContents[3]);
            monthlyDataList.add(monthlyData);
        }
        return monthlyDataList;
    }

    public double sumOfMonth(boolean is_expense) {
        double sumOfMonth = 0;
        for (MonthlyData monthlyData : monthlyDataArrayList) {
            if (monthlyData.is_expense == is_expense) {
                sumOfMonth += monthlyData.sum_of_one * monthlyData.quantity;
            }
        }
        return sumOfMonth;
    }

    public MonthlyData maxMonth(boolean is_expense) {
        double maxMonth = 0;
        MonthlyData maxMonthdata = new MonthlyData();
        for (MonthlyData monthlyData : monthlyDataArrayList) {
            if (monthlyData.is_expense == is_expense) {
                if (monthlyData.sum_of_one* monthlyData.quantity > maxMonth)  {
                    maxMonth = monthlyData.sum_of_one* monthlyData.quantity;
                    maxMonthdata.item_name = monthlyData.item_name;
                    maxMonthdata.is_expense = is_expense;
                    maxMonthdata.quantity = monthlyData.quantity;
                    maxMonthdata.sum_of_one = monthlyData.sum_of_one;
                }
            }
        }
        return maxMonthdata;
    }

    public void showMonthRep(String month) {
        MonthlyData monthlyDataMaxIncome = new MonthlyData();
        MonthlyData monthlyDataMaxExpense = new MonthlyData();
        monthlyDataMaxIncome = maxMonth(false);
        monthlyDataMaxExpense = maxMonth(true);
        Integer monthNum = Integer.parseInt(month);
        System.out.println("\nОтчет за " + FormatForReports.getMonthName(monthNum) + " " + year + " года.");
        System.out.println("Самый прибыльный товар: " + monthlyDataMaxIncome.item_name +
                           " на сумму " + FormatForReports.moneyFormat(monthlyDataMaxIncome.sum_of_one * monthlyDataMaxIncome.quantity) + ".");
        System.out.println("Самая большая трата: " + monthlyDataMaxExpense.item_name +
                " на сумму " + FormatForReports.moneyFormat(monthlyDataMaxExpense.sum_of_one * monthlyDataMaxExpense.quantity) + ".");
    }

}
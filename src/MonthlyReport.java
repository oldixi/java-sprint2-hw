import java.util.List;
import java.util.ArrayList;

public class MonthlyReport {
    ReadFile file;
    boolean isRead = false;
    ArrayList<MonthData> monthDataArrayList = new ArrayList<>();

    MonthlyReport(ReadFile file) {
        this.file = file;
    }

    public ArrayList<MonthData> parseMonthData() {
        List<String> monthList = new ArrayList();
        monthList = file.readFileContents();
        String[] lineContents;
        ArrayList<MonthData> monthDataList = new ArrayList();
        for (int i = 1; i < monthList.size(); i++) {
            MonthData monthData = new MonthData();
            lineContents = monthList.get(i).split(",");
            monthData.item_name = lineContents[0];
            monthData.is_expense = Boolean.parseBoolean(lineContents[1]);
            monthData.quantity = Integer.parseInt(lineContents[2]);
            monthData.sum_of_one = Double.parseDouble(lineContents[3]);
            monthDataList.add(monthData);
        }
        isRead = true;
        return monthDataList;
    }

    public double sumOfMonth(boolean is_expense) {
        double sumOfMonth = 0;
        for (MonthData monthData : monthDataArrayList) {
            if (monthData.is_expense == is_expense) {
                sumOfMonth += monthData.sum_of_one * monthData.quantity;
            }
        }
        return sumOfMonth;
    }

    public MonthData maxMonth(boolean is_expense) {
        double maxMonth = 0;
        MonthData maxMonthdata = new MonthData();
        for (MonthData monthData : monthDataArrayList) {
            if (monthData.is_expense == is_expense) {
                if (monthData.sum_of_one*monthData.quantity > maxMonth)  {
                    maxMonth = monthData.sum_of_one*monthData.quantity;
                    maxMonthdata.item_name = monthData.item_name;
                    maxMonthdata.is_expense = is_expense;
                    maxMonthdata.quantity = monthData.quantity;
                    maxMonthdata.sum_of_one = monthData.sum_of_one;
                }
            }
        }
        return maxMonthdata;
    }

    public void showMonthRep(String month) {
        MonthData monthDataMaxIncome = new MonthData();
        MonthData monthDataMaxExpense = new MonthData();
        monthDataMaxIncome = maxMonth(false);
        monthDataMaxExpense = maxMonth(true);

        Integer monthNum = Integer.parseInt(month);

        System.out.println("Отчет за " + UsefulTools.getMonthName(monthNum) + ".");
        System.out.println("Самый прибыльный товар: " + monthDataMaxIncome.item_name +
                           " на сумму " + UsefulTools.moneyFormat(monthDataMaxIncome.sum_of_one * monthDataMaxIncome.quantity) + ".");
        System.out.println("Самая большая трата за: " + monthDataMaxExpense.item_name +
                " на сумму " + UsefulTools.moneyFormat(monthDataMaxExpense.sum_of_one * monthDataMaxExpense.quantity) + ".");
    }

}
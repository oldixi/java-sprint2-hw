public class YearlyData {
    String month;
    double amount;
    boolean is_expense;

    YearlyData(String month, double amount, boolean is_expense) {
        this.month = month;
        this.amount = amount;
        this.is_expense = is_expense;
    }

    YearlyData() {
    }
}

public class YearData {
    String month;
    double amount;
    boolean is_expense;

    YearData(String month, double amount, boolean is_expense) {
        this.month = month;
        this.amount = amount;
        this.is_expense = is_expense;
    }

    YearData() {
    }
}

import java.text.DecimalFormat;

public class FormatForReports {
    static String DEC_FORMAT = "#0.00";

    FormatForReports() {
    }

    public static String getMonthName(int monthNum) {
        String[] monthName = {"январь","февраль","март","апрель","май","июнь","июль","август","сентябрь","октябрь","ноябрь","декабрь"};
        return monthName[monthNum-1];
    }

    public static String moneyFormat(double money) {
        return new DecimalFormat(DEC_FORMAT).format(money);
    }
}

import java.text.DecimalFormat;

public class UsefulTools {
    static String DEC_FORMAT = "#0.00";

    UsefulTools() {
    }

    public static String getMonthName(int monthNum) {
        String[] monthName = {"январь","февраль","март","апрель","май","июнь","июль","август","сентябрь","октябрь","ноябрь","декабрь"};
        return monthName[monthNum-1];
    }

    public static String moneyFormat(double money) {
        String moneyFormated = new DecimalFormat(DEC_FORMAT).format(money);
        return moneyFormated;
    }
}

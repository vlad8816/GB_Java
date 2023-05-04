import java.text.DecimalFormat;
import java.util.ArrayList;

public class HW_02 {
    public static void main(String[] args) {

//        Написать метод принимающий строку, ищущий в ней основание и степень, после этого
//        рассчитывающий результат и выводящий в консоль строку параметра + ответ.
//        Пример строки: "Основание -12, степень 7, результат". Попробовать с разными входящими строками.
//        Сравнить скорость работы перестановки классов String и StringBuilder.

        double begin, end, result1, result2;
        ArrayList<String> string_list = new ArrayList<>();

        string_list.add("Основание 0, степень 0, результат");
        string_list.add("Основание 0, степень 1, результат");
        string_list.add("Основание 0, степень 10, результат");
        string_list.add("Основание 1, степень 10, результат");
        string_list.add("Основание 2, степень 10, результат");
        string_list.add("Основание 2, степень 1, результат");
        string_list.add("Основание 2, степень 0, результат");
        string_list.add("Основание 2, степень -10, результат");
        string_list.add("Основание -2, степень 11, результат");
        string_list.add("Основание 3, степень 0.3, результат");
        string_list.add("Основание -5, степень 0.5, результат");
        string_list.add("Основание -5, степень 2.1, результат");
        string_list.add("Основание -5, степень -2.1, результат");
        string_list.add("Основание 0.5, степень 1, результат");
        string_list.add("Основание -0.5, степень 3, результат");
        string_list.add("Основание -2.1, степень -3, результат");
        string_list.add("Основание -2.1, степень -3.27, результат");

        hw02_print_message("Использование string.replace", '-', 60);
        begin = hw02_time_stamp();
        for (String s : string_list) hw02_print_parsed_string_with_string(s);
        end = hw02_time_stamp();
        result1 = hw02_elapsed_time(begin, end);
        hw02_print_result("\nВремя выполнения, используя класс String:", result1);

        hw02_print_message("Использование StringBuilder", '-', 60);
        begin = hw02_time_stamp();
        for (String s : string_list) hw02_print_parsed_string_with_builder(s);
        end = hw02_time_stamp();
        result2 = hw02_elapsed_time(begin, end);
        hw02_print_result("\nВремя выполнения, используя класс StringBuilder:", result2);

        hw02_compare_results(result1, result2);
    }

    public static double hw02_time_stamp() {
        return System.nanoTime();
    }

    public static Double hw02_elapsed_time(double in_begin, double in_end) {
        return in_end - in_begin;
    }

    public static void hw02_print_message(String in_message, char in_separator, int in_width) {
        String sep = String.valueOf(in_separator);
        int wid = in_width - in_message.length();
        if (in_message.length() >= in_width) System.out.println("\n" + in_message);
        else if (wid % 2 == 0) {
            System.out.println("\n" + sep.repeat(wid / 2) + in_message + sep.repeat(wid / 2));
        } else System.out.println("\n" + sep.repeat(wid / 2 - 1) + in_message + sep.repeat(wid / 2));
    }

    public static void hw02_print_result(String in_message, double in_result) {
        DecimalFormat df = new DecimalFormat("0.##########");
        System.out.println(in_message + " " + df.format(in_result / 1_000_000) + " мс.");

    }

    public static void hw02_compare_results(double in_result1, double in_result2) {
        DecimalFormat df = new DecimalFormat("0.##########");
        String result = in_result1 < in_result2 ? "Первый" : "Второй";
        System.out.println("\n" + result + " результат оказался быстрее на " +
                df.format(Math.abs(in_result1 - in_result2) / 1_000_000) + " мс.");
    }

    public static void hw02_print_parsed_string_with_string(String in_string) {
        String[] strings = in_string.split(" ");
        Double base = null, power = null;
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].replace(",", "");
            try {
                Double.parseDouble(String.valueOf(strings[i]));
                if (base == null) base = Double.parseDouble(String.valueOf(strings[i]));
                else power = Double.parseDouble(String.valueOf(strings[i]));
            } catch (NumberFormatException ignored) {
            }
        }
        DecimalFormat df = new DecimalFormat("0.##########");
        if (base != null && power != null) {
            if (base == 0 && power == 0)
                System.out.println(in_string + " равен " + "неопределённости.");
            else if (base < 0 && power % 1 != 0)
                System.out.printf("%s равен %s.%n", in_string, df.format((Math.pow(base * -1, power) * -1)));
            else if (base % 1 != 0 || power < 0 || power % 1 != 0)
                System.out.printf("%s равен %s.%n", in_string, df.format(Math.pow(base, power)));
            else
                System.out.printf("%s равен %.0f.%n", in_string, Math.pow(base, power));
        }
    }


    public static void hw02_print_parsed_string_with_builder(String in_string) {
        StringBuilder sb = new StringBuilder();
        Double base = null, power = null;
        for (int i = 0; i < in_string.length(); i++) {
            if (Character.isDigit(in_string.charAt(i))) {
                if (i > 0 && in_string.charAt(i - 1) == '-') sb.append(in_string.charAt(i - 1));
                sb.append(in_string.charAt(i));
                if (i < in_string.length() - 3 && in_string.charAt(i + 1) == '.' &&
                        Character.isDigit(in_string.charAt(i + 2))) {
                    sb.append(in_string.charAt(i + 1));
                    i++;
                }
            } else if (!sb.isEmpty()) {
                try {
                    Double.parseDouble(String.valueOf(sb));
                    if (base == null) base = Double.parseDouble(String.valueOf(sb));
                    else power = Double.parseDouble(String.valueOf(sb));
                    sb.setLength(0);
                } catch (NumberFormatException ignored) {
                }
            }
        }
        DecimalFormat df = new DecimalFormat("0.##########");
        if (base != null && power != null) {
            if (base == 0 && power == 0)
                System.out.println(in_string + " равен " + "неопределённости.");
            else if (base < 0 && power % 1 != 0) {
                base *= -1;
                System.out.printf("%s равен %s.%n", in_string, df.format((Math.pow(base, power) * -1)));
            } else if (base % 1 != 0 || power < 0 || power % 1 != 0)
                System.out.printf("%s равен %s.%n", in_string, df.format(Math.pow(base, power)));
            else
                System.out.printf("%s равен %.0f.%n", in_string, Math.pow(base, power));
        }
    }
}
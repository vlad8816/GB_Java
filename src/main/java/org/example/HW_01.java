import java.util.Random;

public class HW_01 {
    public static void main(String[] args) {

        // 1. Выбросить случайное целое число в диапазоне от 0 до 2000 и сохранить в i
        int i = get_zero_to_2000_random();

        // 2. Посчитать и сохранить в n номер старшего значащего бита выпавшего числа
        int n = get_num_highest_bit(i);

        // 3. Найти все кратные n числа в диапазоне от i до Short.MAX_VALUE сохранить в массив m1
        int[] m1 = get_divisible_array(i, n);

        // 4. Найти все некратные n числа в диапазоне от Short.MIN_VALUE до i и сохранить в массив m2
        int[] m2 = get_not_divisible_array(i, n);

        System.out.println("i = " + i + ", n = " + n);
        System.out.println("Длина массива m1 = " + m1.length + ", m2 = " + m2.length);
        // print_array(m1);
        // print_array(m2);
    }

    public static int get_zero_to_2000_random() {
        return new Random().nextInt(0, 2001);
    }

    public static int get_num_highest_bit(int in_number) {
        return Integer.toBinaryString(in_number).length();
    }

    public static int[] get_divisible_array(int in_number, int in_divider) {
        if (in_divider == 0) return new int[0];
        StringBuilder sb = new StringBuilder();
        for (int i = in_number; i <= Short.MAX_VALUE; i++)
            if (i % in_divider == 0) sb.append(i).append(" ");
        if (sb.length() > 0) {
            String[] strings = sb.toString().split(" ");
            int[] result = new int[strings.length];

            for (int i = 0; i < strings.length; i++) {
                result[i] = Integer.parseInt(strings[i]);
            }
            return result;
        } else {
            return new int[0];
        }
    }

    public static int[] get_not_divisible_array(int in_number, int in_divider) {
        if (in_divider == 0 || in_divider == 1) return new int[0];
        StringBuilder sb = new StringBuilder();
        for (int i = Short.MIN_VALUE; i <= in_number; i++)
            if (i % in_divider != 0) sb.append(i).append(" ");
        if (sb.length() > 0) {
            String[] strings = sb.toString().split(" ");
            int[] result = new int[strings.length];

            for (int i = 0; i < strings.length; i++) {
                result[i] = Integer.parseInt(strings[i]);
            }
            return result;
        } else {
            return new int[0];
        }
    }

    public static void print_array(int[] in_array) {
        for (int i : in_array) System.out.print(i + " ");
    }
}
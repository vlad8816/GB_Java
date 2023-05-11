import java.util.*;

public class HW_03 {
    public static void main(String[] args) {

        ArrayList<Integer> my_list1 = hw3_create_random_array_list(20, -10, 50);
        System.out.println("-".repeat(80) + "\nЗадан список:\n" + my_list1);

        // 1. Реализовать алгоритм обратной сортировки списков компаратором.
        my_list1.sort(new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // my_list1.sort((o1, o2) -> o2 - o1);  // idea просит заменить на лямбду
        System.out.println("1. Обратная сортировка:\n" + my_list1);


        // 2. Пусть дан произвольный список целых чисел, удалить из него чётные числа
        Iterator<Integer> iterator = my_list1.iterator();
        while (iterator.hasNext()) {
            int i = iterator.next();
            if (i % 2 == 0) iterator.remove();
        }
        System.out.println("2. Удаление четных:\n" + my_list1);


        // 3. Задан целочисленный список ArrayList. Найти минимальное, максимальное и среднее из этого списка.
        ArrayList<Integer> my_list2 = hw3_create_random_array_list(20, -10, 50);
        System.out.println("-".repeat(80) + "\nЗадан список:\n" + my_list2);

        int min_value = Integer.MAX_VALUE;
        for (int i : my_list2) min_value = Math.min(min_value, i);
        int max_value = Integer.MIN_VALUE;
        for (int i : my_list2) max_value = Math.max(max_value, i);
        int sum = 0;
        for (int i : my_list2) sum += i;
        double avg_value = (double) sum / my_list2.size();
        System.out.printf("3. Найти значения: \nминимальное -> %s, максимальное -> %s, среднее -> %.2f.\n",
                min_value, max_value, avg_value);


        // 4. Дано два целочисленных списка, объединить их не допуская элементы второго списка,
        // уже встречающиеся в первом.
        ArrayList<Integer> my_list3 = hw3_create_random_array_list(10, 0, 10);
        ArrayList<Integer> my_list4 = hw3_create_random_array_list(10, 0, 10);
        System.out.println("-".repeat(80) + "\nЗаданы списки:\n" + my_list3 + "\n" + my_list4);
        my_list4.removeAll(my_list3);
        my_list3.addAll(my_list4);
        System.out.println("4. Объединение, не допуская элементы второго списка, уже встречающиеся в первом:\n" +
                my_list3);


        // 5. Создать ArrayList<Integer> и добавить нулевым элементом ноль 10000 раз.
        System.out.println("-".repeat(80) + "\nВ пустой список 10 000 раз нулевым элементом добавлен ноль.");
        double begin, end, result1, result2;
        ArrayList<Integer> my_list5 = new ArrayList<>();
        begin = HW_02.hw02_time_stamp();
        for (int i = 0; i < 10_000; i++) my_list5.add(0, 0);
        end = HW_02.hw02_time_stamp();
        result1 = HW_02.hw02_elapsed_time(begin, end);
        HW_02.hw02_print_result("Время выполнения, используя ArrayList<Integer>:", result1);


        // 6. Повторить пятый пункт, но с LinkedList.
        LinkedList<Integer> my_list6 = new LinkedList<>();
        begin = HW_02.hw02_time_stamp();
        for (int i = 0; i < 10_000; i++) my_list6.add(0, 0);
        end = HW_02.hw02_time_stamp();
        result2 = HW_02.hw02_elapsed_time(begin, end);
        HW_02.hw02_print_result("Время выполнения, используя LinkedList<Integer>:", result2);


        // 7. Сравнить время работы пятого и шестого пунктов.
        HW_02.hw02_compare_results(result1, result2);

    }

    public static ArrayList<Integer> hw3_create_random_array_list(int in_len, int in_min, int in_max) {
        Random rand = new Random();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i <= in_len; i++) {
            result.add(rand.nextInt(in_min, in_max));
        }
        return result;
    }
}
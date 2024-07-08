package kattsyn.edu.examples.lamdatasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        //task1
        BinaryOperator<Integer> binaryOperator = Integer::sum;
        System.out.println(binaryOperator.apply(5, 4)); //9
        //task1

        //task2
        String str1 = "some string";
        String str2 = "";

        Predicate<String> stringPredicate = String::isEmpty;
        System.out.println(stringPredicate.test(str1)); //false
        System.out.println(stringPredicate.test(str2)); //true
        //task2

        //task3
        List<String> strList = new ArrayList<>();
        strList.add("Hello");
        strList.add("Mom");
        Consumer<List<String>> consumer = strings -> strings.replaceAll(String::toLowerCase);
        consumer.accept(strList);
        System.out.println(strList); //[hello, mom]
        //task3

        //task4
        List<Integer> integerList = new ArrayList<>();
        integerList.add(4);
        integerList.add(2);
        integerList.add(1);
        integerList.add(5);

        List<Integer> evenList = integerList.stream().filter(n -> n % 2 == 0).toList();
        List<Integer> oddList = integerList.stream().filter(n -> n % 2 != 0).toList();
        System.out.println("evenList: " + evenList); //evenList: [4, 2]
        System.out.println("oddList: " + oddList); //oddList: [1, 5]
        //task4

        //task5

        List<String> stringList = new ArrayList<>();
        stringList.add("google");
        stringList.add("burger");
        stringList.add("monkey");
        stringList.add("africa");

        stringList = stringList.stream().sorted().toList();
        System.out.println(stringList); //[africa, burger, google, monkey]
        //task5


        //task6
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(4.5);
        doubleList.add(5.0);
        doubleList.add(2.0);
        double average = doubleList.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        System.out.println(average); //3.83333..
        //task6

        //task7
        List<Integer> integerList1 = Arrays.asList(4, 5, 1, 123, 144, 1, 4, 94);
        integerList1 = integerList1.stream().distinct().toList();
        System.out.println(integerList1); //[4, 5, 1, 123, 144, 94]
        //task7

        //task8
        LongUnaryOperator longUnaryOperator = num -> {
            long result = 1L;
            for (int i = 1; i <= num; i++) {
                result *= i;
            }
            return result;
        };

        System.out.println(longUnaryOperator.applyAsLong(5)); //120
        //task8
    }


}

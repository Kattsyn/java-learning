package vsu.cs.kattsyn.edu;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Examples {
    public static void main(String[] args) {
        example5();
    }

    public static void example1(){
        int sum = IntStream.iterate(1, n -> n + 1) //перебирает целые числа, начиная с 1, и увеличивает на 1 потом
                .filter(n -> n % 10 == 5) //делает выборку, фильтрует (грубо говоря if (n % 10 == 5) {...}
                .limit(10) //ограничивает кол-во выходных после фильтра параметров
                .map(n -> n * n) //возводит отобранные значения в квадрат
                .sum(); //суммирует все выходные значения
        System.out.println(sum);
        sum = IntStream.iterate(0, n -> n + 2)
                .limit(10)
                .sum();
        System.out.println(sum);
    }
    public static BigInteger streamFactorial(int n){
        return IntStream.rangeClosed(1, n)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }
    public static boolean isPolindrome(String s){
        StringBuilder leftToRight = new StringBuilder();

        s.chars().filter(Character::isLetterOrDigit)
                .map(Character::toLowerCase)
                .forEach(leftToRight::appendCodePoint);
        StringBuilder rightToLeft = new StringBuilder(leftToRight).reverse();

        return leftToRight.toString().contentEquals(rightToLeft);
    }
    public static void example4(){
        IntStream.of(120, 410, 85, 32, 314, 12) //поток из значений 120, 410..., 12
                .filter(x -> x < 300) //выбираем только те, которые меньше 300
                .map(x -> x + 11) //к отобранным значениям добавляем 11
                .limit(3) //ограничиваемся только тремя выходными значениями
                .forEach(System.out::println); //к каждому применяем метод println()

        //так бы это выглядело без потоков
        int[] arr = {120, 410, 85, 32, 314, 12};
        int count = 0;
        for (int x : arr) {
            if (x >= 300) continue;
            x += 11;
            count++;
            if (count > 3) break;
            System.out.println(x);
        }
    }
    public static void example5(){
        IntStream.of(10, 15, 12, 41, 99, 1004, 124, 457, 54, 66) //поток значений
                .limit(5) //из первых 5 значений
                .filter(n -> n > 10) //отбираем те, которые больше 10
                .limit(2) //таких хотим отобрать только два
                .forEach(System.out::println); //печатаем в консоль
    }
    public static void example6(){}
}

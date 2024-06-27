package kattsyn.edu.examples;

import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {

        //Функциональный интерфейс Function<T, R> представляет функцию перехода от объекта типа T к объекту типа R
        Function<Integer, String> convert = x -> String.valueOf(x) + " долларов";
        System.out.println(convert.apply(5)); // 5 долларов

        Function<Integer, Integer> square = x -> x * x;
        System.out.println(square.apply(5));

        Function<Integer, Integer> getFactorial = x -> {
            int result = 1;
            for (int i = 1; i <= x; i++) {
                result *= i;
            }
            return result;
        };

        System.out.println(getFactorial.apply(5));
    }
}

package kattsyn.edu.examples;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class UnaryOperatorExample {
    public static void main(String[] args) {

        //Похож на Function, но принимает и возвращает объект одного и того же типа
        UnaryOperator<Integer> square = x -> x * x;
        System.out.println(square.apply(5)); // 25

        Function<Integer, Integer> getFactorial = x -> {
            int result = 1;
            for (int i = 1; i <= x; i++) {
                result *= i;
            }
            return result;
        };

        System.out.println(getFactorial.apply(5)); //120
    }
}

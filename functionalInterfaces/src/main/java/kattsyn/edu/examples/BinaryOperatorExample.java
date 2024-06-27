package kattsyn.edu.examples;

import java.util.function.BinaryOperator;

public class BinaryOperatorExample {
    public static void main(String[] args) {
        BinaryOperator<Integer> sum = (x, y) -> x + y; //можно заменить на Integer::sum
        System.out.println(sum.apply(5, 4));

        BinaryOperator<String> concat = (x, y) -> {
            StringBuilder stringBuilder = new StringBuilder(x);
            stringBuilder.append(y);
            return stringBuilder.toString();
        };

        System.out.println(concat.apply("Artem ", "Tolik"));
    }
}

package kattsyn.edu.examples;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {

        /*
        Предикат, проверяющий положительное ли число x.
        При описании лямбда выражения пишем входные параметры, и выходные
        В нашем случае выходное выражение - условие.
         */

        Predicate<Integer> isPositive = x -> x > 0;
        BiPredicate<Integer, Integer> firstBigger = (x, y) -> x > y;

        System.out.println(isPositive.test(5)); // true
        System.out.println(isPositive.test(-7)); // false

        System.out.println(firstBigger.test(13, 7)); //true
        System.out.println(firstBigger.test(2, 5)); //false
    }
}

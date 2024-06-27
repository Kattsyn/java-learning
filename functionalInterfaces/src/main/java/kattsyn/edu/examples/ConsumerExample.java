package kattsyn.edu.examples;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {

        /*
        Consumer<T> выполняет некоторое действие над объектом типа T, при этом ничего не возвращая
         */

        Consumer<Integer> printer = x-> System.out.printf("%d долларов \n", x);
        printer.accept(600); // 600 долларов

        BiConsumer<String, String> printNameSurname = (x, y) -> System.out.println("Hi! I'm " + x + " " + y);
        printNameSurname.accept("Ivan", "Ivanov");
    }
}

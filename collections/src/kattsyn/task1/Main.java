package kattsyn.task1;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(0);
        set2.add(1);
        set2.add(2);

        System.out.println(symmetricDifference(set1, set2).toString());
    }

    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> resultSet = new HashSet<>();
        resultSet.addAll(set1);
        resultSet.addAll(set2);
        resultSet.removeIf(t -> set1.contains(t) && set2.contains(t));
        return resultSet;
    }
}

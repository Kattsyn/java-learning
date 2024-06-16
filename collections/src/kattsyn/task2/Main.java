package kattsyn.task2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        //list.
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while(scanner.hasNextInt()) {
            if (i % 2 != 0) {
                list.add(scanner.nextInt());
            } else {
                scanner.next();
            }
            i++;
        }
        for(i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i) + " ");
        }
    }
}

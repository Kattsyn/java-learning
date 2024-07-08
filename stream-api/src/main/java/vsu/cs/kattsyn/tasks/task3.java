package vsu.cs.kattsyn.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String strTest1 = "Мама мыла-мыла-мыла раму!";

        String strTest2 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vel odio nec mi tempor dignissim.";

        //System.out.println(Arrays.toString(scanner.nextLine().split("[^a-zA-Zа-яА-Я0-9']+")));

        //ввод Arrays.stream(scanner.nextLine().split(" "))
        /*
        Map<String, Long> map = Arrays.stream(scanner.nextLine().split("[\\\\p{Punct}\\\\s]+"))
                .collect(Collectors
                        .groupingBy
                                (Function.identity(), Collectors.counting()));*/

        Map<String, Long> resMap = new HashMap<>();
        (new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8)))
                .lines()
                .flatMap(l -> Stream.of(l.split("[\\p{Punct}\\s]+")))
                .map(String::toLowerCase)
                .forEach(w -> {
                    if (resMap.containsKey(w)) resMap.put(w, resMap.get(w) + 1);
                     else resMap.put(w, 1L);
                });
        resMap.entrySet().stream().sorted(((o1, o2) -> {
            if (o2.getValue().equals(o1.getValue())) {
                return o1.getKey().compareTo(o2.getKey());
            } else {
                return o2.getValue().compareTo(o1.getValue());
            }
        }
        )).limit(10).forEach(e -> System.out.println(e.getKey()));

        /*
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue()
                        .reversed())
                .forEachOrdered(x -> System.out.println(x.getKey()));*/
    }
}

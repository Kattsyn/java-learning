package kattsyn.collections;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<>();
        map.put("name", "ivan");
        map.put("surname", "ivanov");
        System.out.println(map);
        hashMapTest();

    }

    public static void hashMapTest() {
        System.out.println("Создали пустой HashMap");
        MyHashMap<String, String> myHashMap = new MyHashMap<>();
        myHashMap.put("age", "14");
        System.out.println("HashMap after adding age=14 - " + myHashMap);
        myHashMap.put("height", "175");
        myHashMap.put("name", "Batman");
        myHashMap.put("surname", "Sopov");
        System.out.println("HashMap after adding height=175, name=Batman, surname=Sopov - " + myHashMap);
        System.out.println("myHashMap.containsKey(\"name\"): " + myHashMap.containsKey("name"));
        System.out.println("myHashMap.containsKey(\"religion\"): " + myHashMap.containsKey("religion"));
        System.out.println("myHashMap.containsValue(\"Sopov\"): " + myHashMap.containsValue("Sopov"));
        System.out.println("myHashMap.containsValue(\"nnhhff\"): " + myHashMap.containsKey("nnhhff"));
        myHashMap.put("name", "Waldemar");
        System.out.println("myHashMap.put(\"name, Waldemar\"): " + myHashMap);
        System.out.println("hashMap.size(): " + myHashMap.size());

        System.out.println("myHashMap.remove(\"name\"): " + myHashMap.remove("name"));
        System.out.println("hashMap after removing \"name\": " + myHashMap);
    }

    public static void linkedListTest() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

        myLinkedList.add(4);
        myLinkedList.add(12);
        myLinkedList.add(92);
        System.out.println("LinkedList after adding 4, 12, 92 to the end: " + myLinkedList);

        myLinkedList.addFirst(44);
        System.out.println("LinkedList after adding 44 in the beginning: " + myLinkedList);

        myLinkedList.add(23, 0);
        System.out.println("LinkedList after adding 23 on 0 index: " + myLinkedList);

        myLinkedList.set(123, 3);
        System.out.println("LinkedList after setting 123 on 3rd index: " + myLinkedList);

        myLinkedList.remove(4);
        System.out.println("LinkedList after deleting 2nd element: " + myLinkedList);
        System.out.println();

        System.out.println("LinkedList output all elems: ");
        for (int i = 0; i < myLinkedList.getSize(); i++) {
            System.out.println(i + ": " + myLinkedList.getValue(i));
        }
    }
}

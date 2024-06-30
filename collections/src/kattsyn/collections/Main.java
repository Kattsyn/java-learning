package kattsyn.collections;


import java.util.*;

public class Main {
    public static void main(String[] args) {

        linkedListTest();

    }

    public static void arrayListTest() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        System.out.println(list);
        ListIterator<Integer> listIterator = list.listIterator();
        for (int i = 0; i < 3; i++) {
            listIterator.next();
        }
        System.out.println(listIterator.previousIndex());
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

        myLinkedList.add(2, 23);
        System.out.println("LinkedList after adding 23 on 2 index: " + myLinkedList);

        myLinkedList.set(3, 123);
        System.out.println("LinkedList after setting 123 on 3rd index: " + myLinkedList);

        myLinkedList.remove(4);
        System.out.println("LinkedList after deleting 4th element: " + myLinkedList);
        System.out.println();

        System.out.println("LinkedList output all elems: ");
        for (int i = 0; i < myLinkedList.size(); i++) {
            System.out.println(i + ": " + myLinkedList.getValue(i));
        }

        System.out.println("---");
        System.out.println("listIterator test: ");
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        System.out.println("has previous: " + listIterator.hasPrevious());
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
        System.out.println("---");
        System.out.println("iterator test");
        Iterator<Integer> iterator = myLinkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("---");
        System.out.println("toArray(T1[]) test");
        System.out.println("Меньшей длины: " + Arrays.toString(myLinkedList.toArray(new Integer[2])));
        System.out.println("Нужной длины: " + Arrays.toString(myLinkedList.toArray(new Integer[4])));
        System.out.println("Большей длины: " + Arrays.toString(myLinkedList.toArray(new Integer[6])));
        System.out.println("toArray() test");
        System.out.println(Arrays.toString(myLinkedList.toArray()));

        System.out.println("---");
        System.out.println("RetainAll test: ");
        Collection<Integer> collection = new ArrayList<>();
        collection.add(44);
        collection.add(23);
        myLinkedList.retainAll(collection);
        System.out.println(myLinkedList);
    }
}

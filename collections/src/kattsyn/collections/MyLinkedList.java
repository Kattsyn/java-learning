package kattsyn.collections;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MyLinkedList<T> implements List<T> {

    private MyListNode<T> head;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    private static class MyListNode<T> {
        T value;
        MyListNode<T> nextNode;

        public MyListNode(T value, MyListNode<T> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }

        public MyListNode(T value) {
            this(value, null);
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public MyListNode<T> getNext() {
            return nextNode;
        }

        public boolean hasNext() {
            return nextNode != null;
        }

        public void setNext(MyListNode<T> nextNode) {
            this.nextNode = nextNode;
        }
    }

    /**
     * @return возвращает длину списка.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return возвращает true, если список пуст.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        MyListNode<T> node = head;
        while (node.hasNext()) {
            if (node.equals(o)) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    private class Itr implements Iterator<T> {
        private MyListNode<T> curNode;
        private int cursor;

        public Itr() {
            this.curNode = head;
            this.cursor = 0;
        }


        @Override
        public boolean hasNext() {
            return curNode.hasNext() && cursor != size;
        }

        @Override
        public T next() {
            T value = curNode.getValue();
            curNode = curNode.getNext();
            cursor++;
            return value;
        }
    }

    private class ListItr extends Itr implements ListIterator<T> {

        private int cursor;
        private MyListNode<T> curNode;
        private boolean calledNextOrPrev = false;
        private boolean calledRemoveOrAdd = false;
        private boolean calledAdd = false;

        private ListItr(int index) {
            this.cursor = index;
            this.curNode = head;
        }

        private ListItr() {
            this(0);
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            calledNextOrPrev = true;
            calledRemoveOrAdd = false;
            calledAdd = false;
            T value = curNode.getValue();
            curNode = curNode.getNext();
            cursor++;
            return value;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            curNode = getNode(--cursor);
            T value = curNode.getValue();
            calledNextOrPrev = true;
            calledRemoveOrAdd = false;
            calledAdd = false;
            return value;
        }

        @Override
        public int nextIndex() {
            if (cursor == size) {
                return size;
            }
            return cursor;
        }

        @Override
        public int previousIndex() {
            if (!hasPrevious()) {
                return -1;
            }
            return cursor - 1;
        }

        @Override
        public void remove() {
            if (calledNextOrPrev && !calledAdd) {
                MyLinkedList.this.remove(cursor);
                curNode = getNode(cursor);
                calledNextOrPrev = false;
            }
        }

        @Override
        public void set(T t) {
            if (calledNextOrPrev && !calledRemoveOrAdd) {
                MyLinkedList.this.set(cursor, t);
            }
        }

        @Override
        public void add(T t) {
            MyLinkedList.this.add(cursor, t);
            calledRemoveOrAdd = true;
            calledAdd = true;
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private MyListNode<T> getNode(int index) {
        MyListNode<T> node = head;
        for(int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    /**
     * @return возвращает массив Object[] со всеми элементами списка.
     */
    @NotNull
    @Override
    public Object @NotNull [] toArray() {
        Object[] arr = new Object[size];
        MyListNode<T> node = head;
        for (int i = 0; i < size; i++) {
            arr[i] = node.getValue();
            node = node.getNext();
        }
        return arr;
    }

    /**
     * Переносит все элементы списка в переданный массив T1[] а.
     * Если тип элементов массива не совпадает с типом элементов,
     * хранящихся в списке, то выбрасывает ClassCastException
     *
     * @param a массив в который все элементы списка будут положены. Если он слишком мал, то создастся новый, который равен размеру списка.
     *          Если больше нужного, то все последующие элементы будут null
     * @return возвращает массив элементов T1[] a.
     */
    @NotNull
    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1 @NotNull [] toArray(T1[] a) {
        if (!a.getClass().getComponentType().isInstance(head.getValue())) {
            throw new ClassCastException();
        }
        if (a.length < size) {
            a = (T1[]) new Object[size];
        }
        MyListNode<T> node = head;
        for (int i = 0; i < a.length; i++) {
            a[i] = (T1) node.getValue();
            if (i < size) {
                node = node.getNext();
            } else {
                a[i] = null;
            }
        }
        return a;
    }

    /**
     * Функция добавляет элемент в конец списка
     *
     * @param value значение
     * @return возвращает true, если список был изменен.
     */
    public boolean add(T value) {
        if (isEmpty()) {
            head = new MyListNode<>(value);
        } else {
            MyListNode<T> listNode = head;
            for (int i = 0; i < size - 1; i++) {
                listNode = listNode.getNext();
            }
            listNode.setNext(new MyListNode<>(value, head));
        }
        size++;
        return true;
    }

    /**
     * Удаляет элемент
     *
     * @param o элемент, который будет удален, если он есть в списке.
     * @return возвращает true, если элемент был найден и удален, иначе false.
     */
    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index != -1) {
            remove(index);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Проверяет, содержатся ли все элементы коллекции "с" в списке.
     *
     * @param c коллекция, элементы которой должны содержаться в списке.
     * @return возвращает true, если все элементы коллекции содержатся в списке, иначе false
     */
    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        for (Object obj : c) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Добавляет все элементы коллекции в список.
     *
     * @param c коллекция, элементы которой будут добавлены в список.
     * @return возвращает true, если список был изменен, иначе false.
     */
    @Override
    public boolean addAll(@NotNull Collection<? extends T> c) {
        if (c.isEmpty()) {
            return false;
        }
        for (T value : c) {
            add(value);
        }
        return true;
    }

    /**
     * Добавляет все элементы коллекции в список.
     *
     * @param c     коллекция, элементы которой будут добавлены в список.
     * @param index индекс, начиная с которого элементы будут добавлены в список.
     * @return возвращает true, если список был изменен, иначе false.
     */
    @Override
    public boolean addAll(int index, @NotNull Collection<? extends T> c) {
        if (c.isEmpty()) {
            return false;
        }
        for (T value : c) {
            add(index++, value);
        }
        return true;
    }

    /**
     * Удаляет все элементы из списка, которые содержатся в передаваемой коллекции.
     *
     * @param c коллекция, содержащая элементы, которые будут удалены из списка.
     * @return возвращает true, если список был изменен, иначе false.
     */
    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }
        for (Object obj : c) {
            remove(obj);
        }
        return true;
    }

    /**
     * Удаляет все элементы, которых нет в передаваемой коллекции.
     *
     * @param c коллекция, содержащая элементы, которые будут сохранены
     * @return возвращает true, если список был изменен, иначе false
     */
    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }
        boolean haveChanged = false;
        MyListNode<T> node = head;
        MyLinkedList<T> newList = new MyLinkedList<>();
        for (int i = 0; i < size; i++) {
            if (c.contains(node.getValue())) {
                newList.add(node.getValue());
                haveChanged = true;
            }
            node = node.getNext();
        }
        if (haveChanged) {
            this.head = newList.head;
            this.size = newList.size;
        }
        return haveChanged;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        MyListNode<T> node = head;
        for (int i = 0; i < size; i++) {
            node = node.getNext();
        }
        return node.getValue();
    }

    /**
     * Добавляет новый элемент по индексу. Начиная с нуля (0).
     * Если список пуст, либо индекс больше длины списка,
     * то значение уйдет в конец списка.
     *
     * @param value значение
     * @param index индекс, на место которого добавим значение value
     */
    public void add(int index, T value) {
        if (index < 0 || index >= size) {
            add(value);
        } else if (index == 0) {
            addFirst(value);
        } else {
            MyListNode<T> currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            MyListNode<T> newNode = new MyListNode<>(value, currentNode.getNext());
            currentNode.setNext(newNode);
            size++;
        }
    }

    /**
     * Функция добавляет в начало списка
     *
     * @param value значение
     */
    public void addFirst(T value) {
        if (isEmpty()) {
            head = new MyListNode<>(value);
        } else {
            head = new MyListNode<>(value, head);
        }
        size++;
    }


    /**
     * Меняет значение по индексу
     *
     * @param value значение
     * @param index индекс
     */
    public T set(int index, T value) {
        MyListNode<T> curNode = head;
        for (int i = 0; i < index; i++) {
            curNode = curNode.getNext();
        }
        T oldValue = curNode.getValue();
        curNode.setValue(value);
        return oldValue;
    }

    /**
     * Удаляет первый элемент списка
     */
    public void removeFirst() {
        if (size <= 1) {
            head = null;
            size = 0;
            return;
        }
        head = head.getNext();
        size--;
        MyListNode<T> listNode = head;
        for (int i = 0; i < size - 1; i++) {
            listNode = listNode.getNext();
        }
        listNode.setNext(head);
    }

    /**
     * Удаляет последний элемент списка
     */
    public void removeLast() {
        if (size <= 1) {
            head = null;
            size = 0;
            return;
        }
        MyListNode<T> listNode = head;
        size--;
        for (int i = 0; i < size; i++) {
            listNode = listNode.getNext();
        }
        listNode.setNext(head);
    }

    /**
     * Удаляет элемент по индексу.
     * @param index индекс удаляемого элемента.
     * @return возвращает значение удаленного элемента.
     * @throws IndexOutOfBoundsException бросает исключение, если переданный индекс за допустимыми границами.
     */
    public T remove(int index) throws IndexOutOfBoundsException {
        T oldValue = null;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс за границами допустимых пределов.");
        } else if (index == 0) {
            removeFirst();
        } else {
            MyListNode<T> node = head;
            for (int i = 0; i < index - 1; i++) {
                node = node.getNext();
            }
            oldValue = node.getNext().getValue();
            node.setNext(node.getNext().getNext());
            size--;
        }
        return oldValue;
    }

    /**
     * Возвращает индекс первого элемента со значением параметра "о".
     *
     * @param o элемент, который ищем
     * @return возвращает индекс элемента, либо -1, если он не был найден.
     */
    @Override
    public int indexOf(Object o) {
        MyListNode<T> node = head;
        for (int i = 0; i < size; i++) {
            if (node.getValue().equals(o)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Возвращает индекс последнего элемента со значением параметра "о".
     *
     * @param o элемент, который ищем
     * @return возвращает индекс элемента, либо -1, если он не был найден.
     */
    @Override
    public int lastIndexOf(Object o) {
        MyListNode<T> node = head;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (node.getValue().equals(o)) {
                index = i;
            }
        }
        return index;
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator() {
        return new ListItr();
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListItr(index);
    }

    @NotNull
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex > toIndex || fromIndex < 0 || toIndex >= size) {
            throw new IllegalArgumentException();
        }
        List<T> newList = new MyLinkedList<>();
        MyListNode<T> curNode = head;
        for (int i = 0; i < toIndex; i++) {
            if (i >= fromIndex) {
                newList.add(curNode.getValue());
                curNode = curNode.getNext();
            }
        }
        return newList;
    }

    /**
     * Получить значение по индексу
     *
     * @param index индекс
     * @return значение T
     */
    public T getValue(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс за границами допустимых пределов.");
        }
        MyListNode<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node.getValue();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        MyListNode<T> node = head;
        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(node.getValue());
            stringBuilder.append(", ");
            node = node.getNext();
        }
        stringBuilder.append(node.getValue());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}

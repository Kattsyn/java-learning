package kattsyn.collections;

public class MyLinkedList<T> {

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

        public void setNext(MyListNode<T> nextNode) {
            this.nextNode = nextNode;
        }
    }

    /**
     * Возвращает true, если список пуст
     */
    private boolean isEmpty() {
        return size == 0;
    }

    /**
     * Функция добавляет элемент в конец списка
     *
     * @param value значение
     */
    public void add(T value) {
        addLast(value);
    }

    /**
     * Добавляет новый элемент по индексу. Начиная с нуля (0).
     * Если список пуст, либо индекс больше длины списка,
     * то значение уйдет в конец списка.
     *
     * @param value значение
     * @param index индекс, на место которого добавим значение value
     */
    public void add(T value, int index) {
        if (index < 0 || index >= size) {
            addLast(value);
        } else if (index == 0) {
            addFirst(value);
        } else {
            MyListNode<T> currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            MyListNode<T> newNode = new MyListNode<T>(value, currentNode.getNext());
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
     * Функция добавляет элемент в конец списка
     *
     * @param value значение
     */
    public void addLast(T value) {
        if (size == 0) {
            head = new MyListNode<>(value);
        } else {
            MyListNode<T> listNode = head;
            for (int i = 0; i < size - 1; i++) {
                listNode = listNode.getNext();
            }
            listNode.setNext(new MyListNode<>(value, head));
        }
        size++;
    }

    /**
     * Меняет значение по индексу
     *
     * @param value значение
     * @param index индекс
     */
    public void set(T value, int index) {
        MyListNode<T> curNode = head;
        for (int i = 0; i < index; i++) {
            curNode = curNode.getNext();
        }
        curNode.setValue(value);
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
     * Удаляет элемент по индексу
     *
     * @param index индекс удаляемого элемента
     */
    public void remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс за границами допустимых пределов.");
        } else if (index == 0) {
            removeFirst();
        } else {
            MyListNode<T> node = head;
            for (int i = 0; i < index - 1; i++) {
                node = node.getNext();
            }
            node.setNext(node.getNext().getNext());
            size--;
        }
    }

    /**
     *
     * @return Длина списка
     */
    public int getSize() {
        return size;
    }

    /**
     * Получить значение по индексу
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

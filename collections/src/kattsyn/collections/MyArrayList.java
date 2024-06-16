package kattsyn.collections;


public class MyArrayList<T> {

    private static final int DEFAULT_ARRAY_CAPACITY = 20;
    private static final int ADDITIONAL_ARRAY_CAPACITY = 20;
    private Object[] array;
    private int size;

    public MyArrayList() {
        this.array = new Object[DEFAULT_ARRAY_CAPACITY];
        this.size = 0;
    }

    public MyArrayList(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public T getValue(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    private void increaseArrayLength() {
        Object[] newArr = new Object[array.length + ADDITIONAL_ARRAY_CAPACITY];
        for (int i = 0; i < array.length; i++) {
            newArr[i] = array[i];
        }
        this.array = newArr;
    }


    public void add(T value) {
        if (size < array.length) {
            this.array[size] = value;
            this.size++;
        } else {
            increaseArrayLength();
            this.array[size] = value;
            this.size++;
        }
    }

    public void add(int index, T value) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (size + 1 == array.length) {
            increaseArrayLength();
        }
        for (int i = size + 1; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        size++;
    }

    public void set(int index, T value) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = value;
    }

    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    public Object[] toArray() {
        Object[] arr = new Object[size];
        System.arraycopy(array, 0, arr, 0, size);
        return arr;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(array[i]).append(", ");
        }
        stringBuilder.append(array[size-1]).append("}");
        return stringBuilder.toString();
    }
}

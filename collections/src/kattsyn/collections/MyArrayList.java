import java.util.*;
import org.jetbrains.annotations.NotNull;

public class MyArrayList<T> implements List<T> {

    private static final int DEFAULT_ARRAY_CAPACITY = 20;
    private static final int ADDITIONAL_ARRAY_CAPACITY = 20;
    private Object[] array;
    private int size;

    public MyArrayList() {
        this.array = new Object[DEFAULT_ARRAY_CAPACITY];
        this.size = 0;
    }

    public MyArrayList(int capacity) {
        this.array = new Object[capacity]; //стоит проверять корректность ввода capacity >= 1
        this.size = 0;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NotNull
    public Iterator<T> iterator() {
        //вынеси в отдельный класс, можно как внутренний статический
        return new Iterator<T>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public T next() {
                try {
                    return (T) array[cursor++];
                } catch (IndexOutOfBoundsException e) {
                    throw new IndexOutOfBoundsException();
                }
            }
        };
    }

    @SuppressWarnings("unchecked")
    //для этого есть метод T get(int index)
    public T getValue(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    private void increaseArrayLength() {
        //в реальном ArrayList увеличивают не на какое-то кол-во а в 1.5 раза
        //так лучше с точки зрения уменьшения кол-ва операций увеличения
        this.array = Arrays.copyOf(array, array.length + ADDITIONAL_ARRAY_CAPACITY);
    }


    public boolean add(T value) {
        if (size >= array.length) {
            increaseArrayLength();
        }
        this.array[size] = value;
        this.size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        this.array = new Object[DEFAULT_ARRAY_CAPACITY];
        this.size = 0;
    }

    @Override
    public T get(int index) {
        return null;
    }

    public void add(int index, T value) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (size + 1 == array.length) {
            increaseArrayLength();
        }
        for (int i = size + 1; i > index; i--) {
            //System.arraycopy
            array[i] = array[i - 1];
        }
        array[index] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T value) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        T oldValue = (T) array[index];
        array[index] = value;
        return oldValue;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        T oldValue = (T) array[index];
        for (int i = index; i < size; i++) {
            //System.arraycopy
            array[i] = array[i + 1];
        }
        size--;
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public @NotNull ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public @NotNull ListIterator<T> listIterator(int index) {
        //вынеси отдельным классом как внутренний статический
        return new ListIterator<>() {
            int cursor = index;
            boolean calledNextOrPrev = false;
            boolean calledRemoveOrAdd = false;
            boolean calledAdd = false;

            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }
                T value = (T) array[cursor++];
                calledNextOrPrev = true;
                calledRemoveOrAdd = false;
                calledAdd = false;
                return value;
            }

            @Override
            public boolean hasPrevious() {
                return cursor != 0;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                T value = (T) array[--cursor];
                calledNextOrPrev = true;
                calledRemoveOrAdd = false;
                calledAdd = false;
                return value;
            }

            @Override
            public int nextIndex() {
                if (!hasNext()) {
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
                    MyArrayList.this.remove(cursor);
                    calledNextOrPrev = false;
                }
            }

            @Override
            public void set(T t) {
                if (calledNextOrPrev && !calledRemoveOrAdd) {
                    MyArrayList.this.set(cursor, t);
                }
            }

            @Override
            public void add(T t) {
                MyArrayList.this.add(cursor, t);
                calledRemoveOrAdd = true;
                calledAdd = true;
            }
        };
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public @NotNull List<T> subList(int fromIndex, int toIndex) {
        MyArrayList<T> newList = new MyArrayList<>();
        // а если fromIndex > toIndex
        if (fromIndex >= 0 && toIndex < size) {
            for (int i = fromIndex; i < toIndex; i++) {
                newList.add((T) array[i]);
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
        return newList;
    }


    public Object @NotNull [] toArray() {
        Object[] arr = new Object[size];
        System.arraycopy(array, 0, arr, 0, size);
        return arr;
    }

    @Override
    public <T1> T1 @NotNull [] toArray(T1 @NotNull [] a) {
        System.arraycopy(array, 0, a, 0, size);
        return a;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(array[i]).append(", ");
        }
        stringBuilder.append(array[size - 1]).append("}");
        return stringBuilder.toString();
    }
}

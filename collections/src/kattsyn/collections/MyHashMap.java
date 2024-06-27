package kattsyn.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyHashMap<K, V> implements Map<K, V> {

    public static class HashMapNode<K, V> {
        private final int hash;
        private final K key;
        private V value;
        private HashMapNode<K, V> next;

        public HashMapNode(K key, V value, HashMapNode<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hash = key.hashCode();
        }

        public HashMapNode(K key, V value) {
            this(key, value, null);
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public HashMapNode<K, V> getNext() {
            return next;
        }

        public void setNext(HashMapNode<K, V> next) {
            this.next = next;
        }

        public boolean hasNext() {
            return next != null;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }


    private int size;
    private static final int DEFAULT_NODE_ARRAY_SIZE = 8;
    private HashMapNode[] nodeArr;

    public MyHashMap() {
        this.nodeArr = new HashMapNode[DEFAULT_NODE_ARRAY_SIZE];
        this.size = 0;
    }


    /**
     * Возвращает индекс внутри nodeList
     * Если key = null, то вернет ноль (0),
     * иначе побитовое сравнение хеша ключа и длины массива - 1
     *
     * @param key ключ
     * @return индекс в массиве nodeList
     */
    private int getIndex(Object key) {
        return key == null ? 0 : key.hashCode() & (nodeArr.length - 1);
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        HashMapNode<K, V> node = nodeArr[getIndex(key)];

        if (node != null) {
            if (node.getKey().equals(key)) {
                return true;
            } else {
                while (node.hasNext()) {
                    node = node.getNext();
                    if (node.getKey().equals(key)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < nodeArr.length; i++) {
            HashMapNode<K, V> node = nodeArr[i];
            if (node != null) {
                if (node.getValue().equals(value)) {
                    return true;
                } else {
                    while (node.hasNext()) {
                        node = node.getNext();
                        if (node.getValue().equals(value)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    /**
     * Возвращает значение по ключу
     *
     * @param key the key whose associated value is to be returned
     * @return значение, если найдет, иначе null
     */
    @Override
    public V get(Object key) {
        HashMapNode<K, V> node = nodeArr[getIndex(key)];

        if (node != null) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            } else {
                while (node.hasNext()) {
                    node = node.getNext();
                    if (node.getKey().equals(key)) {
                        return node.getValue();
                    }
                }
            }
        }
        return null;
    }



    /**
     * Кладет значение value по ключу, если такого ключа еще не было в словаре,
     * если уже есть такой ключ, то переопределяет значение
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return возвращает null, если такого ключа не было, либо при переопределении значения, возвращает предыдущее значение узла.
     */
    @Override
    public V put(K key, V value) {
        int index = getIndex(key);
        HashMapNode<K, V> arrNode = nodeArr[index];
        if (arrNode == null) {
            nodeArr[index] = new HashMapNode<>(key, value);
            size++;
            return null;
        } else {
            if (arrNode.getKey().equals(key)) {
                V previousValue = arrNode.getValue();
                arrNode.setValue(value);
                return previousValue;
            } else {
                while (arrNode.hasNext()) {
                    arrNode = arrNode.getNext();
                    if (arrNode.getKey().equals(key)) {
                        V previousValue = arrNode.getValue();
                        arrNode.setValue(value);
                        return previousValue;
                    }
                }
                arrNode.setNext(new HashMapNode<>(key, value));
            }
        }
        size++;
        return null;
    }

    /**
     * Удаляет пару key=value по ключу. Если по вычисленному индексу ничего не лежит, значит и ключа такого нет уже, возвращаем null.
     * Далее проверяем не лежит ли нужный ключ в первом узле по индексу. Если да, то есть два случая: 1) он единственный в этом связном списке,
     * и тогда обнуляем список; 2) у него есть дальше узлы, тогда делаем головным узлом следующий. В любом случае выводим value.
     * Если он не первый в списке, то проходимся по этому списку, и если есть следующий, и его ключ равен нужному ключу, то сохраняем value,
     * и переопределяем ссылку setNext() на узел, который лежит через один нужному нашему. В противном случае, переходим в следующий узел,
     * и дальше проверяем по тому же принципу.
     * Если перебрав все элементы по этому индексу, ничего не нашли, то выходим из функции и возвращаем null
     * @param key key whose mapping is to be removed from the map
     * @return возвращает значение по ключу, если был такой ключ. Если такого ключа не было, то возвращает null.
     */
    @Override
    public V remove(Object key) {
        int index = getIndex(key);
        if (nodeArr[index] == null) {
            return null;
        }
        HashMapNode<K, V> node = nodeArr[index];
        if (node.getKey().equals(key)) {
            V value = node.getValue();
            if (node.hasNext()) {
                nodeArr[index] = node.getNext();
            } else {
                nodeArr[index] = null;
            }
            size--;
            return value;
        }
        while (node.hasNext()) {
            if (node.getNext().getKey().equals(key)) {
                V value = node.getNext().getValue();
                node.setNext(node.getNext().getNext());
                size--;
                return value;
            }
            node = node.getNext();
        }
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    /**
     * Очищает HashMap
     */
    @Override
    public void clear() {
        this.nodeArr = new HashMapNode[DEFAULT_NODE_ARRAY_SIZE];
        this.size = 0;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public String toString() {
        if(isEmpty()) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < nodeArr.length; i++) {
            HashMapNode<K, V> node = nodeArr[i];
            if (node != null) {
                stringBuilder.append(node);
                stringBuilder.append(", ");
                while (node.hasNext()) {
                    node = node.getNext();
                    stringBuilder.append(node);
                    stringBuilder.append(", ");
                }
            }
        }
        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "}");
        return stringBuilder.toString();
    }
}

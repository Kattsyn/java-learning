package kattsyn.edu;

public class Main {
    public static void main(String[] args) {

    }



    /*
    Параметризация возможна только для ссылочных типов данных
    TreeNode<String> .. - Okay
    TreeNode<Integer> .. - Okay
    TreeNode<int[]> ..  - Okay
    TreeNode<int> .. - Not Okay
    TreeNode<10> .. - Not Okay

    Пример класса с параметризацией из пакета java.utils
        после имени параметризованного класса в diamond записываем generic параметр(ы).
        если их несколько, то записываем через запятую
        Также можем поставить ограничение на принимаемый параметр словом extends
        Применимо как к классам, так и интерфейсам
        (то есть будет принимать класс от кого наследуется, либо какой интерфейс реализуется)
        Можно вставлять несколько таких параметров через &
    public final class Optional<T> {
        private final T value;
        далее наш параметр можем использовать для поля

                        либо для входного параметра в метод
        private Optional(T value) {
            this.value = Objects.requireNonNull(value);
        }

        Параметр заданный на уровне класса используется для параметризации экземпляров,
        поэтому недоступен в статических полях и методах, поэтому для таких методов
        мы можем объявить свой параметр после указания всех модификаторов, как в методе ниже

                   этот T уже не тот T, который относится к классу
        public static <T> Optional<T> of(T value) {
            return new Optional<>(value);
        }
            либо выходной параметр из метода
        public T get() {
            if (value == null) {
                throw new NoSuchElementException("No value present");
            }
            return value;
        }
    }

    Класс Optional нужен, чтобы четко понимать можно ли в программе использовать null значение для переменной,
    и нужно ли везде делать проверку на null
    Либо можно аннотацией показать нужно ли
    @Nullable
    @NonNull,
    но они не входят в перечень стандартных библиотек
    Из-за того что библиотеки не входят в перечень стандартных, компилятор не знает как их обрабатывать.
    Optional решает эту проблему
    String str = "bar";
    обычная ссылка
    Optional<String> optional = Optional.of("bar");
    потенциально отсутствующая ссылка
    для компилятора совершенно разные объекты, несовместимые друг с другом

    также добавляется новая возможность

    optional.isPresent(System.out::println);
    тоже самое что
    if (str != null) {
        System.out.println(str);
    }

    optional.orElse("bar"); вернет bar, если optional.value = null;
    тоже что и
    str != null ? str : "bar";


    Экземпляры класса Optional должны создаваться через фабричные статические методы:
    Optional<String> foo = Optional.empty(); //не содержащий ссылку на объект
    Optional<String> foo = Optional.of("bar"); //содержащий указанный объект, аргумент не может быть null
    Optional<String> foo = Optional.ofNullable("baz"); //2в1 предыдущие
    Компилятор видит какого типа мы передаем данные в метод. В данном случае String
    Но если передаем CharSequence, то нужно явно указать это компилятору
    Optional<CharSequence> optional = Optional.<CharSequence>ofNullable("var");
    Если бы конструктор был бы публичным, то писали бы
    Optional<String> optional = new Optional<String>("vaz");

    В подкапотке класс выглядит так, будто вместо <T> в Optional там Object

    Также можно создавать класс с дженериком не определяя его тип.
    Тогда дальнейшие операции на совести программиста, потому что джава не будет сама
    приводить типы данных к нужным
    Сделано это для того, чтобы код написанный до введения дженериков также продолжал работать

    Не работают следующие строки

    T obj = new T();
    T[] arr = new T[5];
    if (obj instanceof T) {...}

    T a = (T) b - хоть и скомпилируется, но ничего не сделает


    Number num = new Integer(5); - Okay
    Number[] numArray = new Integer[5]; - Okay

    В Optional от Integer нельзя присвоить Optional от Number

    Optional<Integer> optionalInt = Optional.of(1);
    Optional<Number> optionalNum = optionalInt;    НЕ СКОМПИЛИРУЕТСЯ


    В параметры метода можно передавать маски

    ... someMethod(Consumer<? super T> cons){...}  Принимаем консьюмер любого супер типа Т
    ... someMethod(Supplier<? extends T> supplier){...} Принимаем сапллаер любого подтипа Т

    Если нам все равно какой тип в дженерике
    можем в даймонд записать вопрос <?>
    тогда при получении объекта вернется объект типа Object, а передать туда какую-нибудь двойку (2)
    мы не можем

     */

}

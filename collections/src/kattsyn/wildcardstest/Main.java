package kattsyn.wildcardstest;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Labrador("Elinga"));
        animalList.add(new Cat("Catty"));
        animalList.add(new Dog("Doggy"));

        List<?> list = animalList;

        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("dogname1"));
        dogs.add(new Labrador("labrador1"));

        List<Labrador> labradors = new ArrayList<>();
        labradors.add(new Labrador("labrador1.2"));
        labradors.add(new Labrador("labrador1.fff2"));
        //labradors.add(new Dog("labrador1.2"));

        //list.add(new Animal("Name")); error
        //list.add((Object) new Integer(5)); error
        System.out.println(list.get(0)); //то есть при <?> мы можем только получать объекты и работать как с Object'ом.

        //method(animalList);
        method(dogs);
        method(labradors); //то есть при <? extends Dog> положить можем не выше Dog, а при работе с таким списком другие приколы.
        //Добавлять ничего не можем, кроме null, а вытаскивать можем от класса Dog и выше.

        method2(animalList);
        method2(dogs);
        //method2(labradors); //то есть при <? super Dog> можем класть списки, которые либо Dog, либо выше.
        //При работе внутри метода, мы сможем вытаскивать только элементы в виде Object, можно закастить до собаки, но если вдруг там будет кошка,
        //то вылетит ClassCastException
        //а класть что угодно, что Dog или ниже (например, Labrador)



    }

    public static void method(List<? extends Dog> list) {
        Dog dog = list.get(0); //норм
        //Labrador labrador = list.get(0); не норм
        Animal animal = list.get(0); //норм
        //list.add((Object) new Integer(5));
        //list.add(new Dog("asd"));
        /*
        По принципу PECS - Producer Extends Consumer Super. При слове extend нельзя ничего положить в список, кроме null.
        При этом получать мы можем от класса Dog (в нашем случае) и выше (то есть предков (Animal, Object)).
         */
    }

    public static void method2(List<? super Dog> list) {
        Dog dog = (Dog) list.get(0);// на деле возвращает Object, поэтому нужно явное приведение к Dog
        //Object dog = list.get(0); //но если вдруг там будет не Dog, а Cat, то будет ClassCastException))
        dog.voice();
        list.add(new Dog("Dog1")); //норм
        list.add(new Labrador("Dog123")); //норм
        //list.add(new Animal("animal")) //не норм
        //list.add(new Object()); //не норм
        /*
        При <? super Dog> можем класть что угодно что является Dog'ом, то есть это сам Dog и все его наследники.

        На стадии компиляции Generic'и стираются в Object

        Дженерики гарантируют безопасность их использования,
        соответственно во избежания каких-то ситуаций с ошибками в RunTime
        некоторые штуки обрезали для безопасности.
         */

    }
}

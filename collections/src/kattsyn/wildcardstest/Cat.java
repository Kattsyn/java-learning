package kattsyn.wildcardstest;

public class Cat extends Animal{


    public Cat(String name) {
        super(name);
    }

    @Override
    public void voice() {
        System.out.println("Meow");
    }
}

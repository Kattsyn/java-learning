package kattsyn.wildcardstest;

public class Dog extends Animal{


    public Dog(String name) {
        super(name);
    }

    @Override
    public void voice() {
        System.out.println("Woof");
    }
}

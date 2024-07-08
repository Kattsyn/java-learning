package kattsyn.wildcardstest;

public class Animal {

    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void voice() {
        System.out.println("Animal voice");
    }

    @Override
    public String toString() {
        return name;
    }
}

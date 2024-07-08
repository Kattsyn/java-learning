package kattsyn.wildcardstest;

public class Labrador extends Dog {


    public Labrador(String name) {
        super(name);
    }


    @Override
    public void voice() {
        System.out.println("loud woof");
    }
}

package kattsyn.tasks.task1;

import java.util.logging.*;

public class Main {
    public static void main(String[] args) {

    }

    private static void configureLogging(){
        Logger.getLogger("org.stepic.java.logging.ClassA").setLevel(Level.ALL);
        Logger.getLogger("org.stepic.java.logging.ClassB").setLevel(Level.WARNING);
        Logger.getLogger("org.stepic.java").setUseParentHandlers(false);
        Handler handler = new ConsoleHandler();
        handler.setFormatter(new XMLFormatter());
        handler.setLevel(Level.ALL);
        Logger.getLogger("org.stepic.java").addHandler(handler);
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return factorial(n-1) * n;
    }
}

package kattsyn.task0;

public class Main {
    public static int intVar;
    public static long longVar;
    public static byte byteVar;
    public static short shortVar;
    public static double doubleVar;
    public static float floatVar;
    public static boolean boolVar;
    public static char charVar;

    public static void main(String[] args) {
        outVariables();

        intVar = 1253253;
        longVar = 5_432_123_414_231L;
        byteVar = 120;
        shortVar = 23000;
        doubleVar = 1324.1233;
        floatVar = 82.13F;
        boolVar = true;
        charVar = 102;

        outVariables();
    }
    public static void outVariables(){
        System.out.println(intVar);
        System.out.println(longVar);
        System.out.println(byteVar);
        System.out.println(shortVar);
        System.out.println(doubleVar);
        System.out.println(floatVar);
        System.out.println(boolVar);
        System.out.println(charVar);
    }
}

package kattsyn.task4;


public class Main {


    public static void main(String[] args) {
        System.out.println(doubleExpression(0.1, 0.2, 0.3000001)); //true
        System.out.println(doubleExpression(0.2, 0.2, 0.400000001)); //true
        System.out.println(doubleExpression(0.1, 0.2, 0.7000001)); //false
        System.out.println(doubleExpression(0.15, 0.2, 0.3500001)); //true
        System.out.println(doubleExpression(0.1, 0.2, 0.3500001)); //false
        System.out.println(doubleExpression(0.1, 0.2, 0.03000001)); //false
    }

    public static boolean doubleExpression(double a, double b, double c) {
        return (c - a - b <= 0.00001) & (c - a - b >= -0.00001);
    }
}

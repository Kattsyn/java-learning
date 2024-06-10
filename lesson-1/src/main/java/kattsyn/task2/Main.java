package kattsyn.task2;

public class Main {
    public static void main(String[] args) {
        System.out.println(booleanExpression(true, true, false, false)); //true
        System.out.println(booleanExpression(true, true, false, true)); //false
        System.out.println(booleanExpression(false, true, false, false)); //false
        System.out.println(booleanExpression(true, false, false, true)); //true
        System.out.println(booleanExpression(true, true, true, true)); //false
        System.out.println(booleanExpression(false, false, false, false)); //false
        System.out.println("----");
        System.out.println(booleanExpressionVer2(true, true, false, false)); //true
        System.out.println(booleanExpressionVer2(true, true, false, true)); //false
        System.out.println(booleanExpressionVer2(false, true, false, false)); //false
        System.out.println(booleanExpressionVer2(true, false, false, true)); //true
        System.out.println(booleanExpressionVer2(true, true, true, true)); //false
        System.out.println(booleanExpressionVer2(false, false, false, false)); //false
    }

    public static boolean booleanExpression(boolean a, boolean b, boolean c, boolean d) {
        int boolCounter = 0;
        if (a) {
            boolCounter++;
        }
        if (b) {
            boolCounter++;
        }
        if (c) {
            boolCounter++;
        }
        if (d) {
            boolCounter++;
        }
        return boolCounter == 2;
    }

    public static boolean booleanExpressionVer2(boolean a, boolean b, boolean c, boolean d) {
        return (a&b&!c&!d || a&!b&c&!d || a&!b&!c&d || !a&b&c&!d || !a&b&!c&d || !a&!b&c&d);
    }
}

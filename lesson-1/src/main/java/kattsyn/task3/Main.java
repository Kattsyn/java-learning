package kattsyn.task3;

public class Main {
    public static void main(String[] args) {
        System.out.println(isLeapYear(2024)); //true
        System.out.println(isLeapYear(1992)); //true
        System.out.println(isLeapYear(1900)); //false
        System.out.println(isLeapYear(1600)); //true
    }

    public static boolean isLeapYear(int year){
        if (((year % 4 == 0) & (year % 100 != 0)) || (year % 400 == 0)) {
            return true;
        }
        return false;
    }
}

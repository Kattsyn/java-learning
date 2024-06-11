package kattsyn;

import kattsyn.exceptions.task1.RobotConnection;
import kattsyn.exceptions.task1.RobotConnectionManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean sucd = true;
        for (int i = 0; i < 3 && sucd; i++) {
            System.out.println(i);
            sucd = false;
        }
        String s;
        try {
            s = "ddd";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

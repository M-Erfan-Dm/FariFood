package ir.ac.kntu.utils;

import java.util.Scanner;

public class ScannerWrapper {
    private static final Scanner instance = new Scanner(System.in);

    private ScannerWrapper() {
    }


    public static String nextLine() {
        return instance.nextLine();
    }

    public static String next() {
        return instance.next();
    }

    public static void close() {
        instance.close();
    }
}

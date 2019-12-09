package com.example.Exceptions_Assertions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ThrowsClauseDemo {
    public static void main(String[] args) throws FileNotFoundException {
        ThrowsClause3();
    }

    public static void ThrowsClause1() throws FileNotFoundException {
        System.out.println("Reading an integer from the file 'integer.txt': ");
        Scanner consoleScanner = new Scanner(new File("integer.txt"));
        System.out.println("You typed the integer value: " + consoleScanner.nextInt());
    }

    public static void ThrowsClause2() throws FileNotFoundException {
        System.out.println("Reading an integer from the file 'integer.txt': ");
        Scanner consoleScanner = new Scanner(new File("integer.txt"));
        System.out.println("You typed the integer value: " + consoleScanner.nextInt());
    }

    // since this method does not handle FileNotFoundException,
    // the method must declare this exception in the throws clause
    public int readIntFromFile() throws FileNotFoundException {
        Scanner consoleScanner = new Scanner(new File("integer.txt"));
        return consoleScanner.nextInt();
    }

    // since readIntFromFile() throws FileNotFoundException and main() does not handle
    // it, the main() method declares this exception in its throws clause
    public static void ThrowsClause3() throws FileNotFoundException {
        System.out.println("Reading an integer from the file 'integer.txt': ");
        System.out.println("You typed the integer value: " + new ThrowsClauseDemo().readIntFromFile());
    }


    // This interface is meant for implemented by classes that would read an integer from a file
    interface IntReader {
        int readIntFromFile() throws IOException;
    }

    class ThrowsClause4 implements IntReader {
        // implement readIntFromFile with the same throws clause
        // or a more specific throws clause
        public int readIntFromFile() throws FileNotFoundException {
            Scanner consoleScanner = new Scanner(new File("integer.txt"));
            return consoleScanner.nextInt();
        }
        // main method elided in this code since the focus here is to understand
        // issues related to overriding when throws clause is present
    }

}

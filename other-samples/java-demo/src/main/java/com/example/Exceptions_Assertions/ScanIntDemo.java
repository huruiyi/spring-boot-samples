package com.example.Exceptions_Assertions;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScanIntDemo {

  public static void main(String[] args) {

  }

  public static void ScanInt1(String[] args) {
    System.out.println("Type an integer in the console: ");
    Scanner consoleScanner = new Scanner(System.in);
    System.out.println("You typed the integer value: " + consoleScanner.nextInt());
  }

  public static void ScanInt2(String[] args) {
    System.out.println("Type an integer in the console: ");
    Scanner consoleScanner = new Scanner(System.in);
    try {
      System.out.println("You typed the integer value: " + consoleScanner.nextInt());
    } catch (InputMismatchException ime) {
      // nextInt() throws InputMismatchException in case anything
      // other than an integer is typed in the console; so handle it
      System.out.println("Error: You typed some text that is not an integer value...");
    }
  }

  public static void ScanInt3(String[] args) {
    String integerStr = "100";
    System.out.println("The string to scan integer from it is: " + integerStr);
    Scanner consoleScanner = new Scanner(integerStr);
    try {
      System.out.println("The integer value scanned from string is: " + consoleScanner.nextInt());
    } catch (InputMismatchException ime) {
      // nextInt() throws InputMismatchException in case
      // anything other than an integeris provided in the string
      System.out.println("Error: Cannot scan an integer from the given string");
    }
  }

  public static void ScanInt4(String[] args) {
    String integerStr = "";
    System.out.println("The string to scan integer from it is: " + integerStr);
    Scanner consoleScanner = new Scanner(integerStr);
    try {
      System.out.println("The integer value scanned from string is: " + consoleScanner.nextInt());
    } catch (InputMismatchException ime) {
      System.out.println("Error: Cannot scan an integer from the given string");
    } catch (NoSuchElementException nsee) {
      System.out.println("Error: Cannot scan an integer from the given string");
    } catch (IllegalStateException ise) {
      System.out.println("Error: nextInt() called on a closed Scanner object");
    }
  }

  public static void ScanInt5(String[] args) {
    String integerStr = "";
    System.out.println("The string to scan integer from it is: " + integerStr);
    Scanner consoleScanner = new Scanner(integerStr);
    try {
      System.out.println("The integer value scanned from string is: " + consoleScanner.nextInt());
    } catch (NoSuchElementException | IllegalStateException multie) {
      System.out.println("Error: An error occured while attempting to scan the integer");
    }
  }

  public static void ScanInt6(String[] args) {
    System.out.println("Type an integer in the console: ");
    Scanner consoleScanner = new Scanner(System.in);
    try {
      System.out.println("You typed the integer value: " + consoleScanner.nextInt());
      System.out.println("Done reading the text... closing the Scanner");
      consoleScanner.close();
    } catch (Exception e) {
      // call all other exceptions here ...
      System.out.println("Error: Encountered an exception and could not read an integer from the console... ");
      System.out.println("Exiting the program - restart and try the program again!");
    }
  }

  public static void ScanInt7(String[] args) {
    System.out.println("Type an integer in the console: ");
    Scanner consoleScanner = new Scanner(System.in);
    try {
      System.out.println("You typed the integer value: " + consoleScanner.nextInt());
    } catch (Exception e) {
      // call all other exceptions here ...
      System.out.println("Error: Encountered an exception and could not read an integer from the console... ");
      System.out.println("Exiting the program - restart and try the program again!");
    } finally {
      System.out.println("Done reading the integer... closing the Scanner");
      consoleScanner.close();
    }
  }
}

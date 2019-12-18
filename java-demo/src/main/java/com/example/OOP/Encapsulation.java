package com.example.OOP;

public class Encapsulation {

    static class BankAccount {

        public BankAccount() {
            balance = 1000;
        }

        private int balance;

        int getBalance() {
            return balance;
        }

        private void setBalance(int amount) {
            balance = amount;
        }

        boolean withdraw(int amount) {
            if (balance > amount)
                setBalance(balance - amount);
            else
                return false;
            return true;
        }

        boolean deposit(int amount) {
            setBalance(balance + amount);
            return true;
        }
    }

    public static void main(String[] args) {

        BankAccount a = new BankAccount();

        a.deposit(1500);

        if (a.withdraw(24))
            System.out.println("OK");
        else
            System.out.println("NOT ENOUGH MONEY");

        System.out.println(a.getBalance());

    }

}


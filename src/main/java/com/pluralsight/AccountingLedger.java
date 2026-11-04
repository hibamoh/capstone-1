package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AccountingLedger {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        String fileName = "src/main/resources/transactions.csv";
        ArrayList<Transaction> transactions = AccountingLedger.loadTransactions(fileName);
        System.out.println("Welcome to Hub's Accounting Ledger!");
        homeScreen(transactions, myScanner, fileName);
    }

    public static ArrayList<Transaction> loadTransactions(String fileName) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);


            String line = bufferedReader.readLine();
            while (line != null) {
                Transaction transaction = parseTransaction(line);
                transactions.add(transaction);
                System.out.printf("%s %s %s %s $%.2f%n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());

                line = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("I am sorry, it seems like that file does not exist");

        } catch (IOException e) {
            System.out.println("There seems to be an issue, try again");
            ;
        }

        return transactions;

    }

    private static void homeScreen(ArrayList<Transaction> transactions, Scanner myScanner, String fileName) {
        String menuPrompt = """
                What would you like to do today?
                D) Add Deposit
                P) Make a Payment
                L) Ledger
                X) Exit the application
                """;
        boolean exit = false;

        do {
            System.out.println(menuPrompt);
            String userInput = myScanner.nextLine();

            switch (userInput) {
                case "D" -> addDeposit(transactions, myScanner, fileName);
                case "P" -> makePayment();
                case "L" -> displayLedger();
                case "X" -> {
                    exit = true;
                    System.out.println("So sad to watch you go... we are watching ");
                }

                default -> System.out.println("Please choose one of the options above");
            }
        }
        while (!exit);
    }

    private static void displayLedger() {


    }

    private static void makePayment() {

    }

    private static void addDeposit(ArrayList<Transaction> transactions, Scanner myScanner, String fileName) {
        System.out.println("get your money up & not your funny up!");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        String date = now.format(dateFormat);
        String time = now.format(timeFormat);

        System.out.println("Description: ");
        String description = myScanner.nextLine();

        System.out.println("Vendor: ");
        String vendor = myScanner.nextLine();

        System.out.println("Amount: ");
        double amount = Double.parseDouble(myScanner.nextLine());
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);

        Transaction newTransaction = new Transaction(localDate, localTime, description, vendor, amount);
        transactions.add(newTransaction);
        saveTransactionToFile(fileName, newTransaction);
    }

    private static void saveTransactionToFile(String fileName, Transaction transaction) {
        try {
            // true means "append" to the existing file instead of overwriting it
            FileWriter fileWriter = new FileWriter(fileName, true);

            // this makes it so that the information is displayed as: date|time|description|vendor|amount
            String line = transaction.getDate() + "|" +
                    transaction.getTime() + "|" +
                    transaction.getDescription() + "|" +
                    transaction.getVendor() + "|" +
                    transaction.getAmount();

            fileWriter.write(line);
            fileWriter.write("\n"); // adds a new line so the next transaction starts on a new row

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Erm, it seems as though there is an issue..");
        }
    }

    // this method parses the csv file
    private static Transaction parseTransaction(String line) {
        String[] lineParts = line.split(Pattern.quote("|"));
        LocalDate date = LocalDate.parse(lineParts[0]);
        LocalTime time = LocalTime.parse(lineParts[1]);
        String description = lineParts[2];
        String vendor = lineParts[3];
        double amount = Double.parseDouble(lineParts[4]);

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        return transaction;
    }

}

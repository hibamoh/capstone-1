package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
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

    public static ArrayList<Transaction> loadTransactions(String fileName)  {
        ArrayList<Transaction> transactions = new ArrayList<>();
        BufferedReader bufferedReader;

        try {
            FileReader fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String line = bufferedReader.readLine();
            while (line != null) {
                Transaction transaction = parseTransaction(line);
                transactions.add(transaction);
                //System.out.printf("%s %s %s %s $%.2f%n", transaction.getDate(), transaction.getTime(),
                //        transaction.getDescription(), transaction.getVendor(), transaction.getAmount());

                line = bufferedReader.readLine();
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("I am sorry, it seems like that file does not exist");

        } catch (IOException e) {
            System.out.println("There seems to be an issue, try again");
            ;
        }
        transactions.sort(Comparator.comparing(Transaction::getDate).reversed());

        return transactions;

    }

    private static void displayAll(ArrayList<Transaction> transactions){
        for (Transaction transaction : transactions) {
            System.out.printf("%s %s %s %s $%.2f%n", transaction.getDate(), transaction.getTime(),
                    transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
        }
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
                case "D","d" -> addDeposit(transactions, myScanner, fileName);
                case "P","p" -> makePayment(transactions, myScanner, fileName);
                case "L","l" -> displayLedger(transactions, myScanner, fileName);
                case "X","x" -> {
                    exit = true;
                    System.out.println("So sad to watch you go... we do watch you..");
                }
                default -> System.out.println("Please choose one of the options above");
            }
        }
        while (!exit);
    }

    private static void displayLedger(ArrayList<Transaction> transactions, Scanner myScanner, String fileName) {

        String menuPrompt = """
                A) All - Display all entries.
                D) Deposits - View all your deposits.
                P) Payments - View all payments
                R) Reports - Run a search
                H) Home - Go home page
                """;
        boolean exit = false;

        do {
            System.out.println(menuPrompt);
            String userInput = myScanner.nextLine();

            switch (userInput) {
                case "A","a" -> displayAll(transactions);
                case "D","d" -> displayDeposit(transactions);
                case "P","p" -> displayPayments(transactions);
                case "R","r" -> displayReports(myScanner, transactions, fileName);
                case "H","h" ->  {
                    exit = true;
                    System.out.println("sending you back, you don't have to go home but you can't stay here! ");
                    homeScreen(transactions, myScanner, fileName);
                }
                default -> System.out.println("Please choose one of the options above");
            }
        }
        while (!exit);

    }

    private static void displayReports(Scanner myScanner, ArrayList<Transaction> transactions, String fileName){
        String menuPrompt = """
                1) Month to date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by vendor
                0) Back
                """;
        boolean exit = false;
        do {
            System.out.println(menuPrompt);
            String userInput = myScanner.nextLine();

            switch (userInput) {
                case "1" -> monthToDate(transactions);
                case "2" -> previousMonth(transactions);
                case "3" -> yearToDate(transactions);
                case "4" -> previousYear(transactions);
                case "5" -> searchByVendor(transactions);
                case "0" -> {
                    exit = true;
                    System.out.println("sending you back, you don't have to go home but you can't stay here! ");
                    displayLedger(transactions, myScanner,fileName);
                }
                default -> System.out.println("Please choose one of the options above");
            }
        }
        while (!exit);


    }

    private static void searchByVendor() {
    }

    private static void previousYear(ArrayList<Transaction> transactions) {
        System.out.println("all your transactions from this year month: ");
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();

        ArrayList<Transaction> previousYear = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getDate().getYear()  == currentYear -1) {
                previousYear.add(t);
                System.out.printf("%s %s %s %s $%.2f%n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
    }

    private static void yearToDate(ArrayList<Transaction> transactions) {
        System.out.println("all your transactions from this year month: ");
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();

        ArrayList<Transaction> yearToDate = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getDate().getYear() == currentYear ) {
                yearToDate.add(t);
                System.out.printf("%s %s %s %s $%.2f%n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
    }

    private static void previousMonth(ArrayList<Transaction> transactions) {
        System.out.println("all your transactions from last month: ");
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();

        ArrayList<Transaction> previousMonth = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getDate().getMonthValue() == currentMonth - 1 && t.getDate().getYear() == currentYear) {
                previousMonth.add(t);
                System.out.printf("%s %s %s %s $%.2f%n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
    }


    private static void monthToDate(ArrayList<Transaction> transactions) {
        System.out.println("all your transactions from this month: ");
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();

        ArrayList<Transaction> monthToDate = new ArrayList<>();
        for(Transaction t : transactions){
            if(t.getDate().getMonthValue() == currentMonth && t.getDate().getYear() == currentYear){
                monthToDate.add(t);
                System.out.printf("%s %s %s %s $%.2f%n", t.getDate(),t.getTime(), t.getDescription(),t.getVendor(),t.getAmount());
            }

        }

    }

    private static void displayPayments(ArrayList<Transaction> transactions) {
        System.out.println("--- where you getting your coins from? ---");
        for (Transaction t: transactions){ // Transaction t is a temp variable that will hold the current transaction
            // : transactions is for each item in the ArrayList
            if (t.getAmount() < 0.0){
                System.out.printf("%s %s %s %s $%.2f%n", t.getDate(),t.getTime(), t.getDescription(),t.getVendor(),t.getAmount());
            }
        }
        System.out.println("--- that is the end of your deposits ---");
    }

    private static void displayDeposit(ArrayList<Transaction> transactions) {
        System.out.println("--- where you getting your coins from? ---");
        for (Transaction t: transactions){ // Transaction t is a temp variable that will hold the current transaction
                                            // : transactions is for each item in the ArrayList
            if (t.getAmount() > 0.0){
                System.out.printf("%s %s %s %s $%.2f%n", t.getDate(),t.getTime(), t.getDescription(),t.getVendor(),t.getAmount());
            }
        }
        System.out.println("--- that is the end of your deposits ---");

    }

    private static void makePayment(ArrayList<Transaction> transactions,
                                    Scanner myScanner, String fileName) {
        System.out.println("where is all your money going to?");
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
        System.out.println("You lost your dabloons :(");

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
        System.out.println("You got your money up!");
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
            System.out.println("Erm, it seems as though there is an issue saving the transaction file..");
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

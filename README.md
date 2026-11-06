<img width="498" height="245" alt="йокелемене-men-in-black" src="https://github.com/user-attachments/assets/4c312a51-cc03-4ec4-b1fe-8683e2118c82" />

*remove the last 20 presentations from memory*

# *Keepsake Bank*
   *Hold onto what really matters.*                                                                                     

## Descripton

What I created was a simple CLI app that tracks financial transactions. I took creative liberty and spiced it up so that, rather than being a regular bank, it is a memory bank. The memory bank allows you to add and remove memories that cost bliss points. Instead of a typical vendor, it tracks who the memory is shared with. It lets users view their ledgers and filter their reports. It is all saved to a CSV file. 

## What is it made up of?

**Great Question!**

The program is made up of two classes:

### Transaction.java 
This is the blueprint for every transaction. It is made up of: 

* Five private fields: date, time, description, vendor, amount
* A parameterized constructor that initializes the object
* Five getters and setters for the respective fields

### AccountingLedger.java
This class is what runs. It is composed of various methods that:
* Displays the various screens and redirects to the correct screen
* Loads the transactions from the CSV file and sorts them
* Allows users to add and remove memories
* Filters the reports.


                                               ₊˚ ✧ ━━━━⊱⋆⊰━━━━ ✧ ₊˚

The program begins by running the AccountingLedger. It will display the logo.
<img width="1589" height="404" alt="image" src="https://github.com/user-attachments/assets/69adbd1a-eaf3-4904-b38d-5c7c4520973a" />
Followed up with the home screen

``` private static void homeScreen(ArrayList<Transaction> transactions, Scanner myScanner, String fileName) {
        String menuPrompt = """
                What would you like to do today?
                D) Add A Memory
                P) Remove a memory
                L) Ledger
                X) Exit the application
                """;
        boolean exit = false;

        do {
            System.out.println(menuPrompt);
            String userInput = myScanner.nextLine();

            switch (userInput) {
                case "D", "d" -> addDeposit(transactions, myScanner, fileName);
                case "P", "p" -> makePayment(transactions, myScanner, fileName);
                case "L", "l" -> displayLedger(transactions, myScanner, fileName);
                case "X", "x" -> {
                    exit = true;
                    System.out.println("So sad to watch you go... we do watch you..");
                }
                default -> System.out.println("Please choose one of the options above");
            }
        }
        while (!exit);
    } ```



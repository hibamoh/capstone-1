<img width="498" height="245" alt="йокелемене-men-in-black" src="https://github.com/user-attachments/assets/4c312a51-cc03-4ec4-b1fe-8683e2118c82" />

*remove the last 20 presentations from memory*

 # *Keepsake Bank*
   *Hold onto what really matters.*                                                                                     

## Descripton

What I created was a simple CLI app that tracks financial transactions. I took creative liberty and spiced it up so that, rather than being a regular bank, it is a memory bank. The memory bank allows you to add and remove memories that cost bliss points. Instead of a typical vendor, it tracks who the memory is shared with. It lets users view their ledgers and filter their reports. It is all saved to a CSV file. 

## What is it made up of?

**Great Question!**

The program is made up of two classes:

### ⟢──────˚₊‧꒰ა Transaction.java ໒꒱ ‧₊˚──────⟢
This is the blueprint for every transaction. It is made up of: 

* Five private fields: date, time, description, vendor, amount
* A parameterized constructor that initializes the object
* Five getters and setters for the respective fields

### ⟢──────˚₊‧꒰ა AccountingLedger.java ໒꒱ ‧₊˚──────⟢
This class is what runs. It is composed of various methods that:
* Displays the various screens and redirects to the correct screen
* Loads the transactions from the CSV file and sorts them
* Allows users to add and remove memories
* Filters the reports.


                                               ₊˚ ✧ ━━━━⊱⋆⊰━━━━ ✧ ₊˚
## How does it run?

**1. The program begins by running the AccountingLedger. It will display the logo.** 

<img width="1589" height="404" alt="image" src="https://github.com/user-attachments/assets/69adbd1a-eaf3-4904-b38d-5c7c4520973a" />

Followed up with the home screen: 

<br>

<img width="467" height="188" alt="image" src="https://github.com/user-attachments/assets/788d3356-3f81-4e45-88ed-21bb51ed393d" />

2. Users will enter their desired action and then proceed.

* **Add/Remove a Memory** - will prompt users to add information about their dream.
  
<img width="535" height="206" alt="image" src="https://github.com/user-attachments/assets/2cda09ef-452e-45ee-9b63-5d1b8018c222" />

* **Ledger** - takes the user to another screen that prompts them for certain displays.
  <img width="568" height="192" alt="image" src="https://github.com/user-attachments/assets/f5a85b47-5479-4a9d-b97a-33ca84b8ecb9" />

3. 

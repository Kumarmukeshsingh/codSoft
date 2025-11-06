 import java.util.Scanner;

// Class to represent the user's Bank Account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(" Deposit Successful! Current Balance: ₹" + balance);
        } else {
            System.out.println(" Invalid deposit amount.");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(" Withdrawal Successful! Current Balance: ₹" + balance);
        } else if (amount > balance) {
            System.out.println(" Insufficient Balance! Current Balance: ₹" + balance);
        } else {
            System.out.println(" Invalid withdrawal amount.");
        }
    }

    // Check balance
    public void checkBalance() {
        System.out.println(" Current Balance: ₹ " + balance);
    }
}

// Class to represent the ATM Machine
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Display main menu
    public void showMenu() {
        int choice;

        do {
            System.out.println("\n==============================");
            System.out.println("        ATM INTERFACE");
            System.out.println("==============================");
            System.out.println("1  Check Balance");
            System.out.println("2  Deposit Money");
            System.out.println("3  Withdraw Money");
            System.out.println("4  Exit");
            System.out.print("1 Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    account.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println(" Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println(" Invalid choice! Please try again.");
            }
        } while (choice != 4);
    }
}

// Main Class
public class Atm_Machine {
    public static void main(String[] args) {
        // Create a bank account with an initial balance
        BankAccount userAccount = new BankAccount(5000.0);

        // Create ATM object linked to the user's account
        ATM atmMachine = new ATM(userAccount);

        // Start ATM interface
        atmMachine.showMenu();
    }
}

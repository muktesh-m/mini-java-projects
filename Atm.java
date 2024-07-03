import java.util.Scanner;

class BankAccount {
    // Constants
    private static final float MAX_DEPOSIT_AMOUNT = 100000f;
    private static final float MAX_TRANSFER_AMOUNT = 50000f;

    private String name;
    private String userName;
    private String password;
    private String accountNo;
    private float balance = 100000f;
    private int transactions = 0;
    private String transactionHistory = "";

    public void register() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Your Name: ");
        this.name = sc.nextLine();
        System.out.print("Enter Your Username: ");
        this.userName = sc.nextLine();
        System.out.print("Enter Your Password: ");
        this.password = sc.nextLine();
        System.out.print("Enter Your Account Number: ");
        this.accountNo = sc.nextLine();
        System.out.println("\nRegistration completed. Kindly login.");
    }

    public boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Your Username: ");
        String enteredUsername = sc.nextLine();
        if (!enteredUsername.equals(userName)) {
            System.out.println("\nUsername not found");
            return false;
        }

        System.out.print("Enter Your Password: ");
        String enteredPassword = sc.nextLine();
        if (!enteredPassword.equals(password)) {
            System.out.println("\nIncorrect Password");
            return false;
        }

        System.out.print("\nLogin successful!");
        return true;
    }

    public void withdraw() {
        System.out.print("\nEnter amount to withdraw: ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();

        if (balance >= amount) {
            transactions++;
            balance -= amount;
            System.out.println("\nWithdrawal Successful");
            String str = amount + " Rs Withdrawn\n";
            transactionHistory = transactionHistory.concat(str);
        } else {
            System.out.println("\nInsufficient Balance");
        }
    }

    public void deposit() {
        System.out.print("\nEnter amount to deposit: ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();

        if (amount <= MAX_DEPOSIT_AMOUNT) {
            transactions++;
            balance += amount;
            System.out.println("\nSuccessfully Deposited");
            String str = amount + " Rs deposited\n";
            transactionHistory = transactionHistory.concat(str);
        } else {
            System.out.println("\nSorry, the deposit limit is " + MAX_DEPOSIT_AMOUNT);
        }
    }

    public void transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Recipient's Name: ");
        String recipient = sc.nextLine();
        System.out.print("Enter amount to transfer: ");
        float amount = sc.nextFloat();

        if (balance >= amount) {
            if (amount <= MAX_TRANSFER_AMOUNT) {
                transactions++;
                balance -= amount;
                System.out.println("\nSuccessfully Transferred to " + recipient);
                String str = amount + " Rs transferred to " + recipient + "\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("\nSorry, the transfer limit is " + MAX_TRANSFER_AMOUNT);
            }
        } else {
            System.out.println("\nInsufficient Balance");
        }
    }

    public void checkBalance() {
        System.out.println("\nCurrent Balance: " + balance + " Rs");
    }

    public void transactionHistory() {
        if (transactions == 0) {
            System.out.println("\nTransaction History is Empty");
        } else {
            System.out.println("\nTransaction History:\n" + transactionHistory);
        }
    }
}

class Atm {
    private static final int REGISTER_OPTION = 1;
    private static final int EXIT_OPTION = 2;
    private static final int LOGIN_OPTION = 1;
    private static final int EXIT_ATM_OPTION = 6;

    public static int getValidChoice(int limit) {
        int choice = 0;
        boolean isValidChoice = false;
        Scanner sc = new Scanner(System.in);

        while (!isValidChoice) {
            System.out.print("Enter Your Choice: ");
            try {
                choice = sc.nextInt();
                if (choice >= 1 && choice <= limit) {
                    isValidChoice = true;
                } else {
                    System.out.println("Choose a number between 1 and " + limit);
                }
            } catch (Exception e) {
                System.out.println("Enter only an integer value");
                sc.nextLine(); // Clear the input buffer
            }
        }
        return choice;
    }

    public static void main(String[] args) {
        System.out.println("\n********** WELCOME TO SBI ATM SYSTEM **********\n");
        System.out.println("1. Register");
        System.out.println("2. Exit");

        int choice = getValidChoice(EXIT_OPTION);
        if (choice == REGISTER_OPTION) {
            BankAccount bankAccount = new BankAccount();
            bankAccount.register();

            while (true) {
                System.out.println("\n1. Login");
                System.out.println("2. Exit");
                int loginChoice = getValidChoice(EXIT_OPTION);

                if (loginChoice == LOGIN_OPTION) {
                    if (bankAccount.login()) {
                        System.out.println("\n\n********** WELCOME BACK  **********\n");
                        boolean isFinished = false;

                        while (!isFinished) {
                            System.out.println("\n1. Withdraw");
                            System.out.println("2. Deposit");
                            System.out.println("3. Transfer");
                            System.out.println("4. Check Balance");
                            System.out.println("5. Transaction History");
                            System.out.println("6. Exit");

                            int userChoice = getValidChoice(EXIT_ATM_OPTION);
                            switch (userChoice) {
                                case 1:
                                    bankAccount.withdraw();
                                    break;
                                case 2:
                                    bankAccount.deposit();
                                    break;
                                case 3:
                                    bankAccount.transfer();
                                    break;
                                case 4:
                                    bankAccount.checkBalance();
                                    break;
                                case 5:
                                    bankAccount.transactionHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}

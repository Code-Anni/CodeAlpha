import java.util.Scanner;

public class OnlineQuizPlatform {
    private static double balance = 0;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;
            
            while (!exit) {
                System.out.println("Banking Application");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Check Balance");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1 -> deposit(scanner);
                    case 2 -> withdraw(scanner);
                    case 3 -> checkBalance();
                    case 4 -> {
                        exit = true;
                        System.out.println("Thank you for using my platform. Have a nice Day!");
                    }
                    default -> System.out.println("Invalid option. Please choose a valid option.");
                }
            }
        }
    }

    private static void deposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    private static void withdraw(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    private static void checkBalance() {
        System.out.println("Current balance: $" + balance);
    }
}

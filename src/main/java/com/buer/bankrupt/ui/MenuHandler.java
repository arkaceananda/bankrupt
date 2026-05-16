package com.buer.bankrupt.ui;

import com.buer.bankrupt.repository.UserRepository;
import com.buer.bankrupt.service.BankService;
import java.util.Scanner;

public class MenuHandler {
    private static final UserRepository repository = new UserRepository();
    private static final BankService service = new BankService(repository);
    static void main() {
        showMainMenu();
    }

    static void showMainMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Welcome to Bankrupt Menu");
                System.out.println("""
                        1. Login
                        2. Create New Account
                        3. Exit""".stripIndent());
                String input = scanner.nextLine().trim();

                try {
                    switch (input) {
                        case "1":
                            System.out.println("Enter your username: ");
                            String name = scanner.nextLine();
                            if (name == null || name.isBlank()) {
                                System.err.println("[!!] Name cannot be null.");
                                break;
                            }
                            System.out.println("Enter your account number: ");
                            try {
                                String account = scanner.nextLine();
                                if (account == null || account.isBlank()) {
                                    System.err.println("[!!] Account number cannot be null.");
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.err.println("[ERROR] Error Occurred: " + e.getMessage());
                            }
                        case "2": {
                            // TODO
                        }
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    private static void handleLogin(Scanner scanner) {
        System.out.println("Enter your account number: ");
        String account = scanner.nextLine().trim();

        System.out.println("Enter your PIN:");
        String pin = scanner.nextLine().trim();

        if (service.login(account, pin)) {
            System.out.println("Login successfull! Welcome " + service.getCurrentUser().name());
            showMainMenu();
        } else {
            System.err.println("[ERROR!] Login failed! Check your account number and PIN.");
        }
    }

    private static void handleCreateAccount() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter your name: ");
            String name = scanner.nextLine().trim();
            if (name.isBlank()) {
                System.err.println("[!!] Name cannot be null.");
            }
            System.out.println("Enter your account number");
            String account = scanner.nextLine().trim();
            if (account.isBlank()) {
                System.err.println("[!ERROR] Account number cannot be null");
            }
            System.out.println("Enter your pin: ");
            String pin = scanner.nextLine().trim();
            if (pin.isBlank()) {
                System.err.println("[!ERROR] Pin cannot be null");
            }
        }
    }
}

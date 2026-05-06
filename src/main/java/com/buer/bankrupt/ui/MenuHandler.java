package com.buer.bankrupt.ui;

import com.buer.bankrupt.repository.UserRepository;
import com.buer.bankrupt.service.BankService;
import java.util.Scanner;

public class MenuHandler {
    UserRepository repository = new UserRepository();
    BankService service = new BankService(repository);
    static void main() {
        showMainMenu();
    }

    static void showMainMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                System.out.println("Welcome to Bankrupt Menu");
                System.out.println("""
                        1. Login
                        2. Create New Account
                        3. Exit""".stripIndent());

                try {
                    String input;
                    input = scanner.nextLine();
                    if (input.isBlank()) {
                        System.err.println("Please choose an option.");
                    }
                    switch (input) {
                        case "1":
                            System.out.println("Enter your username: ");
                            String name = scanner.nextLine();
                            if (name == null || name.isBlank()) {
                                System.err.println("[!!] Name cannot be null.");
                                break;
                            }
                        case "2": {
                            // TODO
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

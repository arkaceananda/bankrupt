package com.buer.bankrupt.service;

import com.buer.bankrupt.model.User;
import com.buer.bankrupt.repository.UserRepository;
import java.util.List;

public class BankService {
    private final UserRepository userRepository;
    private User currentUser;

    public BankService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean logic(String account, String pin) {
        List<User> users = userRepository.findAll();
        for (User u : users) {
            if (u.getAccount().equals(account) && u.getPin().equals(pin)) {
                this.currentUser = u;
                return true;
            }
        }
        return false;
    }

    public boolean transfer(String norekDest, double amount) {
        List<User> users = userRepository.findAll();
        if (currentUser == null || amount > currentUser.getSaldo() || amount <= 0) {
            return false;
        }

        User targetUser = null;
        for (User u : users) {
            if (u.getAccount().equals(norekDest)) {
                targetUser = u;
                break;
            }
        }

        if (targetUser != null && !targetUser.getAccount().equals(currentUser.getAccount())) {
            for (User u : users) {
                if (u.getAccount().equals(currentUser.getAccount())) {
                    u.setSaldo(u.getSaldo() - amount);
                }
                if (u.getAccount().equals(targetUser.getAccount())) {
                    u.setSaldo(u.getSaldo() + amount);
                }
            }

            userRepository.saveAll(users);
            currentUser.setSaldo(currentUser.getSaldo() - amount);
            return true;
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        this.currentUser = null;
    }
}

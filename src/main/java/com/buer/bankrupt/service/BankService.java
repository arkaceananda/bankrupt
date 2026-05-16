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

    public boolean login(String account, String pin) {
        List<User> users = userRepository.findAll();
        for (User u : users) {
            if (u.account().equals(account) && u.pin().equals(pin)) {
                this.currentUser = u;
                return true;
            }
        }
        return false;
    }

    public boolean transfer(String norekDest, double amount) {
        List<User> users = userRepository.findAll();
        if (currentUser == null || amount > currentUser.saldo() || amount <= 0) {
            return false;
        }

        User targetUser = null;
        for (User u : users) {
            if (u.account().equals(norekDest)) {
                targetUser = u;
                break;
            }
        }

        if (targetUser != null && !targetUser.account().equals(currentUser.account())) {
            for (int i = 0; i < users.size(); i++) {
                User u = users.get(i);

                if (u.account().equals(currentUser.account())) {
                    User updateSender = new User(u.name(), u.account(), u.pin(), u.saldo() - amount);
                    users.set(i, updateSender);
                }
                if (u.account().equals(targetUser.account())) {
                    User updatedTarget = new User(u.name(), u.account(), u.pin(), u.saldo() + amount);
                    users.set(i, updatedTarget);
                }
            }

            userRepository.saveAll(users);
            currentUser = new User(currentUser.name(), currentUser.account(), currentUser.pin(), currentUser.saldo() -amount);
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

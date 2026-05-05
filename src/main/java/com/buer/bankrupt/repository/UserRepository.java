package com.buer.bankrupt.repository;

import com.buer.bankrupt.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public static final String FILE_PATH = "users.csv";

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                User user = new User(
                        data[0],
                        data[1],
                        data[2],
                        Double.parseDouble(data[3])
                );
                users.add(user);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return users;
    }

    public void saveAll(List<User> users) {
        try(PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            pw.println("name,account,pin,saldo");
            for (User u: users) {
                pw.printf("%s,%s,%s,%.0f%n", u.getName(), u.getAccount(), u.getPin(), u.getSaldo());
            }
        } catch (IOException ex) {
            System.err.println("Gagal menyimpan file" + ex.getMessage());
        }
    }
}

package com.tiketkereta;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
    private static final String FILE_PATH = "users.txt";

    // Menyimpan data pengguna ke file
    public static void saveUser(String username, String password) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(username + "," + password);
            writer.newLine();
        }
    }

    // Membaca data pengguna dari file
    public static Map<String, String> loadUsers() throws IOException {
        Map<String, String> users = new HashMap<>();
        File file = new File(FILE_PATH);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        users.put(parts[0], parts[1]);
                    }
                }
            }
        }
        return users;
    }
}

package com.tiketkereta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.awt.Color;

public class FormPemesanan extends JFrame {
    private JTextField txtNamaPenumpang;
    private JComboBox<String> comboJenisKereta;
    private JTextField txtJumlahTiket;
    private JButton btnPesan;

    private JTable tablePemesanan;
    private DefaultTableModel tableModel;

    private JPanel panelLogin;
    private JPanel panelPemesanan;

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegister;

    private boolean isLoggedIn = false;
    public FormPemesanan() {
        setTitle("SmartTrain");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        initLoginPanel();
        initPemesananPanel();

        add(panelLogin);
        add(panelPemesanan);

        switchPanel(panelLogin);
    }

    private void initLoginPanel() {
        panelLogin = new JPanel();
        panelLogin.setLayout(null);
        panelLogin.setBounds(0, 0, 600, 500);
        panelLogin.setBackground(new Color(173, 216, 230)); // Warna biru muda

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 50, 100, 25);
        panelLogin.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(150, 50, 150, 25);
        panelLogin.add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 100, 100, 25);
        panelLogin.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 100, 150, 25);
        panelLogin.add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(50, 150, 100, 30);
        btnLogin.setBackground(new Color(60, 179, 113)); // Hijau muda
        btnLogin.setForeground(Color.WHITE);
        panelLogin.add(btnLogin);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(200, 150, 100, 30);
        btnRegister.setBackground(new Color(30, 144, 255)); // Biru tua
        btnRegister.setForeground(Color.WHITE);
        panelLogin.add(btnRegister);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    login();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    register();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void initPemesananPanel() {
        panelPemesanan = new JPanel();
        panelPemesanan.setLayout(null);
        panelPemesanan.setBounds(0, 0, 600, 500);
        panelPemesanan.setBackground(new Color(255, 239, 213)); // Warna krem

        JLabel lblNamaPenumpang = new JLabel("Nama Penumpang:");
        lblNamaPenumpang.setBounds(20, 20, 150, 20);
        panelPemesanan.add(lblNamaPenumpang);

        txtNamaPenumpang = new JTextField();
        txtNamaPenumpang.setBounds(180, 20, 150, 20);
        panelPemesanan.add(txtNamaPenumpang);

        JLabel lblJenisKereta = new JLabel("Jenis Kereta:");
        lblJenisKereta.setBounds(20, 60, 150, 20);
        panelPemesanan.add(lblJenisKereta);

        comboJenisKereta = new JComboBox<>(new String[]{"Eksekutif", "Ekonomi"});
        comboJenisKereta.setBounds(180, 60, 150, 20);
        panelPemesanan.add(comboJenisKereta);

        JLabel lblJumlahTiket = new JLabel("Jumlah Tiket:");
        lblJumlahTiket.setBounds(20, 100, 150, 20);
        panelPemesanan.add(lblJumlahTiket);

        txtJumlahTiket = new JTextField();
        txtJumlahTiket.setBounds(180, 100, 150, 20);
        panelPemesanan.add(txtJumlahTiket);

        btnPesan = new JButton("Pesan");
        btnPesan.setBounds(150, 150, 100, 30);
        btnPesan.setBackground(new Color(255, 165, 0)); // Oranye
        btnPesan.setForeground(Color.WHITE);
        panelPemesanan.add(btnPesan);

        btnPesan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesPemesanan();
            }
        });

        // Tabel Pemesanan
        tableModel = new DefaultTableModel(new String[]{"Nama Penumpang", "Jenis Kereta", "Jumlah Tiket", "Total Harga"}, 0);
        tablePemesanan = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablePemesanan);
        scrollPane.setBounds(20, 200, 550, 200);
        panelPemesanan.add(scrollPane);
    }

    private void login() throws IOException {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        Map<String, String> users = FileManager.loadUsers();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            JOptionPane.showMessageDialog(this, "Login berhasil!");
            isLoggedIn = true;
            switchPanel(panelPemesanan);
        } else {
            JOptionPane.showMessageDialog(this, "Username atau password salah!");
        }
    }

    private void register() throws IOException {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        Map<String, String> users = FileManager.loadUsers();

        if (users.containsKey(username)) {
            JOptionPane.showMessageDialog(this, "Username sudah terdaftar!");
        } else {
            FileManager.saveUser(username, password);
            JOptionPane.showMessageDialog(this, "Registrasi berhasil!");
        }
    }

    private void switchPanel(JPanel panel) {
        panelLogin.setVisible(false);
        panelPemesanan.setVisible(false);

        panel.setVisible(true);
    }

    private void prosesPemesanan() {
        String namaPenumpang = txtNamaPenumpang.getText();
        String jenisKereta = (String) comboJenisKereta.getSelectedItem();
        int jumlahTiket = Integer.parseInt(txtJumlahTiket.getText());

        double totalHarga = jumlahTiket * (jenisKereta.equals("Eksekutif") ? 150000 : 50000);

        // Tambahkan data ke tabel
        tableModel.addRow(new Object[]{namaPenumpang, jenisKereta, jumlahTiket, totalHarga});

        JOptionPane.showMessageDialog(this, "Pemesanan berhasil diproses!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormPemesanan().setVisible(true);
        });
    }
}

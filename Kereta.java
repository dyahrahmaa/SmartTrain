package com.tiketkereta;

public class Kereta {
    private String namaKereta;
    private int jumlahGerbong;

    public Kereta(String namaKereta, int jumlahGerbong) {
        this.namaKereta = namaKereta;
        this.jumlahGerbong = jumlahGerbong;
    }

    public String getNamaKereta() {
        return namaKereta;
    }

    public void setNamaKereta(String namaKereta) {
        this.namaKereta = namaKereta;
    }

    public int getJumlahGerbong() {
        return jumlahGerbong;
    }

    public void setJumlahGerbong(int jumlahGerbong) {
        this.jumlahGerbong = jumlahGerbong;
    }

    public void tampilkanInfo() {
        System.out.println("Nama Kereta: " + namaKereta + ", Jumlah Gerbong: " + jumlahGerbong);
    }
}

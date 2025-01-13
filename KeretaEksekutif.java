package com.tiketkereta;

public class KeretaEksekutif extends Kereta {
    private double hargaTiket;

    public KeretaEksekutif(String namaKereta, int jumlahGerbong, double hargaTiket) {
        super(namaKereta, jumlahGerbong);
        this.hargaTiket = hargaTiket;
    }

    public double getHargaTiket() {
        return hargaTiket;
    }

    public void setHargaTiket(double hargaTiket) {
        this.hargaTiket = hargaTiket;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Harga Tiket Eksekutif: " + hargaTiket);
    }
}

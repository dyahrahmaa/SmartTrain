package com.tiketkereta;

public class Pemesanan {
    private Kereta kereta;
    private String namaPenumpang;
    private int jumlahTiket;

    public Pemesanan(Kereta kereta, String namaPenumpang, int jumlahTiket) {
        this.kereta = kereta;
        this.namaPenumpang = namaPenumpang;
        this.jumlahTiket = jumlahTiket;
    }

    public void prosesPemesanan() {
        kereta.tampilkanInfo();
        System.out.println("Nama Penumpang: " + namaPenumpang);
        System.out.println("Jumlah Tiket: " + jumlahTiket);
        System.out.println("Total Harga: " + jumlahTiket * ((kereta instanceof KeretaEksekutif)
                ? ((KeretaEksekutif) kereta).getHargaTiket()
                : ((KeretaEkonomi) kereta).getHargaTiket()));
    }
}

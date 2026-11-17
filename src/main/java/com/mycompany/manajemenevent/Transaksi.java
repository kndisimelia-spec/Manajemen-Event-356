package com.mycompany.manajemenevent;

import java.io.Serializable;

public class Transaksi implements Serializable {

    private Aktor peserta;
    private TiketAcara tiket;
    private int jumlah;

    public Transaksi(Aktor peserta, TiketAcara tiket, int jumlah) {
        this.peserta = peserta;
        this.tiket = tiket;
        this.jumlah = jumlah;
    }

    public Aktor getPeserta() {
        return peserta;
    }

    public TiketAcara getTiket() {
        return tiket;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void tampilkanTransaksi() {
        System.out.println("ID Peserta: " + peserta.getId());
        System.out.println("Nama Peserta: " + peserta.getNama());
        System.out.println("Kode Tiket: " + tiket.getKodeTiket());
        System.out.println("Nama Acara: " + tiket.getNamaAcara());
        System.out.println("Jumlah Tiket: " + jumlah);
    }
}
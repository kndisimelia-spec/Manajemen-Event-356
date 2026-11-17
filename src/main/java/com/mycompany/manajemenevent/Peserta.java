package com.mycompany.manajemenevent;

public class Peserta extends Aktor {

    public Peserta(String id, String nama) {
        super(id, nama);
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("=== DATA PESERTA ===");
        System.out.println("ID Peserta: " + getId());
        System.out.println("Nama Peserta: " + getNama());
    }
}
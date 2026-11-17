package com.mycompany.manajemenevent;

import java.io.Serializable;

public class Aktor implements Serializable {

    private String id;
    private String nama;

    public Aktor(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void tampilkanInfo() {
        System.out.println("ID: " + id);
        System.out.println("Nama: " + nama);
    }
}
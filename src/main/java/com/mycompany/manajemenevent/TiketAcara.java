package com.mycompany.manajemenevent;

import java.io.Serializable;

public class TiketAcara implements Serializable {

    private String kodeTiket;
    private String namaAcara;
    private int kuota;

    public TiketAcara(String kodeTiket, String namaAcara, int kuota) {
        this.kodeTiket = kodeTiket;
        this.namaAcara = namaAcara;
        this.kuota = kuota;
    }

    public String getKodeTiket() {
        return kodeTiket;
    }

    public String getNamaAcara() {
        return namaAcara;
    }

    public int getKuota() {
        return kuota;
    }

    public void kurangiKuota(int jumlah) {
        if (jumlah > 0 && jumlah <= kuota) {
            kuota -= jumlah;
        }
    }

    public void tampilkanInfo() {
        System.out.println("Kode Tiket: " + kodeTiket);
        System.out.println("Nama Acara: " + namaAcara);
        System.out.println("Kuota: " + kuota);
    }
}
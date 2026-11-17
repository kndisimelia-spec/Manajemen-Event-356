package com.mycompany.manajemenevent;

public class TransaksiEvent extends Transaksi {

    public TransaksiEvent(Aktor peserta, TiketAcara tiket, int jumlah) {
        super(peserta, tiket, jumlah);
    }

    @Override
    public void tampilkanTransaksi() {
        System.out.println("=== TRANSAKSI EVENT ===");
        super.tampilkanTransaksi();
    }
}
package com.mycompany.manajemenevent;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SistemEvent implements Serializable {

    private String namaEvent;

    private HashMap<String, Aktor> daftarPeserta;
    private HashMap<String, TiketAcara> daftarTiket;

    private ArrayList<TransaksiEvent> daftarTransaksi;

    public SistemEvent(String namaEvent) {
        this.namaEvent = namaEvent;

        daftarPeserta = new HashMap<>();
        daftarTiket = new HashMap<>();
        daftarTransaksi = new ArrayList<>();
    }

    public void tambahPeserta(String id, String nama) {
        Aktor peserta = new Peserta(id, nama);
        daftarPeserta.put(id, peserta);
    }

    public void tambahTiketAcara(String kode, String nama, int kuota) {
        TiketAcara tiket = new TiketAcara(kode, nama, kuota);
        daftarTiket.put(kode, tiket);
    }

    public void prosesTransaksi(String id, String kode, int jumlah)
            throws TransaksiException {

        if (!daftarPeserta.containsKey(id)) {
            throw new TransaksiException(
                    "Peserta dengan ID " + id + " tidak ditemukan!"
            );
        }

        if (!daftarTiket.containsKey(kode)) {
            throw new TransaksiException(
                    "Tiket dengan kode " + kode + " tidak ditemukan!"
            );
        }

        if (jumlah <= 0) {
            throw new TransaksiException(
                    "Jumlah tiket harus lebih dari 0!"
            );
        }

        Aktor peserta = daftarPeserta.get(id);
        TiketAcara tiket = daftarTiket.get(kode);

        if (jumlah > tiket.getKuota()) {
            throw new TransaksiException(
                    "Kuota tiket " + kode + " tidak mencukupi!"
            );
        }

        tiket.kurangiKuota(jumlah);

        TransaksiEvent transaksi =
                new TransaksiEvent(peserta, tiket, jumlah);

        daftarTransaksi.add(transaksi);

        System.out.println("[BERHASIL] Transaksi berhasil diproses.");
        transaksi.tampilkanTransaksi();
        System.out.println();
    }

    public void jalankanTransaksiDariFile(String namaFile)
            throws Exception {

        BufferedReader reader = new BufferedReader(
                new FileReader(namaFile)
        );

        String baris;

        while ((baris = reader.readLine()) != null) {

            if (baris.trim().isEmpty()) {
                continue;
            }

            try {
                String[] data = baris.split("/");

                String id = data[0].split("-", 2)[1];
                String kode = data[1].split("-", 2)[1];
                int jumlah = Integer.parseInt(
                        data[2].split("-", 2)[1]
                );

                prosesTransaksi(id, kode, jumlah);

            } catch (Exception e) {
                System.out.println(
                        "[TRANSAKSI GAGAL] " + e.getMessage()
                );
            }
        }

        reader.close();
    }

    public void simpanLaporanAkhir() {

        // Object Serialization
        try {
            ObjectOutputStream output = new ObjectOutputStream(
                    new FileOutputStream("dump_sys.dat")
            );

            output.writeObject(this);
            output.close();

            System.out.println(
                    "Serialization berhasil disimpan!"
            );

        } catch (IOException e) {
            System.out.println(
                    "Gagal melakukan serialization: " + e.getMessage()
            );
        }

        // Laporan Format Tag XML
        try {
            FileWriter writer = new FileWriter("laporan.txt");

            for (TransaksiEvent transaksi : daftarTransaksi) {

                writer.write(
                        "<aktor>" +
                        transaksi.getPeserta().getId() +
                        "</aktor>"
                );

                writer.write(
                        "<qty>" +
                        transaksi.getJumlah() +
                        "</qty>\n"
                );
            }

            writer.close();

            System.out.println(
                    "Laporan berhasil disimpan!"
            );

        } catch (IOException e) {
            System.out.println(
                    "Gagal menyimpan laporan: " + e.getMessage()
            );
        }
    }
}
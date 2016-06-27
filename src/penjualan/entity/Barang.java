package penjualan.entity;

import penjualan.config.StringConverter;

/**
 *
 * @author Lani
 */
public class Barang {

    private String kode;
    private String kodeKategori;
    private KategoriBarang kategori;
    private String nama;
    private double harga;
    private int jumlah;
    private Boolean paket;

    public Boolean getPaket() {
        return paket;
    }

    public void setPaket(Boolean paket) {
        this.paket = paket;
    }

    @Deprecated
    public String getKodeBarang() {
        return kode;
    }

    @Deprecated
    public void setKodeBarang(String kodeBarang) {
        this.kode = kodeBarang;
    }

    @Deprecated
    public String getKodeKategori() {
        return kodeKategori;
    }

    @Deprecated
    public void setKodeKategori(String kodeKategori) {
        this.kodeKategori = kodeKategori;
    }

    @Deprecated
    public String getNamaBarang() {
        return nama;
    }

    @Deprecated
    public void setNamaBarang(String namaBarang) {
        this.nama = namaBarang;
    }

    public double getHarga() {
        return harga;
    }

    public String getHargaAsCurrency() {
        return StringConverter.getCurrency(harga);
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public KategoriBarang getKategori() {
        return kategori;
    }

    public void setKategori(KategoriBarang kategori) {
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

}

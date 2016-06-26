/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entity;

/**
 *
 * @author dimmaryanto
 */
public class PemesananPembelianDetail {

    private Integer kode;
    private PemesananPembelian pesanPembelian;
    private Barang barang;
    private Integer jumlah;

    public Integer getKode() {
        return kode;
    }

    public void setKode(Integer kode) {
        this.kode = kode;
    }

    public PemesananPembelian getPesanPembelian() {
        return pesanPembelian;
    }

    public void setPesanPembelian(PemesananPembelian pesanPembelian) {
        this.pesanPembelian = pesanPembelian;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

}

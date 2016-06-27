/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.entity;

/**
 *
 * @author Lani
 */
public class KategoriBarang {

    private String kode;
    private String nama;

    @Deprecated
    public String getid_kategori() {
        return kode;
    }

    @Deprecated
    public void setid_kategori(String id_kateg) {
        this.kode = id_kateg;
    }

    @Deprecated
    public String getkategori() {
        return nama;
    }

    @Deprecated
    public void setkategori(String kateg) {
        this.nama = kateg;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

}

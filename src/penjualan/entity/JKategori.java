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
@Deprecated
public class JKategori {

    private String kodeKategori;
    private String nama;

    public String getid_kategori() {
        return kodeKategori;
    }

    public void setid_kategori(String id_kateg) {
        this.kodeKategori = id_kateg;
    }

    public String getkategori() {
        return nama;
    }

    public void setkategori(String kateg) {
        this.nama = kateg;
    }

}

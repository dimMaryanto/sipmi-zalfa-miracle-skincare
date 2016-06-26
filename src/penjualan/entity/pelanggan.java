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
public class pelanggan {

    private String id_pelanggan;
    private String nama;
    private String jp;
    private String alamat;
    private int nope;
    String no_pe = String.valueOf(nope);

    public String getid_pelanggan() {
        return id_pelanggan;
    }

    public void setid_pelanggan(String id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getjp() {
        return jp;
    }

    public void setjp(String jp) {
        this.jp = jp;
    }

    public String getalamat() {
        return alamat;
    }

    public void setalamat(String alamat) {
        this.alamat = alamat;
    }

    public String getnope() {
        return no_pe;
    }

    public void setnope(String nope) {
        this.no_pe = nope;
    }
}

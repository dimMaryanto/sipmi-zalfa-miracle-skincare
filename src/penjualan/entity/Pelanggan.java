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
public class Pelanggan {

    private String kode;
    private String nama;
    private String jp;
    private String alamat;
    String tlp;

    @Deprecated
    public String getid_pelanggan() {
        return kode;
    }

    @Deprecated
    public void setid_pelanggan(String id_pelanggan) {
        this.kode = id_pelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Deprecated
    public String getjp() {
        return jp;
    }

    @Deprecated
    public void setjp(String jp) {
        this.jp = jp;
    }

    @Deprecated
    public String getalamat() {
        return alamat;
    }

    @Deprecated
    public void setalamat(String alamat) {
        this.alamat = alamat;
    }

    @Deprecated
    public String getnope() {
        return tlp;
    }

    @Deprecated
    public void setnope(String nope) {
        this.tlp = nope;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getJp() {
        return jp;
    }

    public void setJp(String jp) {
        this.jp = jp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTlp() {
        return tlp;
    }

    public void setTlp(String tlp) {
        this.tlp = tlp;
    }
    
    
}

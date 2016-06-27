package penjualan.entity;

/**
 *
 * @author Lani
 */
public class Pemasok {

    private String kode;
    private String nama;
    private String alamat;
    private String tlp;

    @Deprecated
    public String getkode_supplier() {
        return kode;
    }

    @Deprecated
    public void setkode_supplier(String kode_supplier) {
        this.kode = kode_supplier;
    }

    @Deprecated
    public String getnama_supplier() {
        return nama;
    }

    @Deprecated
    public void setnama(String nama_supplier) {
        this.nama = nama_supplier;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

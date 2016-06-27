package penjualan.entity;

/**
 *
 * @author Lani
 */
public class Pemasok {

    private String kode;
    private String nama;
    private String alamat;
    private int nope;
    String no_pe = String.valueOf(nope);

    public String getkode_supplier() {
        return kode;
    }

    public void setkode_supplier(String kode_supplier) {
        this.kode = kode_supplier;
    }

    public String getnama_supplier() {
        return nama;
    }

    public void setnama(String nama_supplier) {
        this.nama = nama_supplier;
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

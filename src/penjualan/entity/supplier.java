package penjualan.entity;

/**
 *
 * @author Lani
 */
public class supplier {

    private String kode_supplier;
    private String nama_supplier;
    private String alamat;
    private int nope;
    String no_pe = String.valueOf(nope);

    public String getkode_supplier() {
        return kode_supplier;
    }

    public void setkode_supplier(String kode_supplier) {
        this.kode_supplier = kode_supplier;
    }

    public String getnama_supplier() {
        return nama_supplier;
    }

    public void setnama(String nama_supplier) {
        this.nama_supplier = nama_supplier;
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

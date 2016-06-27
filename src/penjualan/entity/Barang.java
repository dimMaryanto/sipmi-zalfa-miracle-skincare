package penjualan.entity;

/**
 *
 * @author Lani
 */
public class Barang {

    private String kodeBarang;
    private String kodeKategori;
    private String namaBarang;
    private double harga;
    private int jumlah;
    private Boolean paket;

    public Boolean getPaket() {
        return paket;
    }

    public void setPaket(Boolean paket) {
        this.paket = paket;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getKodeKategori() {
        return kodeKategori;
    }

    public void setKodeKategori(String kodeKategori) {
        this.kodeKategori = kodeKategori;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public double getHarga() {
        return harga;
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

}

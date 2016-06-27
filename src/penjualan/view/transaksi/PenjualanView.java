/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.view.transaksi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import penjualan.config.Koneksi;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import penjualan.service.TransaksiImplement;
import java.sql.PreparedStatement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Lani
 */
public final class PenjualanView extends javax.swing.JFrame {

    int row;

    private DefaultTableModel Model;

    /**
     * Creates new form transaksiView
     */
    public PenjualanView() {
        try {
            initComponents();

            Model = new DefaultTableModel();

            tTransaksi.setModel(Model);
            Model.addColumn("Kode Barang");
            Model.addColumn("Nama Barang");
            Model.addColumn("Harga");
            Model.addColumn("Diskon");
            Model.addColumn("Jumlah Beli");
            Model.addColumn("Subtotal");

            DateNow();
            GenFak();
            SetIdPelanggan();
            SetKodeBarang();
            bTambah.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void DateNow() {

        Date TglCur = new Date();

        SimpleDateFormat FrmTgl = new SimpleDateFormat("yyyy-MM-dd");

        String Damoye = FrmTgl.format(TglCur);
        tgl_penjualan.setText(Damoye);

    }

    public void GenFak() throws SQLException {

        TransaksiImplement GetUrutan = new TransaksiImplement();

        int Urutan = GetUrutan.UrutanDb();

        Date TglJual = new Date();

        SimpleDateFormat FormatTgl = new SimpleDateFormat("yyyyMMdd");

        String Dmy = FormatTgl.format(TglJual);
        String KodeAwal = "PO";

        StringBuffer StrValues = new StringBuffer();

        StrValues.append(KodeAwal);
        StrValues.append("-");
        StrValues.append(String.valueOf(Dmy));
        StrValues.append("-");
        StrValues.append(String.valueOf(Urutan));

        String Result = StrValues.toString();
        no_faktur.setText(Result);

    }

    private void SetIdPelanggan() throws SQLException {

        TransaksiImplement GetIdPlg = new TransaksiImplement();

        ArrayList<String> UrutanArr = GetIdPlg.ViewNamaPlg();

        nama_pelanggan.addItem("-- Pilih --");

        for (Object O : UrutanArr) {

            nama_pelanggan.addItem(O);

        }

    }

    private void SetKodeBarang() throws SQLException {

        TransaksiImplement GetKdBrg = new TransaksiImplement();

        ArrayList<String> UrutanArr = GetKdBrg.ViewKdBrg();

        nama_barang.addItem("-- Pilih --");

        for (Object O : UrutanArr) {

            nama_barang.addItem(O);

        }

    }

    private void KosongkanData() {

        jumlah.setText("");
        stok.setText("");
        nama_barang.setSelectedItem("-- Pilih --");
        harga.setText("");
        Nope.setText("");
        diskon.setText("");
    }

    private void HitungTotalBayar() {

        int JmlRow = tTransaksi.getRowCount();
        int Tot = 0;
        if (JmlRow == 0) {
            total_byr.setText(String.valueOf(Tot));
        } else {
            for (int i = 0; i < JmlRow; i++) {
                int JmlTot = Integer.valueOf((String.valueOf(Model.getValueAt(i, 5))));
                Tot += JmlTot;
            }
        }

        total_byr.setText(String.valueOf(Tot));

    }

    private void simpanPenjualan() {
        String noFaktur = no_faktur.getText();
        String tglJual = tgl_penjualan.getText();
        String idPelanggan = (String) nama_pelanggan.getSelectedItem();
        String idPelangganFix = idPelanggan.substring(0, 4);

        if (noFaktur.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Generate no Faktur Gagal/ No Faktur kosong");
        } else if (tglJual.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Generate Tanggal Jual Gagal/Tanggal Jual kosong");
        } else if (idPelanggan == "-- Pilih --") {
            JOptionPane.showMessageDialog(null, "Pilih Id Pelanggan terlebih dahulu");
        } else {
            try {
                PreparedStatement st = Koneksi.getConnection().prepareStatement("INSERT into penjualan values(?,?,?)");
                st.setString(1, noFaktur);
                st.setString(2, tglJual);
                st.setString(3, idPelangganFix);
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Penjualan berhasil di input");
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }

    }

    private void simpanDetailPenjualan() {
        int jml = tTransaksi.getRowCount();
        if (jml == 0) {
            JOptionPane.showMessageDialog(null, "Barang Belum Terpilih");
            return;
        } else {
            for (int i = 0; i < jml; i++) {
                String noFaktur = no_faktur.getText();
                String kodeBarang = (String) Model.getValueAt(i, 0);
                int diskon = Integer.valueOf(String.valueOf(Model.getValueAt(i, 3)));
                int jmlBeli = Integer.valueOf(String.valueOf(Model.getValueAt(i, 4)));
                int hargaJual = Integer.valueOf(String.valueOf(Model.getValueAt(i, 2)));

                try {
                    PreparedStatement st1 = Koneksi.getConnection().prepareStatement("INSERT INTO detail_jual value(?,?,?,?,?)");
                    st1.setString(1, noFaktur);
                    st1.setString(2, kodeBarang);
                    st1.setInt(3, hargaJual);
                    st1.setInt(4, diskon);
                    st1.setInt(5, jmlBeli);
                    st1.executeUpdate();

                    PreparedStatement st2 = Koneksi.getConnection().prepareStatement("Update barang set jumlah=jumlah-'" + jmlBeli + "'");
                    st2.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Data Detail Penjualan Berhasil Di Input");
    }

    private void kosongkanDataTransaksi() {
        no_faktur.setText("");
        tgl_penjualan.setText("");
        nama_pelanggan.setSelectedItem("-- Pilih --");
        pelanggan.setText("");
        total_byr.setText("");
        bSimpanKeranjang.setEnabled(false);
        bHapus.setEnabled(false);
        bSimpanTransaksi.setEnabled(false);
    }

    private void kosongkanDataTransaksi2() {
        nama_pelanggan.setSelectedItem("-= Pilih Pelanggan =-");
        nama_barang.setSelectedItem("-= Pilih Barang =-");
        pelanggan.setText("");
        stok.setText("");
        harga.setText("");
        jumlah.setText("");
        total_byr.setText("");
        //simpanTransaksi.setEnabled(false);
        //hapus.setEnabled(false);
        //tambahKeranjang.setEnabled(false);

        int x = tTransaksi.getRowCount();
        while (x > 0) {
            x = tTransaksi.getRowCount();
            Model.removeRow(x - 1);
            x--;
        }
        this.HitungTotalBayar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        no_faktur = new javax.swing.JTextField();
        tgl_penjualan = new javax.swing.JTextField();
        bTambah = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lNamaPel = new javax.swing.JLabel();
        nama_pelanggan = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        pelanggan = new javax.swing.JTextField();
        lNope = new javax.swing.JLabel();
        lAlamat = new javax.swing.JLabel();
        Nope = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        stok = new javax.swing.JTextField();
        lHargaBrg = new javax.swing.JLabel();
        nama_barang = new javax.swing.JComboBox();
        lStokBrg = new javax.swing.JLabel();
        lNamaBrg = new javax.swing.JLabel();
        harga = new javax.swing.JTextField();
        lJmlBeli = new javax.swing.JLabel();
        jumlah = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        diskon = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lHargaBrg1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tTransaksi = new javax.swing.JTable();
        bSimpanKeranjang = new javax.swing.JButton();
        bHapus = new javax.swing.JButton();
        bSimpanTransaksi = new javax.swing.JButton();
        total_byr = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jLabel9 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel4.setText("Tanggal Transaksi");

        no_faktur.setEditable(false);
        no_faktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_fakturActionPerformed(evt);
            }
        });

        tgl_penjualan.setEditable(false);

        bTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/tambah.png"))); // NOI18N
        bTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTambahActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel5.setText("No.Faktur");

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        lNamaPel.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        lNamaPel.setForeground(new java.awt.Color(255, 255, 255));
        lNamaPel.setText("Nama Pelanggan ");

        nama_pelanggan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nama_pelangganItemStateChanged(evt);
            }
        });
        nama_pelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_pelangganActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel2.setText("DATA PELANGGAN");

        pelanggan.setEditable(false);
        pelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pelangganActionPerformed(evt);
            }
        });

        lNope.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        lNope.setForeground(new java.awt.Color(255, 255, 255));
        lNope.setText("No. Tlp ");

        lAlamat.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        lAlamat.setForeground(new java.awt.Color(255, 255, 255));
        lAlamat.setText("KATEGORI PELANGGAN");

        Nope.setEditable(false);
        Nope.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NopeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lNope)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addComponent(lNamaPel))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(lAlamat))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nama_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Nope, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lNamaPel)
                    .addComponent(nama_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lAlamat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNope)
                    .addComponent(Nope, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87))
        );

        jLabel1.setFont(new java.awt.Font("Stencil Std", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 153, 255));
        jLabel1.setText("Transaksi Penjualan");

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        stok.setEditable(false);

        lHargaBrg.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        lHargaBrg.setForeground(new java.awt.Color(255, 255, 255));
        lHargaBrg.setText("Harga");

        nama_barang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nama_barangItemStateChanged(evt);
            }
        });
        nama_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_barangActionPerformed(evt);
            }
        });

        lStokBrg.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        lStokBrg.setForeground(new java.awt.Color(255, 255, 255));
        lStokBrg.setText("Stok Barang");

        lNamaBrg.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        lNamaBrg.setForeground(new java.awt.Color(255, 255, 255));
        lNamaBrg.setText("Nama Barang");

        harga.setEditable(false);
        harga.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                hargaInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        lJmlBeli.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        lJmlBeli.setForeground(new java.awt.Color(255, 255, 255));
        lJmlBeli.setText("Pembelian");

        jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel3.setText("DATA BARANG");

        lHargaBrg1.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        lHargaBrg1.setForeground(new java.awt.Color(255, 255, 255));
        lHargaBrg1.setText("Diskon");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lNamaBrg)
                    .addComponent(lStokBrg, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lHargaBrg, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lJmlBeli, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(harga, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(37, 37, 37))
                            .addComponent(lHargaBrg1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(diskon, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nama_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stok, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNamaBrg)
                    .addComponent(nama_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stok, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lStokBrg))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(63, 63, 63))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lHargaBrg)
                            .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lHargaBrg1)
                            .addComponent(diskon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lJmlBeli))
                        .addGap(29, 29, 29))))
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));

        tTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tTransaksi);

        bSimpanKeranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/ktambah.png"))); // NOI18N
        bSimpanKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanKeranjangActionPerformed(evt);
            }
        });

        bHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/khapus.png"))); // NOI18N
        bHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusActionPerformed(evt);
            }
        });

        bSimpanTransaksi.setText("Simpan Transaksi");
        bSimpanTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanTransaksiActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel7.setText("Total Pembayaran");

        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/cetak1.jpg"))); // NOI18N
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel9.setText("CETAK NOTA");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(356, 356, 356))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(73, 73, 73)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(bSimpanKeranjang, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(total_byr, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bSimpanTransaksi))
                .addGap(76, 76, 76))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bSimpanKeranjang, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bHapus, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton2)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(total_byr, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bSimpanTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jToggleButton1.setText("Batal");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(132, 132, 132)
                        .addComponent(jToggleButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(no_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tgl_penjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(bTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(no_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tgl_penjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSimpanKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanKeranjangActionPerformed
        // TODO add your handling code here:

        String kode = (String) nama_barang.getSelectedItem();
        String kodeFix = kode.substring(0, 4);
        String namaBrg = kode.substring(5, kode.length() - 0);
        int hrg = Integer.valueOf(harga.getText());
        int jml = Integer.valueOf(jumlah.getText());
        int sk = Integer.valueOf(stok.getText());
        int dk = Integer.valueOf(diskon.getText());

        long subTot = (hrg - dk) * jml;

        Object[] data = new Object[6];
        data[0] = kodeFix;
        data[1] = namaBrg;
        data[2] = harga.getText();
        data[3] = diskon.getText();
        data[4] = jumlah.getText();
        data[5] = subTot;

        if (jml > sk) {

            JOptionPane.showMessageDialog(null, "Jumlah pembelian melebihi batas stok");

        } else {
            Model.addRow(data);
            this.HitungTotalBayar();
            this.KosongkanData();
            nama_pelanggan.setEnabled(false);
        }
        //  if(jumlah.trim().isEmpty()){
        // JOptionPane.showMessageDialog(null,"Generate no Faktur Gagal/ No Faktur kosong");

    }//GEN-LAST:event_bSimpanKeranjangActionPerformed

    private void bTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTambahActionPerformed
        try {
            this.GenFak();
            this.DateNow();
            bSimpanKeranjang.setEnabled(true);
            bHapus.setEnabled(true);
            bSimpanTransaksi.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanView.class.getName()).log(Level.SEVERE, null, ex);

        }
    }//GEN-LAST:event_bTambahActionPerformed

    private void no_fakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_fakturActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_fakturActionPerformed

    private void nama_pelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_pelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nama_pelangganActionPerformed

    private void nama_pelangganItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nama_pelangganItemStateChanged
        // TODO add your handling code here:                                                            //ambil data pelanggan
        String kode = (String) nama_pelanggan.getSelectedItem();
        String kodeFix = kode.substring(0, 4);
        try {
            Statement ktB = Koneksi.getConnection().createStatement();
            ResultSet rsKBr = ktB.executeQuery("SELECT jp,notlp from pelanggan where id_pelanggan='" + kodeFix + "'");
            while (rsKBr.next()) {
                pelanggan.setText(rsKBr.getString("jp"));
                Nope.setText(rsKBr.getString("notlp"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_nama_pelangganItemStateChanged

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        // TODO add your handling code here:
        int delData = tTransaksi.getSelectedRow();
        if (delData == -1) {
            return;
        } else {
            Model.removeRow(delData);
            this.HitungTotalBayar();
        }
    }//GEN-LAST:event_bHapusActionPerformed

    private void bSimpanTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanTransaksiActionPerformed
        // TODO add your handling code here:
        this.simpanPenjualan();
        this.simpanDetailPenjualan();
        this.kosongkanDataTransaksi();
        bTambah.setEnabled(true);
        Model.getDataVector().removeAllElements();
        Model.fireTableDataChanged();
        nama_pelanggan.setEnabled(true);
    }//GEN-LAST:event_bSimpanTransaksiActionPerformed

    private void pelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pelangganActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        int messageType = JOptionPane.INFORMATION_MESSAGE;
        int optionType = JOptionPane.OK_CANCEL_OPTION;
        int code = JOptionPane.showConfirmDialog(null, "Yakin ingin membatalkan transaksi?");
        if (code == JOptionPane.OK_OPTION) {
            this.kosongkanDataTransaksi2();
        } else if (code == JOptionPane.CANCEL_OPTION) {
            return;
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void NopeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NopeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NopeActionPerformed

    private void jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahActionPerformed

    private void hargaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_hargaInputMethodTextChanged

    }//GEN-LAST:event_hargaInputMethodTextChanged

    private void nama_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_barangActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_nama_barangActionPerformed

    private void nama_barangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nama_barangItemStateChanged
        // TODO add your handling code here:
        String kode = (String) nama_barang.getSelectedItem();
        String kodeFix = kode.substring(0, 4);
        try {
            Statement ktB = Koneksi.getConnection().createStatement();
            ResultSet rsKBr = ktB.executeQuery("SELECT * FROM barang where kode_barang='" + kodeFix + "'");

//            ResultSet rsKBr= ktB.executeQuery("SELECT q.kode_barang, q.id_kategori, q.nama_barang, q.harga, q.jum - coalesce(sum(dj.jumlah), 0) as jumlah\n" +
//                "FROM q left join detail_jual dj on q.kode_barang = dj.kode_barang where q.kode_barang='"+kodeFix+"' group by q.kode_barang");          
            while (rsKBr.next()) {
                stok.setText(rsKBr.getString("jumlah"));
                harga.setText(rsKBr.getString("harga"));

                long d;
                double disk;
                disk = Double.parseDouble(harga.getText());
                if (pelanggan.getText().equals("Agen")) {
                    d = (long) ((disk * 15) / 100);
                    String ds = String.valueOf(d);
                    diskon.setText(ds);
                } else if (pelanggan.getText().equals("NoN Agen")) {
                    diskon.setText("0");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_nama_barangItemStateChanged

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PenjualanView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PenjualanView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PenjualanView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PenjualanView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new transaksiView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Nope;
    private javax.swing.JButton bHapus;
    private javax.swing.JButton bSimpanKeranjang;
    private javax.swing.JButton bSimpanTransaksi;
    private javax.swing.JButton bTambah;
    private javax.swing.JTextField diskon;
    private javax.swing.JTextField harga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JTextField jumlah;
    private javax.swing.JLabel lAlamat;
    private javax.swing.JLabel lHargaBrg;
    private javax.swing.JLabel lHargaBrg1;
    private javax.swing.JLabel lJmlBeli;
    private javax.swing.JLabel lNamaBrg;
    private javax.swing.JLabel lNamaPel;
    private javax.swing.JLabel lNope;
    private javax.swing.JLabel lStokBrg;
    private javax.swing.JComboBox nama_barang;
    private javax.swing.JComboBox nama_pelanggan;
    private javax.swing.JTextField no_faktur;
    private javax.swing.JTextField pelanggan;
    private javax.swing.JTextField stok;
    private javax.swing.JTable tTransaksi;
    private javax.swing.JTextField tgl_penjualan;
    private javax.swing.JTextField total_byr;
    // End of variables declaration//GEN-END:variables
}

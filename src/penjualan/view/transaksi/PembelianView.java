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
import penjualan.service.TransaksiPembelianImplement;
import java.sql.PreparedStatement;

/**
 *
 * @author Lani
 */
public final class PembelianView extends javax.swing.JFrame {

    private DefaultTableModel model;

    /**
     * Creates new form transaksiPembelianView
     */
    public PembelianView() {
        try {
            initComponents();
            model = new DefaultTableModel();
            tabelTransaksi.setModel(model);
            model.addColumn("Kode Barang");
            model.addColumn("Nama Barang");
            model.addColumn("Harga");
            model.addColumn("Jumlah Beli");
            model.addColumn("Subtotal");
            DateNow();
            GenFak();
            SetKodeBarang();
            SetIdSupplier();
            btransaksi.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(PembelianView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void GenFak() throws SQLException {

        TransaksiPembelianImplement GetUrutan = new TransaksiPembelianImplement();

        int Urutan = GetUrutan.UrutanDb();

        Date TglJual = new Date();

        SimpleDateFormat FormatTgl = new SimpleDateFormat("yyyyMMdd");

        String Dmy = FormatTgl.format(TglJual);
        String KodeAwal = "PB";

        StringBuilder StrValues = new StringBuilder();

        StrValues.append(KodeAwal);
        StrValues.append("-");
        StrValues.append(String.valueOf(Dmy));
        StrValues.append("-");
        StrValues.append(String.valueOf(Urutan));

        String Result = StrValues.toString();
        no_struk.setText(Result);

    }

    public void DateNow() {

        Date TglCur = new Date();

        SimpleDateFormat FrmTgl = new SimpleDateFormat("yyyy-MM-dd");

        String Damoye = FrmTgl.format(TglCur);
        tgl_transaksi.setText(Damoye);

    }

    private void SetIdSupplier() throws SQLException {

        TransaksiPembelianImplement GetNamaSp = new TransaksiPembelianImplement();

        ArrayList<String> UrutanArr = GetNamaSp.ViewIdSp();

        nama_supplier.addItem("-- Pilih --");

        for (Object O : UrutanArr) {

            nama_supplier.addItem(O);

        }

    }

    private void SetKodeBarang() throws SQLException {

        TransaksiPembelianImplement GetKdBrg = new TransaksiPembelianImplement();

        ArrayList<String> UrutanArr = GetKdBrg.ViewKdBrg();

        nm_barang.addItem("-- Pilih --");

        for (Object O : UrutanArr) {

            nm_barang.addItem(O);

        }

    }

    private void KosongkanData() {

        jumlah.setText("");
        stok.setText("");
        nm_barang.setSelectedItem("-- Pilih --");
        harga.setText("");

    }

    private void HitungTotalBayar() {

        int JmlRow = tabelTransaksi.getRowCount();
        int Tot = 0;
        if (JmlRow == 0) {
            totBeli.setText(String.valueOf(Tot));
        } else {
            for (int i = 0; i < JmlRow; i++) {
                int JmlTot = Integer.valueOf((String.valueOf(model.getValueAt(i, 4))));
                Tot += JmlTot;
            }
        }

        totBeli.setText(String.valueOf(Tot));

    }

    private void simpanPembelian() {
        String noStruk = no_struk.getText();
        String tglBeli = tgl_transaksi.getText();
        String idSupplier = (String) nama_supplier.getSelectedItem();
        String idSupplierFix = idSupplier.substring(0, 1);

        if (noStruk.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Generate no Struk kosong");
        } else if (tglBeli.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Generate Tanggal Transaksi kosong");
        } else if (idSupplier == "-- Pilih --") {
            JOptionPane.showMessageDialog(null, "Pilih Id Supplier terlebih dahulu");
        } else {
            try {
                PreparedStatement st = Koneksi.getConnection().prepareStatement("INSERT into pembelian values(?,?,?)");
                st.setString(1, noStruk);
                st.setString(2, tglBeli);
                st.setString(3, idSupplierFix);
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Pembelian berhasil di input");
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

    private void simpanDetailPembelian() {
        int jml = tabelTransaksi.getRowCount();
        if (jml == 0) {
            JOptionPane.showMessageDialog(null, "Barang Belum Terpilih");
            return;
        } else {
            for (int i = 0; i < jml; i++) {
                String noStruk = no_struk.getText();
                String kodeBarang = (String) model.getValueAt(i, 0);
                int jmlBeli = Integer.valueOf(String.valueOf(model.getValueAt(i, 3)));
                int hargaJual = Integer.valueOf(String.valueOf(model.getValueAt(i, 2)));

                try {
                    PreparedStatement st1 = Koneksi.getConnection().prepareStatement("INSERT INTO detail_beli value(?,?,?,?)");
                    st1.setString(1, noStruk);
                    st1.setString(2, kodeBarang);
                    st1.setInt(3, hargaJual);
                    st1.setInt(4, jmlBeli);
                    st1.executeUpdate();

                    PreparedStatement st2 = Koneksi.getConnection().prepareStatement("Update barang set jumlah=jumlah+'" + jmlBeli + "' where kode_barang='" + kodeBarang + "'");
                    st2.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Data Detail Penjualan Berhasil Di Input");
    }

    private void kosongkanDataTransaksi() {
        no_struk.setText("");
        tgl_transaksi.setText("");
        nama_supplier.setSelectedItem("-- Pilih --");
        alamat.setText("");
        nope.setText("");
        totBeli.setText("");
        bsimpankeranjang.setEnabled(false);
        bhapus.setEnabled(false);
        btransaksi.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        nama_supplier = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nope = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamat = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        no_struk = new javax.swing.JTextField();
        tgl_transaksi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btransaksi = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        nm_barang = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        stok = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        harga = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jumlah = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelTransaksi = new javax.swing.JTable();
        totBeli = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        bsimpankeranjang = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Stencil Std", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 153, 255));
        jLabel3.setText("Transaksi Pembelian");

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        nama_supplier.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nama_supplierItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nama");

        jLabel4.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel4.setText("DATA PEMASOK");

        jLabel6.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("No Telepon");

        jLabel7.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Alamat");

        alamat.setColumns(20);
        alamat.setRows(5);
        jScrollPane1.setViewportView(alamat);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nama_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nope, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nama_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nope, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addComponent(jLabel7)))
                    .addComponent(jLabel5))
                .addGap(26, 26, 26))
        );

        jLabel1.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel1.setText("No Struk");

        jLabel2.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel2.setText("Tanggal Transaksi");

        btransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/tambah.png"))); // NOI18N
        btransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btransaksiActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel8.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel8.setText("DAFTAR BARANG ");

        jLabel9.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nama");

        nm_barang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nm_barangItemStateChanged(evt);
            }
        });
        nm_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nm_barangActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Stok ");

        stok.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Harga");

        jLabel10.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jumlah");

        jLabel14.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Rp");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nm_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jumlah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(harga))
                            .addComponent(stok, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(nm_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stok, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(61, 61, 61))
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));

        tabelTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabelTransaksi);

        jButton4.setText("Simpan Transaksi");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Stencil Std", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Total Pembelian");

        bsimpankeranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/ktambah.png"))); // NOI18N
        bsimpankeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpankeranjangActionPerformed(evt);
            }
        });

        bhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/khapus.png"))); // NOI18N
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(totBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(bsimpankeranjang, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton4)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bhapus)
                    .addComponent(bsimpankeranjang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(no_struk, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tgl_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(166, 166, 166))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(no_struk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tgl_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btransaksi))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nm_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nm_barangActionPerformed
        // TODO add your handling code here:
        String kode = (String) nm_barang.getSelectedItem();
        String kodeFix = kode.substring(0, 4);
        try {
//            Statement ktB=Koneksi.getConnection().createStatement();
//            ResultSet rsKBr= ktB.executeQuery("SELECT q.kode_barang, q.id_kategori, q.nama_barang, q.harga, q.jum - coalesce(sum(dj.jumlah), 0) as jumlah\n" +
//                "FROM q left join detail_jual dj on q.kode_barang = dj.kode_barang where q.kode_barang='"+kodeFix+"' group by q.kode_barang");
//            
            Statement ktB = Koneksi.getConnection().createStatement();
            ResultSet rsKBr = ktB.executeQuery("SELECT * FROM barang where kode_barang='" + kodeFix + "'");

            while (rsKBr.next()) {
                stok.setText(rsKBr.getString("jumlah"));
                harga.setText(rsKBr.getString("harga"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_nm_barangActionPerformed

    private void bsimpankeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpankeranjangActionPerformed
        // TODO add your handling code here:
        String kode = (String) nm_barang.getSelectedItem();
        String kodeFix = kode.substring(0, 4);
        String namaBrg = kode.substring(5, kode.length() - 0);
        int hrg = Integer.valueOf(harga.getText());
        int jml = Integer.valueOf(jumlah.getText());
        long subTot = hrg * jml;

        Object[] data = new Object[5];
        data[0] = kodeFix;
        data[1] = namaBrg;
        data[2] = harga.getText();
        data[3] = jumlah.getText();
        data[4] = subTot;

        model.addRow(data);
        this.HitungTotalBayar();
        this.KosongkanData();
    }//GEN-LAST:event_bsimpankeranjangActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        // TODO add your handling code here:
        int delData = tabelTransaksi.getSelectedRow();
        if (delData == -1) {
            return;
        } else {
            model.removeRow(delData);
            this.HitungTotalBayar();
        }
    }//GEN-LAST:event_bhapusActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.simpanPembelian();
        this.simpanDetailPembelian();
        this.kosongkanDataTransaksi();
        btransaksi.setEnabled(true);
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        nama_supplier.setEnabled(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void btransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btransaksiActionPerformed
        // TODO add your handling code here:
        try {
            this.GenFak();
            this.DateNow();
            bsimpankeranjang.setEnabled(true);
            bhapus.setEnabled(true);
            btransaksi.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanView.class.getName()).log(Level.SEVERE, null, ex);

        }
    }//GEN-LAST:event_btransaksiActionPerformed

    private void nama_supplierItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nama_supplierItemStateChanged
        // TODO add your handling code here:
        String kode = (String) nama_supplier.getSelectedItem();
        String kodeFix = kode.substring(0, 1);
        try {
            Statement ktB = Koneksi.getConnection().createStatement();
            ResultSet rsKBr = ktB.executeQuery("SELECT alamat,notlp from supplier where kode_supplier='" + kodeFix + "'");
            while (rsKBr.next()) {
                alamat.setText(rsKBr.getString("alamat"));
                nope.setText(rsKBr.getString("notlp"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_nama_supplierItemStateChanged

    private void nm_barangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nm_barangItemStateChanged
        // TODO add your handling code here:
        String kode = (String) nm_barang.getSelectedItem();
        String kodeFix = kode.substring(0, 4);
        try {
            Statement ktB = Koneksi.getConnection().createStatement();
            ResultSet rsKBr = ktB.executeQuery("SELECT * from a where kode_barang='" + kodeFix + "' group by kode_barang");
            while (rsKBr.next()) {

                stok.setText(rsKBr.getString("jumlah") + "");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_nm_barangItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // new transaksiPembelianView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpankeranjang;
    private javax.swing.JButton btransaksi;
    private javax.swing.JTextField harga;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jumlah;
    private javax.swing.JComboBox nama_supplier;
    private javax.swing.JComboBox nm_barang;
    private javax.swing.JTextField no_struk;
    private javax.swing.JTextField nope;
    private javax.swing.JTextField stok;
    private javax.swing.JTable tabelTransaksi;
    private javax.swing.JTextField tgl_transaksi;
    private javax.swing.JTextField totBeli;
    // End of variables declaration//GEN-END:variables
}

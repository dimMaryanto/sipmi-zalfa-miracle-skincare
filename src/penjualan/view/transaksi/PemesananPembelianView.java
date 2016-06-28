/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.view.transaksi;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import penjualan.config.Koneksi;
import penjualan.entity.Barang;
import penjualan.entity.Pemasok;
import penjualan.entity.PemesananPembelian;
import penjualan.entity.PemesananPembelianDetail;
import penjualan.repository.RepositoryBarang;
import penjualan.repository.RepositoryPemasok;
import penjualan.repository.RepositoryPemesananPembelian;
import penjualan.service.ServiceBarang;
import penjualan.service.ServicePemasok;
import penjualan.service.ServicePemesananPembelian;

/**
 *
 * @author dimmaryanto
 */
public class PemesananPembelianView extends javax.swing.JFrame {

    private final RepositoryBarang repoBarang;
    private final RepositoryPemasok repoPemasok;
    private final RepositoryPemesananPembelian repoBeli;
    private List<Barang> daftarBarang;
    private List<Pemasok> daftarPemasok;
    private List<PemesananPembelianDetail> daftarPesanBarang;
    private final PemesananPembelian beliPemesanan;

    private final DefaultTableModel tableModel;

    public PemesananPembelianView() {
        setTitle("Pemesanan pembelian");
        initComponents();
        this.beliPemesanan = new PemesananPembelian();

        txtTanggal.setDate(new Date());

        this.repoBarang = new ServiceBarang(Koneksi.getDataSource());
        this.repoBeli = new ServicePemesananPembelian(Koneksi.getDataSource());
        this.repoPemasok = new ServicePemasok(Koneksi.getDataSource());

        this.daftarBarang = new ArrayList<>();
        this.daftarPemasok = new ArrayList<>();
        this.daftarPesanBarang = new ArrayList<>();

        loadDataBarang();
        loadDataPemasok();

        this.tableModel = (DefaultTableModel) tableOrder.getModel();
        this.tableModel.getDataVector().removeAllElements();
        this.tableModel.fireTableDataChanged();

        try {
            Integer jumlahTransaksi;
            jumlahTransaksi = repoBeli.findAll().size() + 1;
            beliPemesanan.setKode("PO-" + new SimpleDateFormat("yyMMdd").format(new Date()) + "-" + String.format("%03d", jumlahTransaksi));
            txtKodePesan.setText(beliPemesanan.getKode());
        } catch (SQLException ex) {
            Logger.getLogger(PemesananPembelianView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void reloadDaftarBarangYgDipesan() {
        this.tableModel.getDataVector().removeAllElements();
        this.tableModel.fireTableDataChanged();
        for (PemesananPembelianDetail beli : daftarPesanBarang) {
            Barang barang = beli.getBarang();
            Object[] row = {
                barang.getKode(),
                barang.getNama(),
                beli.getHarga(),
                beli.getJumlah(),
                (beli.getHarga() * beli.getJumlah())};
            tableModel.addRow(row);
        }
    }

    private void loadDataBarang() {
        try {
            this.daftarBarang.clear();
            this.daftarBarang = repoBarang.findAll();
            txtKodeBarang.removeAllItems();
            for (Barang brg : this.daftarBarang) {
                txtKodeBarang.addItem(brg.getKode());
            }

        } catch (SQLException ex) {
            Logger.getLogger(PemesananPembelianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDataPemasok() {
        try {
            this.daftarPemasok.clear();
            this.daftarPemasok = repoPemasok.findAll();
            txtKodePemasok.removeAllItems();
            for (Pemasok pemasok : this.daftarPemasok) {
                txtKodePemasok.addItem(pemasok.getKode() + " (" + pemasok.getNama() + ")");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PemesananPembelianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtKodePemasok = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtNamaPemasok = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtKodeBarang = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtNamaBarang = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        btnAddBarang = new javax.swing.JButton();
        btnRemoveBarang = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtJumlahPesan = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        txtKodePesan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTanggal = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableOrder = new javax.swing.JTable();
        jToolBar2 = new javax.swing.JToolBar();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Pemasok"));

        jLabel4.setText("Kode");

        txtKodePemasok.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtKodePemasokItemStateChanged(evt);
            }
        });

        jLabel5.setText("Nama");

        txtNamaPemasok.setEditable(false);
        txtNamaPemasok.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel6.setText("Alamat");

        txtAlamat.setEditable(false);
        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        txtAlamat.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtAlamat);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 65, Short.MAX_VALUE)
                        .addComponent(txtKodePemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNamaPemasok))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKodePemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 47, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Barang"));

        jLabel7.setText("Kode");

        txtKodeBarang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtKodeBarangItemStateChanged(evt);
            }
        });

        jLabel8.setText("Nama barang");

        txtNamaBarang.setEditable(false);

        jLabel9.setText("Harga");

        txtHarga.setEditable(false);
        txtHarga.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jToolBar1.setRollover(true);

        btnAddBarang.setText("Tambah");
        btnAddBarang.setFocusable(false);
        btnAddBarang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddBarang.setMaximumSize(new java.awt.Dimension(120, 30));
        btnAddBarang.setMinimumSize(new java.awt.Dimension(120, 30));
        btnAddBarang.setPreferredSize(new java.awt.Dimension(120, 30));
        btnAddBarang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAddBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBarangActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAddBarang);

        btnRemoveBarang.setText("Hapus");
        btnRemoveBarang.setFocusable(false);
        btnRemoveBarang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRemoveBarang.setMaximumSize(new java.awt.Dimension(120, 30));
        btnRemoveBarang.setMinimumSize(new java.awt.Dimension(120, 30));
        btnRemoveBarang.setPreferredSize(new java.awt.Dimension(120, 30));
        btnRemoveBarang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRemoveBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveBarangActionPerformed(evt);
            }
        });
        jToolBar1.add(btnRemoveBarang);

        jLabel10.setText("Pesan");

        txtJumlahPesan.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 5));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJumlahPesan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtJumlahPesan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel2.setText("Kode pesanan");

        txtKodePesan.setEditable(false);
        txtKodePesan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtKodePesan.setMinimumSize(new java.awt.Dimension(120, 30));
        txtKodePesan.setPreferredSize(new java.awt.Dimension(120, 30));

        jLabel3.setText("Tanggal");

        txtTanggal.setDateFormatString("dd-MM-yyyy");
        txtTanggal.setMinimumSize(new java.awt.Dimension(150, 30));
        txtTanggal.setPreferredSize(new java.awt.Dimension(150, 30));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Daftar barang yang dipesan"));

        tableOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Harga", "Jumlah", "Sub Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableOrder.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tableOrder);
        if (tableOrder.getColumnModel().getColumnCount() > 0) {
            tableOrder.getColumnModel().getColumn(0).setResizable(false);
            tableOrder.getColumnModel().getColumn(0).setPreferredWidth(100);
            tableOrder.getColumnModel().getColumn(1).setResizable(false);
            tableOrder.getColumnModel().getColumn(1).setPreferredWidth(150);
            tableOrder.getColumnModel().getColumn(2).setResizable(false);
            tableOrder.getColumnModel().getColumn(2).setPreferredWidth(120);
            tableOrder.getColumnModel().getColumn(3).setResizable(false);
            tableOrder.getColumnModel().getColumn(3).setPreferredWidth(80);
            tableOrder.getColumnModel().getColumn(4).setResizable(false);
            tableOrder.getColumnModel().getColumn(4).setPreferredWidth(150);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );

        jToolBar2.setRollover(true);

        btnSave.setText("Simpan");
        btnSave.setFocusable(false);
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave.setMaximumSize(new java.awt.Dimension(100, 30));
        btnSave.setMinimumSize(new java.awt.Dimension(100, 30));
        btnSave.setPreferredSize(new java.awt.Dimension(100, 30));
        btnSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jToolBar2.add(btnSave);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtKodePesan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKodePesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtKodeBarangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtKodeBarangItemStateChanged
        if (txtKodeBarang.getSelectedItem() != null) {
            Barang barang = daftarBarang.get(txtKodeBarang.getSelectedIndex());
            txtNamaBarang.setText(barang.getNama());
            txtHarga.setText(barang.getHargaAsCurrency());
        } else {
            txtNamaBarang.setText("");
            txtHarga.setText(NumberFormat.getCurrencyInstance(Locale.getDefault()).format(0));
        }
    }//GEN-LAST:event_txtKodeBarangItemStateChanged

    private void txtKodePemasokItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtKodePemasokItemStateChanged
        if (txtKodePemasok.getSelectedItem() != null) {
            Pemasok pemasok = daftarPemasok.get(txtKodePemasok.getSelectedIndex());
            txtNamaPemasok.setText(pemasok.getNama());
            txtAlamat.setText(pemasok.getAlamat());
        } else {
            txtNamaPemasok.setText("");
            txtAlamat.setText("");
        }
    }//GEN-LAST:event_txtKodePemasokItemStateChanged

    private void btnAddBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBarangActionPerformed
        if (txtKodeBarang.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Kode barang belum dipilih!", "Data tidak lengkap", JOptionPane.WARNING_MESSAGE);
            txtKodeBarang.requestFocus();
        } else if (Integer.valueOf(txtJumlahPesan.getValue().toString()) == 0) {
            JOptionPane.showMessageDialog(this, "Jumlah barang yang dipesan masih 0", "Data tidak lengkap", JOptionPane.WARNING_MESSAGE);
            txtJumlahPesan.requestFocus();
        } else {
            PemesananPembelianDetail pesanBarang = new PemesananPembelianDetail();
            Barang barang = daftarBarang.get(txtKodeBarang.getSelectedIndex());
            pesanBarang.setBarang(barang);
            pesanBarang.setHarga(barang.getHarga());
            pesanBarang.setJumlah(Integer.valueOf(txtJumlahPesan.getValue().toString()));
            pesanBarang.setPesan(beliPemesanan);

            daftarPesanBarang.add(pesanBarang);
            reloadDaftarBarangYgDipesan();

            txtKodeBarang.setSelectedItem(null);
            txtJumlahPesan.setValue(0);
        }
    }//GEN-LAST:event_btnAddBarangActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            beliPemesanan.setTgl(java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(txtTanggal.getDate())));
            beliPemesanan.setPemasok(daftarPemasok.get(txtKodePemasok.getSelectedIndex()));
            this.repoBeli.save(beliPemesanan, daftarPesanBarang);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(PemesananPembelianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnRemoveBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveBarangActionPerformed
        Integer rowSelected = tableOrder.getSelectedRow();
        if (rowSelected > -1) {
            daftarPesanBarang.remove(rowSelected.intValue());
            reloadDaftarBarangYgDipesan();
        } else {
            JOptionPane.showMessageDialog(this, "Data barang yang akan dibeli belum dipilih", "Hapus data", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnRemoveBarangActionPerformed

    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PemesananPembelianView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddBarang;
    private javax.swing.JButton btnRemoveBarang;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JTable tableOrder;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JSpinner txtJumlahPesan;
    private javax.swing.JComboBox<String> txtKodeBarang;
    private javax.swing.JComboBox<String> txtKodePemasok;
    private javax.swing.JTextField txtKodePesan;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtNamaPemasok;
    private com.toedter.calendar.JDateChooser txtTanggal;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.view.transaksi;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import penjualan.config.Koneksi;
import penjualan.config.StringConverter;
import penjualan.entity.Barang;
import penjualan.entity.Pemasok;
import penjualan.entity.Pembelian;
import penjualan.entity.PembelianDetail;
import penjualan.entity.PemesananPembelian;
import penjualan.entity.PemesananPembelianDetail;
import penjualan.repository.RepositoryPembelian;
import penjualan.repository.RepositoryPemesananPembelian;
import penjualan.service.ServicePembelian;
import penjualan.service.ServicePemesananPembelian;

/**
 *
 * @author dimmaryanto
 */
public class AmbilPesananPembelianView extends javax.swing.JFrame {

    private final Pembelian pembelian;
    private final List<PembelianDetail> daftarBarangBeli;
    private final List<PemesananPembelian> daftarPesanan;
    private final List<PemesananPembelianDetail> daftarPesananBarang;
    private Pemasok pemasok;
    PemesananPembelian pesan;

    private final RepositoryPembelian repoBeli;
    private final RepositoryPemesananPembelian repoPesan;

    private final DefaultTableModel modelTable;

    public AmbilPesananPembelianView() {
        setTitle("Pembayaran pesanan pembelian");
        initComponents();

        modelTable = (DefaultTableModel) tableBelanjaan.getModel();

        this.pembelian = new Pembelian();
        this.daftarBarangBeli = new ArrayList<>();
        this.daftarPesanan = new ArrayList<>();
        this.daftarPesananBarang = new ArrayList<>();

        this.repoBeli = new ServicePembelian(Koneksi.getDataSource());
        this.repoPesan = new ServicePemesananPembelian(Koneksi.getDataSource());

        txtTanggalBeli.setDate(new Date());
        loadPesanan();

        try {
            Integer noUrut = repoBeli.findAll().size() + 1;
            pembelian.setKode("PB-" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "-" + noUrut);
            txtKodePembelian.setText(pembelian.getKode());
        } catch (SQLException ex) {
            Logger.getLogger(AmbilPesananPembelianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadPesanan() {
        try {
            this.daftarPesanan.clear();
            this.txtKodePesanan.removeAllItems();
            for (PemesananPembelian beli : repoPesan.findAll()) {
                this.daftarPesanan.add(beli);
                this.txtKodePesanan.addItem(beli.getKode());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AmbilPesananPembelianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtKodePesanan = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtTanggalPesan = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableBelanjaan = new javax.swing.JTable();
        txtTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        btnSimpan = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtKodePembelian = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTanggalBeli = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtNamaPemasok = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamatPemasok = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data pemesanan pembelian\n"));

        jLabel1.setText("Kode Transaksi");

        txtKodePesanan.setAutoscrolls(true);
        txtKodePesanan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtKodePesananItemStateChanged(evt);
            }
        });

        jLabel2.setText("Tanggal Pesanan");

        txtTanggalPesan.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKodePesanan, 0, 170, Short.MAX_VALUE)
                    .addComponent(txtTanggalPesan))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKodePesanan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTanggalPesan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Daftar belanjaan"));

        tableBelanjaan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama Barang", "Harga Beli", "Jumlah", "Sub Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBelanjaan.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tableBelanjaan);
        if (tableBelanjaan.getColumnModel().getColumnCount() > 0) {
            tableBelanjaan.getColumnModel().getColumn(0).setResizable(false);
            tableBelanjaan.getColumnModel().getColumn(0).setPreferredWidth(100);
            tableBelanjaan.getColumnModel().getColumn(1).setResizable(false);
            tableBelanjaan.getColumnModel().getColumn(1).setPreferredWidth(200);
            tableBelanjaan.getColumnModel().getColumn(2).setResizable(false);
            tableBelanjaan.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableBelanjaan.getColumnModel().getColumn(3).setResizable(false);
            tableBelanjaan.getColumnModel().getColumn(3).setPreferredWidth(80);
            tableBelanjaan.getColumnModel().getColumn(4).setResizable(false);
            tableBelanjaan.getColumnModel().getColumn(4).setPreferredWidth(120);
        }

        txtTotal.setEditable(false);
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel5.setText("Grant Total");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jToolBar1.setRollover(true);

        btnSimpan.setText("Simpan");
        btnSimpan.setFocusable(false);
        btnSimpan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSimpan.setMaximumSize(new java.awt.Dimension(120, 35));
        btnSimpan.setMinimumSize(new java.awt.Dimension(120, 35));
        btnSimpan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSimpan);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Data transaksi pembelian"));

        jLabel6.setText("Kode Transaksi");

        txtKodePembelian.setEditable(false);
        txtKodePembelian.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel7.setText("Tanggal Beli");

        txtTanggalBeli.setDateFormatString("dd-MM-yyyy");

        jLabel3.setText("Nama Pemasok");

        txtNamaPemasok.setEditable(false);

        jLabel4.setText("Alamat Pemasok");

        txtAlamatPemasok.setEditable(false);
        txtAlamatPemasok.setColumns(20);
        txtAlamatPemasok.setRows(5);
        jScrollPane1.setViewportView(txtAlamatPemasok);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKodePembelian)
                            .addComponent(txtTanggalBeli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamaPemasok)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKodePembelian, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTanggalBeli, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtNamaPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtKodePesananItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtKodePesananItemStateChanged
        modelTable.getDataVector().removeAllElements();
        modelTable.fireTableDataChanged();
        daftarPesananBarang.clear();
        if (txtKodePesanan.getSelectedItem() != null) {
            try {
                pesan = daftarPesanan.get(txtKodePesanan.getSelectedIndex());
                txtTanggalPesan.setText(StringConverter.getDateTime(pesan.getTgl().toLocalDate()));
                txtNamaPemasok.setText(pesan.getPemasok().getNama());
                txtAlamatPemasok.setText(pesan.getPemasok().getAlamat());
                Double grantTotal = 0D;
                for (PemesananPembelianDetail beli : repoPesan.findByPemesananPembelianKode(pesan.getKode())) {
                    Barang brg = beli.getBarang();
                    daftarPesananBarang.add(beli);
                    Object[] row = {
                        brg.getKode(),
                        brg.getNama(),
                        beli.getHarga(),
                        beli.getJumlah(),
                        (beli.getHarga() * beli.getJumlah())
                    };
                    modelTable.addRow(row);
                    grantTotal += (beli.getHarga() * beli.getJumlah());
                }
                txtTotal.setText(StringConverter.getCurrency(grantTotal));
            } catch (SQLException ex) {
                Logger.getLogger(AmbilPesananPembelianView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.pesan = null;
            txtTanggalPesan.setText("");
            txtNamaPemasok.setText("");
            txtAlamatPemasok.setText("");
            txtTotal.setText(StringConverter.getCurrency(0));
        }
    }//GEN-LAST:event_txtKodePesananItemStateChanged

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        try {
            pembelian.setTgl(java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(txtTanggalBeli.getDate())));
            pembelian.setPemasok(pesan.getPemasok());
            for (PemesananPembelianDetail akanDibeli : daftarPesananBarang) {
                PembelianDetail beli = new PembelianDetail();
                beli.setBarang(akanDibeli.getBarang());
                beli.setHarga(akanDibeli.getHarga());
                beli.setJumlah(akanDibeli.getJumlah());
                beli.setPembelian(pembelian);
                daftarBarangBeli.add(beli);
            }
            repoBeli.save(pembelian, daftarBarangBeli);
            repoPesan.delete(pesan.getKode());
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(AmbilPesananPembelianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AmbilPesananPembelianView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tableBelanjaan;
    private javax.swing.JTextArea txtAlamatPemasok;
    private javax.swing.JTextField txtKodePembelian;
    private javax.swing.JComboBox<String> txtKodePesanan;
    private javax.swing.JTextField txtNamaPemasok;
    private com.toedter.calendar.JDateChooser txtTanggalBeli;
    private javax.swing.JTextField txtTanggalPesan;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

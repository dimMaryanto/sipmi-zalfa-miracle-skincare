/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.view.Laporan;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import penjualan.config.Koneksi;
import penjualan.entity.Barang;
import penjualan.entity.DaftarSirkulasiBarang;
import penjualan.entity.PembelianDetail;
import penjualan.entity.PenjualanDetail;
import penjualan.repository.RepositoryBarang;
import penjualan.service.ServiceBarang;
import penjualan.service.ServiceLaporan;

/**
 *
 * @author dimmaryanto
 */
public class LaporanSirkulasiBarang extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;

    private final ServiceLaporan serviceLaporan;
    private final RepositoryBarang repoBarang;
    private List<Barang> daftarBarang;
    private List<DaftarSirkulasiBarang> daftarSirkuliBarang;
    private DefaultTableModel tableModel;

    /**
     * Creates new form LaporanSirkulasiBarang
     *
     * @param parent
     * @param modal
     */
    public LaporanSirkulasiBarang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.tableModel = (DefaultTableModel) tableSirkulasi.getModel();
        this.serviceLaporan = new ServiceLaporan(Koneksi.getDataSource());
        this.repoBarang = new ServiceBarang(Koneksi.getDataSource());
        this.daftarSirkuliBarang = new ArrayList<>();

        try {
            txtAwal.setDate(new Date());
            txtAkhir.setDate(new Date());
            this.daftarBarang = repoBarang.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(LaporanSirkulasiBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshData() {
        tableModel.getDataVector().removeAllElements();
        tableModel.fireTableDataChanged();
        for (DaftarSirkulasiBarang sirkulasi : daftarSirkuliBarang) {
            Barang brg = sirkulasi.getBarang();
            Object[] row = {
                brg.getKode(),
                brg.getNama(),
                sirkulasi.getStokBarangKeluar(),
                sirkulasi.getStokBarangMasuk(),
                sirkulasi.getStokBarangSekarang()
            };
            tableModel.addRow(row);
        }
    }

    private void prosesMerge(List<PenjualanDetail> daftarJualBarang, List<PembelianDetail> daftarBeliBarang) {
        this.daftarSirkuliBarang.clear();
        for (Barang brg : daftarBarang) {
            Integer jumlahJual = 0;
            Integer jumlahBeli = 0;
            for (PenjualanDetail jual : daftarJualBarang) {
                Barang b = jual.getBarang();
                if (brg.getKode().equalsIgnoreCase(b.getKode())) {
                    jumlahJual += 1;
                }
            }

            for (PembelianDetail beli : daftarBeliBarang) {
                Barang b = beli.getBarang();
                if (brg.getKode().equalsIgnoreCase(b.getKode())) {
                    jumlahBeli += 1;
                }
            }

            DaftarSirkulasiBarang sirkulasiBarang = new DaftarSirkulasiBarang();
            sirkulasiBarang.setBarang(brg);
            sirkulasiBarang.setStokBarangKeluar(jumlahJual);
            sirkulasiBarang.setStokBarangMasuk(jumlahBeli);
            sirkulasiBarang.setStokBarangSekarang(brg.getJumlah());
            daftarSirkuliBarang.add(sirkulasiBarang);

            // TODO add to list
            System.out.println("Penjualan Kode barang :" + brg.getKode() + " jumlah " + jumlahJual);
            System.out.println("Pembelian Kode barang :" + brg.getKode() + " jumlah " + jumlahBeli);
            System.out.println("------------------------------------------------------------------");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnTampil = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        txtAwal = new com.toedter.calendar.JDateChooser();
        txtAkhir = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSirkulasi = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Laporan Sirkulasi Barang");

        jLabel2.setText("Dari tanggal");

        jLabel3.setText("Sampai tanggal");

        btnTampil.setText("Tampilkan");
        btnTampil.setMaximumSize(new java.awt.Dimension(100, 25));
        btnTampil.setMinimumSize(new java.awt.Dimension(100, 25));
        btnTampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Sirkulasi barang"));

        tableSirkulasi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode barang", "Nama barang", "Barang Masuk", "Barang Keluar", "Stok saat ini"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSirkulasi.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableSirkulasi);
        if (tableSirkulasi.getColumnModel().getColumnCount() > 0) {
            tableSirkulasi.getColumnModel().getColumn(0).setResizable(false);
            tableSirkulasi.getColumnModel().getColumn(0).setPreferredWidth(100);
            tableSirkulasi.getColumnModel().getColumn(1).setResizable(false);
            tableSirkulasi.getColumnModel().getColumn(1).setPreferredWidth(170);
            tableSirkulasi.getColumnModel().getColumn(2).setResizable(false);
            tableSirkulasi.getColumnModel().getColumn(2).setPreferredWidth(80);
            tableSirkulasi.getColumnModel().getColumn(3).setResizable(false);
            tableSirkulasi.getColumnModel().getColumn(3).setPreferredWidth(80);
            tableSirkulasi.getColumnModel().getColumn(4).setResizable(false);
            tableSirkulasi.getColumnModel().getColumn(4).setPreferredWidth(80);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
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
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 147, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnTampil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtAwal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtAkhir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(160, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAwal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtAkhir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTampil, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilActionPerformed
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date tglAwal = java.sql.Date.valueOf(dateFormat.format(txtAwal.getDate()));
            java.sql.Date tglAkhir = java.sql.Date.valueOf(dateFormat.format(txtAkhir.getDate()));
            List<PenjualanDetail> daftarPenjualan = serviceLaporan.findPenjualanDetailBetweenTanggal(
                    tglAwal, tglAkhir);
            List<PembelianDetail> daftarPembelian = serviceLaporan.findPembelianDetailBetweenTanggal(
                    tglAwal, tglAkhir);
            prosesMerge(daftarPenjualan, daftarPembelian);
            refreshData();
        } catch (SQLException ex) {
            Logger.getLogger(LaporanSirkulasiBarang.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnTampilActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LaporanSirkulasiBarang dialog = new LaporanSirkulasiBarang(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnTampil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableSirkulasi;
    private com.toedter.calendar.JDateChooser txtAkhir;
    private com.toedter.calendar.JDateChooser txtAwal;
    // End of variables declaration//GEN-END:variables
}

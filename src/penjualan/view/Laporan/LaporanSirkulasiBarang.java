/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.view.Laporan;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import penjualan.config.Koneksi;
import penjualan.entity.Barang;
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

    private final ServiceLaporan serviceLaporan;
    private final RepositoryBarang repoBarang;
    private List<Barang> daftarBarang;

    /**
     * Creates new form LaporanSirkulasiBarang
     *
     * @param parent
     * @param modal
     */
    public LaporanSirkulasiBarang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.serviceLaporan = new ServiceLaporan(Koneksi.getDataSource());
        this.repoBarang = new ServiceBarang(Koneksi.getDataSource());

        try {
            txtAwal.setDate(new Date());
            txtAkhir.setDate(new Date());
            this.daftarBarang = repoBarang.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(LaporanSirkulasiBarang.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void prosesMerge(List<PenjualanDetail> daftarJualBarang, List<PembelianDetail> daftarBeliBarang) {
        System.out.println("Jumlah barang jual :" + daftarJualBarang.size());
        System.out.println("Jumlah barang :" + daftarBarang.size());
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
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
                            .addComponent(txtAkhir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(49, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addContainerGap(49, Short.MAX_VALUE))
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
                .addContainerGap(20, Short.MAX_VALUE))
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
            prosesMerge(daftarPenjualan, new ArrayList<PembelianDetail>());
        } catch (SQLException ex) {
            Logger.getLogger(LaporanSirkulasiBarang.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnTampilActionPerformed

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
            java.util.logging.Logger.getLogger(LaporanSirkulasiBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LaporanSirkulasiBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LaporanSirkulasiBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LaporanSirkulasiBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

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
    private com.toedter.calendar.JDateChooser txtAkhir;
    private com.toedter.calendar.JDateChooser txtAwal;
    // End of variables declaration//GEN-END:variables
}

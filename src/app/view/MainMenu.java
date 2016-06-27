package app.view;

import javax.swing.DesktopManager;
import javax.swing.JInternalFrame;

/**
 *
 * @author dimmaryanto
 */
public class MainMenu extends javax.swing.JFrame {

    public MainMenu() {
        initComponents();
    }
    
    private void setContent(JInternalFrame internal){
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        mnuButtonBar = new javax.swing.JToolBar();
        bPelanggan = new javax.swing.JButton();
        bPemasok = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        content = new javax.swing.JDesktopPane();
        mnuBar = new javax.swing.JMenuBar();
        mnuAplikasi = new javax.swing.JMenu();
        mniLogin = new javax.swing.JMenuItem();
        mniLogout = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mniExit = new javax.swing.JMenuItem();
        mnuDataMaster = new javax.swing.JMenu();
        mniPelanggan = new javax.swing.JMenuItem();
        mniPemasok = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mniBarang = new javax.swing.JMenuItem();
        mnuTransaksi = new javax.swing.JMenu();
        mniPemesananPembelian = new javax.swing.JMenuItem();
        mniAbilPesananPembelian = new javax.swing.JMenuItem();
        mniPembelian = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        mniPenjualan = new javax.swing.JMenuItem();
        mnuLaporan = new javax.swing.JMenu();
        mniLaporanPenjualan = new javax.swing.JMenuItem();
        mniLaporanPembelian = new javax.swing.JMenuItem();
        mniLaporanKeuangan = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        mniLaporanSirkulasiBarang = new javax.swing.JMenuItem();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 600));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setPreferredSize(new java.awt.Dimension(1024, 600));
        getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

        mnuButtonBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        mnuButtonBar.setRollover(true);
        mnuButtonBar.setAutoscrolls(true);
        mnuButtonBar.setPreferredSize(new java.awt.Dimension(200, 25));

        bPelanggan.setForeground(new java.awt.Color(0, 0, 0));
        bPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home-button/pelanggan.png"))); // NOI18N
        bPelanggan.setText("Data Pelanggan");
        bPelanggan.setBorderPainted(false);
        bPelanggan.setFocusable(false);
        bPelanggan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bPelanggan.setMaximumSize(new java.awt.Dimension(200, 72));
        bPelanggan.setMinimumSize(new java.awt.Dimension(160, 52));
        bPelanggan.setPreferredSize(new java.awt.Dimension(160, 72));
        bPelanggan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPelangganActionPerformed(evt);
            }
        });
        mnuButtonBar.add(bPelanggan);

        bPemasok.setForeground(new java.awt.Color(0, 0, 0));
        bPemasok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home-button/supplier.jpg"))); // NOI18N
        bPemasok.setText("Data Pemasok");
        bPemasok.setBorderPainted(false);
        bPemasok.setFocusable(false);
        bPemasok.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bPemasok.setMaximumSize(new java.awt.Dimension(200, 72));
        bPemasok.setMinimumSize(new java.awt.Dimension(160, 52));
        bPemasok.setPreferredSize(new java.awt.Dimension(160, 72));
        bPemasok.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mnuButtonBar.add(bPemasok);
        mnuButtonBar.add(jSeparator1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home-button/pembelian.jpg"))); // NOI18N
        jButton1.setText("Pembelian");
        jButton1.setBorderPainted(false);
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setMaximumSize(new java.awt.Dimension(200, 72));
        jButton1.setMinimumSize(new java.awt.Dimension(160, 52));
        jButton1.setPreferredSize(new java.awt.Dimension(160, 72));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mnuButtonBar.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home-button/penjualan.jpg"))); // NOI18N
        jButton2.setText("Penjualan");
        jButton2.setBorderPainted(false);
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setMaximumSize(new java.awt.Dimension(200, 72));
        jButton2.setMinimumSize(new java.awt.Dimension(160, 52));
        jButton2.setPreferredSize(new java.awt.Dimension(160, 72));
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mnuButtonBar.add(jButton2);
        mnuButtonBar.add(jSeparator2);

        getContentPane().add(mnuButtonBar, java.awt.BorderLayout.LINE_START);
        getContentPane().add(content, java.awt.BorderLayout.CENTER);

        mnuAplikasi.setText("Aplikasi");

        mniLogin.setText("Login");
        mnuAplikasi.add(mniLogin);

        mniLogout.setText("Logout");
        mnuAplikasi.add(mniLogout);
        mnuAplikasi.add(jSeparator3);

        mniExit.setText("Exit");
        mniExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniExitActionPerformed(evt);
            }
        });
        mnuAplikasi.add(mniExit);

        mnuBar.add(mnuAplikasi);

        mnuDataMaster.setText("Data Master");

        mniPelanggan.setText("Pelanggan");
        mnuDataMaster.add(mniPelanggan);

        mniPemasok.setText("Pemasok");
        mnuDataMaster.add(mniPemasok);
        mnuDataMaster.add(jSeparator4);

        mniBarang.setText("Barang");
        mnuDataMaster.add(mniBarang);

        mnuBar.add(mnuDataMaster);

        mnuTransaksi.setText("Transaksi");

        mniPemesananPembelian.setText("Pemesanan Pembelian");
        mnuTransaksi.add(mniPemesananPembelian);

        mniAbilPesananPembelian.setText("Ambil Pesanan Pembelian");
        mnuTransaksi.add(mniAbilPesananPembelian);

        mniPembelian.setText("Pembelian");
        mnuTransaksi.add(mniPembelian);
        mnuTransaksi.add(jSeparator5);

        mniPenjualan.setText("Penjualan");
        mnuTransaksi.add(mniPenjualan);

        mnuBar.add(mnuTransaksi);

        mnuLaporan.setText("Laporan");

        mniLaporanPenjualan.setText("Laporan Penjualan");
        mnuLaporan.add(mniLaporanPenjualan);

        mniLaporanPembelian.setText("Laporan Pembelian");
        mnuLaporan.add(mniLaporanPembelian);

        mniLaporanKeuangan.setText("Laporan Keuangan");
        mnuLaporan.add(mniLaporanKeuangan);
        mnuLaporan.add(jSeparator6);

        mniLaporanSirkulasiBarang.setText("Laporan Sirkulasi Barang");
        mnuLaporan.add(mniLaporanSirkulasiBarang);

        mnuBar.add(mnuLaporan);

        setJMenuBar(mnuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mniExitActionPerformed

    private void bPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPelangganActionPerformed

    }//GEN-LAST:event_bPelangganActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bPelanggan;
    private javax.swing.JButton bPemasok;
    private javax.swing.JDesktopPane content;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem mniAbilPesananPembelian;
    private javax.swing.JMenuItem mniBarang;
    private javax.swing.JMenuItem mniExit;
    private javax.swing.JMenuItem mniLaporanKeuangan;
    private javax.swing.JMenuItem mniLaporanPembelian;
    private javax.swing.JMenuItem mniLaporanPenjualan;
    private javax.swing.JMenuItem mniLaporanSirkulasiBarang;
    private javax.swing.JMenuItem mniLogin;
    private javax.swing.JMenuItem mniLogout;
    private javax.swing.JMenuItem mniPelanggan;
    private javax.swing.JMenuItem mniPemasok;
    private javax.swing.JMenuItem mniPembelian;
    private javax.swing.JMenuItem mniPemesananPembelian;
    private javax.swing.JMenuItem mniPenjualan;
    private javax.swing.JMenu mnuAplikasi;
    private javax.swing.JMenuBar mnuBar;
    private javax.swing.JToolBar mnuButtonBar;
    private javax.swing.JMenu mnuDataMaster;
    private javax.swing.JMenu mnuLaporan;
    private javax.swing.JMenu mnuTransaksi;
    // End of variables declaration//GEN-END:variables
}

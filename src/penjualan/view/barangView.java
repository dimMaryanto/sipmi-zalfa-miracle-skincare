/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.view;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import penjualan.entity.JKategori;
import penjualan.entity.Barang;
import penjualan.implement.JKategoriImplement;
import penjualan.implement.barangImplement;
import penjualan.interfc.JKategoriInterfc;
import penjualan.interfc.barangInterfc;
import penjualan.koneksi.koneksi;

/**
 *
 * @author Lani
 */
public class barangView extends javax.swing.JFrame {

    List<JKategori> record = new ArrayList<JKategori>();
    JKategoriInterfc jkategori;
    int row1;
    List<Barang> rekord = new ArrayList<Barang>();
    barangInterfc brgServis;
    int row2;
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

    /**
     * Creates new form brgView
     */
    public barangView() throws SQLException {
        initComponents();
        jkategori = new JKategoriImplement();
        tabelKategori.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                row1 = tabelKategori.getSelectedRow();
                if (row1 != -1) {
                    isiTextKiri();
                }
            }
        });

        brgServis = new barangImplement();
        tabelBarang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                row2 = tabelBarang.getSelectedRow();
                if (row2 != -1) {
                    isiTextKanan();
                }
            }
        });
        this.statusAwal();
    }

    void loadData() {
        try {
            record = jkategori.getAll();
            rekord = brgServis.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(barangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void isiTabel1() {
        Object data[][] = new Object[record.size()][2];
        int x = 0;
        for (JKategori jKateg : record) {
            data[x][0] = jKateg.getid_kategori();
            data[x][1] = jKateg.getkategori();
            x++;
        }
        String judul[] = {"Id Kategori", "Jenis Kategori"};
        tabelKategori.setModel(new DefaultTableModel(data, judul));
        jScrollPane3.setViewportView(tabelKategori);
    }

    void isiTabel2() {
        Object data[][] = new Object[rekord.size()][6];
        int x = 0;
        for (Barang brg : rekord) {
            data[x][0] = brg.getKodeBarang();
            data[x][1] = brg.getKodeKategori();
            data[x][2] = brg.getNamaBarang();
            data[x][3] = brg.getHarga();
            data[x][4] = brg.getJumlah();
            x++;
        }
        String judul[] = {"Kode Barang", "Id Kategori", "Nama Barang", "Harga", "Jumlah"};
        tabelBarang.setModel(new DefaultTableModel(data, judul));
        jScrollPane4.setViewportView(tabelBarang);
    }

    void kosongkanTextKiri() {
        txtIdKategori.setText("");
    }

    void isiKodeKategori() {
        int akhir = record.size() - 1;
        JKategori kategoriAkhir = record.get(akhir);
        int kodeAkhir = Integer.parseInt(kategoriAkhir.getid_kategori()) + 1;
        txtIdKategori.setText(kodeAkhir + "");
    }

    void isiTextKiri() {
        JKategori tKateg = record.get(row1);
        txtIdKategori.setText(tKateg.getid_kategori());
        txtJKateg.setText(tKateg.getkategori());
    }

    void isiTextKanan() {
        Barang brg = rekord.get(row2);
        txtKodeBarang.setText(brg.getKodeBarang());
        txtNamaBarang.setText(brg.getNamaBarang());
        txtHarga.setText(String.valueOf(brg.getHarga()));
        txtJumlah.setText(String.valueOf(brg.getJumlah()));
        String kode_barang = (String) brg.getKodeBarang();
        String kode_barangFix = kode_barang.substring(0, 1);
        try {
            Statement ktB = koneksi.getConnection().createStatement();      //cek koneksi ke database dulu
            ResultSet rsKBr = ktB.executeQuery("select kode_barang, id_kategori, nama_barang, harga, jumlah from barang where kode_barang = '" + kode_barangFix + "' ");     //PILIH DATA YANG SESUAI DARI DATABASE

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setComboKategori() throws SQLException {
        JKategoriImplement ktg = new JKategoriImplement();
        ArrayList<String> arrKtg = ktg.viewKategori();
        comboKategori.addItem("-= PILIH KATEGORI =-");
        for (Object o : arrKtg) {                                                    //LOOPING UNTUK MENAMBAHKAN ISI ARRAY SATU PERSATU KE COMBO BOX
            comboKategori.addItem(o);
        }
    }

    void kosongkanText() {
        txtKodeBarang.setText("");
        txtNamaBarang.setText("");
        comboKategori.setSelectedItem("-= PILIH KATEGORI =-");
        txtNamaBarang.setText("");
        txtHarga.setText("");
        txtJumlah.setText("");
    }

    void statusAwal() throws SQLException {
        kosongkanTextKiri();
        kosongkanText();
        loadData();
        isiKodeKategori();
        isiTabel1();
        isiTabel2();
        setComboKategori();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JLable5 = new javax.swing.JLabel();
        JLabel8 = new javax.swing.JLabel();
        txtIdKategori = new javax.swing.JTextField();
        ubahKateg = new javax.swing.JButton();
        hapusKateg = new javax.swing.JButton();
        tambah1 = new javax.swing.JButton();
        txtJKateg = new javax.swing.JTextField();
        JLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelKategori = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        JLable12 = new javax.swing.JLabel();
        txtKodeBarang = new javax.swing.JTextField();
        tambahkateg4 = new javax.swing.JButton();
        JLable14 = new javax.swing.JLabel();
        comboKategori = new javax.swing.JComboBox();
        JLabel22 = new javax.swing.JLabel();
        txtNamaBarang = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        txtJumlah = new javax.swing.JTextField();
        JLabel23 = new javax.swing.JLabel();
        JLabel25 = new javax.swing.JLabel();
        JLabel26 = new javax.swing.JLabel();
        hapus4 = new javax.swing.JButton();
        ubah4 = new javax.swing.JButton();
        refresh = new javax.swing.JToggleButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        JLable5.setFont(new java.awt.Font("Stencil Std", 1, 18)); // NOI18N
        JLable5.setText(" KATEGOri barang");

        JLabel8.setFont(new java.awt.Font("Stencil Std", 1, 14)); // NOI18N
        JLabel8.setForeground(new java.awt.Color(255, 255, 255));
        JLabel8.setText("JENIS KATEGORI");

        txtIdKategori.setEnabled(false);
        txtIdKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdKategoriActionPerformed(evt);
            }
        });

        ubahKateg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/edit.png"))); // NOI18N
        ubahKateg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahKategActionPerformed(evt);
            }
        });

        hapusKateg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/hapus.png"))); // NOI18N
        hapusKateg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusKategActionPerformed(evt);
            }
        });

        tambah1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/tambah.png"))); // NOI18N
        tambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah1ActionPerformed(evt);
            }
        });

        txtJKateg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJKategActionPerformed(evt);
            }
        });

        JLabel9.setFont(new java.awt.Font("Stencil Std", 1, 14)); // NOI18N
        JLabel9.setForeground(new java.awt.Color(255, 255, 255));
        JLabel9.setText("ID KATEGORI");

        tabelKategori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "id kategori", "jenis kategori"
            }
        ));
        jScrollPane3.setViewportView(tabelKategori);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(JLable5)
                                .addGap(96, 96, 96))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtJKateg, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtIdKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(158, 158, 158))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(tambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ubahKateg, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(hapusKateg, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(JLabel9)
                                    .addComponent(JLabel8))
                                .addGap(0, 251, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLable5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLabel8)
                    .addComponent(txtJKateg, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ubahKateg, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tambah1)
                    .addComponent(hapusKateg, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 204, 204));

        JLable12.setFont(new java.awt.Font("Stencil Std", 1, 18)); // NOI18N
        JLable12.setText("DATA BARANG");

        tambahkateg4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/tambah.png"))); // NOI18N
        tambahkateg4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahkateg4ActionPerformed(evt);
            }
        });

        JLable14.setFont(new java.awt.Font("Stencil Std", 1, 14)); // NOI18N
        JLable14.setForeground(new java.awt.Color(255, 255, 255));
        JLable14.setText("kode Barang");

        comboKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboKategoriActionPerformed(evt);
            }
        });

        JLabel22.setFont(new java.awt.Font("Stencil Std", 1, 14)); // NOI18N
        JLabel22.setForeground(new java.awt.Color(255, 255, 255));
        JLabel22.setText("kategori");

        txtNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaBarangActionPerformed(evt);
            }
        });

        JLabel23.setFont(new java.awt.Font("Stencil Std", 1, 14)); // NOI18N
        JLabel23.setForeground(new java.awt.Color(255, 255, 255));
        JLabel23.setText("JUMLAH");

        JLabel25.setFont(new java.awt.Font("Stencil Std", 1, 14)); // NOI18N
        JLabel25.setForeground(new java.awt.Color(255, 255, 255));
        JLabel25.setText("Harga");

        JLabel26.setFont(new java.awt.Font("Stencil Std", 1, 14)); // NOI18N
        JLabel26.setForeground(new java.awt.Color(255, 255, 255));
        JLabel26.setText("Nama Barang");

        hapus4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/hapus.png"))); // NOI18N
        hapus4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus4ActionPerformed(evt);
            }
        });

        ubah4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/edit.png"))); // NOI18N
        ubah4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubah4ActionPerformed(evt);
            }
        });

        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "null", "null", "null", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tabelBarang);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(JLabel22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(JLable12)
                                .addGap(155, 155, 155))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(tambahkateg4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(refresh)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(hapus4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13)
                                        .addComponent(ubah4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(comboKategori, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtHarga, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                    .addComponent(txtJumlah, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JLabel25)
                            .addComponent(JLabel23)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JLabel26)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(JLable14)
                    .addContainerGap(356, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLable12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLabel22)
                    .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLabel26))
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tambahkateg4)
                    .addComponent(ubah4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hapus4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(63, 63, 63)
                    .addComponent(JLable14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(457, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdKategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdKategoriActionPerformed

    private void ubahKategActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahKategActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubahKategActionPerformed

    private void hapusKategActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusKategActionPerformed
        // TODO add your handling code here:
        try {
            JKategori kateg = new JKategori();
            String kode = txtIdKategori.getText();
            jkategori.delete(kode);
            this.statusAwal();
            JOptionPane.showMessageDialog(this, "data tersimpan");
        } catch (SQLException ex) {
            Logger.getLogger(barangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_hapusKategActionPerformed

    private void tambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah1ActionPerformed
        // TODO add your handling code here:
        try {
            JKategori kateg = new JKategori();
            kateg.setkategori(txtIdKategori.getText());
            jkategori.insert(kateg);
            this.statusAwal();
            JOptionPane.showMessageDialog(this, "data tersimpan");
        } catch (SQLException ex) {
            Logger.getLogger(barangView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_tambah1ActionPerformed

    private void tambahkateg4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahkateg4ActionPerformed
        // TODO add your handling code here:
        String idtipe = txtKodeBarang.getText();
        String idKategori = (String) comboKategori.getSelectedItem();
        String idKategoriFix = idKategori.substring(0, 1);
        String namaBarang = txtNamaBarang.getText();
        String harga = txtHarga.getText();
        String jumlah = txtJumlah.getText();
        try {
            Statement kt = koneksi.getConnection().createStatement();
            ResultSet rsKb = kt.executeQuery("select * from barang where kode_barang='" + idtipe + "'");
            if (rsKb.next()) {
                JOptionPane.showMessageDialog(this, "Data barang dengan kode '" + idtipe + "' telah ada!");
            } else {
                try {
                    PreparedStatement st = (PreparedStatement) koneksi.getConnection().prepareStatement("insert into barang values(?,?,?,?,?)");
                    st.setString(1, idtipe);
                    st.setString(2, idKategoriFix);
                    st.setString(3, namaBarang);
                    st.setString(4, harga);
                    st.setString(5, jumlah);
                    st.executeUpdate();
                    this.statusAwal();
                    JOptionPane.showMessageDialog(this, "data tersimpan");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tambahkateg4ActionPerformed

    private void comboKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboKategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboKategoriActionPerformed

    private void hapus4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus4ActionPerformed
        try {
            Barang kbrg = new Barang();
            String idtipe = txtKodeBarang.getText();
            brgServis.delete(idtipe);
            this.statusAwal();
            JOptionPane.showMessageDialog(this, "data berhasil dihapus");
        } catch (SQLException ex) {
            Logger.getLogger(barangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_hapus4ActionPerformed

    private void ubah4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubah4ActionPerformed
        String idtipe = txtKodeBarang.getText();
        String idKategori = (String) comboKategori.getSelectedItem();
        String idKategoriFix = idKategori.substring(0, 1);
        String namaBarang = txtNamaBarang.getText();
        String harga = txtHarga.getText();
        String jumlah = txtJumlah.getText();
        try {
            PreparedStatement st = (PreparedStatement) koneksi.getConnection().
                    prepareStatement("update barang set id_kategori=?, nama_barang=?, harga=?, jumlah=? where kode_barang=?");
            st.setString(1, idKategoriFix);
            st.setString(2, namaBarang);
            st.setString(3, harga);
            st.setString(4, jumlah);
            st.setString(5, idtipe);
            st.executeUpdate();
            this.statusAwal();
            JOptionPane.showMessageDialog(this, "data berhasil diubah!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_ubah4ActionPerformed

    private void txtJKategActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJKategActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJKategActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        try {
            comboKategori.removeAllItems();
            setComboKategori();
        } catch (SQLException ex) {
            Logger.getLogger(barangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_refreshActionPerformed

    private void txtNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaBarangActionPerformed

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
            java.util.logging.Logger.getLogger(barangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(barangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(barangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(barangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new barangView().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(barangView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabel22;
    private javax.swing.JLabel JLabel23;
    private javax.swing.JLabel JLabel25;
    private javax.swing.JLabel JLabel26;
    private javax.swing.JLabel JLabel8;
    private javax.swing.JLabel JLabel9;
    private javax.swing.JLabel JLable12;
    private javax.swing.JLabel JLable14;
    private javax.swing.JLabel JLable5;
    private javax.swing.JComboBox comboKategori;
    private javax.swing.JButton hapus4;
    private javax.swing.JButton hapusKateg;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToggleButton refresh;
    private javax.swing.JTable tabelBarang;
    private javax.swing.JTable tabelKategori;
    private javax.swing.JButton tambah1;
    private javax.swing.JButton tambahkateg4;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtIdKategori;
    private javax.swing.JTextField txtJKateg;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKodeBarang;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JButton ubah4;
    private javax.swing.JButton ubahKateg;
    // End of variables declaration//GEN-END:variables
}

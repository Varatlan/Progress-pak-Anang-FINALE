/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import Main.Pegawai;
import Main.Skripsi;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RIGISEFA
 */
public class PerbaruiSkripsi extends javax.swing.JPanel {
    
    Connection conn = null;
    
    public void peringatan(String pesan) {
        Component rootPane = null;
        JOptionPane.showMessageDialog(rootPane, pesan);
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Akhir", "postgres", "uinsa");
        } catch (SQLException ex) {
            Logger.getLogger(PerbaruiSkripsi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ArrayList<Test> dataTest;

    private int masukkanData(Connection conn, String Id_skripsi, String judul, String pengarang, String tahun, String halaman) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("INSERT INTO SKRIPSI (id_skripsi,judul,pengarang,tahun,halaman) VALUES(?,?,?,?,?)");
        pst.setString(1, Id_skripsi);
        pst.setString(2, judul);
        pst.setString(3, pengarang);
        pst.setString(4, tahun);
        pst.setString(5, halaman);
        return pst.executeUpdate();
    }
    
    private int editData(Connection conn, String Id_skripsi, String judul, String pengarang, String tahun, String halaman) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("UPDATE SKRIPSI set judul = ?,pengarang = ?,tahun = ?,halaman = ? where id_skripsi = ? ");
        pst.setString(5, Id_skripsi);
        pst.setString(1, judul);
        pst.setString(2, pengarang);
        pst.setString(3, tahun);
        pst.setString(4, halaman);
        return pst.executeUpdate();
    }
    
    private int hapusData(Connection conn, String Id_skripsi) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("DELETE From SKRIPSI where id_skripsi = ? ");
        pst.setString(1, Id_skripsi);
        return pst.executeUpdate();
    }
    
    private void cariData(){
        EntityManager em = Persistence.createEntityManagerFactory("apkPU").createEntityManager();
        em.getTransaction().begin();
        
        String sql = "";
    if (jComboBoxSearch.getSelectedIndex() == 0) {
        sql = "SELECT s FROM Skripsi s WHERE s.idSkripsi LIKE :parameter";
    } else if (jComboBoxSearch.getSelectedIndex() == 1) {
        sql = "SELECT s FROM Skripsi s WHERE s.judul LIKE :parameter";
    } else if (jComboBoxSearch.getSelectedIndex() == 2) {
        sql = "SELECT s FROM Skripsi s WHERE s.pengarang LIKE :parameter";
    } else if (jComboBoxSearch.getSelectedIndex() == 3) {
        sql = "SELECT s FROM Skripsi s WHERE s.tahun LIKE :parameter";
    } else if (jComboBoxSearch.getSelectedIndex() == 4) {
        sql = "SELECT s FROM Skripsi s WHERE s.halaman LIKE :parameter";
    } 

    Query query = em.createQuery(sql);
    query.setParameter("parameter", "%" + jTextFieldSearch.getText() + "%");

    List<Skripsi> result = query.getResultList();

    DefaultTableModel tb = (DefaultTableModel) jTableSkripsi.getModel();
    tb.setRowCount(0);
    for (Skripsi skripsi : result) {
        Object[] ob = new Object[5];
        ob[0] = skripsi.getIdSkripsi();
        ob[1] = skripsi.getJudul();
        ob[2] = skripsi.getPengarang();
        ob[3] = skripsi.getTahun();
        ob[4] = skripsi.getHalaman();
        tb.addRow(ob);
    }

    jTableSkripsi.setModel(tb);

    em.getTransaction().commit();
    em.close();
}
        
    

    private void Tampil(Connection conn) {
        dataTest.clear();
        try {
            String sql = "select * from skripsi";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Test data = new Test();
                data.setId_skripsi(String.valueOf(rs.getObject(1)));
                data.setJudul(String.valueOf(rs.getObject(2)));
                data.setPengarang(String.valueOf(rs.getObject(3)));
                data.setTahun(String.valueOf(rs.getObject(4)));
                data.setHalaman(String.valueOf(rs.getObject(5)));
                dataTest.add(data);
            }
            DefaultTableModel model = (DefaultTableModel) jTableSkripsi.getModel();
            model.setRowCount(0);
            for (Test data : dataTest) {

                Object[] baris = new Object[5];
                baris[0] = data.getId_skripsi();
                baris[1] = data.getJudul();
                baris[2] = data.getPengarang();
                baris[3] = data.getTahun();
                baris[4] = data.getHalaman();

                model.addRow(baris);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PerbaruiSkripsi.class.getName()).log(Level.SEVERE, null, ex);
        }
        SkripsiId.setText("");
        SkripsiJudul.setText("");
        SkripsiPengarang.setText("");
        SkripsiTahun.setText("");
        SkripsiHalaman.setText("");
    }
    
    /**
     * Creates new form PerbaruiSkripsi
     */
    public PerbaruiSkripsi() {
        try {
            dataTest = new ArrayList<>();
            initComponents();
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Akhir", "postgres", "uinsa");
            Tampil(conn);
        } catch (SQLException ex) {
            Logger.getLogger(BukuP.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        SkripsiJudul = new javax.swing.JTextField();
        SkripsiPengarang = new javax.swing.JTextField();
        SkripsiTahun = new javax.swing.JTextField();
        SkripsiHalaman = new javax.swing.JTextField();
        SkripsiDelete = new javax.swing.JButton();
        SkripsiEdit = new javax.swing.JButton();
        SkripsiInsert = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSkripsi = new javax.swing.JTable();
        jTextFieldSearch = new javax.swing.JTextField();
        jComboBoxSearch = new javax.swing.JComboBox<>();
        jButtonCari = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        SkripsiId = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(153, 102, 0));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Perbarui Skripsi");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel2.setText("judul");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel3.setText("pengarang");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel4.setText("tahun");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel5.setText("halaman");

        SkripsiDelete.setText("Delete");
        SkripsiDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkripsiDeleteActionPerformed(evt);
            }
        });

        SkripsiEdit.setText("Edit");
        SkripsiEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkripsiEditActionPerformed(evt);
            }
        });

        SkripsiInsert.setText("Insert");
        SkripsiInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkripsiInsertActionPerformed(evt);
            }
        });

        jTableSkripsi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id_skripsi", "Judul", "Pengarang", "Tahun", "Halaman"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableSkripsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSkripsiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableSkripsi);

        jTextFieldSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldSearchMouseReleased(evt);
            }
        });

        jComboBoxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id_skripsi", "judul", "pengarang", "tahun", "halaman" }));

        jButtonCari.setText("Cari");
        jButtonCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCariActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel6.setText("id skripsi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldSearch)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(SkripsiInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(SkripsiEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(SkripsiDelete)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButtonCari, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(SkripsiId, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(SkripsiJudul, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                            .addComponent(SkripsiPengarang)
                                            .addComponent(SkripsiTahun)
                                            .addComponent(SkripsiHalaman))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(20, 20, 20)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(SkripsiId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SkripsiJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SkripsiPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SkripsiTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SkripsiHalaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonCari))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SkripsiInsert)
                            .addComponent(SkripsiEdit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SkripsiDelete))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void SkripsiInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkripsiInsertActionPerformed
        String id_skripsi = SkripsiId.getText();
        String judul = SkripsiJudul.getText();
        String pengarang = SkripsiPengarang.getText();
        String tahun = SkripsiTahun.getText();
        String halaman = SkripsiHalaman.getText();
        //
        EntityManager entityManager;
        entityManager = Persistence.createEntityManagerFactory("apkPU").createEntityManager();
        entityManager.getTransaction().begin();
        Skripsi m = new Skripsi();
        m.setIdSkripsi(id_skripsi);
        m.setJudul(judul);
        m.setPengarang(pengarang);
        m.setTahun(tahun);
        m.setHalaman(halaman);
        entityManager.persist(m);
        entityManager.getTransaction().commit();
        //
            Tampil(conn);
    }//GEN-LAST:event_SkripsiInsertActionPerformed

    private void jTableSkripsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSkripsiMouseClicked
        int row = jTableSkripsi.getSelectedRow();
        SkripsiId.setText(jTableSkripsi.getValueAt(row, 0).toString());
        SkripsiJudul.setText(jTableSkripsi.getValueAt(row, 1).toString());
        SkripsiPengarang.setText(jTableSkripsi.getValueAt(row, 2).toString());
        SkripsiTahun.setText(jTableSkripsi.getValueAt(row, 3).toString());
        SkripsiHalaman.setText(jTableSkripsi.getValueAt(row, 4).toString());
    }//GEN-LAST:event_jTableSkripsiMouseClicked

    private void SkripsiEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkripsiEditActionPerformed
       
        String Id_skripsi = SkripsiId.getText();
        String judul = SkripsiJudul.getText();
        String pengarang = SkripsiPengarang.getText();
        String tahun = SkripsiTahun.getText();
        String halaman = SkripsiHalaman.getText();
        //
        EntityManager em;
        em = Persistence.createEntityManagerFactory("apkPU").createEntityManager();
        em.getTransaction().begin();
        Skripsi m = em.find(Skripsi.class, Id_skripsi);
        m.setIdSkripsi(Id_skripsi);
        m.setJudul(judul);
        m.setPengarang(pengarang);
        m.setTahun(tahun);
        m.setHalaman(halaman);
        em.persist(m);
        em.getTransaction().commit();
        //
            Tampil(conn);
    }//GEN-LAST:event_SkripsiEditActionPerformed

    private void SkripsiDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkripsiDeleteActionPerformed
        String Id_skripsi = SkripsiId.getText().trim();
        //awal persistence
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("apkPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Skripsi s = em.find(Skripsi.class, Id_skripsi);
        em.remove(s);
        em.getTransaction().commit();
        // akhir persistence
            Tampil(conn);
    }//GEN-LAST:event_SkripsiDeleteActionPerformed

    private void jTextFieldSearchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSearchMouseReleased
        
    }//GEN-LAST:event_jTextFieldSearchMouseReleased

    private void jButtonCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCariActionPerformed
       
        cariData();
    
    }//GEN-LAST:event_jButtonCariActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SkripsiDelete;
    private javax.swing.JButton SkripsiEdit;
    private javax.swing.JTextField SkripsiHalaman;
    private javax.swing.JTextField SkripsiId;
    private javax.swing.JButton SkripsiInsert;
    private javax.swing.JTextField SkripsiJudul;
    private javax.swing.JTextField SkripsiPengarang;
    private javax.swing.JTextField SkripsiTahun;
    private javax.swing.JButton jButtonCari;
    private javax.swing.JComboBox<String> jComboBoxSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableSkripsi;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import Main.Anggota;
import Main.Pegawai;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author RIGISEFA
 */
public class AnggotaP extends javax.swing.JPanel {

    Connection conn = null;
    
    public void peringatan(String pesan) {
        Component rootPane = null;
        JOptionPane.showMessageDialog(rootPane, pesan);
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Akhir", "postgres", "uinsa");
        } catch (SQLException ex) {
            Logger.getLogger(AnggotaP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ArrayList<Test> dataTest;

    private int masukkanData(Connection conn, String id_anggota, String nama_anggota) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("INSERT INTO anggota (id_anggota,nama_anggota) VALUES(?,?)");
        pst.setString(1, id_anggota);
        pst.setString(2, nama_anggota);
        return pst.executeUpdate();
    }
    
    private int editData(Connection conn, String id_anggota, String nama_anggota) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("UPDATE anggota set nama_anggota = ? where id_anggota = ? ");
        pst.setString(2, id_anggota);
        pst.setString(1, nama_anggota);
        return pst.executeUpdate();
    }
    
    private int hapusData(Connection conn, String id_anggota) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("DELETE From anggota where id_anggota = ? ");
        pst.setString(1, id_anggota);
        return pst.executeUpdate();
    }
    
    private void cariData(){
        EntityManager em = Persistence.createEntityManagerFactory("apkPU").createEntityManager();
        em.getTransaction().begin();
        
        String sql = "";
    if (jComboBoxSearch.getSelectedIndex() == 0) {
        sql = "SELECT a FROM Anggota a WHERE a.idAnggota LIKE :parameter";
    } else if (jComboBoxSearch.getSelectedIndex() == 1) {
        sql = "SELECT a FROM Anggota a WHERE a.namaAnggota LIKE :parameter";
    } 

    Query query = em.createQuery(sql);
    query.setParameter("parameter", "%" + jTextFieldSearch.getText() + "%");

    List<Anggota> result = query.getResultList();

    DefaultTableModel tb = (DefaultTableModel) jTableAnggota.getModel();
    tb.setRowCount(0);
    for (Anggota anggota : result) {
        Object[] ob = new Object[2];
        ob[0] = anggota.getIdAnggota();
        ob[1] = anggota.getNamaAnggota();
        tb.addRow(ob);
    }

    jTableAnggota.setModel(tb);

    em.getTransaction().commit();
    em.close();
}


    private void Tampil(Connection conn) {
        dataTest.clear();
        try {
            String sql = "select * from anggota";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Test data = new Test();
                data.setId_anggota(String.valueOf(rs.getObject(1)));
                data.setNama_anggota(String.valueOf(rs.getObject(2)));
                dataTest.add(data);
            }
            DefaultTableModel model = (DefaultTableModel) jTableAnggota.getModel();
            model.setRowCount(0);
            for (Test data : dataTest) {

                Object[] baris = new Object[2];
                baris[0] = data.getId_anggota();
                baris[1] = data.getNama_anggota();

                model.addRow(baris);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnggotaP.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTextFieldId.setText("");
        jTextFieldNama.setText("");
    }
    
    /**
     * Creates new form PerbaruiSkripsi
     */
    JPanel Pnutama;
    public AnggotaP(JPanel pn) {
        try {
            dataTest = new ArrayList<>();
            initComponents();
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Akhir", "postgres", "uinsa");
            Tampil(conn);
        } catch (SQLException ex) {
            Logger.getLogger(AnggotaP.class.getName()).log(Level.SEVERE, null, ex);
        }
        Pnutama = pn;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAnggota = new javax.swing.JTable();
        jTextFieldSearch = new javax.swing.JTextField();
        jComboBoxSearch = new javax.swing.JComboBox<>();
        jButtonCari = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jTextFieldNama = new javax.swing.JTextField();
        jButtonInsert = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonPrint = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(153, 102, 0));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Anggota");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jTableAnggota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "id_anggota", "nama_anggota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAnggotaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAnggota);

        jComboBoxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id_anggota", "nama_anggota" }));

        jButtonCari.setText("Cari");
        jButtonCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCariActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel2.setText("id anggota");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel3.setText("nama anggota");

        jButtonInsert.setText("Insert");
        jButtonInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonUpdate.setText("Edit");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonPrint.setText("Print");
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldNama, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldId, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                .addGap(8, 8, 8)
                                .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonCari)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonInsert)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonUpdate))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonPrint)))))
                .addGap(103, 103, 103))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCari)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonInsert)
                            .addComponent(jButtonUpdate))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonDelete)
                            .addComponent(jButtonPrint))))
                .addGap(10, 10, 10))
        );

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCariActionPerformed
        // TODO add your handling code here:
        cariData();
        
    }//GEN-LAST:event_jButtonCariActionPerformed

    private void jTableAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAnggotaMouseClicked
        // TODO add your handling code here:
        int row = jTableAnggota.getSelectedRow();
        jTextFieldId.setText(jTableAnggota.getValueAt(row, 0).toString());
        jTextFieldNama.setText(jTableAnggota.getValueAt(row, 1).toString());
    }//GEN-LAST:event_jTableAnggotaMouseClicked

    private void jButtonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertActionPerformed
        String id_anggota = jTextFieldId.getText();
        String nama_anggota = jTextFieldNama.getText();
        //
        EntityManager entityManager;
        entityManager = Persistence.createEntityManagerFactory("apkPU").createEntityManager();
        entityManager.getTransaction().begin();
        Anggota m = new Anggota();
        m.setIdAnggota(id_anggota);
        m.setNamaAnggota(nama_anggota);
        entityManager.persist(m);
        entityManager.getTransaction().commit();
        //
            Tampil(conn);// TODO add your handling code here:
    }//GEN-LAST:event_jButtonInsertActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:
        String id_anggota = jTextFieldId.getText();
        String nama_anggota = jTextFieldNama.getText();
        //
        EntityManager em;
        em = Persistence.createEntityManagerFactory("apkPU").createEntityManager();
        em.getTransaction().begin();
        Anggota m = em.find(Anggota.class, id_anggota);
        m.setIdAnggota(id_anggota);
        m.setNamaAnggota(nama_anggota);
        em.persist(m);
        em.getTransaction().commit();
        //
            Tampil(conn);
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        String id_anggota = jTextFieldId.getText().trim();
        //awal persistence
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("apkPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Anggota s = em.find(Anggota.class, id_anggota);
        em.remove(s);
        em.getTransaction().commit();
        // akhir persistence
            Tampil(conn);// TODO add your handling code here:
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed
        // TODO add your handling code here:
        EntityManager em = Persistence.createEntityManagerFactory("apkPU").createEntityManager();
        em.getTransaction().begin();
        
        String sql = "";
    if (jComboBoxSearch.getSelectedIndex() == 0) {
        sql = "SELECT a FROM Anggota a WHERE a.idAnggota LIKE :parameter";
    } else if (jComboBoxSearch.getSelectedIndex() == 1) {
        sql = "SELECT a FROM Anggota a WHERE a.namaAnggota LIKE :parameter";
    } 

    Query query = em.createQuery(sql);
    query.setParameter("parameter", "%" + jTextFieldSearch.getText() + "%");

    List<Anggota> result = query.getResultList();
    
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);

            HashMap<String, Object> param = new HashMap<>();
            param.put("berdasarkan", "%" + jTextFieldSearch.getText() + "%");

    try {
        String reportPath = "src/Report/reportAnggota.jrxml";
        JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, param, dataSource);
        JasperViewer jv = new JasperViewer(print, false);
        jv.setVisible(true);
    } catch (JRException ex) {
        ex.printStackTrace();
    }

 catch (Exception e) {
    e.printStackTrace();
} finally {
    em.getTransaction().commit();
    em.close();
}
    }//GEN-LAST:event_jButtonPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCari;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBoxSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableAnggota;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldNama;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;
import Main.Anggota;
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
public class PegawaiP extends javax.swing.JPanel {

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

    private int masukkanData(Connection conn, String id_pegawai, String nama_pegawai) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("INSERT INTO pegawai (id_pegawai,nama_pegawai) VALUES(?,?)");
        pst.setString(1, id_pegawai);
        pst.setString(2, nama_pegawai);
        return pst.executeUpdate();
    }
    
    private int editData(Connection conn, String id_pegawai, String nama_pegawai) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("UPDATE pegawai set nama_pegawai = ? where id_pegawai = ? ");
        pst.setString(2, id_pegawai);
        pst.setString(1, nama_pegawai);
        return pst.executeUpdate();
    }
    
    private int hapusData(Connection conn, String id_pegawai) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("DELETE From pegawai where id_pegawai = ? ");
        pst.setString(1, id_pegawai);
        return pst.executeUpdate();
    }
    
    private void cariData(){
        EntityManager em = Persistence.createEntityManagerFactory("apkPU").createEntityManager();
        em.getTransaction().begin();
        
        String sql = "";
    if (jComboBoxSearch.getSelectedIndex() == 0) {
        sql = "SELECT p FROM Pegawai p WHERE p.idPegawai LIKE :parameter";
    } else if (jComboBoxSearch.getSelectedIndex() == 1) {
        sql = "SELECT p FROM Pegawai p WHERE p.namaPegawai LIKE :parameter";
    } 

    Query query = em.createQuery(sql);
    query.setParameter("parameter", "%" + jTextFieldSearch.getText() + "%");

    List<Pegawai> result = query.getResultList();

    DefaultTableModel tb = (DefaultTableModel) jTablePegawai.getModel();
    tb.setRowCount(0);
    for (Pegawai pegawai : result) {
        Object[] ob = new Object[2];
        ob[0] = pegawai.getIdPegawai();
        ob[1] = pegawai.getNamaPegawai();
        tb.addRow(ob);
    }

    jTablePegawai.setModel(tb);

    em.getTransaction().commit();
    em.close();
}

    private void Tampil(Connection conn) {
        dataTest.clear();
        try {
            String sql = "select * from pegawai";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Test data = new Test();
                data.setId_pegawai(String.valueOf(rs.getObject(1)));
                data.setNama_pegawai(String.valueOf(rs.getObject(2)));
                dataTest.add(data);
            }
            DefaultTableModel model = (DefaultTableModel) jTablePegawai.getModel();
            model.setRowCount(0);
            for (Test data : dataTest) {

                Object[] baris = new Object[2];
                baris[0] = data.getId_pegawai();
                baris[1] = data.getNama_pegawai();

                model.addRow(baris);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiP.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTextFieldId.setText("");
        jTextFieldNama.setText("");
    }
    
    /**
     * Creates new form PerbaruiSkripsi
     */
    JPanel Pnutama;
    public PegawaiP(JPanel pn) {
        try {
            dataTest = new ArrayList<>();
            initComponents();
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Akhir", "postgres", "uinsa");
            Tampil(conn);
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiP.class.getName()).log(Level.SEVERE, null, ex);
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
        jScrollPanePegawai = new javax.swing.JScrollPane();
        jTablePegawai = new javax.swing.JTable();
        jTextFieldSearch = new javax.swing.JTextField();
        jComboBoxSearch = new javax.swing.JComboBox<>();
        jButtonCari = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jTextFieldNama = new javax.swing.JTextField();
        jButtonInsert = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonPrint = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(153, 102, 0));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Pegawai");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jTablePegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "id_pegawai", "nama_pegawai"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePegawaiMouseClicked(evt);
            }
        });
        jScrollPanePegawai.setViewportView(jTablePegawai);

        jComboBoxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id_pegawai", "nama_pegawai" }));

        jButtonCari.setText("Cari");
        jButtonCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCariActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel2.setText("id pegawai");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel3.setText("nama pegawai");

        jButtonInsert.setText("Insert");
        jButtonInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertActionPerformed(evt);
            }
        });

        jButtonUpdate.setText("Edit");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
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
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPanePegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCari)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jTextFieldNama, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldSearch, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonInsert)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonUpdate))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonDelete)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonPrint)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPanePegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCari)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel2)
                        .addGap(5, 5, 5)
                        .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonInsert)
                            .addComponent(jButtonUpdate))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonDelete)
                            .addComponent(jButtonPrint))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCariActionPerformed
                
        cariData();
// TODO add your handling code here:
    }//GEN-LAST:event_jButtonCariActionPerformed

    private void jButtonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertActionPerformed
        String id_pegawai = jTextFieldId.getText();
        String nama_pegawai = jTextFieldNama.getText();
        //
        EntityManager entityManager;
        entityManager = Persistence.createEntityManagerFactory("apkPU").createEntityManager();
        entityManager.getTransaction().begin();
        Pegawai m = new Pegawai();
        m.setIdPegawai(id_pegawai);
        m.setNamaPegawai(nama_pegawai);
        entityManager.persist(m);
        entityManager.getTransaction().commit();// TODO add your handling code here:
        Tampil(conn);
    }//GEN-LAST:event_jButtonInsertActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        String id_pegawai = jTextFieldId.getText();
        String nama_pegawai = jTextFieldNama.getText();
        //
        EntityManager em;
        em = Persistence.createEntityManagerFactory("apkPU").createEntityManager();
        em.getTransaction().begin();
        Pegawai m = em.find(Pegawai.class, id_pegawai);
        m.setIdPegawai(id_pegawai);
        m.setNamaPegawai(nama_pegawai);
        em.persist(m);
        em.getTransaction().commit();
        //
            Tampil(conn);// TODO add your handling code here:
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        String id_pegawai = jTextFieldId.getText().trim();
        //awal persistence
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("apkPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Pegawai s = em.find(Pegawai.class, id_pegawai);
        em.remove(s);
        em.getTransaction().commit();
        // akhir persistence
            Tampil(conn);
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jTablePegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePegawaiMouseClicked
        // TODO add your handling code here:
        int row = jTablePegawai.getSelectedRow();
        jTextFieldId.setText(jTablePegawai.getValueAt(row, 0).toString());
        jTextFieldNama.setText(jTablePegawai.getValueAt(row, 1).toString());
    }//GEN-LAST:event_jTablePegawaiMouseClicked

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed
        // TODO add your handling code here:
        EntityManager em = Persistence.createEntityManagerFactory("apkPU").createEntityManager();
        em.getTransaction().begin();
        
        String sql = "";
    if (jComboBoxSearch.getSelectedIndex() == 0) {
        sql = "SELECT p FROM Pegawai p WHERE p.idPegawai LIKE :parameter";
    } else if (jComboBoxSearch.getSelectedIndex() == 1) {
        sql = "SELECT p FROM Pegawai p WHERE p.namaPegawai LIKE :parameter";
    } 

    Query query = em.createQuery(sql);
    query.setParameter("parameter", "%" + jTextFieldSearch.getText() + "%");

    List<Pegawai> result = query.getResultList();
    
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);

            HashMap<String, Object> param = new HashMap<>();
            param.put("berdasarkan", "%" + jTextFieldSearch.getText() + "%");

    try {
        String reportPath = "src/Report/reportPegawai.jrxml";
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
    private javax.swing.JScrollPane jScrollPanePegawai;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTablePegawai;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldNama;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}

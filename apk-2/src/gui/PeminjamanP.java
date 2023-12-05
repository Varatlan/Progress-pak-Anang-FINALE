/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package gui;

import Main.DetailSkripsi;
import Main.DetailSkripsiPK;
import Main.Pegawai;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RIGISEFA
 */
public class PeminjamanP extends javax.swing.JPanel {

    Connection conn = null;
    
    public void peringatan(String pesan) {
        Component rootPane = null;
        JOptionPane.showMessageDialog(rootPane, pesan);
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Akhir", "postgres", "uinsa");
        } catch (SQLException ex) {
            Logger.getLogger(PerbaruiBuku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ArrayList<Test> dataTest;
    
    private void cariDs(){
        EntityManager em = Persistence.createEntityManagerFactory("apkPU").createEntityManager();
        em.getTransaction().begin();
            String sql = "";
            if(jComboBoxSs.getSelectedIndex()==0){
                sql = "SELECT d FROM DetailSkripsi d WHERE d.detailSkripsiPK.idPegawai LIKE :parameter";
            }else if(jComboBoxSs.getSelectedIndex()==1){
                sql = "SELECT d FROM DetailSkripsi d WHERE d.detailSkripsiPK.idAnggota LIKE :parameter";
            }else if(jComboBoxSs.getSelectedIndex()==2){
                sql = "SELECT d FROM DetailSkripsi d WHERE d.detailSkripsiPK.idPinjam LIKE :parameter";
            }else if(jComboBoxSs.getSelectedIndex()==3){
                sql = "SELECT d FROM DetailSkripsi d WHERE d.detailSkripsiPK.idSkripsi LIKE :parameter";
            }else if(jComboBoxSs.getSelectedIndex()==4){
                sql = "SELECT d FROM DetailSkripsi d WHERE d.jumlahSkripsi LIKE :parameter";
            }
            
            Query query = em.createQuery(sql);
    query.setParameter("parameter", "%" + jTextFieldSs.getText() + "%");

    List<DetailSkripsi> result = query.getResultList();       
            
            DefaultTableModel tb = (DefaultTableModel) jTableSd.getModel();
            tb.setRowCount(0);
            for (DetailSkripsi ds : result){
                Object[] ob = new Object[5];
                ob[0] = ds.getDetailSkripsiPK();
                ob[1] = ds.getDetailSkripsiPK();
                ob[2] = ds.getDetailSkripsiPK();
                ob[3] = ds.getDetailSkripsiPK();
                ob[4] = ds.getJumlahSkripsi();
                tb.addRow(ob);
            }
            jTableSd.setModel(tb);

    em.getTransaction().commit();
    em.close();
            }
            
    

    private void TampilDs(Connection conn) {
        dataTest.clear();
        try {
            String sql = "select * from detail_skripsi";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Test data = new Test();
                data.setId_pegawai(String.valueOf(rs.getObject(1)));
                data.setId_anggota(String.valueOf(rs.getObject(2)));
                data.setId_pinjam(String.valueOf(rs.getObject(3)));
                data.setId_skripsi(String.valueOf(rs.getObject(4)));
                data.setJumlah_skripsi(String.valueOf(rs.getObject(5)));
                dataTest.add(data);
            }
            DefaultTableModel model = (DefaultTableModel) jTableSd.getModel();
            model.setRowCount(0);
            for (Test data : dataTest) {

                Object[] baris = new Object[5];
                baris[0] = data.getId_pegawai();
                baris[1] = data.getId_anggota();
                baris[2] = data.getId_pinjam();
                baris[3] = data.getId_skripsi();
                baris[4] = data.getJumlah_skripsi();

                model.addRow(baris);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void TampilDb(Connection conn) {
        dataTest.clear();
        try {
            String sql = "select * from detail_buku";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Test data = new Test();
                data.setId_pegawai(String.valueOf(rs.getObject(1)));
                data.setId_anggota(String.valueOf(rs.getObject(2)));
                data.setId_pinjam(String.valueOf(rs.getObject(3)));
                data.setIsbn(String.valueOf(rs.getObject(4)));
                data.setJumlah_buku(String.valueOf(rs.getObject(5)));
                dataTest.add(data);
            }
            DefaultTableModel model = (DefaultTableModel) jTableBd.getModel();
            model.setRowCount(0);
            for (Test data : dataTest) {

                Object[] baris = new Object[5];
                baris[0] = data.getId_pegawai();
                baris[1] = data.getId_anggota();
                baris[2] = data.getId_pinjam();
                baris[3] = data.getIsbn();
                baris[4] = data.getJumlah_buku();

                model.addRow(baris);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void TampilP(Connection conn) {
        dataTest.clear();
        try {
            String sql = "select * from peminjaman";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Test data = new Test();
                data.setId_pegawai(String.valueOf(rs.getObject(1)));
                data.setId_anggota(String.valueOf(rs.getObject(2)));
                data.setId_pinjam(String.valueOf(rs.getObject(3)));
                data.setTgl_pinjam(String.valueOf(rs.getObject(4)));
                dataTest.add(data);
            }
            DefaultTableModel model = (DefaultTableModel) jTableP.getModel();
            model.setRowCount(0);
            for (Test data : dataTest) {

                Object[] baris = new Object[4];
                baris[0] = data.getId_pegawai();
                baris[1] = data.getId_anggota();
                baris[2] = data.getId_pinjam();
                baris[3] = data.getTgl_pinjam();

                model.addRow(baris);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Creates new form PeminjamanP */
    JPanel Pnutama;
    public PeminjamanP(JPanel pn) {
        try {
            dataTest = new ArrayList<>();
            initComponents();
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Akhir", "postgres", "uinsa");
            TampilDs(conn);
            TampilDb(conn);
            TampilP(conn);
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanP.class.getName()).log(Level.SEVERE, null, ex);
        }
        Pnutama = pn;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSd = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableBd = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableP = new javax.swing.JTable();
        jTextFieldSs = new javax.swing.JTextField();
        jTextFieldBs = new javax.swing.JTextField();
        jTextFieldPs = new javax.swing.JTextField();
        jComboBoxSs = new javax.swing.JComboBox<>();
        jComboBoxBs = new javax.swing.JComboBox<>();
        jComboBoxPs = new javax.swing.JComboBox<>();
        jButtonSd = new javax.swing.JButton();
        jButtonBd = new javax.swing.JButton();
        jButtonP = new javax.swing.JButton();
        jButtonCs = new javax.swing.JButton();
        jButtonCb = new javax.swing.JButton();
        jButtonCp = new javax.swing.JButton();
        jButtonPp = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(153, 102, 0));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Peminjaman");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jTableSd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id_pegawai", "id_anggota", "id_pinjam", "id_skripsi", "jumlah_skripsi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableSd);

        jTableBd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id_pegawai", "id_anggota", "id_pinjam", "isbn", "jumlah_buku"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableBd);

        jTableP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id_pegawai", "id_anggota", "id_pinjam", "tgl_pinjam"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableP);

        jComboBoxSs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id_pegawai", "id_anggota", "id_pinjam", "id_skripsi", "jumlah_skripsi" }));

        jComboBoxBs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id_pegawai", "id_anggota", "id_pinjam", "id_skripsi", "jumlah_buku" }));

        jComboBoxPs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id_pegawai", "id_anggota", "id_pinjam", "tgl_pinjam" }));

        jButtonSd.setText("otakatik");
        jButtonSd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSdActionPerformed(evt);
            }
        });

        jButtonBd.setText("otakatik");

        jButtonP.setText("otakatik");
        jButtonP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPActionPerformed(evt);
            }
        });

        jButtonCs.setText("Cari");
        jButtonCs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCsActionPerformed(evt);
            }
        });

        jButtonCb.setText("Cari");

        jButtonCp.setText("Cari");
        jButtonCp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCpActionPerformed(evt);
            }
        });

        jButtonPp.setText("print");
        jButtonPp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPpActionPerformed(evt);
            }
        });

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
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonSd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCs))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldSs, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxSs, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(163, 163, 163))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldBs, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxBs, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldPs, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxPs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonBd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCb))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCp))
                            .addComponent(jButtonPp))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldSs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxSs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSd)
                            .addComponent(jButtonCs)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldBs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxBs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonBd)
                            .addComponent(jButtonCb))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldPs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxPs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonP)
                            .addComponent(jButtonCp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPp)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))))
        );

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPpActionPerformed

    private void jButtonCpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCpActionPerformed

    private void jButtonCsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCsActionPerformed
        // TODO add your handling code here:
        cariDs();
    }//GEN-LAST:event_jButtonCsActionPerformed

    private void jButtonSdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSdActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButtonSdActionPerformed

    private void jButtonPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPActionPerformed
        // TODO add your handling code here:
        Pnutama.removeAll();
        Pnutama.add(new PerbaruiPeminjaman());
        Pnutama.repaint();
        Pnutama.revalidate();
    }//GEN-LAST:event_jButtonPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBd;
    private javax.swing.JButton jButtonCb;
    private javax.swing.JButton jButtonCp;
    private javax.swing.JButton jButtonCs;
    private javax.swing.JButton jButtonP;
    private javax.swing.JButton jButtonPp;
    private javax.swing.JButton jButtonSd;
    private javax.swing.JComboBox<String> jComboBoxBs;
    private javax.swing.JComboBox<String> jComboBoxPs;
    private javax.swing.JComboBox<String> jComboBoxSs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableBd;
    private javax.swing.JTable jTableP;
    private javax.swing.JTable jTableSd;
    private javax.swing.JTextField jTextFieldBs;
    private javax.swing.JTextField jTextFieldPs;
    private javax.swing.JTextField jTextFieldSs;
    // End of variables declaration//GEN-END:variables

}

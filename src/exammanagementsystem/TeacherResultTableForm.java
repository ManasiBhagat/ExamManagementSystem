/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exammanagementsystem;

import static exammanagementsystem.PrevPaperQuestionsTableForm.DB_URL;
import static exammanagementsystem.PrevPaperQuestionsTableForm.PASS;
import static exammanagementsystem.PrevPaperQuestionsTableForm.USER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manasi
 */
public class TeacherResultTableForm extends javax.swing.JFrame {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/javaprodb";
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    char exam_no;
    String resultId, examNo, studentId, rollNo, marksObtained;
    String firstName, lastName, attendance, result;

    /**
     * Creates new form TeacherResultTableForm
     */
    public TeacherResultTableForm() {
        initComponents();
    }

    public TeacherResultTableForm(char exam_no) {
        initComponents();
        this.exam_no = exam_no;
        showTable();
    }

    public void showTable() {
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String selectQuery = "SELECT rt.result_id,rt.exam_number,rt.student_id,"
                    + "st.first_name,st.last_name,st.roll_no,rt.marks_obtained,"
                    + "rt.attendance,rt.result FROM `result_table` as rt "
                    + "JOIN student_table as st ON rt.student_id = st.student_id "
                    + "WHERE rt.exam_number = '" + exam_no + "'";
            rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                resultId = rs.getString("rt.result_id");
                examNo = rs.getString("rt.exam_number");
                studentId = rs.getString("rt.student_id");
                firstName = rs.getString("st.first_name");
                lastName = rs.getString("st.last_name");
                rollNo = rs.getString("st.roll_no");
                marksObtained = rs.getString("rt.marks_obtained");
                attendance = rs.getString("rt.attendance");
                result = rs.getString("rt.result");

                String table[] = {resultId, examNo, studentId, firstName, lastName,
                    rollNo, marksObtained, attendance, result};
                DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
                tableModel.addRow(table);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void showTableAttendance(String attendanceToggle) {
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String selectQuery = "SELECT rt.result_id,rt.exam_number,rt.student_id,"
                    + "st.first_name,st.last_name,st.roll_no,rt.marks_obtained,"
                    + "rt.attendance,rt.result FROM `result_table` as rt "
                    + "JOIN student_table as st ON rt.student_id = st.student_id "
                    + "WHERE rt.exam_number = '" + exam_no + "' AND rt.attendance = '" + attendanceToggle + "'";
            rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                resultId = rs.getString("rt.result_id");
                examNo = rs.getString("rt.exam_number");
                studentId = rs.getString("rt.student_id");
                firstName = rs.getString("st.first_name");
                lastName = rs.getString("st.last_name");
                rollNo = rs.getString("st.roll_no");
                marksObtained = rs.getString("rt.marks_obtained");
                attendance = rs.getString("rt.attendance");
                result = rs.getString("rt.result");

                String table[] = {resultId, examNo, studentId, firstName, lastName,
                    rollNo, marksObtained, attendance, result};
                DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
                tableModel.addRow(table);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void showTableResult(String resultToggle) {
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String selectQuery = "SELECT rt.result_id,rt.exam_number,rt.student_id,"
                    + "st.first_name,st.last_name,st.roll_no,rt.marks_obtained,"
                    + "rt.attendance,rt.result FROM `result_table` as rt "
                    + "JOIN student_table as st ON rt.student_id = st.student_id "
                    + "WHERE rt.exam_number = '" + exam_no + "' AND rt.result = '" + resultToggle + "'";
            rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                resultId = rs.getString("rt.result_id");
                examNo = rs.getString("rt.exam_number");
                studentId = rs.getString("rt.student_id");
                firstName = rs.getString("st.first_name");
                lastName = rs.getString("st.last_name");
                rollNo = rs.getString("st.roll_no");
                marksObtained = rs.getString("rt.marks_obtained");
                attendance = rs.getString("rt.attendance");
                result = rs.getString("rt.result");

                String table[] = {resultId, examNo, studentId, firstName, lastName,
                    rollNo, marksObtained, attendance, result};
                DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
                tableModel.addRow(table);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        absentButton = new javax.swing.JToggleButton();
        presentButton = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        passButton = new javax.swing.JToggleButton();
        failButton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Student Results");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Result Id", "Exam No.", "Student Id", "First Name", "Last Name", "Roll No.", "Marks Obtained", "Attendance", "Result"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        absentButton.setText("Absent");
        absentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                absentButtonActionPerformed(evt);
            }
        });

        presentButton.setText("Present");
        presentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presentButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Attendance");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(presentButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(absentButton)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(absentButton)
                    .addComponent(presentButton))
                .addContainerGap())
        );

        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Result");

        passButton.setText("Pass");
        passButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passButtonActionPerformed(evt);
            }
        });

        failButton.setText("Fail");
        failButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                failButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(passButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(failButton)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passButton)
                    .addComponent(failButton))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(272, 272, 272)
                            .addComponent(jLabel1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void presentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presentButtonActionPerformed
        // TODO add your handling code here:
        if (presentButton.isSelected()) {
            absentButton.setSelected(false);
            passButton.setSelected(false);
            failButton.setSelected(false);
            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
            tableModel.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Students who were present for the exam.");
            showTableAttendance("Present");
        } else {
            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
            tableModel.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Student Result");
            showTable();
        }
    }//GEN-LAST:event_presentButtonActionPerformed

    private void absentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_absentButtonActionPerformed
        // TODO add your handling code here:
        if (absentButton.isSelected()) {
            presentButton.setSelected(false);
            passButton.setSelected(false);
            failButton.setSelected(false);
            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
            tableModel.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Students who were Absent for the exam.");
            showTableAttendance("Absent");
        } else {
            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
            tableModel.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Student Result");
            showTable();
        }
    }//GEN-LAST:event_absentButtonActionPerformed

    private void passButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passButtonActionPerformed
        // TODO add your handling code here:
        if (passButton.isSelected()) {
            presentButton.setSelected(false);
            absentButton.setSelected(false);
            failButton.setSelected(false);
            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
            tableModel.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Students who Passed the exam.");
            showTableResult("Pass");
        } else {
            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
            tableModel.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Student Result");
            showTable();
        }
    }//GEN-LAST:event_passButtonActionPerformed

    private void failButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_failButtonActionPerformed
        // TODO add your handling code here:
        if (failButton.isSelected()) {
            presentButton.setSelected(false);
            absentButton.setSelected(false);
            passButton.setSelected(false);
            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
            tableModel.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Students who Failed the exam.");
            showTableResult("Fail");
        } else {
            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
            tableModel.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Student Result");
            showTable();
        }
    }//GEN-LAST:event_failButtonActionPerformed

   

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
            java.util.logging.Logger.getLogger(TeacherResultTableForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherResultTableForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherResultTableForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherResultTableForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherResultTableForm().setVisible(true);
            }
        });
    }

    @Override
    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //To change body of generated methods, choose Tools | Templates.
    }

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton absentButton;
    private javax.swing.JToggleButton failButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton passButton;
    private javax.swing.JToggleButton presentButton;
    // End of variables declaration//GEN-END:variables
}

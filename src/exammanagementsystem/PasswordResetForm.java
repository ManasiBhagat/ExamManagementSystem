/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exammanagementsystem;

import static exammanagementsystem.StudentLoginForm.DB_URL;
import static exammanagementsystem.StudentLoginForm.PASS;
import static exammanagementsystem.StudentLoginForm.USER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Manasi
 */
public class PasswordResetForm extends javax.swing.JFrame {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/javaprodb";
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    String username, db_username, db_password, password, confirmPassword;
    int studentId, verificationFlag;

    /**
     * Creates new form PasswordResetForm
     */
    public PasswordResetForm() {
        initComponents();
    }

    public void verifyUsername() {
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String loginQuery = "SELECT student_username FROM student_table "
                    + "WHERE student_username = '" + username + "'";
            rs = stmt.executeQuery(loginQuery);
            while (rs.next()) {
                db_username = rs.getString("student_username");
            }
            stmt.close();
            rs.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePassword() {
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String updateQuery = "UPDATE student_table SET student_password = '" + confirmPassword + "' "
                    + "WHERE student_username = '" + username + "'";
            stmt.executeUpdate(updateQuery);
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
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
        jLabel2 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        verifyButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtNewPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        updatePasswordButton = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Reset Password");
        setResizable(false);

        jLabel2.setText("Username");

        txtUserName.setToolTipText("username..");

        verifyButton.setIcon(new javax.swing.ImageIcon("D:\\MBAIT-SEM1\\Java\\ProjectDocumentation\\image and icons\\1x\\verify.png")); // NOI18N
        verifyButton.setText("Verify");
        verifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("New Password");

        txtNewPassword.setToolTipText("");
        txtNewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNewPasswordActionPerformed(evt);
            }
        });

        jLabel4.setText("Confirm Password");

        updatePasswordButton.setIcon(new javax.swing.ImageIcon("D:\\MBAIT-SEM1\\Java\\ProjectDocumentation\\image and icons\\1x\\update.png")); // NOI18N
        updatePasswordButton.setText("Update");
        updatePasswordButton.setEnabled(false);
        updatePasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePasswordButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(verifyButton))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(22, 22, 22)
                                            .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(txtConfirmPassword)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(updatePasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verifyButton))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(updatePasswordButton)
                .addGap(35, 35, 35))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNewPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNewPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNewPasswordActionPerformed

    private void verifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifyButtonActionPerformed
        // TODO add your handling code here:
        username = txtUserName.getText();
        verifyUsername();
        if (username.equals(db_username)) {
            JOptionPane.showMessageDialog(null, "This username exists.");
            verificationFlag = 1;
            verifyButton.setIcon(new ImageIcon("D:\\MBAIT-SEM1\\Java\\ProjectDocumentation\\image and icons\\1x\\verified.png"));
            updatePasswordButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Username doesn't exist!");
            verificationFlag = 0;
            verifyButton.setIcon(new ImageIcon("D:\\MBAIT-SEM1\\Java\\ProjectDocumentation\\image and icons\\1x\\verify.png"));
            updatePasswordButton.setEnabled(false);
        }
    }//GEN-LAST:event_verifyButtonActionPerformed

    private void updatePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePasswordButtonActionPerformed
        // TODO add your handling code here:
        password = String.valueOf(txtNewPassword.getPassword());
        confirmPassword = String.valueOf(txtConfirmPassword.getPassword());
        if (verificationFlag == 1) {
            if (password.equals(confirmPassword)) {
                System.out.println(password + " " + confirmPassword);
                updatePassword();
                try {
                    con = DriverManager.getConnection(DB_URL, USER, PASS);
                    stmt = con.createStatement();
                    String loginQuery = "SELECT student_username,student_password FROM student_table "
                            + "WHERE student_username = '" + username + "'";
                    rs = stmt.executeQuery(loginQuery);
                    while (rs.next()) {
                        db_username = rs.getString("student_username");
                        db_password = rs.getString("student_password");
                    }
                    JOptionPane.showMessageDialog(null, "Username : " + db_username + "\n"
                            + "New Password : " + db_password);
                    stmt.close();
                    rs.close();
                    con.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Please Verify the username first");
        }
        txtUserName.setText("");
        txtNewPassword.setText("");
        txtConfirmPassword.setText("");
    }//GEN-LAST:event_updatePasswordButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PasswordResetForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PasswordResetForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PasswordResetForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PasswordResetForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PasswordResetForm().setVisible(true);
            }
        });
    }

    @Override
    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JPasswordField txtNewPassword;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JButton updatePasswordButton;
    private javax.swing.JButton verifyButton;
    // End of variables declaration//GEN-END:variables

}

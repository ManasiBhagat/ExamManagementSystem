/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exammanagementsystem;

import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Date;

/**
 *
 * @author Manasi
 */
public class RegistrationForm extends javax.swing.JFrame {

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
    String inputFirstName, inputLastName, inputGender, inputEmailId, inputDOB,
            halfFName, halfLName, inputUsername, inputPassword;
    int inputrollNo, inputStudentId;
    long inputcontactNo;
    Date date, nullDate = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public void getStudentData() {
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            ps = con.prepareStatement("SELECT MAX(student_id)+1 FROM student_table");
            rs = ps.executeQuery();
            while (rs.next()) {
                inputStudentId = rs.getInt(1);
            }
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        if (inputStudentId == 0) {
            inputStudentId = 1;
        }
        inputFirstName = firstName.getText();
        inputLastName = lastName.getText();
        if (rollNumber.getText().equals("")) {
            System.out.println("Roll Number cannot be blank");
        } else {
            inputrollNo = Integer.valueOf(rollNumber.getText());
        }
        if (contactNumber.getText().equals("")) {
            System.out.println("Contact Number cannot be blank");
        } else {
            inputcontactNo = Long.valueOf(contactNumber.getText());
        }
        if (jDateChooser1.getDate() == null) {
            System.out.println("Date Cannot be blank");
        } else {
            date = jDateChooser1.getDate();
            inputDOB = sdf.format(date);
        }
        if (emailId.getText().endsWith("@gmail.com")) {
            inputEmailId = emailId.getText();
        } else {
            JOptionPane.showMessageDialog(null, "Email id is invalid");
        }
        if (femaleRadioButton.isSelected()) {
            inputGender = "F";
        }
        if (maleRadioButton.isSelected()) {
            inputGender = "M";
        }
    }

    /**
     * Creates new form RegistrationForm
     */
    public RegistrationForm() {
        initComponents();
    }

    public void clearFields() {
        firstName.setText("");
        lastName.setText("");
        rollNumber.setText("");
        jDateChooser1.setDate(nullDate);
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        emailId.setText("");
        contactNumber.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        firstName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lastName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rollNumber = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        femaleRadioButton = new javax.swing.JRadioButton();
        maleRadioButton = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        emailId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        contactNumber = new javax.swing.JTextField();
        clearButton = new javax.swing.JButton();
        submitButton = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        studentDetails = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Registration Form");
        setResizable(false);

        jLabel2.setText("First Name");

        firstName.setToolTipText("Enter First Name..");

        jLabel3.setText("Last Name");

        lastName.setToolTipText("Enter Last Name");
        lastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameActionPerformed(evt);
            }
        });

        jLabel4.setText("Roll No.");

        rollNumber.setToolTipText("Enter Roll No.");

        jLabel5.setText("DOB");

        jLabel6.setText("Gender");

        femaleRadioButton.setText("Female");
        femaleRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleRadioButtonActionPerformed(evt);
            }
        });

        maleRadioButton.setText("Male");
        maleRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleRadioButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("Email Id");

        emailId.setToolTipText("Enter Email id");
        emailId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailIdActionPerformed(evt);
            }
        });

        jLabel8.setText("Contact No.");

        contactNumber.setToolTipText("Enter Contact number");

        clearButton.setIcon(new javax.swing.ImageIcon("D:\\MBAIT-SEM1\\Java\\ProjectDocumentation\\image and icons\\1x\\clear.png")); // NOI18N
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        submitButton.setIcon(new javax.swing.ImageIcon("D:\\MBAIT-SEM1\\Java\\ProjectDocumentation\\image and icons\\1x\\submit.png")); // NOI18N
        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        studentDetails.setIcon(new javax.swing.ImageIcon("D:\\MBAIT-SEM1\\Java\\ProjectDocumentation\\image and icons\\1x\\viewlist.png")); // NOI18N
        studentDetails.setText("Student Details");
        studentDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(femaleRadioButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(firstName, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                    .addComponent(rollNumber))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(maleRadioButton)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(emailId, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(clearButton)
                                .addGap(18, 18, 18)
                                .addComponent(studentDetails)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(10, 10, 10)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lastName)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contactNumber, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(submitButton)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(lastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(rollNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(femaleRadioButton)
                    .addComponent(maleRadioButton))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(emailId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(contactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearButton)
                    .addComponent(submitButton)
                    .addComponent(studentDetails))
                .addGap(35, 35, 35))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameActionPerformed

    private void femaleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleRadioButtonActionPerformed
        // TODO add your handling code here:
        maleRadioButton.setSelected(false);
    }//GEN-LAST:event_femaleRadioButtonActionPerformed

    private void emailIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailIdActionPerformed

    private void maleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleRadioButtonActionPerformed
        // TODO add your handling code here:
        femaleRadioButton.setSelected(false);
    }//GEN-LAST:event_maleRadioButtonActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // TODO add your handling code here:
        getStudentData();
        if (inputFirstName.equals("") || inputLastName.equals("") || 
                inputDOB.equals("") || inputGender.equals("") || inputEmailId.equals("")) {
            JOptionPane.showMessageDialog(null, "All fields are compulsory");
        } else { //Student Username and password creation.
            halfFName = inputFirstName.substring(0, inputFirstName.length() / 2);
            halfLName = inputLastName.substring(0, inputLastName.length() / 2);
            inputUsername = halfFName + halfLName + inputrollNo;
            inputPassword = inputUsername;
            try {
                con = DriverManager.getConnection(DB_URL, USER, PASS);
                ps = con.prepareStatement("INSERT INTO student_table VALUES(?,?,?,?,?,?,?,?,?,?,NOW())");
                ps.setInt(1, inputStudentId);
                ps.setString(2, inputFirstName);
                ps.setString(3, inputLastName);
                ps.setInt(4, inputrollNo);
                ps.setString(5, inputGender);
                ps.setLong(6, inputcontactNo);
                ps.setString(7, inputEmailId);
                ps.setString(8, inputDOB);
                ps.setString(9, inputUsername);
                ps.setString(10, inputPassword);
                ps.executeUpdate();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e);
            }
            JOptionPane.showMessageDialog(null, "Records Inserted Successfully\n"
                    + "Your Username : " + inputUsername + "\n"
                    + "Your Password : " + inputPassword);
            System.out.println(inputStudentId + inputUsername + inputPassword);
            System.out.println(inputFirstName + inputLastName + inputrollNo + inputEmailId + inputDOB + inputGender + inputcontactNo);
            clearFields();
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void studentDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentDetailsActionPerformed
        // TODO add your handling code here:
        StudentListForm studentListForm = new StudentListForm();
        studentListForm.setVisible(true);
    }//GEN-LAST:event_studentDetailsActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
        clearFields();
    }//GEN-LAST:event_clearButtonActionPerformed

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
            java.util.logging.Logger.getLogger(RegistrationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrationForm().setVisible(true);
            }
        });
    }

    @Override
    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //To change body of generated methods, choose Tools | Templates.
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField contactNumber;
    private javax.swing.JTextField emailId;
    private javax.swing.JRadioButton femaleRadioButton;
    private javax.swing.JTextField firstName;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField lastName;
    private javax.swing.JRadioButton maleRadioButton;
    private javax.swing.JTextField rollNumber;
    private javax.swing.JButton studentDetails;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables

}

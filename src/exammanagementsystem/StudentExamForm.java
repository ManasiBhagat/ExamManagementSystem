/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exammanagementsystem;

import static exammanagementsystem.RegistrationForm.DB_URL;
import static exammanagementsystem.RegistrationForm.PASS;
import static exammanagementsystem.RegistrationForm.USER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Manasi
 */
public class StudentExamForm extends javax.swing.JFrame {

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
    char getExamNo;
    int examDuration, examNo, questionNo = 0, totalQuestions, marks;
    String examDate, question, answer1, answer2, answer3, answer4, correct_ans, ansSelected;

    /**
     * Creates new form StudentExamForm
     */
    public StudentExamForm() {
        initComponents();
    }

    public StudentExamForm(char getExamNo) {
        initComponents();
        this.getExamNo = getExamNo;
        System.out.println("Exam no : " + getExamNo);
        setData();
        setQuestion();

    }

    public void setData() {
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            ps = con.prepareStatement("SELECT exam_number,exam_duration,exam_date,total_marks "
                    + "FROM exam_table WHERE exam_number = '" + getExamNo + "' GROUP BY exam_number");
            rs = ps.executeQuery();
            while (rs.next()) {
                examNo = rs.getInt("exam_number");
                examDuration = rs.getInt("exam_duration");
                examDate = rs.getString("exam_date");
                totalQuestions = rs.getInt("total_marks");
            }
            labelExamNo.setText(String.valueOf(examNo));
            labelExamDuration.setText(String.valueOf(examDuration));
            labelExamDate.setText(examDate);
            txtTotalQuestion.setText(String.valueOf(totalQuestions));
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void setQuestion() {
        if (questionNo == 0) {
            questionNo = 1;
        } else {
            questionNo = questionNo + 1;
        }

        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            ps = con.prepareStatement("SELECT exam_number,question_no,question,"
                    + "answer1,answer2,answer3,answer4,correct_ans FROM exam_table"
                    + " WHERE exam_number = '" + getExamNo + "' AND question_no = '" + questionNo + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                examNo = rs.getInt("exam_number");
                questionNo = rs.getInt("question_no");
                question = rs.getString("question");
                answer1 = rs.getString("answer1");
                answer2 = rs.getString("answer2");
                answer3 = rs.getString("answer3");
                answer4 = rs.getString("answer4");
                correct_ans = rs.getString("correct_ans");
            }
            lbQuestionNo.setText(String.valueOf(questionNo));
            lbQuestion.setText(question);
            optionAnswer1.setText(answer1);
            optionAnswer2.setText(answer2);
            optionAnswer3.setText(answer3);
            optionAnswer4.setText(answer4);
            if (questionNo == totalQuestions) {
                btnNext.setEnabled(false);
            }
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void setPreviousQuestions() {
        if (questionNo == 0) {
            questionNo = 1;
        } else {
            questionNo = questionNo - 1;
        }

        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            ps = con.prepareStatement("SELECT exam_number,question_no,question,"
                    + "answer1,answer2,answer3,answer4,correct_ans FROM exam_table"
                    + " WHERE exam_number = '" + getExamNo + "' AND question_no = '" + questionNo + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                examNo = rs.getInt("exam_number");
                questionNo = rs.getInt("question_no");
                question = rs.getString("question");
                answer1 = rs.getString("answer1");
                answer2 = rs.getString("answer2");
                answer3 = rs.getString("answer3");
                answer4 = rs.getString("answer4");
                correct_ans = rs.getString("correct_ans");
            }
            lbQuestionNo.setText(String.valueOf(questionNo));
            lbQuestion.setText(question);
            optionAnswer1.setText(answer1);
            optionAnswer2.setText(answer2);
            optionAnswer3.setText(answer3);
            optionAnswer4.setText(answer4);
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void correctAns() {
        if (optionAnswer1.isSelected()) {
            ansSelected = optionAnswer1.getText();
            System.out.println("Ans : " + ansSelected);
        }
        if (optionAnswer2.isSelected()) {
            ansSelected = optionAnswer2.getText();
            System.out.println("Ans : " + ansSelected);
        }
        if (optionAnswer3.isSelected()) {
            ansSelected = optionAnswer3.getText();
            System.out.println("Ans : " + ansSelected);
        }
        if (optionAnswer4.isSelected()) {
            ansSelected = optionAnswer4.getText();
            System.out.println("Ans : " + ansSelected);
        }
        if (ansSelected.equalsIgnoreCase(correct_ans)) {
            System.out.println("Correct Ans : " + correct_ans);
            marks = marks + 1;
        } else {
            System.out.println("Wrong Ans");
            System.out.println(ansSelected + " " + correct_ans);
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
        lbRollNo = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbTotalQuestion = new javax.swing.JLabel();
        lbTotalMarks = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbQuestionNo = new javax.swing.JLabel();
        optionAnswer1 = new javax.swing.JRadioButton();
        optionAnswer2 = new javax.swing.JRadioButton();
        optionAnswer3 = new javax.swing.JRadioButton();
        optionAnswer4 = new javax.swing.JRadioButton();
        lbQuestion = new javax.swing.JLabel();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lbExamNo = new javax.swing.JLabel();
        lbExamDuration = new javax.swing.JLabel();
        lbDate = new javax.swing.JLabel();
        labelExamNo = new javax.swing.JLabel();
        labelExamDuration = new javax.swing.JLabel();
        labelExamDate = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbPassingMarks = new javax.swing.JLabel();
        txtRollNo = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtTotalQuestion = new javax.swing.JTextField();
        txtTolatMarks = new javax.swing.JTextField();
        txtPasingMarks = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbRollNo.setText("Roll No");

        lbName.setText("Name");

        lbTotalQuestion.setText("Total Question");

        lbTotalMarks.setText("Total Marks");

        lbQuestionNo.setText("1");

        optionAnswer1.setText("Option 1");
        optionAnswer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionAnswer1ActionPerformed(evt);
            }
        });

        optionAnswer2.setText("Option 2");
        optionAnswer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionAnswer2ActionPerformed(evt);
            }
        });

        optionAnswer3.setText("Option 3");
        optionAnswer3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionAnswer3ActionPerformed(evt);
            }
        });

        optionAnswer4.setText("Option 4");
        optionAnswer4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionAnswer4ActionPerformed(evt);
            }
        });

        lbQuestion.setText("Question");

        btnPrevious.setText("Previous");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(lbQuestionNo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(optionAnswer4)
                            .addComponent(optionAnswer1)
                            .addComponent(lbQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(optionAnswer2)
                            .addComponent(optionAnswer3)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnPrevious)
                        .addGap(76, 76, 76)
                        .addComponent(btnNext)
                        .addGap(95, 95, 95)
                        .addComponent(btnSubmit)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbQuestionNo)
                    .addComponent(lbQuestion))
                .addGap(33, 33, 33)
                .addComponent(optionAnswer1)
                .addGap(18, 18, 18)
                .addComponent(optionAnswer2)
                .addGap(18, 18, 18)
                .addComponent(optionAnswer3)
                .addGap(18, 18, 18)
                .addComponent(optionAnswer4)
                .addGap(91, 91, 91)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrevious)
                    .addComponent(btnNext)
                    .addComponent(btnSubmit))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        lbExamNo.setText("Exam No");

        lbExamDuration.setText("Exam Duration");

        lbDate.setText("Date");

        labelExamNo.setText("1");

        labelExamDuration.setText("1");

        labelExamDate.setText("1");

        jLabel1.setText("mins");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbExamNo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelExamNo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(lbExamDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelExamDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(lbDate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelExamDate, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbExamNo)
                    .addComponent(lbExamDuration)
                    .addComponent(lbDate)
                    .addComponent(labelExamNo)
                    .addComponent(labelExamDuration)
                    .addComponent(labelExamDate)
                    .addComponent(jLabel1))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        lbPassingMarks.setText("Passing Marks");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbTotalQuestion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotalQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTotalMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbPassingMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTolatMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPasingMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRollNo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtRollNo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRollNo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRollNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotalQuestion)
                    .addComponent(txtTotalQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotalMarks)
                    .addComponent(txtTolatMarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPassingMarks)
                    .addComponent(txtPasingMarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        correctAns();
        setQuestion();
        System.out.println("Next Marks : " + marks);
        optionAnswer1.setSelected(false);
        optionAnswer2.setSelected(false);
        optionAnswer3.setSelected(false);
        optionAnswer4.setSelected(false);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        // TODO add your handling code here:
        setPreviousQuestions();
        marks = marks - 1;
        System.out.println("Marks after previous : "+marks);
        btnNext.setEnabled(true);
        optionAnswer1.setSelected(false);
        optionAnswer2.setSelected(false);
        optionAnswer3.setSelected(false);
        optionAnswer4.setSelected(false);
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void optionAnswer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionAnswer1ActionPerformed
        // TODO add your handling code here:
        optionAnswer2.setSelected(false);
        optionAnswer3.setSelected(false);
        optionAnswer4.setSelected(false);
    }//GEN-LAST:event_optionAnswer1ActionPerformed

    private void optionAnswer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionAnswer2ActionPerformed
        // TODO add your handling code here:
        optionAnswer1.setSelected(false);
        optionAnswer3.setSelected(false);
        optionAnswer4.setSelected(false);
    }//GEN-LAST:event_optionAnswer2ActionPerformed

    private void optionAnswer3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionAnswer3ActionPerformed
        // TODO add your handling code here:
        optionAnswer1.setSelected(false);
        optionAnswer2.setSelected(false);
        optionAnswer4.setSelected(false);
    }//GEN-LAST:event_optionAnswer3ActionPerformed

    private void optionAnswer4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionAnswer4ActionPerformed
        // TODO add your handling code here:
        optionAnswer1.setSelected(false);
        optionAnswer2.setSelected(false);
        optionAnswer3.setSelected(false);
    }//GEN-LAST:event_optionAnswer4ActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        correctAns();
        System.out.println("Total Marks : " + marks);
    }//GEN-LAST:event_btnSubmitActionPerformed

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
            java.util.logging.Logger.getLogger(StudentExamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentExamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentExamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentExamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentExamForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelExamDate;
    private javax.swing.JLabel labelExamDuration;
    private javax.swing.JLabel labelExamNo;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbExamDuration;
    private javax.swing.JLabel lbExamNo;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPassingMarks;
    private javax.swing.JLabel lbQuestion;
    private javax.swing.JLabel lbQuestionNo;
    private javax.swing.JLabel lbRollNo;
    private javax.swing.JLabel lbTotalMarks;
    private javax.swing.JLabel lbTotalQuestion;
    private javax.swing.JRadioButton optionAnswer1;
    private javax.swing.JRadioButton optionAnswer2;
    private javax.swing.JRadioButton optionAnswer3;
    private javax.swing.JRadioButton optionAnswer4;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPasingMarks;
    private javax.swing.JTextField txtRollNo;
    private javax.swing.JTextField txtTolatMarks;
    private javax.swing.JTextField txtTotalQuestion;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exammanagementsystem;

import static exammanagementsystem.PasswordResetForm.DB_URL;
import static exammanagementsystem.PasswordResetForm.PASS;
import static exammanagementsystem.PasswordResetForm.USER;
import static exammanagementsystem.RegistrationForm.DB_URL;
import static exammanagementsystem.RegistrationForm.PASS;
import static exammanagementsystem.RegistrationForm.USER;
import static exammanagementsystem.StudentListForm.DB_URL;
import static exammanagementsystem.StudentListForm.PASS;
import static exammanagementsystem.StudentListForm.USER;
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
public class AddExamForm extends javax.swing.JFrame {

    final int columnQuestionNo = 0, columnQuestion = 1, columnAnswer1 = 2,
            columnAnswer2 = 3, columnAnswer3 = 4, columnAnswer4 = 5, columnCorrectAns = 6;
    int selectedRow;
    String tableQuestionNo, tableQuestion, tableAnswer1, tableAnswer2, tableAnswer3, tableAnswer4, tableCorrectAns;
    int examNo, examDuration, totalQuestions, totalMarks, passingMarks, examId,
            questionNo;
    String examDate, question, answer1, answer2, answer3, answer4, correctAns,
            tbQuestion, tbAnswer1, tbAnswer2, tbAnswer3, tbAnswer4, tbCorrectAns, tbQuestionNo;

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

    /**
     * Creates new form AddExamForm
     */
    public AddExamForm() {
        initComponents();
        System.out.println(examNo + examDuration + totalQuestions + examDate);
    }

    public AddExamForm(int examNo, int examDuration, int totalQuestions, String examDate) {
        initComponents();
        this.examNo = examNo;
        this.examDuration = examDuration;
        this.totalQuestions = totalQuestions;
        this.examDate = examDate;
        totalMarks = totalQuestions;
        if (totalMarks < 5) {
            passingMarks = 1;
        } else {
            passingMarks = ((40 * totalMarks) / 100);
        }
        txtExamNo.setText(String.valueOf(examNo));
        txtTotalQuestions.setText(String.valueOf(totalQuestions));
        txtTotalMarks.setText(String.valueOf(totalMarks));
        txtPassingMarks.setText(String.valueOf(passingMarks));
        txtExamDuration.setText(String.valueOf(examDuration));
        txtExamDate.setText(examDate);
        showQuestionNo();
    }

    public void addQuestion() {
        question = txtQuestionField.getText();
        answer1 = txtAnswer1.getText();
        answer2 = txtAnswer2.getText();
        answer3 = txtAnswer3.getText();
        answer4 = txtAnswer4.getText();
        if (radioAnswer1.isSelected()) {
            correctAns = answer1;
        }
        if (radioAnswer2.isSelected()) {
            correctAns = answer2;
        }
        if (radioAnswer3.isSelected()) {
            correctAns = answer3;
        }
        if (radioAnswer4.isSelected()) {
            correctAns = answer4;
        }
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            ps = con.prepareStatement("SELECT MAX(exam_id)+1 FROM exam_table");
            rs = ps.executeQuery();
            while (rs.next()) {
                examId = rs.getInt(1);
            }
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        if (examId == 0) {
            examId = 1;
        }

    }

    public void showQuestionNo() {
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            ps = con.prepareStatement("SELECT MAX(question_no)+1 FROM exam_table where exam_number = ?");
            ps.setInt(1, examNo);
            rs = ps.executeQuery();
            while (rs.next()) {
                questionNo = rs.getInt(1);
            }
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        if (questionNo == 0) {
            questionNo = 1;
        }
        lableQuestionNo.setText(String.valueOf(questionNo));
        if (questionNo > totalQuestions) {
            addButton.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Question Limit Crossed");
        } else {
            addButton.setEnabled(true);
        }
    }

    public void showTable() {
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String selectQuery = "SELECT question_no,question,answer1,answer2"
                    + ",answer3,answer4,correct_ans from exam_table WHERE exam_number = '" + examNo + "'";
            rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                tbQuestionNo = String.valueOf(rs.getInt("question_no"));
                tbQuestion = rs.getString("question");
                tbAnswer1 = rs.getString("answer1");
                tbAnswer2 = rs.getString("answer2");
                tbAnswer3 = rs.getString("answer3");
                tbAnswer4 = rs.getString("answer4");
                tbCorrectAns = rs.getString("correct_ans");

                String table[] = {tbQuestionNo, tbQuestion, tbAnswer1, tbAnswer2, tbAnswer3,
                    tbAnswer4, tbCorrectAns};
                DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
                tableModel.addRow(table);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void clearFields() {
        txtQuestionField.setText("");
        txtAnswer1.setText("");
        txtAnswer2.setText("");
        txtAnswer3.setText("");
        txtAnswer4.setText("");
        radioAnswer1.setSelected(false);
        radioAnswer2.setSelected(false);
        radioAnswer3.setSelected(false);
        radioAnswer4.setSelected(false);
    }

    public void getSelectedQuestion() {
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        selectedRow = jTable1.getSelectedRow();
        tableQuestionNo = tableModel.getValueAt(selectedRow, columnQuestionNo).toString();
        tableQuestion = tableModel.getValueAt(selectedRow, columnQuestion).toString();
        tableAnswer1 = tableModel.getValueAt(selectedRow, columnAnswer1).toString();
        tableAnswer2 = tableModel.getValueAt(selectedRow, columnAnswer2).toString();
        tableAnswer3 = tableModel.getValueAt(selectedRow, columnAnswer3).toString();
        tableAnswer4 = tableModel.getValueAt(selectedRow, columnAnswer4).toString();
        tableCorrectAns = tableModel.getValueAt(selectedRow, columnCorrectAns).toString();
        System.out.println("Row : " + selectedRow);
        System.out.println(tableQuestionNo + "" + tableQuestion + "" + tableAnswer1 + "" + tableAnswer2 + ""
                + tableAnswer3 + "" + tableAnswer4 + "" + tableCorrectAns);
    }

    public void setSelectedQuestion() {
        lableQuestionNo.setText(tableQuestionNo);
        txtQuestionField.setText(tableQuestion);
        txtAnswer1.setText(tableAnswer1);
        txtAnswer2.setText(tableAnswer2);
        txtAnswer3.setText(tableAnswer3);
        txtAnswer4.setText(tableAnswer4);
        if (tableCorrectAns.equals(tableAnswer1)) {
            radioAnswer1.setSelected(true);
        }
        if (tableCorrectAns.equals(tableAnswer2)) {
            radioAnswer2.setSelected(true);
        }
        if (tableCorrectAns.equals(tableAnswer3)) {
            radioAnswer3.setSelected(true);
        }
        if (tableCorrectAns.equals(tableAnswer4)) {
            radioAnswer4.setSelected(true);
        }
    }

    public void updateQuestion() {
        question = txtQuestionField.getText();
        answer1 = txtAnswer1.getText();
        answer2 = txtAnswer2.getText();
        answer3 = txtAnswer3.getText();
        answer4 = txtAnswer4.getText();
        questionNo = Integer.valueOf(lableQuestionNo.getText());
        if (radioAnswer1.isSelected()) {
            correctAns = answer1;
        }
        if (radioAnswer2.isSelected()) {
            correctAns = answer2;
        }
        if (radioAnswer3.isSelected()) {
            correctAns = answer3;
        }
        if (radioAnswer4.isSelected()) {
            correctAns = answer4;
        }
        System.out.println(questionNo + " " + question + " " + answer1 + " " + answer2 + " "
                + answer3 + " " + answer4 + " " + correctAns);
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String updateQuery = "UPDATE exam_table SET question = '" + question + "', "
                    + "answer1 = '" + answer1 + "', answer2 = '" + answer2 + "', "
                    + "answer3 = '" + answer3 + "', answer4 = '" + answer4 + "', "
                    + "correct_ans = '" + correctAns + "' "
                    + "WHERE exam_number = '" + examNo + "' AND question_no = '" + questionNo + "'";
            stmt.executeUpdate(updateQuery);
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteQuestion() {
        getSelectedQuestion();
        System.out.println("Delete : " + examNo + tableQuestionNo);
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String deleteQuery = "DELETE FROM exam_table WHERE exam_number = '" + examNo + "' "
                    + "AND question_no = '" + tableQuestionNo + "'";
            stmt.executeUpdate(deleteQuery);
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setRowCount(0);
        JOptionPane.showMessageDialog(null, "Question Deleted!");
        showQuestionNo();
        showTable();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txtExamNo = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTotalQuestions = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtTotalMarks = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtPassingMarks = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtExamDuration = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtExamDate = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        lableQuestionNo = new javax.swing.JLabel();
        txtQuestionField = new javax.swing.JTextField();
        txtAnswer1 = new javax.swing.JTextField();
        txtAnswer2 = new javax.swing.JTextField();
        txtAnswer3 = new javax.swing.JTextField();
        txtAnswer4 = new javax.swing.JTextField();
        radioAnswer1 = new javax.swing.JRadioButton();
        radioAnswer2 = new javax.swing.JRadioButton();
        radioAnswer3 = new javax.swing.JRadioButton();
        radioAnswer4 = new javax.swing.JRadioButton();
        addButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        getSelectedQuestionBtn = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Set Exam");
        setResizable(false);

        jLabel1.setText("Exam No.");

        txtExamNo.setEditable(false);
        jScrollPane1.setViewportView(txtExamNo);

        jLabel2.setText("Total Questions");

        txtTotalQuestions.setEditable(false);
        jScrollPane2.setViewportView(txtTotalQuestions);

        jLabel3.setText("Total Marks");

        txtTotalMarks.setEditable(false);
        jScrollPane3.setViewportView(txtTotalMarks);

        jLabel4.setText("Passing Marks");

        txtPassingMarks.setEditable(false);
        jScrollPane4.setViewportView(txtPassingMarks);

        jLabel5.setText("Exam Duration");

        txtExamDuration.setEditable(false);
        jScrollPane5.setViewportView(txtExamDuration);

        jLabel6.setText("Exam Date");

        txtExamDate.setEditable(false);
        jScrollPane6.setViewportView(txtExamDate);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(105, 105, 105)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(116, 116, 116)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(87, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(87, 87, 87))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        lableQuestionNo.setText("1");

        txtAnswer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnswer2ActionPerformed(evt);
            }
        });

        txtAnswer3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnswer3ActionPerformed(evt);
            }
        });

        radioAnswer1.setText("answerOption1");
        radioAnswer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioAnswer1ActionPerformed(evt);
            }
        });

        radioAnswer2.setText("answerOption2");
        radioAnswer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioAnswer2ActionPerformed(evt);
            }
        });

        radioAnswer3.setText("answerOption3");
        radioAnswer3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioAnswer3ActionPerformed(evt);
            }
        });

        radioAnswer4.setText("answerOption4");
        radioAnswer4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioAnswer4ActionPerformed(evt);
            }
        });

        addButton.setIcon(new javax.swing.ImageIcon("D:\\MBAIT-SEM1\\Java\\ProjectDocumentation\\image and icons\\1x\\add.png")); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        clearButton.setIcon(new javax.swing.ImageIcon("D:\\MBAIT-SEM1\\Java\\ProjectDocumentation\\image and icons\\1x\\clear.png")); // NOI18N
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        updateButton.setIcon(new javax.swing.ImageIcon("D:\\MBAIT-SEM1\\Java\\ProjectDocumentation\\image and icons\\1x\\update.png")); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(lableQuestionNo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQuestionField, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(radioAnswer3)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtAnswer3, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(radioAnswer2)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtAnswer2))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(radioAnswer1)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtAnswer1))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(radioAnswer4)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtAnswer4)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(addButton)
                        .addGap(18, 18, 18)
                        .addComponent(clearButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updateButton)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableQuestionNo)
                    .addComponent(txtQuestionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnswer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioAnswer1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnswer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioAnswer2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnswer3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioAnswer3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnswer4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioAnswer4))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton)
                    .addComponent(clearButton)
                    .addComponent(addButton))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Question No.", "Questions", "Answer 1", "Answer 2", "Answer 3", "Answer 4", "Correct Answer"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable1);

        getSelectedQuestionBtn.setIcon(new javax.swing.ImageIcon("D:\\MBAIT-SEM1\\Java\\ProjectDocumentation\\image and icons\\1x\\select.png")); // NOI18N
        getSelectedQuestionBtn.setText("Select Question");
        getSelectedQuestionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getSelectedQuestionBtnActionPerformed(evt);
            }
        });

        deleteButton.setIcon(new javax.swing.ImageIcon("D:\\MBAIT-SEM1\\Java\\ProjectDocumentation\\image and icons\\1x\\delete.png")); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(deleteButton)
                        .addGap(18, 18, 18)
                        .addComponent(getSelectedQuestionBtn)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(getSelectedQuestionBtn)
                    .addComponent(deleteButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtAnswer3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnswer3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnswer3ActionPerformed

    private void txtAnswer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnswer2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnswer2ActionPerformed

    private void radioAnswer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioAnswer1ActionPerformed
        // TODO add your handling code here:
        radioAnswer2.setSelected(false);
        radioAnswer3.setSelected(false);
        radioAnswer4.setSelected(false);
    }//GEN-LAST:event_radioAnswer1ActionPerformed

    private void radioAnswer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioAnswer2ActionPerformed
        // TODO add your handling code here:
        radioAnswer1.setSelected(false);
        radioAnswer3.setSelected(false);
        radioAnswer4.setSelected(false);
    }//GEN-LAST:event_radioAnswer2ActionPerformed

    private void radioAnswer3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioAnswer3ActionPerformed
        // TODO add your handling code here:
        radioAnswer1.setSelected(false);
        radioAnswer2.setSelected(false);
        radioAnswer4.setSelected(false);
    }//GEN-LAST:event_radioAnswer3ActionPerformed

    private void radioAnswer4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioAnswer4ActionPerformed
        // TODO add your handling code here:
        radioAnswer1.setSelected(false);
        radioAnswer2.setSelected(false);
        radioAnswer3.setSelected(false);
    }//GEN-LAST:event_radioAnswer4ActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        addQuestion();
        System.out.println(examId);
        System.out.println(examNo);
        System.out.println(examDate);
        System.out.println(examDuration);
        System.out.println(questionNo);
        System.out.println(question);
        System.out.println(answer1);
        System.out.println(answer2);
        System.out.println(answer3);
        System.out.println(answer4);
        System.out.println(correctAns);
        System.out.println(totalMarks);
        System.out.println(passingMarks);
        if (question.equals("") || answer1.equals("") || answer2.equals("") || answer3.equals("")
                || answer4.equals("") || correctAns.equals("")) {
            JOptionPane.showMessageDialog(null, "All fields are compulsory!");
        } else {
            try {
                con = DriverManager.getConnection(DB_URL, USER, PASS);
                ps = con.prepareStatement("INSERT INTO exam_table VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setInt(1, examId);
                ps.setInt(2, examNo);
                ps.setString(3, examDate);
                ps.setInt(4, examDuration);
                ps.setInt(5, questionNo);
                ps.setString(6, question);
                ps.setString(7, answer1);
                ps.setString(8, answer2);
                ps.setString(9, answer3);
                ps.setString(10, answer4);
                ps.setString(11, correctAns);
                ps.setInt(12, 1);
                ps.setInt(13, totalMarks);
                ps.setInt(14, passingMarks);
                ps.executeUpdate();
                ps.close();
                con.close();
                JOptionPane.showMessageDialog(null, "Question Inserted");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setRowCount(0);
        showTable();
        clearFields();
        showQuestionNo();
    }//GEN-LAST:event_addButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
        clearFields();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void getSelectedQuestionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getSelectedQuestionBtnActionPerformed
        // TODO add your handling code here:
        getSelectedQuestion();
        setSelectedQuestion();
    }//GEN-LAST:event_getSelectedQuestionBtnActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        updateQuestion();
        txtQuestionField.setText("");
        txtAnswer1.setText("");
        txtAnswer2.setText("");
        txtAnswer3.setText("");
        txtAnswer4.setText("");
        radioAnswer1.setSelected(false);
        radioAnswer2.setSelected(false);
        radioAnswer3.setSelected(false);
        radioAnswer4.setSelected(false);
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setRowCount(0);
        showQuestionNo();
        showTable();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        deleteQuestion();
    }//GEN-LAST:event_deleteButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AddExamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddExamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddExamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddExamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddExamForm().setVisible(true);
            }
        });
    }

    @Override
    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //To change body of generated methods, choose Tools | Templates.
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton getSelectedQuestionBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lableQuestionNo;
    private javax.swing.JRadioButton radioAnswer1;
    private javax.swing.JRadioButton radioAnswer2;
    private javax.swing.JRadioButton radioAnswer3;
    private javax.swing.JRadioButton radioAnswer4;
    private javax.swing.JTextField txtAnswer1;
    private javax.swing.JTextField txtAnswer2;
    private javax.swing.JTextField txtAnswer3;
    private javax.swing.JTextField txtAnswer4;
    private javax.swing.JTextPane txtExamDate;
    private javax.swing.JTextPane txtExamDuration;
    private javax.swing.JTextPane txtExamNo;
    private javax.swing.JTextPane txtPassingMarks;
    private javax.swing.JTextField txtQuestionField;
    private javax.swing.JTextPane txtTotalMarks;
    private javax.swing.JTextPane txtTotalQuestions;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}

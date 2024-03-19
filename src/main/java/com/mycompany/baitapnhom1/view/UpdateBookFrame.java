/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.baitapnhom1.view;

import com.mycompany.baitapnhom1.entity.BookCategory;
import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.service.implement.BookService;
import com.mycompany.baitapnhom1.util.AppUtil;
import com.mycompany.baitapnhom1.util.JOptionPaneUtil;

import javax.swing.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * @author kienl
 */
public class UpdateBookFrame extends javax.swing.JFrame {

    /*
     * Creates new form AddBookFrame
     */
    private final BookService bookService;
    private final BookEntity book;

    public UpdateBookFrame(BookService bookService, BookEntity book) {
        this.bookService = bookService;
        this.book = book;
        initComponents();
        initComboBox();
        initBookData();
        initNumberInputRequire();
    }

    private void initBookData() {
        Date date = book.getPublishDate();
        txtBookId.setText(book.getBookId());
        txtBookName.setText(book.getBookName());
        txtAuthor.setText(book.getAuthor());
        txtCategory.setSelectedIndex(book.getCategory().getIndex());
        txtPublisher.setText(book.getPublisher());
        txtPublishDate.setText(AppUtil.getPublishDateString(date));
        txtQuantity.setText(String.valueOf(book.getTotalQuantity()));
        txtRestQuantity.setText(String.valueOf(book.getRestQuantity()));
    }

    private void initNumberInputRequire() {
        var consumer = AppUtil.getCharacterKeyAdapter();
        txtQuantity.addKeyListener(consumer);
        txtPublishDate.addKeyListener(consumer);
        //txtPublishDate.setDocument(AppUtil.getNumberRequireCharacter(4));
    }

    private void initComboBox() {
        BookCategory[] enumCategory = BookCategory.values();
        DefaultComboBoxModel<BookCategory> model = (DefaultComboBoxModel) txtCategory.getModel();
        Arrays.stream(enumCategory).forEach(model::addElement);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtAuthor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtPublisher = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPublishDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        txtBookId = new javax.swing.JTextField();
        txtCategory = new javax.swing.JComboBox<>();
        txtBookName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtRestQuantity = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAuthorActionPerformed(evt);
            }
        });

        jLabel6.setText("Nhà xuất bản");

        jLabel7.setText("Năm xuất bản");

        jLabel8.setText("Tổng số lượng");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Cập nhật sách");

        jLabel2.setText("Mã sách");

        jLabel3.setText("Tên sách");

        jLabel4.setText("Tên tác giả");

        btnUpdate.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/image/Add.png")))); // NOI18N
        btnUpdate.setText("Cập nhật ");
        btnUpdate.setFocusable(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel5.setText("Thể loại");

        btnCancel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/image/exit-full-screen.png")))); // NOI18N
        btnCancel.setText("Huỷ");
        btnCancel.setFocusable(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        txtCategory.setFocusable(false);

        jLabel10.setText("Số lượng kho");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(41, 41, 41)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(37, 37, 37)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(txtAuthor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                        .addComponent(txtBookName, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtBookId, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(123, 123, 123)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jLabel6)
                                                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(jLabel10))
                                                .addGap(52, 52, 52)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(txtPublisher)
                                                        .addComponent(txtPublishDate)
                                                        .addComponent(txtQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                        .addComponent(txtRestQuantity)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(234, 234, 234)
                                                .addComponent(btnUpdate)
                                                .addGap(179, 179, 179)
                                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(369, 369, 369)
                                                .addComponent(jLabel1)))
                                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtBookId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6)
                                        .addComponent(txtPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txtBookName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7)
                                        .addComponent(txtPublishDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8)
                                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10)
                                        .addComponent(txtRestQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAuthorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAuthorActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        try {
            String bookId = txtBookId.getText();
            String bookName = txtBookName.getText();
            String author = txtAuthor.getText();
            String rawDate = txtPublishDate.getText();
            String rawQuantity = txtQuantity.getText();
            String publisher = txtPublisher.getText();
            String rawRestQuantity = txtRestQuantity.getText();
            //checkValidInput(inputs)
            AppUtil.checkValidInput(bookId,bookName,author,rawDate,rawQuantity,publisher,rawRestQuantity);

            BookCategory bookCategory = (BookCategory) txtCategory.getSelectedItem();
            int publishDate = Integer.parseInt(rawDate);
            LocalDate localDate = LocalDate.of(publishDate, 1, 1);
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            int quantity = Integer.parseInt(rawQuantity);
            int availableQuantity = Integer.parseInt(rawRestQuantity);
            var prevBookId = book.getBookId();
            book.setBookId(bookId);
            book.setBookName(bookName);
            book.setCategory(bookCategory);
            book.setAuthor(author);
            book.setPublisher(publisher);
            book.setPublishDate(date);
            book.setTotalQuantity(quantity);
            book.setRestQuantity(availableQuantity);
            var result = bookService.updateBook(book, prevBookId);
            JOptionPaneUtil.showMessageDialog(result.getMessage(), 800, null);
            dispose();
        } catch (RuntimeException e) {
            JOptionPaneUtil.showErrorDialog(e.getMessage(), this);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void clearText() {
        txtBookId.setText("");
        txtBookName.setText("");
        txtAuthor.setText("");
        txtCategory.setSelectedIndex(0);
        txtPublisher.setText("");
        txtPublishDate.setText("");
        txtQuantity.setText("");
        txtBookId.requestFocus();
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * @param args the command line arguments
     * //
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AddBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AddBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AddBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AddBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AddBookFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtBookId;
    private javax.swing.JTextField txtBookName;
    private javax.swing.JComboBox<String> txtCategory;
    private javax.swing.JTextField txtPublishDate;
    private javax.swing.JTextField txtPublisher;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtRestQuantity;
    // End of variables declaration//GEN-END:variables
}

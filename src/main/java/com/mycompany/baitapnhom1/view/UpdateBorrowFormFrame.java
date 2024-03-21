/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.baitapnhom1.view;

import com.mycompany.baitapnhom1.entity.BorrowFormEntity;
import com.mycompany.baitapnhom1.model.ReturnState;
import com.mycompany.baitapnhom1.model.TimeBorrow;
import com.mycompany.baitapnhom1.service.implement.BorrowBookService;
import com.mycompany.baitapnhom1.util.AppUtil;
import com.mycompany.baitapnhom1.util.JOptionPaneUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author kienl
 */
public class UpdateBorrowFormFrame extends JFrame {

    private final BorrowFormEntity item;
    private final BorrowBookService borrowBookService;

    public UpdateBorrowFormFrame(BorrowFormEntity item, BorrowBookService borrowBookService) {
        this.item = item;
        this.borrowBookService = borrowBookService;
        initComponents();
        initData();
    }

    /**
     * Creates new form UpdateBorrowFormFrame
     */

    private void initData() {
        txtBookId.setText(item.getBook().getBookId());
        txtUserId.setText(item.getUser().getPersonalId());
        DefaultComboBoxModel<String> returnModel = (DefaultComboBoxModel) snpReturnState.getModel();
        DefaultComboBoxModel<String> timeModel = (DefaultComboBoxModel) snpReturnDate.getModel();
        var defaultTime = Arrays.stream(TimeBorrow.values())
                .toList().stream()
                .map(TimeBorrow::getTime)
                .toList();
        timeModel.addAll(defaultTime);
        returnModel.addAll(Arrays.stream(ReturnState.values()).toList().stream().map(ReturnState::getState).toList());
        snpReturnDate.setSelectedIndex(getReturnDate() - 1);
        snpReturnState.setSelectedIndex(getReturnStateIndex());
        txtQuantity.setText(String.valueOf(item.getQuantity()));
        txtQuantity.addKeyListener(AppUtil.getCharacterKeyAdapter());
    }

    private int getReturnStateIndex() {
        return switch (item.getState()) {
            case RETURNED -> 0;
            case NOT_YET -> 1;
            case EXPIRED -> 2;
        };
    }

    private ReturnState getReturnState(int index) {
        return switch (index){
            case 0 -> ReturnState.RETURNED;
            case 2 -> ReturnState.NOT_YET;
            default -> ReturnState.EXPIRED;
        };
    }

    private int getReturnDate() {
        var borrowDate = item.getBorrowDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        var returnDate = item.getExpiredDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return returnDate.getMonthValue() - borrowDate.getMonthValue();
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUserId = new javax.swing.JTextField();
        txtBookId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        snpReturnDate = new javax.swing.JComboBox<>();
        snpReturnState = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Phiếu mượn");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Phiếu mượn");

        jLabel2.setText("Mã sinh viên");

        jLabel3.setText("Mã sách");

        jLabel4.setText("Ngày hẹn trả");

        txtUserId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserIdActionPerformed(evt);
            }
        });

        jLabel5.setText("Trạng thái");

        btnUpdate.setText("Cập nhật");

        btnCancel.setText("Huỷ");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        snpReturnDate.setBackground(new java.awt.Color(255, 255, 255));
        snpReturnDate.setForeground(new java.awt.Color(0, 0, 0));
        snpReturnDate.setToolTipText("");

        snpReturnState.setBackground(new java.awt.Color(255, 255, 255));
        snpReturnState.setForeground(new java.awt.Color(0, 0, 0));

        jLabel6.setText("Số lượng");

        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addComponent(btnCancel)
                .addGap(96, 96, 96))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBookId)
                            .addComponent(txtUserId)
                            .addComponent(snpReturnDate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(snpReturnState, 0, 160, Short.MAX_VALUE)
                            .addComponent(txtQuantity))))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBookId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(snpReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdate)
                            .addComponent(btnCancel)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(snpReturnState, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserIdActionPerformed(ActionEvent evt) {//GEN-FIRST:event_txtUserIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserIdActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            String userId = txtUserId.getText();
            String bookId = txtBookId.getText();
            int time = Integer.parseInt(((String) Objects.requireNonNull(snpReturnDate.getSelectedItem())).substring(0, 1));
            var state = getReturnState(snpReturnState.getSelectedIndex());
            int quantity = Integer.parseInt(txtQuantity.getText());
            AppUtil.checkValidQuantity(quantity);
            AppUtil.checkValidInput(userId,bookId);
            borrowBookService.updateBorrowForm(item,userId,bookId,time,state,quantity);
        }catch (Exception e){
            JOptionPaneUtil.showErrorDialog(e.getMessage(),this);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * @param args the command line arguments
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
//            java.util.logging.Logger.getLogger(UpdateBorrowFormFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(UpdateBorrowFormFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(UpdateBorrowFormFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(UpdateBorrowFormFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new UpdateBorrowFormFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JComboBox<String> snpReturnDate;
    private javax.swing.JComboBox<String> snpReturnState;
    private javax.swing.JTextField txtBookId;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtUserId;
    // End of variables declaration//GEN-END:variables
}

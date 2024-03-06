/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.baitapnhom1.view;

import javax.swing.*;

import com.mycompany.baitapnhom1.Baitapnhom1;
import com.mycompany.baitapnhom1.entity.Role;
import com.mycompany.baitapnhom1.entity.UserEntity;
import com.mycompany.baitapnhom1.model.ResultModel;
import com.mycompany.baitapnhom1.service.implement.BookService;
import com.mycompany.baitapnhom1.service.implement.BorrowBookService;
import com.mycompany.baitapnhom1.service.implement.UserService;
import com.mycompany.baitapnhom1.util.AppUtil;
import com.mycompany.baitapnhom1.util.JOptionPaneUtil;

import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author DELL
 */

public class DangNhap extends javax.swing.JFrame {

    /**
     * Creates new form DangNhap
     */

    private final UserService userService;
    private final BookService bookService;
    private final BorrowBookService borrowBookService;

    public DangNhap(UserService userService,BookService bookService,BorrowBookService borrowBookService) {
        initComponents();
        setLocationRelativeTo(null);
        this.userService=userService;
        this.bookService=bookService;
        this.borrowBookService=borrowBookService;
    }

    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lbMK = new javax.swing.JLabel();
        lbTK = new javax.swing.JLabel();
        txtTK = new javax.swing.JTextField();
        txtMK = new javax.swing.JPasswordField();
        btnExit = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý thư viện");

        lbMK.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(Baitapnhom1.class.getResource("/image/icons8-password-24 (2).png")))); // NOI18N
        lbMK.setText("Mật khẩu");

        lbTK.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(Baitapnhom1.class.getResource("/image/icons8-user-default-24.png")))); // NOI18N
        lbTK.setText("Tên tài  khoản");

        txtTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTKActionPerformed(evt);
            }
        });

        txtMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMKActionPerformed(evt);
            }
        });

        btnExit.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(Baitapnhom1.class.getResource("/image/icons8-cancel-24 (1).png")))); // NOI18N
        btnExit.setText("Thoát");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnLogin.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(Baitapnhom1.class.getResource("/image/icons8-log-in-24.png")))); // NOI18N
        btnLogin.setText("Đăng nhập");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("ĐĂNG NHẬP  HỆ THỐNG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(61, 61, 61)
                                                .addComponent(btnLogin)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbTK, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lbMK, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(15, 15, 15)))
                                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbTK)
                                        .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbMK)
                                        .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52))
        );

        pack();
    }// </editor-fold>                        

    private void txtTKActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void txtMKActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        dispose();
    }


    private void resetForm(){
        txtMK.setText("");
        txtTK.setText("");
    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String id = txtTK.getText().trim();
        String pass = new String(txtMK.getPassword());
        // TODO add your handling code here:
        if(!id.isEmpty()&& !pass.isEmpty()){
            var data = checkUser(id,pass);
            if(data.getData()!=null){
                var user =(UserEntity) data.getData();
                AppUtil.setCurrentUser(user);
                JFrame frame;
                if(user.getRole()== Role.USER){
                    frame = new UserMenuFrame(bookService,borrowBookService);
                }else {
                    frame = new MenuFrame(userService,bookService,borrowBookService);
                }
                AppUtil.setUpWindowListener(frame,this,this::resetForm);
            }
            else{
                resetForm();
            }
            JOptionPaneUtil.showMessageDialog(data.getMessage(),800,null);
        }else {
            JOptionPane.showMessageDialog(this, "Vui long nhap userName va password");
        }
    }

    private ResultModel checkUser(String id, String pass) {
        try{
            UserEntity user = userService.findUserByUserIdAndPassword(id,pass);
            if(user!=null){
                return new ResultModel(user,"Đăng nhập thành công");
            }else return new ResultModel(null,"Tên khoản hoặc mật khẩu không đúng. Vui lòng nhập lại");
        }catch (SQLException e) {
            return new ResultModel(null,e.getMessage());
        }
    }



    // Variables declaration - do not modify                     
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbMK;
    private javax.swing.JLabel lbTK;
    private javax.swing.JPasswordField txtMK;
    private javax.swing.JTextField txtTK;
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.baitapnhom1.view;

import com.mycompany.baitapnhom1.entity.Role;
import com.mycompany.baitapnhom1.entity.UserEntity;
import com.mycompany.baitapnhom1.service.implement.BorrowBookService;
import com.mycompany.baitapnhom1.service.implement.UserService;
import com.mycompany.baitapnhom1.util.AppUtil;
import com.mycompany.baitapnhom1.util.JOptionPaneUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * @author kienl
 */
public class UserManagementFrame extends javax.swing.JFrame {
    private final UserService userService;
    private final BorrowBookService borrowBookService;
    private DefaultTableModel model = null;
    private UserEntity currentUser;
    private Role userRole;
    private List<UserEntity> items;

    /**
     * Creates new form QuanLyDocGia
     */
    public UserManagementFrame(UserService userService, BorrowBookService borrowBookService) {
        this.userService = userService;
        this.borrowBookService = borrowBookService;
        initUser();
        initTableModel();
        initBtn();
        fetchExistUsers();
        setUpRow();
        setLocationRelativeTo(null);
    }

    private void initBtn() {
        switch (this.userRole) {
            case ADMIN -> {
                this.btnAction.setText("Thêm");
                this.btnAction.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/image/icons8-add-24.png"))));
            }
            case MANAGER -> {
                this.btnAction.setText("Làm mới");
                this.btnAction.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/image/icons8-refresh-24.png"))));
            }
        }
    }

    private void initUser() {
        this.currentUser = AppUtil.getCurrentUser();
        this.userRole = currentUser.getRole();
        String[] elements;
        String title;
        if (this.userRole == Role.MANAGER) {
            title = "Quản lý độc giả";
            elements = new String[]{"Stt", "Mã sinh viên", "Tên sinh viên"};
        } else {
            title = "Quản lý nhân viên";
            elements = new String[]{"Stt", "Mã nhân viên", "Tên nhân viên"};
        }
        initComponents(title, elements);

    }

    private void setUpRow() {
        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    String id = (String) model.getValueAt(row, 1);
                    try {
                        var user = userService.findUserByPersonalId(id);
                        if (userRole == Role.ADMIN) {
                            addNewManager(user);
                        } else {
                            displayUserBorrowFrame(user);
                        }
                    } catch (RuntimeException ex) {
                        JOptionPaneUtil.showErrorDialog(ex.getMessage(), null);
                    }
                }
            }
        });
    }

    private void displayUserBorrowFrame(UserEntity user) {
        UserBorrowDetailFrame frame = new UserBorrowDetailFrame(user, borrowBookService);
        AppUtil.setUpWindowListener(frame, this, null);
    }

    private void initTableModel() {
        if (this.model == null) {
            model = (DefaultTableModel) userTable.getModel();
        }
    }

    private void fetchData(List<UserEntity> items) {
        model.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        items.forEach(user -> {
            var data = new Object[]{
                    String.valueOf(index.get()),
                    user.getPersonalId(),
                    user.getUserName()
            };
            model.addRow(data);
            index.addAndGet(1);
        });
    }


    private void fetchData(Supplier<List<UserEntity>> supplier) {
        try {
            List<UserEntity> items = supplier.get();
            fetchData(items);
        } catch (RuntimeException e) {
            JOptionPaneUtil.showErrorDialog(e.getMessage(), this);
        }
    }

    private void fetchExistUsers() {
        var currentUserRole = currentUser.getRole();
        switch (currentUserRole) {
            case Role.ADMIN -> {
                if (items==null || items.isEmpty()){
                    items=userService.findAllUserByRole(Role.MANAGER);
                }
            }
            case Role.MANAGER -> {
                if (items==null || items.isEmpty()){
                    items=userService.findAllUserByRole(Role.USER);
                }
            }
        }
        fetchData(items);
    }

    private void fetchNewUsers() {
        try {
            items=userService.fetchAllUser(Role.USER);
            fetchData(items);
        } catch (RuntimeException e) {
            JOptionPaneUtil.showErrorDialog(e.getMessage(), this);
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(String title, String[] elements) {

        jScrollPane2 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnAction = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(title);

        userTable.setBackground(new java.awt.Color(255, 255, 255));
        userTable.setForeground(new java.awt.Color(0, 0, 0));
        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            elements
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(userTable);
        if (userTable.getColumnModel().getColumnCount() > 0) {
            userTable.getColumnModel().getColumn(0).setMinWidth(80);
            userTable.getColumnModel().getColumn(0).setPreferredWidth(80);
            userTable.getColumnModel().getColumn(0).setMaxWidth(80);
            userTable.getColumnModel().getColumn(1).setMinWidth(150);
            userTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            userTable.getColumnModel().getColumn(1).setMaxWidth(150);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText(title);

        btnAction.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/image/icons8-replace-24.png")))); // NOI18N
        btnAction.setText("Làm mới");
        btnAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionActionPerformed(evt);
            }
        });

        btnExit.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/image/icons8-log-out-24.png")))); // NOI18N
        btnExit.setText("Thoát");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/image/icons8-search-24.png")))); // NOI18N
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAction, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionActionPerformed
        switch (this.userRole) {
            case ADMIN -> {
                addNewManager(null);
            }
            case MANAGER -> fetchNewUsers();
        }
    }//GEN-LAST:event_btnActionActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        List<UserEntity> items = new ArrayList<>();
        String key = txtSearch.getText();
        switch (this.userRole) {
            case ADMIN -> searchUser(key,items,Role.MANAGER);
            case MANAGER -> searchUser(key,items,Role.USER);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void searchUser(String key ,List<UserEntity> items, Role role) {
        try {
            var users = userService.findByIdOrUserName(key);
            for(UserEntity user : users){
                if (user.getRole() == role) {
                    items.add(user);
                }
            }
            fetchData(items);
        } catch (RuntimeException e) {
            JOptionPaneUtil.showErrorDialog(e.getMessage(), this);
        }
    }

    private void addNewManager(UserEntity user) {
        AddLibrarianFrame frame = new AddLibrarianFrame(userService, user);
        AppUtil.setUpWindowListener(frame, this, this::fetchExistUsers);
    }


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
//            java.util.logging.Logger.getLogger(QuanLyDocGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(QuanLyDocGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(QuanLyDocGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(QuanLyDocGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new QuanLyDocGia().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAction;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}

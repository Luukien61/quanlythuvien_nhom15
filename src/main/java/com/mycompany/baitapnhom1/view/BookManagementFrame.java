/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.baitapnhom1.view;

import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.model.BookFields;
import com.mycompany.baitapnhom1.service.implement.BookService;
import com.mycompany.baitapnhom1.service.implement.UserService;
import com.mycompany.baitapnhom1.util.AppUtil;
import com.mycompany.baitapnhom1.util.JOptionPaneUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kienl
 */
public class BookManagementFrame extends javax.swing.JFrame {

    private final BookService bookService;
    private final UserService userService;
    private List<BookEntity> items;
    private WindowListener windowListener = null;

    /**
     * Creates new form BookManagement
     */
    public BookManagementFrame(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
        initComponents();
        initData();
        initRowFunction();
        initSearch();
        setLocationRelativeTo(null);
    }

    private void initSearch() {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) snpSearch.getModel();
        var items = new ArrayList<String>();
        items.add(BookFields.NAME.getFieldName());
        items.add(BookFields.BOOK_ID.getFieldName());
        items.add(BookFields.CATEGORY.getFieldName());
        items.add(BookFields.AUTHOR.getFieldName());
        items.add(BookFields.PUBLISHER.getFieldName());
        items.add(BookFields.PUBLISH_DATE.getFieldName());
        items.forEach(model::addElement);
    }

    private void initRowFunction() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Xoá");
        JMenuItem updateItem = new JMenuItem("Sửa");
        popupMenu.add(deleteItem);
        popupMenu.add(updateItem);

        deleteItem.addActionListener(e -> {
            int selectedRow = tableSach.getSelectedRow();
            if (selectedRow >= 0) {
                deleteRow(selectedRow);
            }
        });
        updateItem.addActionListener(e -> {
            int selectedRow = tableSach.getSelectedRow();
            if (selectedRow >= 0) {
                showUpdateBookFrame(selectedRow);
            }
        });

        tableSach.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                int r = tableSach.rowAtPoint(e.getPoint());
                if (r >= 0 && r < tableSach.getRowCount()) {
                    tableSach.setRowSelectionInterval(r, r);
                } else {
                    tableSach.clearSelection();
                }

                int rowindex = tableSach.getSelectedRow();
                if (rowindex < 0)
                    return;
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    private void showUpdateBookFrame(int selectedRow) {
        var book = getBookEntity(selectedRow);
        if (book != null) {
            UpdateBookFrame updateBookFrame = new UpdateBookFrame(bookService, book);
            updateBookFrame.setLocationRelativeTo(null);
            updateBookFrame.setVisible(true);
            setVisible(false);
            updateBookFrame.addWindowListener(getWindowListener());
        }
    }

    private BookEntity getBookEntity(int selectedRow) {
        var model = tableSach.getModel();
        var bookId = model.getValueAt(selectedRow, 0).toString();
        BookEntity book = null;
        try {
            book = bookService.findBookByBookId(bookId);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return book;
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSach = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        snpSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnExist = new javax.swing.JButton();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tableSach.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Mã sách", "Tên sách", "Số lượng", "Số lượng kho", "Tên tác giả", "Thể Loại", "Nhà xuất bản", "Năm xuất bản"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tableSach.getTableHeader().setReorderingAllowed(false);
        tableSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSachMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableSach);
        if (tableSach.getColumnModel().getColumnCount() > 0) {
            tableSach.getColumnModel().getColumn(2).setResizable(false);
            tableSach.getColumnModel().getColumn(3).setResizable(false);
            tableSach.getColumnModel().getColumn(4).setResizable(false);
            tableSach.getColumnModel().getColumn(5).setResizable(false);
            tableSach.getColumnModel().getColumn(6).setResizable(false);
            tableSach.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Quản lý sách");

        snpSearch.setBackground(new java.awt.Color(255, 255, 255));
        snpSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snpSearchActionPerformed(evt);
            }
        });

        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnAdd.setText("Thêm mới");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnExist.setText("Thoát");
        btnExist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExistActionPerformed(evt);
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
                                                .addComponent(jScrollPane2))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(371, 371, 371)
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(snpSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(48, 48, 48)
                                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(105, 105, 105)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnExist, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(snpSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnExist, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteRow(int selectedRow) {
        int choose = JOptionPane.showConfirmDialog(
                this,
                "Are you sure to delete this book. This action can not be undo",
                "Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (choose == JOptionPane.YES_OPTION) {
            var model = tableSach.getModel();
            var bookId = model.getValueAt(selectedRow, 0).toString();
            var bookName = model.getValueAt(selectedRow, 1).toString();
            try {
                bookService.deleteBookByBookIdAndName(bookId, bookName);
                initData();
                JOptionPaneUtil.showMessageDialog("Deleted Successfully", 1500, jScrollPane2);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private WindowListener getWindowListener() {
        if (windowListener == null) {
            windowListener = new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    setVisible(true);
                    initData();
                }
            };
        }
        return windowListener;
    }

    private void initData() {
        try {
            items = bookService.findAllBook();
            var defaultTableModel = (DefaultTableModel) tableSach.getModel();
            defaultTableModel.setRowCount(0);
            items.forEach((book) -> {
                var date = book.getPublishDate();
                var data = new Object[]{
                        book.getBookId(), book.getBookName(), book.getTotalQuantity(),
                        book.getRestQuantity(), book.getAuthor(), book.getCategory(),
                        book.getPublisher(), AppUtil.getPublishDateString(date)
                };
                defaultTableModel.addRow(data);
            });

        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tableSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSachMouseClicked
        // TODO add your handling code here:
        int rowID = tableSach.rowAtPoint(evt.getPoint());


    }//GEN-LAST:event_tableSachMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        AddBookFrame addBookFrame = new AddBookFrame(bookService);
        addBookFrame.setLocationRelativeTo(null);
        addBookFrame.setVisible(true);
        setFocusable(false);
        setVisible(false);
        addBookFrame.addWindowListener(getWindowListener());
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnExistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExistActionPerformed
        dispose();
    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        var key = txtSearch.getText();
        if (!key.isBlank()) {
            var field = (String) snpSearch.getModel().getSelectedItem();
            if (field.equals("Năm xuất bản")) {
                try {
                    var year = Integer.parseInt(key);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Please type the year correctly",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }

    private void snpSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snpSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_snpSearchActionPerformed

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
//            java.util.logging.Logger.getLogger(BookManagementFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(BookManagementFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(BookManagementFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(BookManagementFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BookManagementFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnExist;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> snpSearch;
    private javax.swing.JTable tableSach;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.baitapnhom1.view;

import com.mycompany.baitapnhom1.entity.BookCategory;
import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.service.implement.BookService;
import com.mycompany.baitapnhom1.service.implement.UserService;
import com.mycompany.baitapnhom1.util.AppUtil;
import com.mycompany.baitapnhom1.util.JOptionPaneUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
        setLocationRelativeTo(null);
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
        if(book!=null){
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
        btnadd = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        btnsearch = new javax.swing.JButton();

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

        btnadd.setText("Thêm mới");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnexit.setText("Thoát");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        btnsearch.setText("Tìm kiếm");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
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
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(59, 59, 59)
                                                .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(207, 207, 207)
                                                .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(49, 49, 49)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29))
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

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        SearchBookFrame searchBookFrame = new SearchBookFrame();
    }//GEN-LAST:event_btnsearchActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        AddBookFrame addBookFrame = new AddBookFrame(bookService);
        addBookFrame.setLocationRelativeTo(null);
        addBookFrame.setVisible(true);
        setFocusable(false);
        setVisible(false);
        addBookFrame.addWindowListener(getWindowListener());

    }//GEN-LAST:event_btnaddActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        dispose();
    }//GEN-LAST:event_btnexitActionPerformed

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
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnsearch;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableSach;
    // End of variables declaration//GEN-END:variables
}

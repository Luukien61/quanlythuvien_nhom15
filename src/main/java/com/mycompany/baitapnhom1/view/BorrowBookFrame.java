package com.mycompany.baitapnhom1.view;

import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.entity.BorrowFormEntity;
import com.mycompany.baitapnhom1.entity.ReturnState;
import com.mycompany.baitapnhom1.service.implement.BorrowBookService;
import com.mycompany.baitapnhom1.util.AppUtil;
import com.mycompany.baitapnhom1.util.JOptionPaneUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author kienl
 */
public class BorrowBookFrame extends javax.swing.JFrame {
    private final BorrowBookService borrowBookService;
    private DefaultTableModel model;

    /**
     * Creates new form BorrowBookFrame
     */
    public BorrowBookFrame(BorrowBookService borrowBookService) {
        this.borrowBookService = borrowBookService;
        initComponents();
        initModel();
        fetchAllData();
    }

    private void initModel() {
        model = (DefaultTableModel) tableContent.getModel();
        DefaultComboBoxModel<Integer> quantityModel = (DefaultComboBoxModel) snpQuantity.getModel();
        DefaultComboBoxModel<String> timeModel = (DefaultComboBoxModel) snpTime.getModel();
        var defaultQuantity = new int[]{1, 2, 3};
        var defaultTime = new String[]{"1 month", "2 month", "3 month"};
        Arrays.stream(defaultQuantity).forEach(quantityModel::addElement);
        Arrays.stream(defaultTime).forEach(timeModel::addElement);
        JPopupMenu menu = new JPopupMenu();
        JMenuItem returnAction = new JMenuItem("Trả sách");
        JMenuItem deleteAction = new JMenuItem("Xoá");
        menu.add(returnAction);
        menu.add(deleteAction);
        deleteAction.addActionListener(e -> {
            var selectedRow = tableContent.getSelectedRow();
            deleteBorrowItem(selectedRow);
        });
        returnAction.addActionListener(e -> {
            var selectedRow = tableContent.getSelectedRow();
            returnBook(selectedRow);
        });
        tableContent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                var row = tableContent.rowAtPoint(e.getPoint());
                if (row >= 0 && row < tableContent.getRowCount()) {
                    tableContent.setRowSelectionInterval(row, row);
                    if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                        menu.show(e.getComponent(), e.getX(), e.getY());
                    }
                } else {
                    tableContent.clearSelection();
                }
            }
        });
    }

    private void returnBook(int selectedRow) {
        var state = model.getValueAt(selectedRow, 7);
        if (ReturnState.RETURNED.getState().equals(state)) {
            JOptionPane.showMessageDialog(
                    this,
                    "This items was returned",
                    "Action",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            var choose = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure to return this item?",
                    "Return",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE
            );
            if(choose==JOptionPane.YES_OPTION){
                var borrowId = (String) model.getValueAt(selectedRow, 1);
                borrowBookService.returnBook(borrowId);
                fetchAllData();
            }
        }
    }

    private void deleteBorrowItem(int selectedRow) {
        var state = (String) model.getValueAt(selectedRow, 7);
        if (checkItemReturn(state)) {
            JOptionPaneUtil.showErrorDialog("Please return the book before deleting this item", this);
        } else {
            var choose = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure to delete this item?",
                    "Delete",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE
            );
            if (choose == JOptionPane.YES_OPTION) {
                var borrowId = (String) model.getValueAt(selectedRow, 1);
                try {
                    borrowBookService.deleteItem(borrowId);
                    fetchAllData();
                } catch (RuntimeException e) {
                    JOptionPaneUtil.showErrorDialog(e.getMessage(), this);
                }
            }
        }

    }

    private boolean checkItemReturn(String state) {
        return ReturnState.NOT_YET.getState().equals(state) || state.equals(ReturnState.EXPIRED.getState());
    }

    private void fetchAllData() {
        fetchData(borrowBookService::fetchAll);
    }

    private void fetchData(Supplier<List<BorrowFormEntity>> function) {
        try {
            List<BorrowFormEntity> items = function.get();
            if (!items.isEmpty()) {
                borrowBookService.displayData(model, items, BookEntity::getBookId);
            } else throw new RuntimeException("There is no result matching");
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
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jLabel1 = new javax.swing.JLabel();
        txtUserId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtBookId = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableContent = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        snpQuantity = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        snpTime = new javax.swing.JComboBox<>();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý mượn trả");

        jLabel1.setText("Mã sinh viên");

        jLabel2.setText("Mã sách");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-add-24.png"))); // NOI18N
        btnAdd.setText("Mượn");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-24.png"))); // NOI18N
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        tableContent.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Stt", "Mã phiếu mượn", "Mã sinh viên", "Mã sách", "Số lượng", "Ngày mượn", "Ngày hẹn trả", "Trạng thái"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableContent);
        if (tableContent.getColumnModel().getColumnCount() > 0) {
            tableContent.getColumnModel().getColumn(0).setResizable(false);
            tableContent.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableContent.getColumnModel().getColumn(4).setResizable(false);
            tableContent.getColumnModel().getColumn(4).setPreferredWidth(50);
            tableContent.getColumnModel().getColumn(6).setMinWidth(100);
            tableContent.getColumnModel().getColumn(6).setPreferredWidth(100);
            tableContent.getColumnModel().getColumn(6).setMaxWidth(100);
            tableContent.getColumnModel().getColumn(7).setMinWidth(70);
            tableContent.getColumnModel().getColumn(7).setPreferredWidth(70);
            tableContent.getColumnModel().getColumn(7).setMaxWidth(70);
        }

        jLabel3.setText("Số lượng");

        snpQuantity.setBackground(new java.awt.Color(255, 255, 255));
        snpQuantity.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setText("Hẹn trả");

        snpTime.setBackground(new java.awt.Color(255, 255, 255));
        snpTime.setForeground(new java.awt.Color(0, 0, 0));
        snpTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snpTimeActionPerformed(evt);
            }
        });

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-replace-24.png"))); // NOI18N
        btnRefresh.setText("Làm mới");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(50, 50, 50)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel1)
                                                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(33, 33, 33)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(txtBookId, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addGap(84, 84, 84)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel3)
                                                                                        .addComponent(jLabel4))
                                                                                .addGap(26, 26, 26)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(snpQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(snpTime, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(339, 339, 339)
                                                                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 65, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)
                                        .addComponent(snpQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtBookId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel4)
                                        .addComponent(snpTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAdd)
                                        .addComponent(btnSearch))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                .addComponent(btnRefresh)
                                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        var userId = txtUserId.getText();
        var bookId = txtBookId.getText();
        AppUtil.checkValidInput(userId, bookId);
        int time = Integer.parseInt(((String) Objects.requireNonNull(snpTime.getSelectedItem())).substring(0, 1));
        int quantity = (Integer) snpQuantity.getSelectedItem();
        try {
            borrowBookService.addNewBorrowItem(userId, bookId, time, quantity);
            JOptionPaneUtil.showMessageDialog("Added successfully", 800, null);
            clearText();
        } catch (Exception e) {
            JOptionPaneUtil.showErrorDialog(e.getMessage(), this);
        }
    }//GEN-LAST:event_btnAddActionPerformed


    private void clearText() {
        txtBookId.setText("");
        txtUserId.setText("");
        snpTime.setSelectedIndex(0);
        snpQuantity.setSelectedIndex(0);
    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        var userId = txtUserId.getText().trim();
        var bookId = txtBookId.getText().trim();
        if (userId.isBlank() && bookId.isBlank()) {
            JOptionPaneUtil.showErrorDialog("Please fill the require fields", this);
        } else {
            fetchData(() -> borrowBookService.findAllByUserIdAndBookId(userId, bookId));
        }

    }//GEN-LAST:event_btnSearchActionPerformed

    private void snpTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snpTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_snpTimeActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        borrowBookService.expireBorrowForm();
        fetchAllData();
    }//GEN-LAST:event_btnRefreshActionPerformed

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
//            java.util.logging.Logger.getLogger(BorrowBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(BorrowBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(BorrowBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(BorrowBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BorrowBookFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> snpQuantity;
    private javax.swing.JComboBox<String> snpTime;
    private javax.swing.JTable tableContent;
    private javax.swing.JTextField txtBookId;
    private javax.swing.JTextField txtUserId;
    // End of variables declaration//GEN-END:variables
}

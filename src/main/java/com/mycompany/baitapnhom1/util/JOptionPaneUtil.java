package com.mycompany.baitapnhom1.util;

import org.springframework.lang.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JOptionPaneUtil {
    public static void showMessageDialog(String message, int delay,@Nullable Component component){
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
        final JDialog dialog = new JDialog();
        dialog.setTitle("Thông báo");
        dialog.setModal(true);
        dialog.setContentPane(optionPane);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setLocationRelativeTo(component);
        dialog.pack();
        Timer timer = new Timer(delay, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

}

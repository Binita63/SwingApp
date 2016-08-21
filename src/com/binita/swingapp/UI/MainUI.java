/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.binita.swingapp.UI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Bini
 */
public class MainUI extends JFrame {
    
    private JLabel lblFileName;
    private JTextField txtFileName;
    private JTextArea txtNotepad;
    private JButton btnCreate, btnopen;
    private JScrollPane scNotepad;
    
    public MainUI() {
        setTitle("Jframe App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        initComponents();
        setVisible(true);
        
    }
    
    private void initComponents() {
        lblFileName = new JLabel("enter file Name:");
        
        txtFileName = new JTextField(20);
        txtNotepad = new JTextArea(25, 40);
        scNotepad=new JScrollPane(txtNotepad);
        btnCreate = new JButton("create file");
        btnCreate.addActionListener(new CreateButtonListener());
        btnopen = new JButton("open file");
        btnopen.addActionListener(new openButtonListener());
        add(scNotepad, 0);
        add(lblFileName, 1);
        add(txtFileName, 2);
        add(btnCreate);
        add(btnopen);
        
    }

    private class CreateButtonListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String fileName = txtFileName.getText();
                FileWriter writer = new FileWriter(fileName);
                writer.write(txtNotepad.getText());
                writer.close();
                txtNotepad.setText("");
                txtFileName.setText("");
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, ioe.getMessage());
            }
            
        }
        
    }

    private class openButtonListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(txtFileName.getText()));
                String line = "";
                StringBuilder content = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\r\n");
                    
                }
                reader.close();
                txtNotepad.setText(content.toString());
                
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, ioe.getMessage());
            }
        }
        
    }
}

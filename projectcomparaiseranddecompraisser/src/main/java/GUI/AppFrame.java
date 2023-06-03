/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package GUI;
//
//import comp_decomp.compressor;
//import comp_decomp.decompressor;
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import javax.swing.JButton;
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//
//public class AppFrame extends JFrame implements ActionListener {
//    private JButton compressButton;
//    private JButton decompressButton;
//    
//    public AppFrame() {
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        
//        compressButton = new JButton("Select file to compress");
//        compressButton.setBounds(20, 100, 200, 30);
//        compressButton.addActionListener(this);
//        this.add(compressButton);
//        
//        decompressButton = new JButton("Select file to decompress");
//        decompressButton.setBounds(250, 100, 200, 30);
//        decompressButton.addActionListener(this);
//        this.add(decompressButton);
//        
//        this.setSize(1000, 500);
//        this.getContentPane().setBackground(Color.GREEN);
//        this.setLayout(null);
//        this.setVisible(true);
//    }
//    
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == compressButton) {
//            JFileChooser filechooser = new JFileChooser();
//            int response = filechooser.showSaveDialog(null);
//            if (response == JFileChooser.APPROVE_OPTION) {
//                File file = new File(filechooser.getSelectedFile().getAbsolutePath());
//                System.out.print(file);
//                try {
//                    compressor.method(file);
//                } catch (Exception ee) {
//                    JOptionPane.showMessageDialog(null, ee.toString());
//                }
//            }
//        }
//        if (e.getSource() == decompressButton) {
//            JFileChooser filechooser = new JFileChooser();
//            int response = filechooser.showSaveDialog(null);
//            if (response == JFileChooser.APPROVE_OPTION) {
//                File file = new File(filechooser.getSelectedFile().getAbsolutePath());
//                System.out.print(file);
//                try {
//                    decompressor.method(file);
//                } catch (Exception ee) {
//                    JOptionPane.showMessageDialog(null, ee.toString());
//                }
//            }
//        }
//    }
//}
package GUI;

import comp_decomp.compressor;
import comp_decomp.decompressor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AppFrame extends JFrame implements ActionListener {
    private JButton compressButton;
    private JButton decompressButton;
    private JTextArea outputTextArea;

    public AppFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("File Compressor and Decompressor");
        this.setSize(500, 300);
       this.getContentPane().setLayout(new BorderLayout());
        
        JPanel buttonPanel = new JPanel();
        compressButton = new JButton("Select File to Compress");
        decompressButton = new JButton("Select File to Decompress");
        
        buttonPanel.add(compressButton);
        buttonPanel.add(decompressButton);
        
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        
      this.add(buttonPanel, BorderLayout.NORTH);
        this.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
        
        compressButton.addActionListener(this);
        decompressButton.addActionListener(this);
        
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == compressButton) {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(this);
          String s=  Integer.toString(response);
            outputTextArea.append(s);
            
            
            
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    compressor.method(file);
                    outputTextArea.append("File compressed successfully.\nLocation: " + file.getAbsolutePath() + ".gz\n");
                } catch (Exception ex) {
                    outputTextArea.append("Compression error: " + ex.toString() + "\n");
                }
            }
        } else if (e.getSource() == decompressButton) {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(this);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    decompressor.method(file);
                    String decompressedFilePath = file.getAbsolutePath().replace(".gz", "");
                    outputTextArea.append("File decompressed successfully.\nLocation: " + decompressedFilePath + "\n");
                } catch (Exception ex) {
                    outputTextArea.append("Decompression error: " + ex.toString() + "\n");
                }
            }
        }
    }
}


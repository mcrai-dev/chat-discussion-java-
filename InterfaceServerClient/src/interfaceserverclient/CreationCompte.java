/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceserverclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Ny Aina Solofomasy
 */
//public class CreationCompte extends JFrame {
//
//    private JLabel entete = new JLabel("Incription");
//    private JLabel nom = new JLabel("Name");
//    private JLabel prenom = new JLabel("Prenoms");
//    private JLabel pass = new JLabel("Password");
//    private JLabel confirmePass = new JLabel("Confirme MDP");
//    private JLabel inscrit = new JLabel("Inscrire");
//    private JPanel image = new JPanel();
//    private JTextField textNom = new JTextField();
//    private JTextField textPrenom = new JTextField();
//    private JPasswordField textPass = new JPasswordField();
//    private JPasswordField textConfirmePass = new JPasswordField();
//    private JButton file = new JButton("File");
//
//    private final JPanel panelPrincipal = (JPanel) this.getContentPane();
//    private final JPanel panelAmbony = new JPanel();
//    private final JPanel panelCentre = new JPanel();
//    private final JPanel panelAmbany = new JPanel();
//
//    public CreationCompte() {
//        this.setResizable(true);
//        this.setTitle("CREATE ACCOUNT ");
//        this.setSize(400, 500);
//        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        this.setLocationRelativeTo(null);
//
//        textNom.setPreferredSize(new Dimension(200, 24));
//        textPrenom.setPreferredSize(new Dimension(200, 24));
//        textPass.setPreferredSize(new Dimension(200, 24));
//        textConfirmePass.setPreferredSize(new Dimension(200, 24));
//        image.setPreferredSize(new Dimension(100, 100));
//        image.setBackground(Color.ORANGE);
//        file.setBackground(Color.CYAN);
//        file.setPreferredSize(new Dimension(100, 24));
//
//        panelAmbony.setBackground(Color.WHITE);
//        panelAmbony.add(entete);
//        panelPrincipal.add(panelAmbony, BorderLayout.NORTH);
//
//        panelPrincipal.add(panelCentre, BorderLayout.CENTER);
//        panelCentre.setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//        c.insets = new Insets(5, 5, 15, 5);
//
//        c.weightx = 5;
//        c.gridx = 0;
//        c.gridy = 0;
//        panelCentre.add(nom, c);
//        c.gridx = 1;
//        c.gridy = 0;
//        panelCentre.add(textNom, c);
//
//        c.gridx = 0;
//        c.gridy = 1;
//        panelCentre.add(prenom, c);
//        c.gridx = 1;
//        c.gridy = 1;
//        panelCentre.add(textPrenom, c);
//
//        c.gridx = 0;
//        c.gridy = 2;
//        panelCentre.add(pass, c);
//        c.gridx = 1;
//        c.gridy = 2;
//        panelCentre.add(textPass, c);
//
//        c.gridx = 0;
//        c.gridy = 3;
//        panelCentre.add(confirmePass, c);
//        c.gridx = 1;
//        c.gridy = 3;
////        panelCentre.add(textConfirmePass, c);
//
//        c.fill = GridBagConstraints.NORTH;
//        c.gridx = 0;
//        c.gridy = 4;
//        c.gridwidth = 3;
//
//        panelCentre.add(image, c);
//        c.gridx = 0;
//        c.gridy = 5;
//
//        panelCentre.add(file, c);
//        c.gridx = 0;
//        c.gridy = 6;
//        panelCentre.add(inscrit, c);
//        inscrit.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        file.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        inscrit.addMouseListener((new MouseAdapter() {
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                String password = String.valueOf(textPass.getPassword());
//                String nom = textNom.getText();
//                if (textNom.getText().equals("")) {
//                    JOptionPane.showMessageDialog(panelPrincipal, "Add at least one name", "Notification", JOptionPane.ERROR_MESSAGE);
//
//                }
//                if (password.equals("")) {
//                    JOptionPane.showMessageDialog(panelPrincipal, "You must have a password", "Notification", JOptionPane.ERROR_MESSAGE);
//
//                } else {
//                    JOptionPane.showMessageDialog(panelPrincipal, "Votre information est enregistré\n LOGGING : " + nom + "\n PASSWORDS : " + password + " ", "Notification", JOptionPane.INFORMATION_MESSAGE);
//                }
//
//            }
////            else 
////                   JOptionPane.showMessageDialog(panelPrincipal, "Mot de passe different de celle de confirmation.", "Notification", JOptionPane.ERROR_MESSAGE);
//// 
////                }
//
//        }));
//
//    }
//
//}

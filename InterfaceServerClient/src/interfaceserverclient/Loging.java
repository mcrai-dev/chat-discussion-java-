package interfaceserverclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

//public class Loging extends JFrame {
//
//    private final JPanel panelPrincipal = (JPanel) this.getContentPane();
//    private final JPanel panelAmbony = new JPanel();
//    private final JPanel panelCentre = new JPanel();c
//    private final JPanel panelAmbany = new JPanel();
//    private final JTextField adresse = new JTextField();
//    private final JPasswordField pass = new JPasswordField();
//    private JLabel nomadresse = new JLabel("Address");
//    private JLabel nompass = new JLabel("Password");
//    private JLabel create = new JLabel("Create");
//    private JLabel copp = new JLabel("EA2i");masy
//    private JLabel login = new JLabel("Logging");
//    private JButton connection = new JButton("Connecter");
//
//    ArrayList<String> users = new ArrayList();
//
//    Socket sock;
//    BufferedReader reader;
//    PrintWriter writer;
//
//    String username = "mety";
//
////    public void ListenThread() {
////        Thread IncomingReader = new Thread(new IncomingReader());
////        IncomingReader.start();
////    }
//
//    public Loging() {
//        // interface loging
//        this.setResizable(false);
//        this.setTitle("Logging");
//        this.setSize(400, 300);
//        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        this.setLocationRelativeTo(null);
//
//        login.setFont(login.getFont().deriveFont(Font.BOLD, 44));
//        login.setForeground(Color.WHITE);
//
//        //
//        adresse.setPreferredSize(new Dimension(200, 24));
//        pass.setPreferredSize(new Dimension(200, 24));
//        connection.setPreferredSize(new Dimension(200, 24));
//
//        //panel ambony maneho "Login"
//        panelAmbony.setBackground(Color.red);
//        panelPrincipal.add(panelAmbony, BorderLayout.NORTH);
//        panelAmbony.add(login);
//
//        panelPrincipal.add(panelCentre, BorderLayout.CENTER);
//        panelCentre.setBackground(Color.GRAY);
//        panelCentre.setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//
//        //les composant new Insets(margeSupérieure, margeGauche, margeInférieur, margeDroite)
//        c.insets = new Insets(5, 5, 15, 5);
//
//        c.weightx = 5;
//        c.gridx = 0;
//        c.gridy = 0;
//        panelCentre.add(nomadresse, c);
//        c.gridx = 1;
//        c.gridy = 0;
//        panelCentre.add(adresse, c);
//        c.gridx = 0;
//        c.gridy = 1;
//        panelCentre.add(nompass, c);
//        c.gridx = 1;
//        c.gridy = 1;
//        panelCentre.add(pass, c);
//
//        c.fill = GridBagConstraints.NORTH;
//        c.gridx = 0;
//        c.gridy = 2;
//        c.gridwidth = 3;
//        panelCentre.add(connection, c);
//
//        c.gridy = 4;
//        panelCentre.add(create, c);
//
//        // panel Ambany
//        panelAmbany.setBackground(Color.BLACK);
//        panelPrincipal.add(panelAmbany, BorderLayout.SOUTH);
//        panelAmbany.add(copp);
//
//        connection.addActionListener(new Affichage());
////
////        /**
////         * ***************************************
////         */
////        try {
////            sock = new Socket("localhost", 6000);
////            InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
////            reader = new BufferedReader(streamreader);
////            writer = new PrintWriter(sock.getOutputStream());
////            writer.println(username + ": has connected :Connect");
////            writer.flush();
//////                isConnected = true; 
//////                changetxtareafontsize(ta_chat) ;
////        } catch (Exception ex) {
////        }
////
////        ListenThread();
//
//        /**
//         * **************************************
//         */
//        create.setForeground(Color.WHITE);
//        copp.setForeground(Color.WHITE);
//        create.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        connection.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        create.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                CreationCompte a = new CreationCompte();
//                a.setVisible(true);
//
//                //super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
//            }
//
//        });
//    }
////
////    /**
////     * *********************************
////     */
////    public class IncomingReader implements Runnable {
////
////        @Override
////        public void run() {
////            String[] data;
////            String stream, connect = "Connect", disconnect = "Disconnect", chat = "Chat", privatemsg = "private", inscription = "inscription";
////
////            try {
////                while ((stream = reader.readLine()) != null) {
////                    data = stream.split(":");
////
////                    if (data[2].equals(chat)) {
//////                        ta_chat.append(data[0] + ": " + data[1] + "\n");
//////                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
////                    } else if (data[2].equals(connect)) {
//////                        ta_chat.removeAll();
//////                        userAdd(data[0]); // name of the client "data [0]"
////
////                    } else if (data[2].equals(disconnect)) {
//////                         userRemove(data[0]);
////                    } else if (data[2].equals(privatemsg)) {
//////                         ta_chat.append("Private Message from " + data[0] + " : " + data[1] +"\n");
////
////                    } else if (data[2].equals("request")) {
//////                              JOptionPane.showMessageDialog(null, "i am here 2 ");
//////                        ta_chat.append(" Server replied " + "\n" + data[1] +"\n");
////                    } else if (data[2].equals("inscription")) {
////
////                    }
////                }
////            } catch (Exception ex) {
////            }
////        }
////    }
////
////    //--------------------------//
////    /**
////     * ***************************
////     */
//    private class Affichage implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            InterfaceFinal a = new InterfaceFinal();
//            a.setVisible(true);
//            dispose();
//        }
//
//    }
///*************** creation account**********************/
//    public class CreationCompte extends JFrame {
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
//        }));
//  }
//
//}
//    /*********** end creation account ***************/
//    
//}

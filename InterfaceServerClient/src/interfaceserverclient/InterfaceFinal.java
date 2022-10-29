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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.LineBorder;

/**
 *
 * @author Mcrai Laydam
 */
public class InterfaceFinal extends JFrame {

    private final JPanel panelPrincipal = (JPanel) this.getContentPane();
    private final JPanel panelHavia = new JPanel();
    private final JPanel panelCentre = new JPanel();
    private final JPanel panelHavanana = new JPanel();
    private final JPanel panelAmbony = new JPanel();

    private final JPanel panelCentreAmbany = new JPanel();
    private final JPanel panelCentreAmbony = new JPanel();
    private final JPanel panelCentreAmpovoany = new JPanel(new BorderLayout());

    private final JPanel panelHaviaProfil = new JPanel();
    private final JPanel panelHaviaProfilPh = new JPanel();
    private final JPanel panelHaviaProfilIn = new JPanel(new SpringLayout());
    private final JPanel panelHaviaProfilGr = new JPanel();
    private final JButton envoyer = new JButton("Send");
    private final JTextArea text = new JTextArea("");
    static JInternalFrame jInternalFrame = new JInternalFrame();
    static JInternalFrame jInternalFrameGroup = new JInternalFrame();

    private final JButton closeSession = new JButton("Exit session");
    private final JButton creatGroup = new JButton("Create Group");

    private final JPanel image = new JPanel();

    private final JLabel Chate = new JLabel("Chat EA2I");
    public static JLabel profil = new JLabel("Profil");
    private final JLabel nom = new JLabel("Name : ");
    public static JLabel nNom = new JLabel(" Username ");
    private final JLabel personeConnecter = new JLabel("Online people");
    private final JLabel message = new JLabel("Message");
    private final JLabel creation = new JLabel("separate with\",\" all name. (eg: name1,name2)");
    private static final JTextArea textGroup = new JTextArea();

    public static JTextArea srv = new JTextArea();//---main screen 
    /*jdialog*/
    JDialog ok = new JDialog();
    JTextField nommp = new JTextField();
    JTextField textiing = new JTextField();
    JTextArea txtmp = new JTextArea();
    JButton sendmp = new JButton("OK");
    /*socket*/
    static String username;
    static ArrayList<String> users = new ArrayList();

    public static Socket socket;
    public static BufferedReader reader;
    public static PrintWriter writer;

    static DefaultListModel dlm = new DefaultListModel();
    static JList liste = new JList(dlm);

    public InterfaceFinal() {
        this.setResizable(true);
        this.setTitle("Chat");
        this.setSize(1300, 730);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        messagePrive a = new messagePrive();

        // message prive
        jInternalFrame.setLocation(100, 100);
        jInternalFrame.setSize(300, 400);
        jInternalFrame.setClosable(true);
        jInternalFrame.add(a);

        //group message
        jInternalFrameGroup.setLocation(100, 100);
        jInternalFrameGroup.setSize(300, 400);
        jInternalFrameGroup.setClosable(true);
//        jInternalFrameGroup.add(b);

        panelPrincipal.add(jInternalFrame);
        panelPrincipal.add(jInternalFrameGroup);
        profil.setFont(profil.getFont().deriveFont(Font.BOLD, 44));

        panelHavia.setBackground(Color.gray);
        panelHavanana.setBackground(Color.gray);
        panelCentre.setBackground(Color.white);
        panelHaviaProfilGr.setBackground(Color.GRAY);
        panelHaviaProfil.setBackground(Color.GRAY);
        panelHaviaProfilIn.setBackground(Color.GRAY);
        panelHaviaProfilPh.setBackground(Color.gray);

        panelHaviaProfil.setPreferredSize(new Dimension(200, 60));

        panelAmbony.setBackground(Color.BLACK);

        Chate.setForeground(Color.WHITE);
        Chate.setFont(Chate.getFont().deriveFont(Font.BOLD, 37));
        panelAmbony.add(Chate, BorderLayout.CENTER);

        panelPrincipal.setBorder(new LineBorder(Color.BLACK, 10));
        panelHavia.setBorder(new LineBorder(Color.BLACK, 10));
        panelHavanana.setBorder(new LineBorder(Color.BLACK, 10));

        panelPrincipal.add(panelAmbony, BorderLayout.NORTH);
        panelPrincipal.add(panelHavanana, BorderLayout.EAST);

        panelHavanana.setLayout(new BoxLayout(panelHavanana, BoxLayout.Y_AXIS));
        panelHavanana.add(personeConnecter);
        JScrollPane scrollPane = new JScrollPane(liste);
        scrollPane.setPreferredSize(new Dimension(200, 500));
        panelHavanana.add(scrollPane);

        panelCentre.setBorder(new LineBorder(Color.BLACK, 10));
        panelPrincipal.add(panelCentre, BorderLayout.CENTER);

        panelCentreAmpovoany.add(new JScrollPane(srv));

        panelCentre.setLayout(new BorderLayout());
        panelCentreAmbony.add(message);
        panelCentre.add(panelCentreAmbony, BorderLayout.NORTH);
        panelCentreAmpovoany.setBackground(Color.WHITE);
        panelCentreAmpovoany.setBorder(new LineBorder(Color.BLACK, 5));

        panelCentre.add(panelCentreAmpovoany, BorderLayout.CENTER);

        panelCentre.add(panelCentreAmbany, BorderLayout.SOUTH);

        JScrollPane sc = new JScrollPane(text);
        sc.setPreferredSize(new Dimension(WIDTH, 60));

        panelCentreAmbany.setLayout(new BoxLayout(panelCentreAmbany, BoxLayout.X_AXIS));
        panelCentreAmbany.add(sc);
        panelCentreAmbany.add(envoyer);

        panelHavia.setLayout(new BoxLayout(panelHavia, BoxLayout.Y_AXIS));
        panelPrincipal.add(panelHavia, BorderLayout.WEST);

        panelHavia.add(panelHaviaProfil);
        panelHaviaProfil.add(profil);

        panelHavia.add(panelHaviaProfilPh);
        image.setPreferredSize(new Dimension(130, 150));
        image.setBackground(Color.WHITE);
        panelHaviaProfilPh.setPreferredSize(new Dimension(300, 170));
        textGroup.setPreferredSize(new Dimension(100, 24));
        panelHaviaProfilPh.add(image);

        panelHavia.add(panelHaviaProfilIn);

        panelHaviaProfilIn.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //les composant new Insets(margeSupérieure, margeGauche, margeInférieur, margeDroite)
        c.insets = new Insets(1, 5, 15, 5);

        c.weightx = 5;
        c.gridx = 0;
        c.gridy = 0;
        panelHaviaProfilIn.add(nom, c);
        c.gridx = 1;
        c.gridy = 0;
        panelHaviaProfilIn.add(nNom, c);

        c.fill = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;

        panelHaviaProfilIn.add(closeSession, c);
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        panelHaviaProfilGr.add(textGroup, c);
        c.gridx = 1;
        c.gridy = 2;
        panelHaviaProfilGr.add(creatGroup, c);

        c.fill = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        panelHaviaProfilGr.add(creation, c);

        panelHaviaProfilGr.setPreferredSize(new Dimension(200, 300));
        panelHavia.add(panelHaviaProfilGr);
        srv.setFont(new Font("DialogInput", Font.ITALIC, 16));

//        username = Loging.adresse.getText();
        creatGroup.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Group a = new Group();
                a.setVisible(true);

                a.setTitle(textGroup.getText());

            }
        });
        /**
         * *****send close session
         */
        closeSession.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String Disconnect = (username + ": :Disconnect");
                try {
                    writer.println(Disconnect);
                    writer.flush();
                    writer.close();
                    socket.close();
                    System.exit(0);
                } catch (Exception ee) {
                    srv.append("error to Disconnect.\n");
                }
            }
        });

        liste.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                jInternalFrame.setVisible(true);
                jInternalFrame.setTitle((String) liste.getSelectedValue());
            }
        });
        srv.setEditable(false);
        envoyer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                send();
            }
        });
//socketConnection();
        ListenThread();

    }

    public static void socketConnection(int option, String firstname, String username, String mdp, String textFieldMsgToSendSocket, String participate) {
        /*SOCKET*/
        try {
            socket = new Socket("localhost", 6000);
            InputStreamReader streamreader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamreader);
            writer = new PrintWriter(socket.getOutputStream());
            if (option == 1) {
                writer.println(username + ": has connected :Connect:" + mdp);//socketConnection(1, username, username, mdp, null, null)
                writer.flush();
            } else if (option == 0) {
                writer.println(username + ": want to subscribe :inscription:" + mdp + ":" + firstname);
                writer.flush();
            } else if (option == 2) {
                writer.println(username + ":" + textFieldMsgToSendSocket + ":" + "group" + ":" + participate);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error of connexion");
        }
        /*FIN SOCKET*/
    }

    public void send() {

        if ((text.getText()).equals("")) {
            text.setText("");
        } else {
            try {
                writer.println(username + ":" + text.getText() + ":" + "Chat");
                writer.flush();
            } catch (Exception ex) {
                srv.append("Message was not sent. \n");
            }
            text.setText("");
            text.requestFocus();
        }

    }

    /* DEBUT TRY TO COONECT */
//    public static void tryToConnect(String address, String pswd) {
//        try {
//            writer.println(username + ":" + address + "," + pswd + ":" + "tryToConnect");
//            System.out.println("mety ny avy ato amin'ny methode trytoconnect");
//            writer.flush();
//        } catch (Exception xe) {
//            System.out.println("ito veny " + xe.getMessage());
//        }
//    }
    /* FIN TRY TO CONNECT */

    /* listener thread*/
    public static void ListenThread() {
        Thread InputReader = new Thread(new InputReader());
        InputReader.start();
    }
    /* fin listener thread*/
    /* input thread*/

    public static class InputReader implements Runnable {

        @Override
        public void run() {
            String[] data;
            String stream;
            try {
                while ((stream = reader.readLine()) != null) {// -------------------------------------point d'entree
                    data = stream.split(":");

                    if (data[2].equals("Chat")) {
                        srv.append(data[0] + ": " + data[1] + "\n");
                        System.out.println("mandeha chat");

                    } else if (data[2].equals("Connect")) {
                        srv.removeAll();
                        users.add(data[0]);
                        dlm.clear();
                        liste.removeAll();

                        for (String user : users) {
                            dlm.removeElement(user);
                        }

                        for (String data1 : users) {
                            dlm.addElement(data1);
                        }

                    } else if (data[2].equals("Disconnect")) {
                        users.remove(data[0]);
                        dlm.removeElement(data[0]);
//                        validate();
                    } else if (data[2].equals("private")) {
                        users.add(data[0]);
                        srv.append("Private Message from " + data[0] + " : \"" + data[1] + "\"\n"); //data[0] le nom d'expediteur, data[1] contenu 
                        jInternalFrame.setVisible(true);
                        messagePrive.clt.setText(data[1] + "\n");
                        String destinator = data[0];
                        messagePrive.envoyer.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                messagePrive.clt.setText(messagePrive.textArea1.getText() + "\n");
                                messagePrive.textArea1.setText("");
                                writer.println(username + ":" + messagePrive.textArea1.getText() + ":" + "private" + ":" + destinator);// selectionne lapersonne qui va etre le destinataire du msg 
                                writer.flush();
                            }
                        });

                    } else if (data[2].equals("inscriptionSuccess")) {

                    } else if (data[2].equals("group")) {
                        String[] participateFromServer = data[3].split(",");//splitter le data[3] qui contient les noms des participants dans la conversation 
                        Group g = new Group();
                        g.setVisible(true);
                        g.txtAreaGroup.append(data[0] + " " + data[1] + "\n");
                        g.setTitle(data[3]);// le nom du groupe devient les nom des participants dans le goup
                        g.panelbtnOption.setVisible(false);

                        for (String participateFromServer1 : participateFromServer) {//ajout de utilisateur depuis la data[3] splitté dans la listModel
                            g.listModelGp.addElement(participateFromServer1);
                        }
                        g.btnsendGroup.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                for (String elementFromList1 : g.elementFromList) {
                                    g.a = g.a + elementFromList1 + ",";
                                }
                                g.a = g.a.substring(0, g.a.length() - 1);//utiliser pour enlever la derniere virgule dans la "elementFromList"
//                    writer.println(username + ":" + textFieldMsgToSend + ":" + "group" + ":" + a)
                                socketConnection(2, null, username, null, g.textFieldMsgToSend.getText(), g.a);//int option, String firstname, String username, String mdp,String textFieldMsgToSendSocket, String participate)
                                writer.flush();
                                g.txtAreaGroup.append(g.textFieldMsgToSend.getText() + "\n");
                                g.textFieldMsgToSend.setText("");
                                g.a = "";
                            }
                        });

                    } else if (data[2].equals("LoggingSuccess")) {
                        System.out.println("mety ny logging");
                    }
                }
            } catch (Exception ex) {
            }
        }
    }

    /* message prive */
    public static class messagePrive extends JPanel {

        public static final JButton envoyer = new JButton("Envoyer");
        private final JPanel panelAmbany = new JPanel();
        private final JPanel panelCentre = new JPanel();
        private final JPanel panelPrincipal = new JPanel();
        private final JPanel panelCentreHavia = new JPanel();
        private final JPanel panelCentreHavanana = new JPanel();
        public static JTextArea clt = new JTextArea();
        public static JTextArea textArea1 = new JTextArea();
        public static String textWriten;

        public messagePrive() {
            this.setBackground(Color.gray);

            this.add(panelPrincipal);
            panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

            panelCentre.setBackground(Color.WHITE);
            panelCentre.setBorder(new LineBorder(Color.BLACK, 3));

            panelCentre.setPreferredSize(new Dimension(230, 300));

            panelCentre.setLayout(new BoxLayout(panelCentre, BoxLayout.X_AXIS));
            panelCentreHavia.setBackground(Color.red);
            panelCentreHavanana.setBackground(Color.BLUE);

            JScrollPane scrollP = new JScrollPane(clt);
            clt.setEditable(false);
            panelCentre.add(scrollP);

            panelPrincipal.add(panelCentre);
            panelPrincipal.add(panelAmbany);

            JScrollPane scrollPane = new JScrollPane(textArea1);
            scrollPane.setPreferredSize(new Dimension(200, 50));
            panelAmbany.add(scrollPane);
            panelAmbany.add(envoyer);

            envoyer.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    send_private();
                }
            });
        }

        /* message a envoyer*/
        public static void send_private() {
            textWriten = textArea1.getText();
            try {
                writer.println(username + ":" + textWriten + ":" + "private" + ":" + liste.getSelectedValue());// selectionne lapersonne qui va etre le destinataire du msg 
                writer.flush();
                clt.append(liste.getSelectedValue() + ": " + textWriten + "\n");
                textArea1.setText("");
            } catch (Exception ex) {
                clt.append("Message n'est pas envoyer. \n");
            }
        }
        /*fin mesg a envoyer*/
    }

    /**
     * ************* begin creation account*********************
     */
    public static class CreationCompte extends JFrame {

        private JLabel entete = new JLabel("Incription");
        private JLabel nom = new JLabel("Name");
        private JLabel prenom = new JLabel("Prenoms");
        private JLabel pass = new JLabel("Password");
        private JLabel confirmePass = new JLabel("Confirme MDP");
        private JLabel inscrit = new JLabel("Inscrire");
        private JPanel image = new JPanel();
        private JTextField textNom = new JTextField();
        private JTextField textPrenom = new JTextField();
        private JPasswordField textPass = new JPasswordField();
        private JPasswordField textConfirmePass = new JPasswordField();
        private JButton file = new JButton("File");

        private final JPanel panelPrincipal = (JPanel) this.getContentPane();
        private final JPanel panelAmbony = new JPanel();
        public static final JPanel panelCentreInscription = new JPanel();
//        private final JPanel panelAmbany = new JPanel();

        public static String passwords;
        public static String nomUser;

        public CreationCompte() {
            this.setResizable(true);
            this.setTitle("CREATE ACCOUNT");
            this.setSize(400, 500);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setLocationRelativeTo(null);

            file.setEnabled(false);
            textNom.setPreferredSize(new Dimension(200, 24));
            textPrenom.setPreferredSize(new Dimension(200, 24));
            textPass.setPreferredSize(new Dimension(200, 24));
            textConfirmePass.setPreferredSize(new Dimension(200, 24));
            image.setPreferredSize(new Dimension(100, 100));
            image.setBackground(Color.ORANGE);
            file.setBackground(Color.CYAN);
            file.setPreferredSize(new Dimension(100, 24));

            panelAmbony.setBackground(Color.WHITE);
            panelAmbony.add(entete);
            panelPrincipal.add(panelAmbony, BorderLayout.NORTH);

            panelPrincipal.add(panelCentreInscription, BorderLayout.CENTER);
            panelCentreInscription.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(5, 5, 15, 5);

            c.weightx = 5;
            c.gridx = 0;
            c.gridy = 0;
            panelCentreInscription.add(nom, c);
            c.gridx = 1;
            c.gridy = 0;
            panelCentreInscription.add(textNom, c);

            c.gridx = 0;
            c.gridy = 1;
            panelCentreInscription.add(prenom, c);
            c.gridx = 1;
            c.gridy = 1;
            panelCentreInscription.add(textPrenom, c);

            c.gridx = 0;
            c.gridy = 2;
            panelCentreInscription.add(pass, c);
            c.gridx = 1;
            c.gridy = 2;
            panelCentreInscription.add(textPass, c);

            c.gridx = 0;
            c.gridy = 3;
            panelCentreInscription.add(confirmePass, c);
            c.gridx = 1;
            c.gridy = 3;
//        panelCentre.add(textConfirmePass, c);

            c.fill = GridBagConstraints.NORTH;
            c.gridx = 0;
            c.gridy = 4;
            c.gridwidth = 3;

            panelCentreInscription.add(image, c);
            c.gridx = 0;
            c.gridy = 5;

            panelCentreInscription.add(file, c);
            c.gridx = 0;
            c.gridy = 6;
            panelCentreInscription.add(inscrit, c);
            inscrit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            file.setCursor(new Cursor(Cursor.HAND_CURSOR));

            inscrit.addMouseListener((new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    passwords = String.valueOf(textPass.getPassword());
                    nomUser = textNom.getText();

                    if (textNom.getText().equals("")) {
                        JOptionPane.showMessageDialog(panelPrincipal, "Add at least one name", "Notification", JOptionPane.ERROR_MESSAGE);

                    }
                    if (passwords.equals("")) {
                        JOptionPane.showMessageDialog(panelPrincipal, "You must have a password", "Notification", JOptionPane.ERROR_MESSAGE);

                    } else {
//                        try {//-------------------------------------------registre to the database
                        socketConnection(0, textPrenom.getText(), nomUser, passwords, null, null);//(int option, String firstname, String username, String mdp)
                        ListenThread();
                        JOptionPane.showMessageDialog(panelPrincipal, "Votre information est enregistré\n LOGGING : " + nomUser + "\n PASSWORDS : " + passwords + " ", "Notification", JOptionPane.INFORMATION_MESSAGE);
                        Loging.adresse.setText(nomUser);
                        Loging l = new Loging();
                        l.setVisible(true);
                        dispose();
                    }
                }
            }));
        }
    }

    /**
     * ********* end creation account **************
     */

    /* beging logging */
    /**
     * ***************************************************************
     */
    public static class Loging extends JFrame {

        private final JPanel panelPrincipal = (JPanel) this.getContentPane();
        private final JPanel panelAmbony = new JPanel();
        private final JPanel panelCentre = new JPanel();
        private final JPanel panelAmbany = new JPanel();
        public static final JTextField adresse = new JTextField();
        public static final JPasswordField pass = new JPasswordField();
        private JLabel nomadresse = new JLabel("Address");
        private JLabel nompass = new JLabel("Password");
        private JLabel create = new JLabel("Create");
        private JLabel copp = new JLabel("EA2I");
        private JLabel login = new JLabel("Logging");
        private JButton connection = new JButton("Connecter");

        public Loging() {
            // interface loging
            this.setResizable(false);
            this.setTitle("Logging");
            this.setSize(400, 300);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setLocationRelativeTo(null);

            login.setFont(login.getFont().deriveFont(Font.BOLD, 44));
            login.setForeground(Color.WHITE);

            adresse.setPreferredSize(new Dimension(200, 24));
            pass.setPreferredSize(new Dimension(200, 24));
            connection.setPreferredSize(new Dimension(200, 24));

            //panel ambony maneho "Login"
            panelAmbony.setBackground(Color.red);
            panelPrincipal.add(panelAmbony, BorderLayout.NORTH);
            panelAmbony.add(login);

            panelPrincipal.add(panelCentre, BorderLayout.CENTER);
            panelCentre.setBackground(Color.GRAY);
            panelCentre.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            //les composant new Insets(margeSupérieure, margeGauche, margeInférieur, margeDroite)
            c.insets = new Insets(5, 5, 15, 5);

            c.weightx = 5;
            c.gridx = 0;
            c.gridy = 0;
            panelCentre.add(nomadresse, c);
            c.gridx = 1;
            c.gridy = 0;
            panelCentre.add(adresse, c);
            c.gridx = 0;
            c.gridy = 1;
            panelCentre.add(nompass, c);
            c.gridx = 1;
            c.gridy = 1;
            panelCentre.add(pass, c);

            c.fill = GridBagConstraints.NORTH;
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth = 3;
            panelCentre.add(connection, c);

            c.gridy = 4;
            panelCentre.add(create, c);

            // panel Ambany
            panelAmbany.setBackground(Color.BLACK);
            panelPrincipal.add(panelAmbany, BorderLayout.SOUTH);
            panelAmbany.add(copp);

            connection.addActionListener(//---------------------------------------------- mampiasa base de donnee
                    new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String mdp = String.valueOf(pass.getPassword());
                            username = adresse.getText();
                            nNom.setText(username);
                            profil.setText(username);
                            ListenThread();
                            socketConnection(1, username, username, mdp, null, null);// ona pas besion des autres parametre et c'est pour cella qu'on a juste fait "null"
                            InterfaceFinal a = new InterfaceFinal();
                            a.setVisible(true);
                            dispose();
                        }
                    }
            );
            /**
             * **************************************
             */
            create.setForeground(Color.WHITE);
            copp.setForeground(Color.WHITE);
            create.setCursor(new Cursor(Cursor.HAND_CURSOR));
            connection.setCursor(new Cursor(Cursor.HAND_CURSOR));

            create.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    CreationCompte a = new CreationCompte();

                    a.setVisible(true);
                    dispose();
                }
            });
        }
    }
    /* end loging*/

    public static class Group extends JFrame {

        JPanel mainFrame = (JPanel) getContentPane();
        JPanel panelAreaCenter = new JPanel();
        JPanel panelEastList = new JPanel(new BorderLayout());
        JPanel panelWestOption = new JPanel();
        JPanel panelSouthSend = new JPanel(new BorderLayout());
        JPanel panelNorthGroupName = new JPanel();
        JPanel panelbtnOption = new JPanel(new FlowLayout());
        JScrollPane jScrollPane;
        public static JList listGp;
        public static DefaultListModel listModelGp;
        public static JButton btnaddPeople = new JButton("ADD  ");
        public static JButton btnremovePeople = new JButton("REMOVE ");
        public static JButton btnsendGroup = new JButton("SEND");
        public static JTextField fieldAddPeople = new JTextField();
        public static JTextField textFieldMsgToSend = new JTextField();

        public static JTextArea txtAreaGroup = new JTextArea();
        JPanel panelTextAreaGroup = new JPanel(new BorderLayout());
        ArrayList<String> elementFromList = new ArrayList<>();
        String a = "";// used to get  list of participate in the " elementFromList "

        public Group() {
            super();
            pack();
            this.setSize(700, 500);
//            fieldAddPeople.setPreferredSize(new Dimension(100, 24));
            mainFrame.setLayout(new BorderLayout());
            listModelGp = new DefaultListModel();
            listGp = new JList(listModelGp);
            panelEastList.add(new JScrollPane(listGp), BorderLayout.CENTER);

            mainFrame.add(panelEastList, BorderLayout.EAST);
//            listModelGp.addElement("mety");
//            listModelGp.addElement("mety");

            panelAreaCenter.setBackground(Color.GRAY);
            panelEastList.setBackground(Color.WHITE);

            panelWestOption.setLayout(new BorderLayout());
            panelWestOption.setBackground(Color.GRAY);
            panelWestOption.add(fieldAddPeople, BorderLayout.NORTH);

            panelbtnOption.add(btnaddPeople);
            panelbtnOption.add(btnremovePeople);
//            panelbtnOption.setBackground(Color.GRAY);
            panelWestOption.add(panelbtnOption, BorderLayout.CENTER);
            mainFrame.add(panelWestOption, BorderLayout.WEST);

            mainFrame.add(panelAreaCenter, BorderLayout.CENTER);

            panelSouthSend.add(textFieldMsgToSend, BorderLayout.CENTER);
            panelSouthSend.add(btnsendGroup, BorderLayout.EAST);
            mainFrame.add(panelSouthSend, BorderLayout.SOUTH);

            Font font1 = new Font("Consolas", Font.TRUETYPE_FONT, 16);
            txtAreaGroup.setFont(font1);
            txtAreaGroup.setForeground(Color.WHITE);
            txtAreaGroup.setBackground(Color.GRAY);
            panelTextAreaGroup.add(txtAreaGroup, BorderLayout.CENTER);

            panelTextAreaGroup.add(new JScrollPane(txtAreaGroup));
            mainFrame.add(panelTextAreaGroup, BorderLayout.CENTER);

            String[] participate = textGroup.getText().split(","); //------------adding participate to the groupeList avy any anaty jtextField tam creation du groupe
            for (String participate1 : participate) {
                listModelGp.addElement(participate1);
                elementFromList.add(participate1);
            }

            btnaddPeople.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    add_group();
                }
            });

            btnremovePeople.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove_group(fieldAddPeople.getText());
                }
            });

            listGp.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent E) {
                    String nameSelected = listGp.getSelectedValue().toString();// afficher le nom selectionné depuis le jlist dans le jtextField de moditication(ajout ou suppression)
                    fieldAddPeople.setText(nameSelected);
                }
            });

            btnsendGroup.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    for (String elementFromList1 : elementFromList) {
                        a = a + elementFromList1 + ",";
                    }
                    a = a.substring(0, a.length() - 1);//utiliser pour enlever la derniere virgule dans la "elementFromList"
//                    writer.println(username + ":" + textFieldMsgToSend + ":" + "group" + ":" + a)
                    socketConnection(2, null, username, null, textFieldMsgToSend.getText(), a);//int option, String firstname, String username, String mdp,String textFieldMsgToSendSocket, String participate)
                    writer.flush();
                    txtAreaGroup.append(textFieldMsgToSend.getText() + "\n");
                    textFieldMsgToSend.setText("");
                    a = "";
                }
            });

        }

        public void add_group() {
//            String textWriten = textFieldMsgToSend.getText();

            String[] participate = fieldAddPeople.getText().split(",");

            for (String participate1 : participate) {
                listModelGp.addElement(participate1);
                elementFromList.add(participate1);
                validate();
            }
            fieldAddPeople.setText("");
        }

        public void remove_group(String name) {
            String nameToRemove = fieldAddPeople.getText();
            fieldAddPeople.setText(nameToRemove);
            listModelGp.removeElement(nameToRemove);
            elementFromList.remove(nameToRemove);
            fieldAddPeople.setText("");
        }
    }
    /* fin groupe*/

}

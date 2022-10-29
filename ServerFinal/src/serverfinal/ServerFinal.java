/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverfinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Mcrai Laydam
 */
public class ServerFinal extends JFrame {

    /**
     * @param args the command line arguments
     */
    JPanel mainFrame = (JPanel) getContentPane();

    JPanel panelTextArea = new JPanel(new BorderLayout());
    JPanel panelTextAreaServer = new JPanel(new BorderLayout());
    JPanel panelList = new JPanel(new BorderLayout());
    JPanel panelOnline = new JPanel(new BorderLayout());
    JPanel panelListAndOnline = new JPanel(new GridLayout(2, 1));
    JPanel panelBtnList = new JPanel(new FlowLayout());

//    JButton btnStart = new JButton("START SERVER");
//    JButton btnStop = new JButton("STOP SERVER");
    JButton btnOnline = new JButton("START SERVER");
    JButton btnRegister = new JButton("REGISTER USERS");
    public static JTextArea textArea = new JTextArea();
    public static JTextArea textAreaServer = new JTextArea();
    JScrollPane jScrollPane;
    public static JList list;
    public static DefaultListModel listModel;
    public static JList listOnline;
    public static DefaultListModel listModelOnline;
    static Statement stmt;
    static Connection con;

    ArrayList<PrintWriter> clientOutputStreams = new ArrayList<>();
    ArrayList<String> users = new ArrayList<>();
    ArrayList< String> usersSubscribe = new ArrayList<>();

    public ServerFinal() {

        super();
        pack();
        try {
            Database.getConnection();
            Database.creationDB();
            Database.creationTableDB();
        } catch (Exception ex) {
            ex.getMessage();
        }

        mainFrame.setLayout(new BorderLayout());
        Font font1 = new Font("Consolas", Font.TRUETYPE_FONT, 16);
        textArea.setFont(font1);
        textArea.setForeground(Color.GREEN);
        textArea.setBackground(Color.BLACK);
        panelTextArea.add(textArea, BorderLayout.CENTER);

        panelTextArea.add(new JScrollPane(textArea));
        mainFrame.add(panelTextArea, BorderLayout.CENTER);

//        panelBtnList.add(btnStart);
//        panelBtnList.add(btnStop);
//panelBtnList.add(new JButton("USERS SUBSCRIBED"));
        panelList.add(btnRegister, BorderLayout.NORTH);
        panelList.add(btnOnline, BorderLayout.SOUTH);

        panelListAndOnline.add(panelList);
        panelListAndOnline.add(panelOnline);
        mainFrame.add(panelListAndOnline, BorderLayout.EAST);
//        mainFrame.add(panelList, BorderLayout.EAST);

        listModel = new DefaultListModel();
        list = new JList(listModel);
        panelList.add(new JScrollPane(list));

        listModelOnline = new DefaultListModel();
        listOnline = new JList(listModelOnline);
        panelOnline.add(new JScrollPane(listOnline));

        ButtonListener listener = new ButtonListener();
//        btnStart.addActionListener(listener);
//        btnStop.addActionListener(listener);
        btnOnline.addActionListener(listener);
    }

    /**
     * ***** BUTTON LISTENER *******
     */
    public class ButtonListener implements ActionListener {

        Thread t;

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jb = (JButton) e.getSource();

//            if (jb == btnStart) {
//                textArea.setText("");
//                textArea.setEditable(false);
//
//            }
//            if (jb == btnStop) {
//
//            }
            if (jb == btnOnline) {
                Thread starter = new Thread(new ServerStart());
                starter.start();
                textArea.setEditable(false);
                textArea.append(" Server started \n Waiting for users connection ...");
                listModel.removeAllElements();
                for (String userActif : users) {
                    listModel.addElement(userActif);
                }
            }
        }

    }

    public class ClientHandler implements Runnable {

        BufferedReader reader;
        Socket sock;
        PrintWriter client;
//        int id_privateChat;

        public ClientHandler(Socket clientSocket, PrintWriter user) {
            client = user;
            try {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            } catch (Exception ex) {
                textArea.append("connection error... \n");
            }
        }

        @Override
        public void run() {
            String message, connect = "Connect", deconnect = "Disconnect", chat = "Chat", privatemsg = "private", inscription = "inscription", group = "group";
            String[] data;

            try {
                while ((message = reader.readLine()) != null) {

                    textArea.append("\nReceived : " + message + "---\n");
                    data = message.split(":");
//                    int counter2steps = 0;
//                    for (String token : data) {
//                        if (counter2steps != 2) {
//                            textArea.append(token + " ");
//                        }
//                        counter2steps++;
//                    }
                    if (data[2].equals(connect)) {
                        publication((data[0] + ":" + data[1] + ":" + chat));
                        userAdd(data[0]);
                        System.out.println("tonga ny userAdd");
                        textArea.append("username : " + data[0] + "\n");
                        System.out.println("tonga ny eto am append");

                    } else if (data[2].equals(deconnect)) {
                        publication((data[0] + "  has :Disconnected" + ":" + chat));
                        clientOutputStreams.remove(getid(data[0]));
                        userRemove(data[0]);

                    } else if (data[2].equals(chat)) {
                        publication(message);

                    } else if (data[2].equals(inscription)) { //nom: want to subscribe:inscription:password:prenom--------------------------inscription database
                        //  pourdb writer.println(username + ": want to subscribe :inscription:" + mdp + ":" + firstname);
                        Integer id = 1;
                        String noniscription = data[0], prenominscription = data[4], motdp = String.valueOf(data[3]), login = data[0];
                        try {
                            // recuperation de numero de la dernier identifiant qui existe dans la base 
                            String recupere_id = "SELECT max(id) as NAME FROM REGISTRATIONS";
                            ResultSet rs = stmt.executeQuery(recupere_id);
                            while (rs.next()) {
                                id = rs.getInt("ID");
                                System.out.println(id);
                            }
                        } catch (Exception e) {
                        }
                        try {
                            // incrementation d'identifiant
                            id++;
                            Database.insertDataDB(id, noniscription, prenominscription, login, motdp);
                            System.out.println("inscription succes");
                            ServerFinal.textArea.append("personne enregistrer");
                        } catch (Exception e) {
                            System.out.println("erreur inscription " + e.getLocalizedMessage());
                        }
                        id++;
                        System.out.println("tonga ny trytoconnect");
                        usersSubscribe.add(data[0]);

                        int userid = usersSubscribe.indexOf(data[0]);
                        PrintWriter writer = clientOutputStreams.get(userid); //clientOutputStreams[personid];   //(PrintWriter) it.next();
                        textArea.append("inscription mety ,ny writer dia:" + writer);
                        String inscriptionSuccess = data[0] + ": :inscriptionSuccess";
                        writer.println(inscriptionSuccess);

                    } else if (data[2].equals(privatemsg)) {//  writer.println(username + ":" + textWriten + ":" + "private" + ":" + liste.getSelectedValue());

                        int recievedID = getid(data[3]);

                        if (recievedID != -1) {
                            privateMessage(message, recievedID, data[3]);
                        } else {
                            privateMessage(message, recievedID, data[0]);
                        }
                    } else if (data[2].equals(group)) {
                        textArea.append("metyyyyyyyy ny groupe " + message);
                        String[] participateGroup = data[3].toString().split(",");
                        for (String participateGroup1 : participateGroup) {
                            int recievedID = getid(participateGroup1);
                            groupMessage(message, recievedID, participateGroup1, data[3]);// groupMessage(String msg, int personid, String recievername,String allParticipate) {
                        }
                    } else {
                        textArea.append("No Conditions. \n");
                    }
                }
            } catch (Exception ex) {
                textArea.append("connection perdue. \n");
            }
        }
    }
    /*methodes*/

    public int getid(String data) {
        int userid = users.indexOf(data);
        return userid;
    }

    public void userAdd(String data) {
        String message, s = ": :Connect", ok = "Server: :Done", name = data;
        users.add(name);
        for (String token : users) {
            message = (token + s);
            publication(message);
        }
        publication(ok);
    }

    public void userRemove(String data) {
        @SuppressWarnings("unused")
        String message, s = ": :Disconnect", done = "Server: :Done", name = data;
        users.remove(name);
        for (String UserName : users) {
            message = (UserName + s);
            publication(message);
        }

    }

    public void privateMessage(String msg, int personid, String recievername) {//  writer.println(username + ":" + textWriten + ":" + "private" + ":" + liste.getSelectedValue());
//  privateMessage(message, recievedID, data[0]); ou  //  privateMessage(message, recievedID, data[3]);
        if (personid == -1) {

            msg = " Server : The User is Not Found in the online :private";
            personid = getid(recievername);
            try {
                PrintWriter writer = clientOutputStreams.get(personid);   //(PrintWriter) it.next();
                writer.println(msg);
                writer.flush();
                textArea.append("Sending error to {" + recievername + "}  User not found in the online Users \n");
                textArea.setCaretPosition(textArea.getDocument().getLength());
            } catch (Exception ex) {
                textArea.append("Error in telling this to " + recievername + "." + "\n");
            }

        } else {

            if (clientOutputStreams.get(personid) != null) {

                try {
                    PrintWriter writer = clientOutputStreams.get(personid); //clientOutputStreams[personid];   //(PrintWriter) it.next();
                    writer.println(msg);
                    writer.flush();
                    textArea.append("Sending to {" + recievername + "} only this msg :  " + msg + "\n");
                    textArea.setCaretPosition(textArea.getDocument().getLength());
                } catch (Exception ex) {
                    textArea.append("Error in telling this " + recievername + "." + "\n");
                }
            } else {
                textArea.append("Error ...ID not found" + recievername + "." + "\n");
            }
        }
    }

    public void groupMessage(String msg, int personid, String recievername, String allParticipate) {
        if (personid == -1) {
            msg = " Server : The participate is Not Found in the online :group";
            personid = getid(recievername);
            try {
                PrintWriter writer = clientOutputStreams.get(personid);   //(PrintWriter) it.next();
                writer.println(msg);
                writer.flush();
                textArea.append("Sending error to {" + allParticipate + "}  participate not found in the online Users \n");
                textArea.setCaretPosition(textArea.getDocument().getLength());
            } catch (Exception ex) {
                textArea.append("Error in telling this to " + recievername + "." + "\n");
            }

        } else {

            if (clientOutputStreams.get(personid) != null) {

                try {
                    PrintWriter writer = clientOutputStreams.get(personid); //clientOutputStreams[personid];   //(PrintWriter) it.next();
                    writer.println(msg);
                    writer.flush();
                    textArea.append("Sending to {" + allParticipate + "} this group msg :  " + msg + "\n");
                    textArea.setCaretPosition(textArea.getDocument().getLength());
                } catch (Exception ex) {
                    textArea.append("Error in telling this " + recievername + "." + "\n");
                }
            } else {
                textArea.append("Error ...ID not found" + recievername + "." + "\n");
            }
        }

    }

    public void publication(String message) {
        @SuppressWarnings("rawtypes")
        Iterator it = clientOutputStreams.iterator(); // itrator for looping 

        while (it.hasNext()) // for(int i=0 ; i<clientOutputStreams.length ;i++)
        {
            try {
                PrintWriter writer = (PrintWriter) it.next();  //clientOutputStreams[i];   //(PrintWriter) it.next();
                writer.println(message);
                writer.flush();
                textArea.setCaretPosition(textArea.getDocument().getLength());

            } catch (Exception ex) {
                textArea.append("Error to publish. \n");
            }
        }
    }
    /* server start */

    public class ServerStart implements Runnable {

        @Override
        public void run() {

            try {
                ServerSocket serverSock = new ServerSocket(6000);
                System.out.println("mandeha ato ve");
                while (true) {
                    Socket clientSock = serverSock.accept();
                    PrintWriter writer = new PrintWriter(clientSock.getOutputStream());

                    System.out.println("met ve");
                    clientOutputStreams.add(writer);
                    Thread listener = new Thread(new ClientHandler(clientSock, writer));
                    System.out.println("tonga ve ny thread listener");
                    listener.start();
                    textArea.append("connected. \n");//--got connection

                }
            } catch (Exception ex) {
                textArea.append("Error making a connection. \n");
            }
        }
    }

    public static void main(String[] args) {
        ServerFinal s = new serverfinal.ServerFinal();
        s.setVisible(true);
        s.setSize(700, 500);
    }
}

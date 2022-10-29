/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverfinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mcrai Laydam
 */
public class Database {

    static Statement stmt;
    static Connection con;
//
//    public Database() {
//    }

    public static Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection("jdbc:h2:E:\\database\\informatique\\Database_Chat" + "./Data/mainDatabaseChat", "admin", "admin");
            return con;
        } catch (Exception e) {
            return null;
        }
    }

    public static void creationDB() throws Exception {
        System.out.println("Create a database... ");
        Class.forName("org.h2.Driver");
        con = DriverManager.getConnection("jdbc:h2:E:\\database\\informatique\\Database_Chat" + "./Data/mainDatabaseChat", "admin", "admin");
        System.out.println("database created");

    }

    public static void creationTableDB() {
        try {
            stmt = con.createStatement();
            String sql
                    =  "CREATE TABLE REGISTRATIONS( ID INTEGER AUTO_INCREMENT,"
                    + "NAME VARCHAR(255) NOT NULL , " //nampiana
                    + "PRENOM VARCHAR(255), "
                    + "  LOGIN VARCHAR(255), "
                    + " MOTDP VARCHAR(255), "
                    + "PRIMARY KEY(ID) )";

//            String dropTable = "DROP TABLE IF EXISTS REGISTRATIONS";
//           stmt.executeUpdate(dropTable);
//            System.out.println("Drop success");
//            stmt.executeUpdate(sql);
            System.out.println("Table created");

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void insertDataDB(Integer a, String nom, String prenom, String login, String mdp) {
        try {
            String insertTable = "INSERT INTO REGISTRATIONS  VALUES ('" +a
                    + "','" + nom + "','" + prenom + "','" + login + "','" + mdp + "')";
            System.out.println("Insert int table loading...");
            stmt.executeUpdate(insertTable);

            System.out.println("Insert Successfully");
            ServerFinal.textArea.append("Account :\"" + login + "\" was registed in database successfully\n");
        } catch (Exception e) {
            System.out.println("ato erreur " + e.getLocalizedMessage());
            System.out.println(e.getMessage());
        }
    }

    public static void searchFromDB(String loginn, String password) {
        String request = "SELECT * FROM REGISTRATIONs WHERE LOGIN='" + loginn + "' AND MOTDP='" + password + "' ";
        PreparedStatement pr;
        ResultSet rs;
        try {

            pr = con.prepareStatement(request);
            rs = pr.executeQuery();

            while (rs.next()) {
//                ServerFinal.connectionDB = true;
                System.out.println("login depuis la DB est " + loginn + "\b son MDP est " + password);
                ServerFinal.textArea.append("Account :" + loginn + " was logged in successfully");
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
}

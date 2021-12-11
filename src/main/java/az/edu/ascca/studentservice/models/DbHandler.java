/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package az.edu.ascca.studentservice.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Tunjay.Habibbayli
 */
public class DbHandler {

    private static DbHandler handler = new DbHandler();

    private Connection dbConn;

    private DbHandler() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("mysql success");
            dbConn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/teamdgka?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "teamdgka", "teamdgka");
            System.out.println("dbconn - " + dbConn.getSchema());

        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("connection error ");
            ex.printStackTrace();
        }
    }

    public static DbHandler getDbHandler() {
        return handler;
    }

    public Connection getConnection() {
        return dbConn;
    }
}

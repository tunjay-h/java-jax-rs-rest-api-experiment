/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package az.edu.ascca.studentservice.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tunjay.Habibbayli
 */
public class StudentDao {
    
    public List<Student> getAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        
        try {
            Connection dbConn = DbHandler.getDbHandler().getConnection();
            Statement stmt = dbConn.createStatement();
            
            ResultSet results = stmt.executeQuery("SELECT * FROM STUDENTS");
            while (results.next()) {                
                int id = results.getInt(1);
                String firstname = results.getString(2);
                String lastname = results.getString(3);
                
                Student st = new Student(id, firstname, lastname);
                allStudents.add(st);
            }
            
//        hibernate.getList<Student>() ORM, Hibernate myBatis, Spring Data
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return allStudents;
    }
    
    public Student getStudentById(int id) {
        Student st = null;
        
        try {
            Connection dbConn = DbHandler.getDbHandler().getConnection();
            Statement stmt = dbConn.createStatement();
            
            ResultSet results = stmt.executeQuery("SELECT * FROM STUDENTS WHERE id = " + id); // sql injection ola biler
            
            if (results.first()) {
                int res_id = results.getInt(1);
                String firstname = results.getString(2);
                String lastname = results.getString(3);
                
                st = new Student(res_id, firstname, lastname);
            } else {
                st = null;
            }
            
//        hibernate.getList<Student>() ORM, Hibernate myBatis, Spring Data
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return st;
    }
    
    
    public boolean createNewStudent(String firstname, String lastname) {
        try {
            Connection dbConn = DbHandler.getDbHandler().getConnection();
            PreparedStatement prestmt = dbConn.prepareStatement("INSERT INTO STUDENTS(firstname, lastname) VALUES(?, ?)");
            prestmt.setString(1, firstname);
            prestmt.setString(2, lastname);

            int rowsAffected = prestmt.executeUpdate();
            
            if (rowsAffected != 0) {
                return true;
            } else {
                return false;
            }
            
//        hibernate.getList<Student>() ORM, Hibernate myBatis, Spring Data
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}

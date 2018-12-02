/*
 * Created on : 17/11/2018, 11:52:37
 * @author : Douglas Souza 
 */
package br.com.douglas.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Douglas Souza
 */
public class ConnectionFactory {
    
    private static final String BD_URL = "jdbc:mysql://localhost:3306/cadastro?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection() {
        try {
            //Class.forName(DRIVER);
            return DriverManager.getConnection(BD_URL, USER, PASS);
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);  
        }
    }
    
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            } 
        }   catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }       
    }
    
    public static void closeConnection(Connection connection, PreparedStatement stmt) {
        closeConnection(connection);
        
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement stmt, ResultSet rs) {
        closeConnection(connection, stmt);
        
        try {
           if(rs != null) {
               rs.close();
            }
        } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
       
}

package br.com.douglas.connection;

import java.sql.*;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class ConnectionFactory {

    static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());

    static final String DB_URL = "jdbc:mysql://localhost:3306/cadastro?useSSL=FALSE";

    static final String USER = "root";

    static final String PASS = "root";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            LOGGER.log(SEVERE, null, ex);
        }

    }

    public static void closeConnection(Connection connection, PreparedStatement stmt) {
        closeConnection(connection);

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            LOGGER.log(SEVERE, null, ex);
        }

    }

    public static void closeConnection(Connection connection, PreparedStatement stmt, ResultSet rs) {
        closeConnection(connection, stmt);

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            LOGGER.log(SEVERE, null, ex);
        }

    }
}

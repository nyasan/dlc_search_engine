/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 *
 * @author Fabricio Simoncelli
 */
public final class DBUtils {

    private DBUtils() {
        throw new AssertionError("Utility classes should not be instantiated");
    }

    private static final Logger LOGGER = Logger.getLogger("DBUtils");

    /*
     * The method creates a Connection object. Loads the embedded driver, starts and connects to the database
     * using the connection URL.
     */
    public static Connection createDatabaseConnection() throws SQLException, ClassNotFoundException {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        Class.forName(driver);
        String url = "jdbc:derby:VocabularioDB;create=true";
        Connection connection = DriverManager.getConnection(url);
        return connection;
    }

    public static void main(String[] arg) throws SQLException, ClassNotFoundException {
        try (Connection conn = createDatabaseConnection()) {
            createTablePalabra(conn);
            createTableDocumento(conn);
            createTableDocumentosXPalabra(conn);
        } catch (SQLException ex) {
            LOGGER.severe("Database ya existe");
        }
    }

    private static void createTablePalabra(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table PALABRA(nombre varchar(80), frecuencia int, PRIMARY KEY (nombre))");
        }
    }
    private static void createTableDocumento(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table DOCUMENTO(nombre varchar(150), PRIMARY KEY (nombre))");
        }
    }
    private static void createTableDocumentosXPalabra(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table DOCUMENTOSXPALABRA(palabra varchar(80), documento varchar(150), PRIMARY KEY (palabra,documento))");
        }
    }
}

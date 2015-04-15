/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Establece una conexión con una base de datos que utilize a modo de prueba durante el desarrollo del tp
 * y con los fines de implementar el patrón dao
 * 
 * @author Fabricio Simoncelli
 */
public class ClientDBUtils {
    public static Connection createDatabaseConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/vocabularyTest");
        return conn;
    }
}

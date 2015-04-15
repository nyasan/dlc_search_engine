/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fabricio Simoncelli
 */
public final class ClientDBService implements VocabularioDAO {

    public static Map<String, Integer> mapaPalabras;

    @Override
    public void insertarBD(Map<String, Integer> map, String documento) throws SQLException, ClassNotFoundException {
        
        String insertPalabra = "insert into palabra values (?,?)";
        PreparedStatement psInsertPalabra;
        String insertDocumentosXPalabra = "insert into documentosxpalabra values (?,?)";
        PreparedStatement psInsertDocumentosXPalabra;
        String actualizarPalabra = "update palabra set frecuencia = frecuencia + ? where nombre = ?";
        PreparedStatement psActualizarPalabra;

        final int batchSize = 100;
        int count1, count2, count3;
        count1 = count2 = count3 = 1;
        leerBD();
//        String insertDocument = "insert into documento values (?)";
//        PreparedStatement stmtInsertDocument;

        Connection con = ClientDBUtils.createDatabaseConnection();
        con.setAutoCommit(false);

        psInsertPalabra = con.prepareStatement(insertPalabra);

        psActualizarPalabra = con.prepareStatement(actualizarPalabra);

        psInsertDocumentosXPalabra = con.prepareStatement(insertDocumentosXPalabra);

        if (!existeDocumento(documento)) {
            insertarDocumento(documento);

            for (Map.Entry<String, Integer> entrySet : map.entrySet()) {

                String palabra = entrySet.getKey();
                Integer frecuencia = entrySet.getValue();

                if (!existePalabra(palabra)) {
//                    insertarPalabra(palabra, frecuencia);
                    psInsertPalabra.setString(1, palabra);
                    psInsertPalabra.setInt(2, frecuencia);
                    psInsertPalabra.addBatch();
                    if (++count1 % batchSize == 0) {
                        psInsertPalabra.executeBatch();
                    }
                } else {
//                    actualizarPalabra(palabra, frecuencia);
                    psActualizarPalabra.setInt(1, frecuencia);
                    psActualizarPalabra.setString(2, palabra);
                    psActualizarPalabra.addBatch();
                    if (++count2 % batchSize == 0) {
                        psActualizarPalabra.executeBatch();
                    }
                }
//                insertarDocumentosXPalabra(palabra, documento);
                psInsertDocumentosXPalabra.setString(1, palabra);
                psInsertDocumentosXPalabra.setString(2, documento);
                psInsertDocumentosXPalabra.addBatch();
                if (++count3 % batchSize == 0) {
                    psInsertDocumentosXPalabra.executeBatch();
                }
            }
            // insertan los elementos sobrantes de los batchs

            psInsertPalabra.executeBatch();
            psInsertDocumentosXPalabra.executeBatch();
            psActualizarPalabra.executeBatch();

            con.commit(); // hace el commit y finaliza la transacción
        }
    }

    private static void insertarPalabra(String nombre, int frecuencia) throws SQLException, ClassNotFoundException {
        String query = "insert into palabra values ('" + nombre + "'," + frecuencia + ")";
        try (Connection conn = ClientDBUtils.createDatabaseConnection(); Statement stmt = conn.createStatement();) {
            stmt.execute(query);
        }
    }

    @Override
    public void insertarDocumento(String nombre) throws SQLException, ClassNotFoundException {
        String query = "insert into documento values ('" + nombre + "')";
        try (Connection conn = ClientDBUtils.createDatabaseConnection(); Statement stmt = conn.createStatement();) {
            stmt.execute(query);
        }
    }

    private static void insertarDocumentosXPalabra(String nombre, String documento) throws SQLException, ClassNotFoundException {
        String query = "insert into documentosxpalabra values ('" + nombre + "','" + documento + "')";
        try (Connection conn = ClientDBUtils.createDatabaseConnection(); Statement stmt = conn.createStatement();) {
            stmt.execute(query);
        }
    }

    private static void actualizarPalabra(String palabra, int frecuencia) throws SQLException, ClassNotFoundException {
        String query = "update palabra set cantidad = cantidad + " + frecuencia + " where palabra = '" + palabra + "'";
        try (Connection conn = ClientDBUtils.createDatabaseConnection(); Statement stmt = conn.createStatement();) {
            stmt.execute(query);
        }
    }

    @Override
    public boolean existeDocumento(String documento) throws SQLException, ClassNotFoundException {
        boolean existe = false;
        String query = "select * from documento where nombre = '" + documento + "'";
        try (Connection conn = ClientDBUtils.createDatabaseConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query);) {
            if (rs.next()) { //Si el documento ya existe en la BD entonces rs va a tener una entrada, sino estará vacío
                existe = true;
            }
        }
        return existe;
    }

    @Override
    public boolean existePalabra(String palabra) throws SQLException, ClassNotFoundException {
//        boolean existe = false;
//        String query = "select * from palabra where nombre = '" + palabra + "'";
//        try (Connection conn = ClientDBUtils.createDatabaseConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query);) {
//            if (rs.next()) { //Si la palabra ya existe en la BD entonces rs va a tener una entrada, sino estará vacío
//                existe = true;
//            }
//        }
//        return existe;
        boolean exist = false;
        if (mapaPalabras.containsKey(palabra)) {
            exist = true;
        }
        return exist;
    }

    @Override
    public List<String> obtenerDocumentos() throws SQLException, ClassNotFoundException {
        List<String> documentos = new ArrayList<>();
        String query = "select * from documento";
        try (Connection conn = ClientDBUtils.createDatabaseConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query);) {
            String aux;
            while (rs.next()) {
                aux = rs.getString(1);
                documentos.add(aux);
            }
        }
        return documentos;
    }

    @Override
    public DefaultTableModel obtenerDatosTabla() throws ClassNotFoundException, SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            /**
             * Sobreescribimos el método getColumnClass para que devuelva el
             * tipo de dato almacenado en cada columna así el sorter puede
             * ordenar los datos de la tabla. Si no lo sobreescribimos las
             * columnas con valores numéricos no se ordenan correctamente ya que
             * los compara como si fueran cadenas de caracteres y no como
             * números
             *
             * @param column
             * @return
             */
            @Override
            public Class getColumnClass(int column) {
                if (column == 0) {
                    return String.class;
                } else {
                    return Integer.class;
                }
            }
        };
        modelo.addColumn("Palabras");
        modelo.addColumn("Frecuencia");
        modelo.addColumn("Documentos");

        String query = "SELECT PALABRA.NOMBRE as palabra, PALABRA.FRECUENCIA as cantidad, COUNT(*) AS cantDoc "
                + "FROM PALABRA INNER JOIN DOCUMENTOSXPALABRA ON PALABRA.NOMBRE = DOCUMENTOSXPALABRA.PALABRA GROUP BY PALABRA.NOMBRE, PALABRA.FRECUENCIA";
        try (Connection conn = ClientDBUtils.createDatabaseConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query);) {
            while (rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getString("palabra");
                fila[1] = rs.getInt("cantidad");
                fila[2] = rs.getInt("cantDoc");
                modelo.addRow(fila);
            }
        }
        return modelo;

    }

    @Override
    public void leerBD() throws SQLException, ClassNotFoundException {
        mapaPalabras = new TreeMap<>();
        String query = "select * from PALABRA";
        try (Connection conn = ClientDBUtils.createDatabaseConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query);) {
            while (rs.next()) {
                mapaPalabras.put(rs.getString("nombre"), rs.getInt("frecuencia"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDBService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void limpiarBD() throws SQLException, ClassNotFoundException {
        String[] tablas = {"DOCUMENTOSXPALABRA", "PALABRA", "DOCUMENTO"};
        try (Connection conn = ClientDBUtils.createDatabaseConnection(); Statement stmt = conn.createStatement();) {
            for (String tb : tablas) {
                PreparedStatement p = conn.prepareStatement("DELETE FROM " + tb);
                p.execute();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientDBService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<String> obtenerDocumentosXPalabra(String palabra) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

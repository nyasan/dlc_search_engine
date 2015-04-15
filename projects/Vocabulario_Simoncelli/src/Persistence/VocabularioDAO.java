/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fabricio Simoncelli
 */
public interface VocabularioDAO {

    void insertarBD(Map<String, Integer> map, String documento) throws SQLException, ClassNotFoundException;

    void insertarDocumento(String nombre) throws SQLException, ClassNotFoundException;

    boolean existeDocumento(String documento) throws SQLException, ClassNotFoundException;

    boolean existePalabra(String palabra) throws SQLException, ClassNotFoundException ;
    
    List<String> obtenerDocumentosXPalabra(String palabra) throws SQLException, ClassNotFoundException;

    List<String> obtenerDocumentos() throws SQLException, ClassNotFoundException;

    DefaultTableModel obtenerDatosTabla() throws ClassNotFoundException, SQLException;

    void leerBD() throws ClassNotFoundException, SQLException;

    void limpiarBD() throws ClassNotFoundException, SQLException;
}

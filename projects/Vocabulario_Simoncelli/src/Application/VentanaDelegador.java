/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Persistence.ClientDBService;
import Persistence.DBService;
import Persistence.VocabularioDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fabricio Simoncelli
 */
public class VentanaDelegador {
        VocabularioDAO servicioPersistencia = new DBService();

    public void limpiarBaseDeDatos() throws SQLException, ClassNotFoundException {
        servicioPersistencia.limpiarBD();
    }

    public DefaultTableModel getDatosTablas() throws ClassNotFoundException, SQLException {
        return servicioPersistencia.obtenerDatosTabla();
    }

    public List<String> getDocumentosXPalabra(String palabra) throws SQLException, ClassNotFoundException {
        return servicioPersistencia.obtenerDocumentosXPalabra(palabra);
    }

    public List<String> getDocumentos() throws SQLException, ClassNotFoundException {
        return servicioPersistencia.obtenerDocumentos();
    }

    public void insertarBD(Map<String, Integer> mapa, String documento) throws SQLException, ClassNotFoundException {
        servicioPersistencia.insertarBD(mapa, documento);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Persistence.ClientDBService;
import Persistence.DBService;
import Persistence.VocabularioDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Fabricio Simoncelli
 */
public class Procesador {
    
    VocabularioDAO service = new DBService();
    
    public Map<String,Integer> procesar(String documento, String path) throws SQLException, ClassNotFoundException, IOException {
//        String doc = "41575-8.txt";
        File file = new File(path);
        Map<String, Integer> mapa = new HashMap<>();
        try {
            if (!service.existeDocumento(documento)) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    Scanner scan = new Scanner(fis).useDelimiter(Pattern.compile("[^áéñóíúüÁÉÑÓÍÚÜa-zA-Z]"));
                    while (scan.hasNext()) {
                        String aux = scan.next().toUpperCase();
                        if (aux.length() > 1) {
                            if (!mapa.containsKey(aux)) {
                                mapa.put(aux, 1);
                            } else {
                                mapa.put(aux, mapa.get(aux) + 1);
                            }
                        }
                    }
                    scan.close();
                }
            }            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Procesador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapa;
//        ClientDBService.insertarBD(mapa, documento);
    }
}

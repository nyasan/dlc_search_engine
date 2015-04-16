package dlc.search.engine.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public abstract class ProcessorUtils {

	/**
	 * Retorna un mapa con las palabras contenidas en un documento junto a la frecuencia de cada palabra
	 * @param file el archivo a procesar
	 * @return
	 */
	public static Map<String, Integer> process(File file) {
		Map<String, Integer> mapa = new TreeMap<>();	//new HashMap<>();
		
		try {
			try (FileInputStream fis = new FileInputStream(file)) {
				
				try (Scanner scan = new Scanner(fis).useDelimiter(Pattern
						.compile("[^áéñóíúüÁÉÑÓÍÚÜa-zA-Z]"))) {

					while (scan.hasNext()) {
						String aux = scan.next().toLowerCase();
						if (aux.length() > 1) {
							if (!mapa.containsKey(aux)) {
								mapa.put(aux, 1);
							} else {
								mapa.put(aux, mapa.get(aux) + 1);
							}
						}
					}
				}
			}
		} catch (IOException ex) {
			Logger.getLogger(ProcessorUtils.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		
		return mapa;
	}
	
	// DESCOMENTAR ESTA IMPLEMENTACION
	/*
	public static Map<String, Integer> process(String path) {
		return process(new File(path));
	}
	*/
	
	// DEBUG
	public static Map<String, Integer> process(String doc) {
		File f = new File("src/main/webapp/resources/docs/" + doc);
		return process(f);
	}
	
}

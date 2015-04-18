package dlc.search.engine.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ProcessorFile implements Processor {

	@Override
	public Map<String, Integer> process(String uri) {
		Map<String, Integer> mapa = new TreeMap<>();
		File file = new File(uri);
		
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
			Logger.getLogger(ProcessorFile.class.getName()).log(Level.ERROR,
					null, ex);
		}
		
		return mapa;
	}

}

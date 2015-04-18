package dlc.search.engine.domain;

import java.util.Map;

public interface Processor {
	
	/**
	 * Retorna un mapa con las palabras contenidas en un documento junto a la frecuencia de cada palabra
	 * @param uri del archivo a procesar
	 */
	Map<String, Integer> process(String uri);

}

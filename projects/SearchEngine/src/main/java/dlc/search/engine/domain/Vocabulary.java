package dlc.search.engine.domain;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Vocabulary {
	public static final int DOC_1 = 1;
	// Se puede implementar con HashMap o TreeMap(nos permite ordenar el vocabulario)
	private Map<String, VocabularyEntry> vocabulary;
	
	public Vocabulary() {
		vocabulary = new TreeMap<>();	//new HashMap<>();
	}

	public Map<String, VocabularyEntry> getVocabulary() {
		return vocabulary;
	}

	public void setVocabulary(Map<String, VocabularyEntry> vocabulary) {
		this.vocabulary = vocabulary;
	}
	
	private void addEntry(VocabularyEntry entry) {
		if(entry == null) {
			return;
		}
		
		VocabularyEntry auxEntry = vocabulary.get(entry.getWord());
		
		// verificar si es una nueva entrada o si ya estaba contenida en el vocabulario
		if(auxEntry == null) {
			auxEntry = new VocabularyEntry();
			auxEntry.setWord(entry.getWord());
			auxEntry.setMaxTf(entry.getMaxTf());
			auxEntry.setNr(DOC_1);
		} else {
			// verificar el tf maximo entre la nueva entrada al vocabulario y la que ya existia
			if(auxEntry.getMaxTf() < entry.getMaxTf()) {
				auxEntry.setMaxTf(entry.getMaxTf());
			}
			// sumar en 1 la cantidad de documentos con la entrada del vocabulario
			auxEntry.addNr();
		}
		
		vocabulary.put(auxEntry.getWord(), auxEntry);
	}
	
	public void removeEntry(VocabularyEntry entry) {
		if(entry != null) {
			vocabulary.remove(entry.getWord());
		}
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("Vocabulary:\n");
		
		for (VocabularyEntry entry : vocabulary.values()) {
			string.append(entry);
			string.append("\n");
		}
		
		return string.toString();		
	}
	
	public void addDocument(Map<String, Integer> document) {
		if(document == null) {
			return;
		}
		
		for (String word : document.keySet()) {
			VocabularyEntry entry = new VocabularyEntry();
			entry.setWord(word);
			entry.setMaxTf(document.get(word));
			addEntry(entry);
		}
	}

}

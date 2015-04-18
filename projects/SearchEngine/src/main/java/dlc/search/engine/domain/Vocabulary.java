package dlc.search.engine.domain;

import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Vocabulary {
	public static final int DOC_1 = 1;
	private Map<String, VocabularyEntry> vocabulary;
	
	private static final Logger logger = Logger.getLogger(Vocabulary.class); 
	
	public Vocabulary() {
		vocabulary = new TreeMap<>();
	}

	public Map<String, VocabularyEntry> getVocabulary() {
		return vocabulary;
	}

	public void setVocabulary(Map<String, VocabularyEntry> vocabulary) {
		this.vocabulary = vocabulary;
	}
	
	private void addEntry(VocabularyEntry entry, Document document) {
		if(entry == null) {
			return;
		}
		
		int tf = entry.getMaxTf();	//	tf de la nueva entrada del vocabulario
		VocabularyEntry auxEntry = vocabulary.get(entry.getWord());
		
		// verificar si es una nueva entrada o si ya estaba contenida en el vocabulario
		if(auxEntry == null) {
			auxEntry = new VocabularyEntry();
			auxEntry.setWord(entry.getWord());
			auxEntry.setMaxTf(tf);
			auxEntry.setNr(DOC_1);
		} else {
			// verificar el tf maximo entre la nueva entrada al vocabulario y la que ya existia
			if(auxEntry.getMaxTf() < tf) {
				auxEntry.setMaxTf(tf);
			}
			// sumar en 1 la cantidad de documentos con la entrada del vocabulario
			auxEntry.addNr();		
		}
		
		// settear una entrada en la lista de posteo para el término actual
		PostingEntry postingEntry = new PostingEntry(document, tf);
		auxEntry.getPostingList().addPostingEntry(postingEntry);	
		
		vocabulary.put(auxEntry.getWord(), auxEntry);
	}
	
	public void removeEntry(VocabularyEntry entry) {
		if(entry != null) {
			vocabulary.remove(entry.getWord());
		}
	}
	
	public void addDocument(Map<String, Integer> docMap, Document document) {
		if(docMap == null || document == null) {
			return;
		}
		
		long start = System.currentTimeMillis();
		logger.debug("Se agrega nuevo documento al vocabulario");
		
		for (String word : docMap.keySet()) {
			VocabularyEntry entry = new VocabularyEntry();
			entry.setWord(word);
			int tf = docMap.get(word);			
			entry.setMaxTf(tf);
			
			// agregar la entrada en el vocabulario
			addEntry(entry, document);
		}
		
		logger.debug(String.format("Finaliza procesamiento documento - Tiempo: %d ms",
				System.currentTimeMillis() - start));
	}
	
	public void sortPostingList() {
		for (VocabularyEntry entry : vocabulary.values()) {
			entry.getPostingList().sort();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("Vocabulary:\n");
		
		for (VocabularyEntry entry : vocabulary.values()) {
			string.append(entry);
			string.append(":\n");
			string.append(entry.getPostingList());
			string.append("\n");
		}
		
		return string.toString();		
	}

}

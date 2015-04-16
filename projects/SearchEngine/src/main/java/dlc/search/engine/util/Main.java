package dlc.search.engine.util;

import java.io.IOException;
import java.util.Map;

import dlc.search.engine.domain.Vocabulary;

public class Main {

	private static String[] docs = new String[]{ "d1.txt", "d2.txt", "d3.txt" };
	
	public static void testDocuments() {
		for (int i = 0; i < docs.length; i++) {
			System.out.printf("%s = %s%n%n", docs[i], ProcessorUtils.process(docs[i]));
		}
		
//		System.out.println(ProcessorUtils.process(docs[0]));
//		System.out.println(ProcessorUtils.process(docs[1]));
//		System.out.println(ProcessorUtils.process(docs[2]));
		
	}
	
	public static void processDocuments(Vocabulary vocabulary, String doc) {
		Map<String, Integer> document = ProcessorUtils.process(doc);
		vocabulary.addDocument(document);
	}
	
	public static void main(String[] args) throws IOException {
		testDocuments();
		
		Vocabulary vocabulary = new Vocabulary();
		processDocuments(vocabulary, docs[0]);
		processDocuments(vocabulary, docs[1]);
		processDocuments(vocabulary, docs[2]);
		
		System.out.println(vocabulary);

	}

}

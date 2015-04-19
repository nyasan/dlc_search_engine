package dlc.search.engine.util;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

import dlc.search.engine.domain.Document;
import dlc.search.engine.domain.Vocabulary;
import dlc.search.engine.processor.Processor;
import dlc.search.engine.processor.ProcessorFile;
import dlc.search.engine.processor.ProcessorGoogleDrive;

public class Main {
	
	public static final Logger logger = Logger.getLogger(Main.class);
	
	public static final String PATH_DOCS = "src/main/webapp/resources/docs/";
	public static final String DOC = "C:\\Users\\francoar\\Downloads\\00ws110.txt";
	
	private static Vocabulary vocabulary;
	private static Processor processor; 

	private static String[] docs = new String[]{"d1.txt", "d2.txt", "d3.txt" };
	
	public static void testDocuments(Processor processor) {
		for (int i = 0; i < docs.length; i++) {
			System.out.printf("%s = %s%n%n", docs[i], processor.process(docs[i]));
		}
		
//		System.out.println(ProcessorUtils.process(docs[0]));
//		System.out.println(ProcessorUtils.process(docs[1]));
//		System.out.println(ProcessorUtils.process(docs[2]));
		
	}
	
	public static void processDocuments(Vocabulary vocabulary, Processor processor, String docName, String path) {
		String abspath = path + docName; 
		
		Map<String, Integer> docMap = processor.process(abspath);
		Document document = new Document(docName, abspath);
		vocabulary.addDocument(docMap, document);
	}
	
	public static void testPostingList() {
		
	}
	
	public static void main(String[] args) throws IOException {
		Vocabulary vocabulary = new Vocabulary();
		Processor processor = new ProcessorFile();
//		Processor processor = new ProcessorGoogleDrive();
		
		processDocuments(vocabulary, processor, docs[0], PATH_DOCS);
		processDocuments(vocabulary, processor, docs[1], PATH_DOCS);
		processDocuments(vocabulary, processor, docs[2], PATH_DOCS);
		
		vocabulary.sortPostingList();
		
		System.out.println(vocabulary.getVocabulary().size());
		
//		testDocuments();
//		processDocuments(vocabulary, processor, docs[0]);
//		processDocuments(vocabulary, processor, docs[1]);
//		processDocuments(vocabulary, processor, docs[2]);
		
		logger.debug(vocabulary);

	}

}

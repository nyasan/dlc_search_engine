package dlc.search.engine.domain;

public class PostingEntry {
	private String word;
	private Document document;
	private int tf;
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public Document getDocument() {
		return document;
	}
	
	public void setDocument(Document document) {
		this.document = document;
	}
	
	public int getTf() {
		return tf;
	}
	
	public void setTf(int tf) {
		this.tf = tf;
	}
	
	

}

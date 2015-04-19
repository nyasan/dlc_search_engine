package dlc.search.engine.domain;

public class PostingEntry {
	private Document document;
	private int tf;
	
	public PostingEntry() {		
	}
	
	public PostingEntry(Document document, int tf) {
		this.document = document;
		this.tf = tf;
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

	@Override
	public String toString() {
		return String.format("%s --> tf = %d", document.getName(), tf);
	}
	
	

}

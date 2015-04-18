package dlc.search.engine.domain;

public class PostingEntry implements Comparable<PostingEntry>{
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
	public int compareTo(PostingEntry other) {
		if(other == null) {
			throw new IllegalArgumentException("Other must not be null");
		}
		
		return this.tf - other.tf;
	}
	
	@Override
	public String toString() {
		return String.format("%s - %d", document, tf);
	}
	
	

}

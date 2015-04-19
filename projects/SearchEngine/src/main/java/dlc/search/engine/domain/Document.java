package dlc.search.engine.domain;

public class Document {
	private String name;
	private String uri;

	public Document() {
	}
	
	public Document(String name) {
		this(name, null);
	}
	
	public Document(String name, String uri){
		this.name = name;
		this.uri = uri;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	@Override
	public String toString() {
		return String.format("name:%s - path:%s", name, uri);
	}
}

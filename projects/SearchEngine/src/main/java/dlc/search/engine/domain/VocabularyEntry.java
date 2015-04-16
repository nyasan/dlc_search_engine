package dlc.search.engine.domain;

public class VocabularyEntry {
	private String word;
	private int nr;
	private int maxTf;
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public int getNr() {
		return nr;
	}
	
	public void setNr(int nr) {
		this.nr = nr;
	}
	
	public int getMaxTf() {
		return maxTf;
	}
	
	public void setMaxTf(int maxTf) {
		this.maxTf = maxTf;
	}
	
	public void addNr() {
		this.nr++;
	}
	
	@Override
	public String toString() {
		return String.format("%s [nr=%d, max tf=%d]", word, nr, maxTf);
	}

}

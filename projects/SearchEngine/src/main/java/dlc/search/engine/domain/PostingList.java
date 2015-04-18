package dlc.search.engine.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PostingList {
	private List<PostingEntry> list;
	
	public PostingList() {
		list = new LinkedList<>();
	}
	
	public List<PostingEntry> getList() {
		return list;
	}
	
	public void setList(List<PostingEntry> postingList) {
		this.list = postingList;
	}
	
	public void addPostingEntry(PostingEntry entry) {
		if(entry != null){
			list.add(entry);
		}
	}
	
	public void sort() {
		Collections.sort(list);
	}
	
	@Override
	public String toString() {
		return list.toString();
	}
	

}

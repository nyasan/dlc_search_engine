package dlc.search.engine.domain;

import java.util.Collections;
import java.util.Comparator;
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
		if (entry != null) {
			list.add(entry);
		}
	}

	/**
	 * Ordena en orden decreciente por tf una lista de posteo
	 */
	public void sort() {
		Collections.sort(list, new Comparator<PostingEntry>(){
			@Override
			public int compare(PostingEntry o1, PostingEntry o2) {
				return o1.getTf() < o2.getTf() ? 1 : (o1.getTf() > o2.getTf() ? -1 : 0);
			}
		});
	}

	@Override
	public String toString() {
		return list.toString();
	}

}

import java.io.Serializable;
import java.util.ArrayList;


public class WordGraph implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7479226874114515115L;
	private ArrayList<Word> words;
	private Word currentWord;
	
	
	public WordGraph() {
		words = new ArrayList<Word>();
	}
	
	public Word addWord(String s) {
		//check if it already exists
		boolean exists = false;
		Word current = null;
		for (Word w : words) {
			if (w.getString().equals(s)) {
				current = w;
				exists = true;
			}
		}
		
		if (exists) {
			return current;
		} else {
			Word w = new Word(s);
			words.add(w);
			return w;
		}
	}
	
	public void setCurrentWord(Word w) {
		currentWord = w;
	}
	
	public ArrayList<Word> getWords() {
		return words;
	}
	
	public Word getCurrentWord() {
		return currentWord;
	}

	public ArrayList<Word> findNClosestEdge(int n) {
		ArrayList<Word> closest = new ArrayList<Word>();
		dijkstra();
		ArrayList<Word> allWords = new ArrayList<Word>();
		for (Word w : words) {
			allWords.add(w);
		}
		allWords = selectionSortByDistance(allWords);
		allWords.remove(currentWord);
		for (int i = 0; i < n; i++) {
			closest.add(allWords.get(i));
		}
		return closest;
	}

	private void dijkstra() {
		Word word;
		Word current = currentWord;
		ArrayList<Word> allWords = new ArrayList<Word>();
		for (Word w : words) {
			allWords.add(w);
		}
		for (int i = 0; i < allWords.size(); i++) { //1
			allWords.get(i).distance = Double.MAX_VALUE;
			allWords.get(i).known = false;
		}
		
		current.distance = 0;
		current.known = true;
		
		for (NextWord nw : current.getNextWords()) {
			nw.getWord().distance = nw.getProbability();
		}
		
		while (!allWords.isEmpty()) {
			word = removeSmallest(allWords);
			word.known = true;
			for (NextWord nw : word.getNextWords()) {
				if (!nw.getWord().known) {
					double dist = nw.getProbability();
					if (word.distance + dist < nw.getWord().distance) {
						nw.getWord().distance = word.distance + dist;
						nw.getWord().path = word; //not by id
					}
				}
			}
			
		}
	}

	private Word removeSmallest(ArrayList<Word> temp) {
		Word word = temp.get(0);
		for (Word w : temp) {
			if (w.distance < word.distance) 
				word = w;
		}
		temp.remove(word);
		return word;
	}

	private ArrayList<Word> selectionSortByDistance(ArrayList<Word> m) {
		ArrayList<Word> sorted = new ArrayList<Word>();
		int numWords = m.size();
		for (int j = 0; j < numWords; j++) {
			double min = Double.MAX_VALUE;
			Word closest = null;
			for (int i = 0; i < m.size(); i++) {
				if (m.get(i).distance < min) {
					min = m.get(i).distance;
					closest = m.get(i);
				}
			}
			sorted.add(closest);
			m.remove(closest);
		}
		return sorted;
	}

	public ArrayList<Word> shortestPath(Word word) {
		ArrayList<Word> path = new ArrayList<Word>();
		dijkstra();
		while(word.path != null) {
			path.add(word);
			word = word.path;
		}
		return path;
	}

	public Word findWord(String s) {
		for (Word w : words) {
			if (w.getString().equals(s)) {
				return w;
			}
		}
		return null;
	}
	
	public void resetReferences() {
		for (Word w : words) {
			w.distance = 0;
			w.path = null;
			w.known = false;
		}
	}
	
}

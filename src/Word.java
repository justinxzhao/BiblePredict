import java.io.Serializable;
import java.util.ArrayList;


public class Word implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2439881307384187469L;
	private String word;
	private ArrayList<NextWord> nextWords;
	
	//alterable reference variables
	double distance;
	boolean known = false;
	Word path = null;
	
	public Word(String word) {
		this.word = word;
		nextWords = new ArrayList<NextWord>();
	}
	
	public void addNextWord(Word w) {
		//check if that word already exists
		boolean exists = false;
		NextWord found = null;
		for (NextWord nw : nextWords) {
			if ((nw.getWord().getString()).equals( w.getString() )) {
				found = nw;
				exists = true;
			}
		}
		
		if (exists) {
			found.incFreq();
		} else {
			NextWord nw = new NextWord(w);
			nextWords.add(nw);
		}
	}
	
	public String getString() {
		return word;
	}
	
	public ArrayList<NextWord> getNextWords() {
		return nextWords;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append(word);
		for (NextWord nw : nextWords) {
			sb.append("\n");
			sb.append(nw);
		}
		return sb.toString();
	}
	
}

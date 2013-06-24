import java.io.Serializable;


public class NextWord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6530924124517386960L;
	private Word word;
	private double probability; //between 0 and 1
	private int freq;
	
	public NextWord(Word w) {
		this.word = w;
		freq = 1;
	}
	
	public Word getWord() {
		return word;
	}
	
	public double getProbability() {
		return probability;
	}
	
	public void incFreq() {
		freq++;
	}
	
	public void setProbability(int total) {
		probability = (double) freq / (double) total;
	}
	
	public int getFreq() {
		return freq;
	}
	
	public String toString() {
		return "\t" + word.getString() + " " + probability;
	}
	
}

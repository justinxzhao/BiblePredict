import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Tester {

	public static WordGraph g = new WordGraph();
	public static Scanner user = new Scanner(System.in);
	public static Random r = new Random();
	public static File graphBin;
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//create the save file if it doesn't exist yet
		graphBin = new File("wordGraph.bin");
		if (!graphBin.exists()) {
			graphBin.createNewFile();
		}
		
		//begin program
		boolean again = true;
		while (again) {
			printOptions(); //print menu
			String option = user.nextLine();
			
			switch (option) {
				case "a":
					processNewData();
					break;
				case "b":
					loadData();
					break;
				case "c":
					saveData();
					break;
				case "d": 
					System.out.println("What is the word you want to find?");
					findWord(user.nextLine());
					break;
				case "e":
					System.out.println("What is the keyword you would like to begin wirh?");
					generateBiblePhrase(user.nextLine());
					break;
				case "f":
					System.out.println("What word do you want to set to current?");
					setCurrentWord(user.nextLine());
					break;
				case "g":
					printCurrentWord();
					break;
				case "h":
					findNClosestEdge();
					break;
				case "i":
					findShortestPath();
					break;
				default:
					System.out.println("Thank you! Goodbye!");
					again = false;
					break;
			}
		}
	}
	public static void processNewData() throws IOException {

		System.out.println("Clear or append to previous data? 0 - clear, 1 - append");
		int doClear = Integer.parseInt(user.nextLine());
		
		if (doClear == 0) {
			PrintWriter writer = new PrintWriter(graphBin);
			writer.print("");
			writer.close();
			System.out.println("Previous data cleared.");
		}
		
		System.out.println("What is the name of the text file?");
		String fileName = user.nextLine();
		File data = new File(fileName);
		
		if (!data.exists()) {
			System.out.println("Sorry, that file does not exist.");
			return;
		}
		
		//reading in all the information of the text file
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String line = null;
		while ((line = in.readLine()) != null) {
			String text = line.split("\t")[1];
			String[] words = text.split(" ");
			for (int i = 0; i < words.length-1; i++) {
				Word current = g.addWord(words[i]);
				Word next = g.addWord(words[i+1]);
				current.addNextWord(next);
			}
		}
		
		//after all the lines are read, we have to update all the words' nextWords
		for (Word w : g.getWords()) {
			int freqsum = 0;
			for (NextWord nw : w.getNextWords()) {
				freqsum += nw.getFreq(); //sum up all the frequencies for this word
			}
			for (NextWord nw : w.getNextWords()) {
				//set the probability of all the nextWords for this word
				nw.setProbability(freqsum);
			}
		}
		
		System.out.println("Text has been analyzed.");
		in.close();
		
	}//end processNewData
	
	public static void loadData() throws IOException, ClassNotFoundException {
		System.out.println("This will automatically overwrite any previously loaded word data.");

		FileInputStream fileIn = new FileInputStream(graphBin);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		g = (WordGraph) in.readObject();
		
		//closing the streams
		fileIn.close();
		in.close();
		
		System.out.println("Word Graph Loaded.");
	}
	
	public static void saveData() throws IOException {
		FileOutputStream gB = new FileOutputStream(graphBin);
		ObjectOutputStream output = new ObjectOutputStream(gB);
		output.writeObject(g);
		
		//closing the streams
		gB.close();
		output.close();
		
		System.out.println("Graph Saved.");
	}
	
	public static Word findWord(String request) {
		boolean found = false;
		for (Word w : g.getWords()) {
			if (w.getString().equals(request)) {
				System.out.println("Word found!");
				found = true;
				System.out.println(w);
				return w;
			}
		}
		if (!found) {
			System.out.println("Sorry that word could not be located");
			return null;
		}
		return null;
		
	}
	
	public static void generateBiblePhrase(String request) {
		Word start = null;
		for (Word w : g.getWords()) {
			if (w.getString().equals(request)) {
				start = w;
			}
		}

		if (start == null) {
			System.out.println("Start word could not be found.");
			return;
		}
		
		//initialization
		Word next = start;
		System.out.println(next.getString());
		char c;
		
		do {
			//picking the next word in the sequence
			double sum = 0;
			ArrayList<NextWord> nextWords = next.getNextWords();
			double stop = r.nextDouble();
			for (int i = 0; i < nextWords.size(); i++) {
				NextWord nw = nextWords.get(i); //test this nextword
				
				sum += nw.getProbability();
				//if the sum is greater than or equal to the stopping point, this word is selected
				if (sum >= stop) {
					next = nw.getWord(); //new next is selected
					break;
				}
			}
			
			System.out.println(next.getString());
			
			//set-up the halting condition
			char[] chars = next.getString().toCharArray();
			c = chars[chars.length-1];
			
		} while (c != '.' && c != '?'); //while the last character isn't a period
		
	}
	
	public static void setCurrentWord(String request) {
		boolean found = false;
		for (Word w : g.getWords()) {
			if (w.getString().equals(request)) {
				System.out.println("Word found.");
				found = true;
				g.setCurrentWord(w);
			}
		}
		if (!found) {
			System.out.println("Word could not be found.");
		}
	}
	
	public static void printCurrentWord() {
		System.out.println(g.getCurrentWord());
	}
	
	public static void findNClosestEdge() {
		if (g.getCurrentWord() == null) {
			System.out.println("No city is currently selected.");
			return;
		}
		System.out.println("Please enter number of words to be found.");
		int n = Integer.parseInt(user.nextLine());
		ArrayList<Word> nClosest = g.findNClosestEdge(n);
		for (Word w : nClosest) {
			System.out.println(w.getString() + " ");
			System.out.println("Edge Cost Away: " + w.distance + "\n");
		}
	}
	
	public static void findShortestPath() {
		g.resetReferences();
		if (g.getCurrentWord() == null) {
			System.out.println("No word is currently selected.");
			return;
		}
		System.out.println("Please enter the name of the destination word.");
		String s = user.nextLine();
		Word word = g.findWord(s);
	
		ArrayList<Word> path = g.shortestPath(word);
		
		System.out.println("Shortest path to selected word:");
		System.out.println("_______________");
		System.out.println("Origin: " + g.getCurrentWord().getString());
		for (int i = path.size() - 1; i >= 0; i--) {
			System.out.println(path.get(i).getString());
		}
		
	}
	
	public static void printOptions() {
		System.out.println("---------Menu Options---------");
		System.out.println("a - Load a text file into the system");							//good
		System.out.println("b - Load a WordGraph from a file called wordGraph.bin");		//good
		System.out.println("c - Save current graph to file called wordGraph.bin");			//good
		System.out.println("d - Search for word and display some information about it");	//good
		System.out.println("e - Generate a bible phrase");									//good
		System.out.println("f - Set Word X as current");									//good
		System.out.println("g - Print current word");										//good
		System.out.println("h - Find n closest words to current word using word probabilities");	//good
		System.out.println("i - Find shortest path between current word and some target word");
		System.out.println("X - Quit");														//good
	}

}//end class

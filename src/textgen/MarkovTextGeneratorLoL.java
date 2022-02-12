package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		if (!wordList.isEmpty()) { return; }
		String[] words = sourceText.split("[\\s]+");
		
		starter = words[0];
		String prevWord = starter;
		for(int i=1;i<words.length;i++) {
			ListNode temp = getListNode(prevWord);
			temp.addNextWord(words[i]);
			prevWord = words[i];
		}
		ListNode temp = getListNode(words[words.length-1]);
		temp.addNextWord(starter);
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		if (wordList.size() == 0 || numWords == 0) {
			return "";
		}
		String currWord = starter;
		String currentText = starter+" ";
		for (int i=0;i<numWords-1;i++) {
			ListNode temp = getListNode(currWord);
			if (temp == null) {
				temp = getListNode(currWord);
			}
			String genWord = temp.getRandomNextWord(rnGenerator);
			currentText += genWord + " ";
			currWord = genWord;
		}
		return currentText;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList.clear();
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	private ListNode getListNode(String word) {
		for(ListNode ln:wordList) {
			if (ln.getWord().equalsIgnoreCase(word)) {
				return ln;
			}
		}
		ListNode temp = new ListNode(word);
		wordList.add(temp);
		return temp;
	}
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
	    if (nextWords.size()==0) {
	    	return null;
	    }
	    return nextWords.get(generator.nextInt(nextWords.size()));
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


//Looping over all the words and updating wordList
		/** for (int i=0;i<words.length;i++) {
			String w = words[i];
			int index = 0;
			for (int j=0;j<wordList.size();j++) {
				if (wordList.get(j).getWord().equalsIgnoreCase(w)) {
					ListNode temp = wordList.get(j);
					try {
						temp.addNextWord(words[i+1]);
					}
					catch(IndexOutOfBoundsException e){					}
					//temp.addNextWord(words[i+1]);
					wordList.set(j, temp);
					index++;
				}
				else {
					ListNode temp = new ListNode(w);
					try {
						temp.addNextWord(words[i+1]);
					}
					catch(IndexOutOfBoundsException e){
						//temp.addNextWord(words[0]);
					}
					wordList.add(temp);
					index++;
				}
			}
		}
		**/


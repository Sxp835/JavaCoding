package lab13;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;


public class WordFisher {

	public HashMap<String, Integer> vocabulary;
	private List<String> stopwords;
	private String inputTextFile;
	private String stopwordsFile;
	
	
	public WordFisher (String inputTextFile, String stopwordsFile) {
		this.inputTextFile = inputTextFile;
		this.stopwordsFile = stopwordsFile;
		getStopwords();
		buildVocabulary();
		
	}
	
	private void getStopwords() {
		
		BufferedReader input;
		try {
		input = new BufferedReader(new FileReader(stopwordsFile));
		String currentLine = input.readLine();
		stopwords = new ArrayList<String>();
		while(currentLine != null) {
			stopwords.add(currentLine);
			currentLine = input.readLine();
		}
		input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	private void buildVocabulary(){
		try {
			String reader = new String(Files.readAllBytes(Paths.get(inputTextFile)));
			reader = reader.replaceAll("[^a-zA-Z0-9 ]", "");
			reader = reader.toLowerCase();
			String[] allWords = reader.split("\\s+"); // any # of spaces
			vocabulary = new HashMap<String, Integer>();
			for(int i = 0; i< allWords.length;i++) {
				int v = 1;
				if(vocabulary.containsKey(allWords[i])) {
					int freq = vocabulary.get(allWords[i]);
					v = freq + 1;
					
				}
				vocabulary.put(allWords[i], v);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
    
	}
	public int getWordCount() {
		int sum = 0;
		ArrayList<Integer> count;
		count = new ArrayList<> (vocabulary.values());
		for(int i = 0; i<count.size(); i++) {
			sum += count.get(i);
		}
		return(sum);
	}
	public int getNumUniqueWords() {
		return(vocabulary.size());
	}
	 public int getFrequency(String word) {
		 word = word.toLowerCase();
		 if(!vocabulary.containsKey(word)) {
			 return -1;
		 }
		 return(vocabulary.get(word));
	 }
	 public void pruneVocabulary() {
		 for(int i= 0; i<stopwords.size();i++) {
			 if(vocabulary.containsKey(stopwords.get(i))) {
				 vocabulary.remove(stopwords.get(i));
			 }
		 }
	 }
	 public ArrayList<String> getTopWords(int n){
		 OrderByFrequency comparator = new OrderByFrequency();
		 PriorityQueue<WordNode> wordList = new PriorityQueue<WordNode>(vocabulary.size(), comparator);
		 ArrayList<String> list= new ArrayList<String>(vocabulary.keySet());
		 for(int i=0; i<list.size(); i++) {
			 WordNode word = new WordNode(list.get(i), vocabulary.get(list.get(i)));
			 wordList.add(word);
		 }
		 ArrayList<String> topNWords = new ArrayList<String>(n);
		 for(int i = 0; i<n;i++) {
			 WordNode currWord = wordList.poll();
			 topNWords.add(currWord.word);
		 }
		 return(topNWords);
	 }
	 public ArrayList<String> commonPopularWords(int n, WordFisher other){
		 ArrayList<String> topWords1 = getTopWords(n);
		 ArrayList<String> topWords2 = other.getTopWords(n);
		 ArrayList<String> commonWords = new ArrayList<String>();
		 for(int i =0; i<n;i++) {
			 if(topWords2.contains(topWords1.get(i))) {
				 commonWords.add(topWords1.get(i));
			 }		 
		 }
		 return(commonWords);
	 }

//	 nested class for PriorityQueue
	 private static class WordNode{
		 private String word;
		 private int frequency; 
		 
		 public WordNode(String word, int frequency) {
			 this.word = word;
			 this.frequency = frequency;
		 }
		 
	 }
//   comparator for PriorityQueue
	 protected class OrderByFrequency implements Comparator<WordNode>{
		 public int compare(WordFisher.WordNode w1, WordFisher.WordNode w2) {
			 return(w2.frequency - w1.frequency);
		 }
	 }
	
}

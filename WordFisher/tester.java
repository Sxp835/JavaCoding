package lab13;

import lab13.WordFisher;

public class tester {
	public static void main(String[] args) {
		WordFisher book = new WordFisher("moby-dick.txt", "stopwords.txt");
		System.out.println(book.getWordCount());
		System.out.println(book.getNumUniqueWords());
		System.out.println(book.getFrequency("whale"));
		System.out.println(book.getFrequency("handkerchief"));
		System.out.println(book.getFrequency("Alice"));
		System.out.println(book.getTopWords(10));
		book.pruneVocabulary();
		System.out.println(book.getWordCount());
		System.out.println(book.getTopWords(10));
		WordFisher book2 = new WordFisher("carroll-alice.txt", "stopwords.txt");
		book2.pruneVocabulary();
		System.out.println(book.commonPopularWords(20,book2));

	}
}

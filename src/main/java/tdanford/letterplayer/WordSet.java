package tdanford.letterplayer;

import java.util.*;
import java.io.*;

public class WordSet implements Iterable<Word> {
	
	private TreeSet<Word> words;

	public WordSet() throws IOException { 
		words = new TreeSet<Word>();
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream is = loader.getResourceAsStream("wordsEn.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try { 
			String line;
			while((line = br.readLine()) != null) { 
				Word w = new Word(line.trim());
				words.add(w);
			}
			
		} finally { 
			br.close();
		}
	}
	
	public WordSet(WordSet base, LetterSet filter) { 
		words = new TreeSet<Word>();
		for(Word w : base.words) { 
			if(filter.containsLetterSet(w.getLetters())) { 
				words.add(w);
			}
		}
	}
	
	public Iterator<Word> iterator() { return words.iterator(); }
	
	public int size() { return words.size(); }
	
	public boolean isEmpty() { return words.isEmpty(); }
}

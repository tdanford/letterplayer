package tdanford.letterplayer;

import java.util.ArrayList;
import java.util.Collection;

public class Word implements Comparable<Word> {

	private String value;
	
	public Word(String v) { 
		value = v.toLowerCase();
	}

    public char charAt(int i) { return value.charAt(i); }

	public int length() { return value.length(); }
	
	public LetterSet getLetters() { return new LetterSet(value); }
	
	public boolean isPrefixOf(Word w) { return w.value.startsWith(value); }
	
	public String toString() { return value; }
	
	public int hashCode() { return value.hashCode(); }
	
	public boolean equals(Object o) { 
		if(!(o instanceof Word)) { return false; }
		Word w = (Word)o;
		return value.equals(w.value);
	}
	
	public int compareTo(Word w) { return value.compareTo(w.value); }
}

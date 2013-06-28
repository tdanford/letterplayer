package tdanford.letterplayer;

import java.util.*;

public class LetterSet {

	private Map<Character,int[]> charCounts;
	
	public LetterSet(char[] chars) { 
		charCounts = new TreeMap<Character,int[]>();
		for(int i = 0; i < chars.length; i++) {
			 if(!charCounts.containsKey(chars[i])) { 
				 charCounts.put(chars[i], new int[] { 0 });
			 }
			 charCounts.get(chars[i])[0] += 1;
		}
	}

	public LetterSet(String word) { 
		this(word.toCharArray());
	}
	
	public int count(Character c) { 
		return charCounts.containsKey(c) ? charCounts.get(c)[0] : 0;
	}
	
	public boolean containsLetterSet(LetterSet s) { 
		for(Character c : s.charCounts.keySet()) { 
			if(!charCounts.containsKey(c) || s.charCounts.get(c)[0] > charCounts.get(c)[0]) { 
				return false;
			}
		}
		return true;
	}
}

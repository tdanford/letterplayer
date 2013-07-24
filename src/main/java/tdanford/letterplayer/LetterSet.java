package tdanford.letterplayer;

import java.util.*;

public class LetterSet {

	private Map<Character,int[]> charCounts;

    /**
     * Turns an array of characters into a LetterSet.
     *
     * @param chars
     */
	public LetterSet(char... chars) {
		charCounts = new TreeMap<Character,int[]>();
		for(int i = 0; i < chars.length; i++) {
			 if(!charCounts.containsKey(chars[i])) { 
				 charCounts.put(chars[i], new int[] { 0 });
			 }
			 charCounts.get(chars[i])[0] += 1;
		}
	}

    /**
     * Creates a LetterSet that counts the letters in a given word.
     *
     * @param word
     */
	public LetterSet(String word) { 
		this(word.toCharArray());
	}

    /**
     * Copies a LetterSet.
     *
     * @param set
     */
    public LetterSet(LetterSet set) {
        charCounts = new TreeMap<Character,int[]>();
        for(Character c : set.charCounts.keySet()) {
            charCounts.put(c, new int[] { set.charCounts.get(c)[0] });
        }
    }

    /**
     * Updates (writes to!) the LetterSet on which this method is invoked,
     * removing a given character from the letter set.
     *
     * If the LetterSet already has 0 characters of this type, then this method
     * does nothing.
     *
     * @param removed
     */
    public void remove(char removed) {
        if(charCounts.containsKey(removed)) {
            charCounts.get(removed)[0] -= 1;
            if(charCounts.get(removed)[0] <= 0) {
                charCounts.remove(removed);
            }
        }
    }

    /**
     * Updates (writes to!) the LetterSet on which this method is invoked, adding a
     * single character of the given type to the set.
     *
     * @param add
     */
    public void add(char add) {
        if(!charCounts.containsKey(add)) { charCounts.put(add, new int[] { 0 }); }
        charCounts.get(add)[0] += 1;
    }
	
	public int count(Character c) { 
		return charCounts.containsKey(c) ? charCounts.get(c)[0] : 0;
	}

    public int getNumLetters() { return charCounts.size(); }
	
	public boolean containsLetterSet(LetterSet s) { 
		for(Character c : s.charCounts.keySet()) { 
			if(!charCounts.containsKey(c) || s.charCounts.get(c)[0] > charCounts.get(c)[0]) { 
				return false;
			}
		}
		return true;
	}

    public int hashCode() {
        int code = 17;
        for(Character ch : charCounts.keySet()) {
            code += ch.hashCode(); code *= 37;
            code += charCounts.get(ch)[0]; code *= 37;
        }
        return code;
    }

    public boolean equals(Object o) {
        if(!( o instanceof LetterSet )) { return false; }
        LetterSet ls = (LetterSet)o;
        if(charCounts.size() != ls.charCounts.size()) { return false; }
        for(Character ch : charCounts.keySet()) {
            if(!ls.charCounts.containsKey(ch)) { return false; }
            if(charCounts.get(ch)[0] != ls.charCounts.get(ch)[0]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(Character ch : charCounts.keySet()) {
            if(sb.length() > 1) { sb.append(", "); }
            sb.append(String.format("'%c': %d", ch, charCounts.get(ch)[0]));
        }
        sb.append("}");
        return sb.toString();
    }
}

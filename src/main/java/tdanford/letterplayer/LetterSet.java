package tdanford.letterplayer;

import java.util.*;

public class LetterSet {

	private Map<Character,int[]> charCounts;

	public LetterSet(char... chars) {
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

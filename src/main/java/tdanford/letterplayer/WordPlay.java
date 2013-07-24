package tdanford.letterplayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * WordPlay is the edge in the Board position tree -- from a particular board position,
 * you can't just know what word was played to get to the next board position, you also
 * have to know which letters were played in particular (so if there were two 'a's on the board,
 * and the played word only contained one 'a', you need to know *which* of those 'a's was
 * actually played as part of the word).
 *
 * WordPlay tracks this information, and allows a transition from one BoardTreePosition to
 * a child BoardTreePosition.
 */
public class WordPlay {

	private ArrayList<LetterPoint> pts;
	
	public WordPlay(LetterPoint... ps) {
		this.pts = new ArrayList<LetterPoint>();
        for(LetterPoint pt : ps) { pts.add(pt); }

		if(pts.size() < 2) { throw new IllegalArgumentException("Every word play must have at least 2 letters"); }
		for(int i = 1; i < pts.size(); i++) {
			for(int p = 0; p < i; p++) { 
				if(pts.get(p).equals(pts.get(i))) {
                    throw new IllegalArgumentException(String.format(
                            "Letter %d (%d,%d) is a duplicate of letter %d in %s",
							i, pts.get(i).getRow(), pts.get(i).getCol(), p, pts
                    ));
				}
			}
		}
	}

    public int size() { return pts.size(); }

    public LetterPoint getPoint(int i) { return pts.get(i); }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(LetterPoint pt : pts) {
            if(sb.length() > 0) { sb.append("-"); }
            sb.append(pt.toString());
        }
        return sb.toString();
    }

    public int hashCode() {
        int code = 17;
        for(LetterPoint pt : pts) {
            code += pt.hashCode(); code *= 37;
        }
        return code;
    }

    public boolean equals(Object o) {
        if(!(o instanceof WordPlay)) {
            return false;
        }
        WordPlay wp = (WordPlay)o;
        if(pts.size() != wp.pts.size()) { return false; }
        for(int i = 0; i < pts.size(); i++) {
            if(!pts.get(i).equals(wp.pts.get(i))) {
                return false;
            }
        }
        return true;
    }
	
}

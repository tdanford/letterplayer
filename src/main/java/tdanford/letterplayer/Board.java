package tdanford.letterplayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents the basic arrangement of the board -- the position of all the letters on all the squares.
 * The Board object also contains, for convenience, an array of all legal Word objects for this particular
 * board arrangement.
 * 
 * @author tdanford
 *
 */
public class Board {

	private char[][] letters;
	private LetterSet letterSet;
	private Word[] words;

    private Map<Character,ArrayList<LetterPoint>> ambiguity;

    public Board(String b) throws IOException {
        this(b.toCharArray());
    }
	
	public Board(char[] b) throws IOException { 
		if(b.length != 25) { 
			throw new IllegalArgumentException(String.format("b.length == %d, which isn't 25", b.length)); 
		}
		letters = new char[5][5];
		for(int i = 0, k = 0; i < 5; i++) { 
			for(int j = 0; j < 5; j++, k++) { 
				letters[i][j] = Character.toLowerCase(b[k]);
                if(!Character.isLetter(letters[i][j])) {
                    throw new IllegalArgumentException(String.format("%d,%d character (%c) isn't a letter", i, j, letters[i][j]));
                }
			}
		}
		
		letterSet = new LetterSet(b);
		WordSet ws = new WordSet(new WordSet(), letterSet);
		words = new Word[ws.size()];
		int i = 0;
		for(Word w : ws) { words[i++] = w; }

        ambiguity = new TreeMap<Character,ArrayList<LetterPoint>>();

        for(i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                char c = letters[i][j];
                if(!ambiguity.containsKey(c)) { ambiguity.put(c, new ArrayList<LetterPoint>()); }
                ambiguity.get(c).add(new LetterPoint(i, j));
            }
        }
	}

    public Word getWord(WordPlay wp) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < wp.size(); i++) {
            sb.append(getLetter(wp.getPoint(i)));
        }
        return new Word(sb.toString());
    }

    /**
     * Creates a collection of WordPlay objects, one for every distinct way in which
     * a particular word *could* be played on this board.
     *
     * @param w The word whose plays are to be enumerated.
     * @return A Collection of WordPlay objects; for each 'p' in this array, getWord(p) should return the original Word 'w'.
     * @throws IllegalArgumentException if the given word cannot be played.
     */
    public Collection<WordPlay> enumerateWordPlays(Word w) {

        ArrayList<WordPlay> plays = new ArrayList<WordPlay>();
        LetterPoint[] ambig = new LetterPoint[w.length()];

        subEnumerateWordPlays(plays, w, ambig, 0);

        return plays;
    }

    private void subEnumerateWordPlays(ArrayList<WordPlay> acc,
                                       Word w,
                                       LetterPoint[] points,
                                       int offset) {
        if(offset >= w.length()) {
            acc.add(new WordPlay(points));

        } else {
            char ch = w.charAt(offset);

            for(int i = 0; i < ambiguity.get(ch).size(); i++) {
                LetterPoint lpi = ambiguity.get(ch).get(i);
                points[offset] = lpi;
                subEnumerateWordPlays(acc, w, points, offset+1);
            }
        }
    }

    private WordPlay createWordPlay(Word w, int[] ambig) {
        LetterPoint[] pts = new LetterPoint[w.length()];

        for(int i = 0; i < w.length(); i++) {
            pts[i] = findLetter(w.charAt(i), ambig[i]);
        }

        return new WordPlay(pts);
    }

    /**
     *
     * @param c The character to find.
     * @param i The ambiguity code -- either a 0, indicated with '.', or a digit between 1 and 9
     * @return A LetterPoint indicating the position of the i'th c-character on the board, starting
     * from the upper left and counting in row-major order.
     */
    public LetterPoint findPoint(char c, char i) {
        return findLetter(c, i == '.' ? 0 : Integer.parseInt(String.valueOf(i)));
    }

    public LetterPoint findLetter(char ch, int ambig) {
        if(!ambiguity.containsKey(ch)) { throw new IllegalArgumentException(String.valueOf(ch)); }
        if(ambig < 0 || ambig >= ambiguity.get(ch).size()) {
            throw new IllegalArgumentException(String.format("%d isn't in char %c range [0, %d) of %s", ambig,
                    ch,
                    ambiguity.get(ch).size(),
                    ambiguity.get(ch)));
        }
        return ambiguity.get(ch).get(ambig);
    }

    public WordSet getWordSet() { return new WordSet(words); }

    public LetterSet getLetterSet() { return letterSet; }

    public char getLetter(LetterPoint lp) { return getLetter(lp.getRow(), lp.getCol()); }

	public char getLetter(int row, int col) { return letters[row][col]; }

    public int hashCode() {
        int code = 17;
        for(int i = 0; i < letters.length; i++) {
            for(int j = 0; j < letters[i].length; j++) {
                code += (i*5)+j; code *= 37;
                code += (int)letters[i][j]; code *= 37;
            }
        }

        return code;
    }

    public boolean equals(Object o) {
        if(!(o instanceof Board)) { return false; }
        Board b = (Board)o;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(letters[i][j] != b.letters[i][j]) { return false; }
            }
        }

        return true;
    }

}

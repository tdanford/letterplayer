package tdanford.letterplayer;

import java.io.IOException;

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
			}
		}
		
		letterSet = new LetterSet(b);
		WordSet ws = new WordSet(new WordSet(), letterSet);
		words = new Word[ws.size()];
		int i = 0;
		for(Word w : ws) { words[i++] = w; }
	}

    public WordSet getWordSet() { return new WordSet(words); }

    public LetterSet getLetterSet() { return letterSet; }

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

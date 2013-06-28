package tdanford.letterplayer;

import java.io.IOException;

/**
 * Represents the basic arrangement of the board -- the position of all the letters on all the squares.
 * The Board object also contains, for convenience, an array of all legal Word objects for this particular
 * board arrangement.
 * 
 * @author danfoti1
 *
 */
public class Board {

	public char[][] letters;
	public LetterSet letterSet;
	public Word[] words;
	
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

	public char getLetter(int row, int col) {
		return letters[row][col];
	}
}

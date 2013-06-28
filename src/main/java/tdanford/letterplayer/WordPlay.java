package tdanford.letterplayer;

import java.util.TreeSet;

public class WordPlay {

	public Board board;
	public Word word;
	public LetterPoint[] pts;
	
	public WordPlay(Board b, LetterPoint... pts) { 
		board = b;
		this.pts = pts.clone();

		if(pts.length < 2) { throw new IllegalArgumentException("Every word play must have at least 2 letters"); }
		for(int i = 1; i < pts.length; i++) { 
			for(int p = 0; p < i; p++) { 
				if(pts[p].equals(pts[i])) { 
					throw new IllegalArgumentException(String.format("Letter %d (%d,%d) is a duplicate of letter %d",
							i, pts[i].getRow(), pts[i].getCol(), p));
				}
			}
		}
		this.word = buildWord();
	}
	
	public void updateState(BoardState state) { 
		TreeSet<LetterPoint> neighbors = new TreeSet<LetterPoint>();
		
	}
	
	private Word buildWord() { 
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < pts.length; i++) { 
			sb.append(pts[i].getLetter(board));
		}
		return new Word(sb.toString());
	}
	
	
}

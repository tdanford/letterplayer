package tdanford.letterplayer;

import java.util.*;

public class LetterPoint {
	
	private int row, col;

	public LetterPoint(int r, int c) {
		if(!isValidRowColumn(r, c)) { 
			throw new IllegalArgumentException(String.format("Invalid letter point %d,%d", r, c));
		}
		this.row = row;
		this.col = col;
	}
	
	public int getRow() { return row; }
	public int getCol() { return col; }
	
	public char getLetter(Board b) { return b.getLetter(row, col); }
	
	public Collection<LetterPoint> neighbors() { 
		ArrayList<LetterPoint> nbs = new ArrayList<LetterPoint>();
		for(int dr = -1; dr <= 1; dr += 1) { 
			for(int dc = -1; dc <= 1; dc += 1) { 
				if(dr != 0 || dc != 0) { 
					int rr = row + dr, rc = row + dc;
					if(isValidRowColumn(rr, rc)) { 
						nbs.add(new LetterPoint(rr, rc));
					}
				}
			}
		}
		return nbs;
	}
	
	public static boolean isValidRowColumn(int r, int c) { 
		return r >= 0 && c >= 0 && r < 5 && c < 5;
	}

    public int hashCode() {
        int code = 17;
        code += row; code *= 37;
        code += col; code *= 37;
        return code;
    }

    public boolean equals(Object o) {
        if(!( o instanceof LetterPoint )) { return false; }
        LetterPoint p = (LetterPoint)o;
        return row == p.row && col == p.col;
    }

    public String toString() { return String.format("<%d,%d>", row, col); }
}

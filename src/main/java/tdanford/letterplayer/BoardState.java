package tdanford.letterplayer;

/**
 * Four bit-masks, representing the current "ownership" state of a board -- which squares 
 * are owned, and which are defended, by each of the two players.
 * 
 * @author Timothy Danford
 *
 */
public class BoardState {
	
	public static final int BLUE = 0, RED = 1;
	
	public static LetterPoint[] all;
	public static LetterPoint[][] neighbors;
	
	static { 
		neighbors = new LetterPoint[25][];
		all = new LetterPoint[25];
		for(int r = 0, i = 0; r < 5; r++) { 
			for(int c = 0; c < 5; c++, i++) { 
				neighbors[i] = (new LetterPoint(r, c)).neighbors().toArray(new LetterPoint[0]);
				all[i] = new LetterPoint(r, c);
			}
		}
	}
	
	private int[] own, defend;
	
	public BoardState() {
		own = new int[] { 0, 0 };
		defend = new int[] { 0, 0 };
	}
	
	public BoardState(BoardState copy) { 
		own = new int[] { copy.own[0], copy.own[1] };
		defend = new int[] { copy.defend[0], copy.defend[1] };
	}
	
	public boolean isOwned(int player, LetterPoint pt) { 
		return isMasked(own[player], pt.getRow(), pt.getCol());
	}
	
	public boolean isDefended(int player, LetterPoint pt) {
		return isMasked(defend[player], pt.getRow(), pt.getCol());
	}
	
	public int ownership(LetterPoint pt) { 
		int blueOwner = (isOwned(BLUE, pt) ? 1 : 0) << BLUE;
		int redOwner = (isOwned(RED, pt) ? 1 : 0) << RED;
		int ownership = blueOwner | redOwner;
		if(ownership > 2) { throw new IllegalArgumentException(String.format("Illegal ownership %d", ownership)); }
		return ownership;
	}
	
	public void own(int player, LetterPoint pt) { 
		if(!isDefended(1-player, pt)) { 
			own[player] = setMask(own[player], pt.getRow(), pt.getCol(), true);
			own[1-player] = setMask(own[1-player], pt.getRow(), pt.getCol(), false);
		}
	}
	
	private void setDefend(int player, int r, int c) { 
		if(!isMasked(own[player], r, c)) { throw new IllegalArgumentException(String.format("Can't defend %d,%d when player %d doesn't own that square", r, c, player)); }
		
	}
	
	public int findDefendingPlayer(int r, int c) { 
		int i = r * 5 + c;
		int p = -1;
		for(LetterPoint npt : neighbors[i]) { 
			p = p & ownership(npt);
		}
		return p;
	}
	
	public void updateDefended() { 
		for(int i = 0, r = 0; r < 5; r++) { 
			for(int c = 0; c < 5; c++, i++) { 
				int defender = findDefendingPlayer(r, c);
				
			}
		}
	}

	private static boolean isMasked(int mask, int r, int c) { 
		return ((1 << (r*5 + c)) & mask) != 0;
	}
	
	private static int setMask(int mask, int r, int c, boolean value) {
		int bit = 1 << (r*5+c);
		return value ? (mask | bit) : (mask & (-1 ^ bit));
	}
}

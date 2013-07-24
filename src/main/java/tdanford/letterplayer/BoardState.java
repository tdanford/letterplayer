package tdanford.letterplayer;

import static tdanford.letterplayer.MaskUtils.*;

/**
 * Four bit-masks, representing the current "ownership" state of a board -- which squares 
 * are owned, and which are defended, by each of the two players.
 * 
 * @author Timothy Danford
 *
 */
public class BoardState {
	
	public static final int BLUE = 0, RED = 1;

    public static final int RED_OWNERSHIP = 2, BLUE_OWNERSHIP = 1, NO_OWNERSHIP = 0;
	
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

    /*
     * 'own' and 'defend' are both arrays of length 2 --
     * each are a pair of bit-masks for the entire board, indicating which
     * squares each color owns and defends (respectively).
     */
	private int[] own, defend;
	
	public BoardState() {
		own = new int[] { 0, 0 };
		defend = new int[] { 0, 0 };
	}
	
	public BoardState(BoardState copy) { 
		own = new int[] { copy.own[0], copy.own[1] };
		defend = new int[] { copy.defend[0], copy.defend[1] };
	}

    int getOwnMask(int owner) { return own[owner]; }
    int getDefendMask(int defender) { return defend[defender]; }

    public boolean isFull() {
        return -1 == (-1 & (own[0] | own[1]));
    }

    public int countOwnership(int player) {
        return Integer.bitCount(own[player]);
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
        if(player != BLUE && player != RED) { throw new IllegalArgumentException(String.valueOf(player)); }
		if(!isDefended(1-player, pt)) { 
			own[player] = setMask(own[player], pt.getRow(), pt.getCol(), true);
			own[1-player] = setMask(own[1-player], pt.getRow(), pt.getCol(), false);
		}
	}
	
	private void setDefend(int player, int r, int c) { 
		if(!isMasked(own[player], r, c)) {
            throw new IllegalArgumentException(String.format("Can't defend %d,%d when player %d doesn't own that square", r, c, player));
        }
        defend[player] = setMask(defend[player], r, c, true);
	}
	
	int findDefendingPlayer(int r, int c) {
		int i = r * 5 + c;
		int p = 3;
		for(LetterPoint npt : neighbors[i]) { 
			p = p & ownership(npt);
		}
		return p;
	}
	
	public void updateDefended() {
        defend[RED] = 0; defend[BLUE] = 0;

		for(int i = 0, r = 0; r < 5; r++) { 
			for(int c = 0; c < 5; c++, i++) { 
				int defender = findDefendingPlayer(r, c);
                if(defender == RED_OWNERSHIP) { setDefend(RED, r, c); }
                else if (defender == BLUE_OWNERSHIP) { setDefend(BLUE, r, c); }
			}
		}
	}
}

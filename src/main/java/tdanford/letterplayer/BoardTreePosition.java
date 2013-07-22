package tdanford.letterplayer;

/**
 * A node in the game tree, representing a past, present, or future state of the game.
 * 
 * @author Timothy Danford
 */
public class BoardTreePosition {

	private Board board;
	
	private Word lastWord;
	
	private boolean isRedPlayer;
	
	private BoardTreePosition previousPosition;
	
	private BoardState state;

	public BoardTreePosition(Board b, boolean isRedPlayer) {
		this.board = b;
		this.isRedPlayer = isRedPlayer;
		previousPosition = null;
		lastWord = null;
		state = new BoardState();
	}
	
	public BoardTreePosition(BoardTreePosition p, Word w) {
		board = p.board;
		lastWord = w;
		previousPosition = p;
		isRedPlayer = !p.isRedPlayer;
		state = null; // TODO FIX ME.
	}

    public boolean isRedPlayer() { return isRedPlayer; }

    public Board getBoard() { return board; }

    public BoardTreePosition getPrevious() { return previousPosition; }

    public BoardState getBoardState() { return state; }


}

package tdanford.letterplayer;

/**
 * A node in the game tree, representing a past, present, or future state of the game.
 * 
 * @author Timothy Danford
 */
public class BoardPosition {

	private Board board;
	
	private Word lastWord;
	
	private boolean isRedPlayer;
	
	private BoardPosition previousPosition;
	
	private BoardState state;

	public BoardPosition(Board b, boolean isRedPlayer) {
		this.board = b;
		this.isRedPlayer = isRedPlayer;
		previousPosition = null;
		lastWord = null;
		state = new BoardState();
	}
	
	public BoardPosition(BoardPosition p, Word w) { 
		board = p.board;
		lastWord = w;
		previousPosition = p;
		isRedPlayer = !p.isRedPlayer;
		state = null; // TODO FIX ME.
	}
	
}

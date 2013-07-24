package tdanford.letterplayer;

/**
 * A node in the game tree, representing a past, present, or future state of the game.
 * 
 * @author tdanford
 */
public class BoardTreePosition {

	private Board board;
	private Word lastWord;
    private WordPlay lastPlay;
	private boolean isRedPlayer;
	private BoardTreePosition previousPosition;
	private BoardState state;

	public BoardTreePosition(Board b, boolean isRedPlayer) {
		this.board = b;
		this.isRedPlayer = isRedPlayer;
		previousPosition = null;
		lastWord = null;
        lastPlay = null;
		state = new BoardState();
	}

    public boolean isFinal() { return state.isFull(); }

    public int[] score() {
        return new int[] {
                state.countOwnership(BoardState.RED),
                state.countOwnership(BoardState.BLUE)
        };
    }

    public boolean isRedPlayer() { return isRedPlayer; }

    public Board getBoard() { return board; }

    public BoardTreePosition getPrevious() { return previousPosition; }

    public BoardState getBoardState() { return state; }

    public WordPlay getLastPlay() { return lastPlay; }

    public BoardTreePosition play(WordPlay play) {
        return this; // TODO FIX ME
    }

    // this method should probably go in Board.
    public BoardTreePosition play(String word, String ambiguity) {
        LetterPoint[] pts =new LetterPoint[word.length()];
        for(int i = 0; i < pts.length; i++) {
            pts[i] = board.findPoint(word.charAt(i), ambiguity.charAt(i));
        }

        return play(new WordPlay(pts));
    }
}

package tdanford.letterplayer;

import java.util.*;
import java.io.*;

/**
 *
 * GameLoader reads a game from a file.
 *
 * The first five lines are the board -- five characters each.
 *
 * The moves are listed after the board; each move is given as two lines.
 * The first line of the move is the word played, and the second line is the
 * 'ambiguity code' for that word, indicating which letters on the board are used
 * for the word.
 *
 * User: tdanford
 * Date: 7/21/13
 */
public class GameLoader {

    private Board board;
    private ArrayList<BoardTreePosition> positions;
    private ArrayList<WordPlay> plays;

    public GameLoader(Reader r) throws IOException {
        plays = new ArrayList<WordPlay>();
        positions = new ArrayList<BoardTreePosition>();

        BufferedReader br = new BufferedReader(r);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 5; i++) {
            String line = br.readLine();
            if(line == null) {
                throw new IOException("Unexpected end of file");
            }
            sb.append(line.trim());
        }

        try {
            board = new Board(sb.toString());
            positions.add(new BoardTreePosition(board, true));
            BoardTreePosition current = positions.get(0);

            String move;
            while((move = br.readLine()) != null) {
                String ambig = br.readLine();
                if(ambig == null) {
                    throw new IOException("Unexpected end of file");
                }

                if(ambig.length() != move.length()) {
                    throw new IllegalArgumentException("move and ambiguity lines must match lengths");
                }

                current = current.play(move, ambig);
                WordPlay play = current.getLastPlay();

                positions.add(current);
                plays.add(play);
            }
        } catch(IllegalArgumentException e) {
            throw new IOException("Error occurred while parsing file", e);
        }
    }

    public Board getBoard() { return board; }

    public int getNumPlays() { return plays.size(); }

    public WordPlay getPlay(int i) { return plays.get(i); }

    public BoardTreePosition getPosition(int i) { return positions.get(i); }
}

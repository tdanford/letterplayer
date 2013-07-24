package tdanford.letterplayer;

import java.io.IOException;
import java.util.*;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import static org.mockito.Mock.*;

/**
 * PlayTest puts all the tests together, and tests the ability of the Board,
 * BoardTreePosition, BoardState, and WordPlay classes to simulate and track a
 * game of LetterPress.
 *
 * User: tdanford
 * Date: 7/21/13
 */
public class PlayTest {

    @Test
    public void testBasePlay() throws IOException {
        Board b = new Board(
                "lndhh" +
                "tbech" +
                "wrsep" +
                "iebur" +
                "afbae");

        BoardTreePosition position = new BoardTreePosition(b, true);

        position = position.play("unwatchable", "...0..210.0");
    }
}

package tdanford.letterplayer;

import java.io.IOException;
import java.util.*;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import static org.mockito.Mock.*;
import static tdanford.letterplayer.MaskUtils.*;

/**
 * User: tdanford
 * Date: 7/21/13
 */
public class BoardStateTest {

    @Test
    public void testSimpleOwnership() {
        BoardState s = new BoardState();

        s.own(BoardState.RED, new LetterPoint(3, 3));
        s.updateDefended();

        assertTrue(s.isOwned(BoardState.RED, new LetterPoint(3, 3)));
        assertEquals(s.ownership(new LetterPoint(3, 3)), BoardState.RED_OWNERSHIP,
                String.format("Board ownership %d should have been %d",
                s.ownership(new LetterPoint(3, 3)), BoardState.RED));
        assertFalse(s.isDefended(BoardState.RED, new LetterPoint(3, 3)));

        s.own(BoardState.RED, new LetterPoint(2, 3));
        s.own(BoardState.RED, new LetterPoint(4, 3));
        s.own(BoardState.RED, new LetterPoint(3, 2));
        s.own(BoardState.RED, new LetterPoint(3, 4));
        s.updateDefended();

        assertTrue(s.isDefended(BoardState.RED, new LetterPoint(3, 3)));

        s.own(BoardState.BLUE, new LetterPoint(2, 3));
        s.updateDefended();

        assertFalse(s.isDefended(BoardState.RED, new LetterPoint(3, 3)), "\n" + printMask(s.getOwnMask(BoardState.RED)));
        assertFalse(s.isDefended(BoardState.BLUE, new LetterPoint(2, 3)));
    }
}

package tdanford.letterplayer;

import java.io.IOException;
import java.util.*;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import static org.mockito.Mock.*;

/**
 * User: tdanford
 * Date: 7/21/13
 */
public class BoardTreePositionTest {

    @Test
    public void testBoardPositionConstructor() throws IOException {

        Board b = new Board("abcdeabcdeabcdeabcdeabcde");
        BoardTreePosition p = new BoardTreePosition(b, true);

        assertEquals(p.getBoard(), b);
        assertEquals(p.isRedPlayer(), true);
        assertEquals(p.getPrevious(), null);
    }
}

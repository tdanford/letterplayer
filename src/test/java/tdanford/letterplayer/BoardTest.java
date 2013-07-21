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
public class BoardTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testShortBoardConstructorThrowsException() throws IOException {
        new Board(new char[] { 'a', 'b', 'c' });
    }

    @Test
    public void testBoardWithDistinctLetters() throws IOException {
        char[] letters = new char[] {
                'a', 'b', 'c', 'd', 'e',
                'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y'
        };
        Board b = new Board(letters);

        assertEquals(b.getLetterSet().count('a'), 1);
        assertEquals(b.getLetterSet().count('y'), 1);
        assertEquals(b.getLetterSet().getNumLetters(), 25);

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                int ii = i * 5 + j;
                assertEquals(b.getLetter(i, j), letters[ii]);
            }
        }

        Board b2 = new Board("abcdefghijklmnopqrstuvwxy");
        assertEquals(b, b2);
        assertEquals(b.hashCode(), b2.hashCode());

        Board b3 = new Board("abcdefghijklmnopqrstuvwxx");

        assertNotEquals(b, b3);
        assertNotEquals(b.hashCode(), b3.hashCode());
    }

    @Test
    public void testBoardWithDuplicateLetters() throws IOException {
        char[] letters = new char[] {
                'a', 'b', 'c', 'd', 'e',
                'a', 'b', 'c', 'd', 'e',
                'a', 'b', 'c', 'd', 'e',
                'a', 'b', 'c', 'd', 'e',
                'a', 'b', 'c', 'd', 'e',
        };
        Board b = new Board(letters);

        assertEquals(b.getLetterSet().count('a'), 5);
        assertEquals(b.getLetterSet().count('e'), 5);
        assertEquals(b.getLetterSet().getNumLetters(), 5);

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                int ii = i * 5 + j;
                assertEquals(b.getLetter(i, j), letters[ii]);
            }
        }

        Board b2 = new Board("abcdeabcdeabcdeabcdeabcde");

        assertEquals(b.hashCode(), b2.hashCode());
        assertEquals(b, b2);
    }
}

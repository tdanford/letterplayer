package tdanford.letterplayer;

import java.util.*;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import static org.mockito.Mock.*;

/**
 * User: tdanford
 * Date: 7/22/13
 */
public class WordPlayTest {

    @Test
    public void testConstructor() {
        new WordPlay(new LetterPoint(1, 1), new LetterPoint(2, 2), new LetterPoint(3, 3));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConstructorThrowsExceptionOnDuplicate() {
        new WordPlay(new LetterPoint(1, 1), new LetterPoint(2, 2), new LetterPoint(1, 1));
    }

    @Test
    public void testEquality() {

        WordPlay wp1 = new WordPlay(new LetterPoint(1, 1), new LetterPoint(2, 2), new LetterPoint(3, 3));
        WordPlay wp2 = new WordPlay(new LetterPoint(1, 1), new LetterPoint(2, 2), new LetterPoint(3, 3));
        WordPlay wp3 = new WordPlay(new LetterPoint(1, 1), new LetterPoint(2, 2), new LetterPoint(3, 4));
        WordPlay wp4 = new WordPlay(new LetterPoint(1, 1), new LetterPoint(2, 2), new LetterPoint(3, 3), new LetterPoint(3, 4));

        assertEquals(wp1, wp2);
        assertNotEquals(wp1, wp3);
        assertNotEquals(wp1, wp4);
        assertNotEquals(wp3, wp4);
    }
}

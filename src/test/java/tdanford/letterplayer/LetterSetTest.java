package tdanford.letterplayer;

import java.util.*;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import static org.mockito.Mock.*;

/**
 * User: tdanford
 * Date: 7/21/13
 */
public class LetterSetTest {

    @Test
    public void testLetterSetConstructor() {
        LetterSet ls = new LetterSet('a', 'b', 'c');

        assertEquals(ls.count('a'), 1);
        assertEquals(ls.count('b'), 1);
        assertEquals(ls.count('c'), 1);
        assertEquals(ls.getNumLetters(), 3);

        ls = new LetterSet('a', 'a', 'a', 'b');

        assertEquals(ls.count('a'), 3);
        assertEquals(ls.count('b'), 1);
        assertEquals(ls.getNumLetters(), 2);
    }

    @Test
    public void testLetterSetContainment() {
        LetterSet ls1 = new LetterSet('a', 'b', 'c');
        LetterSet ls2 = new LetterSet('a', 'b', 'b', 'c');
        LetterSet ls3 = new LetterSet('a', 'a', 'b', 'c');
        LetterSet ls4 = new LetterSet('a', 'a', 'a', 'a');

        assertTrue(ls1.containsLetterSet(ls1), "each letter set should contain itself");
        assertTrue(ls2.containsLetterSet(ls2), "each letter set should contain itself");
        assertTrue(ls3.containsLetterSet(ls3), "each letter set should contain itself");
        assertTrue(ls4.containsLetterSet(ls4), "each letter set should contain itself");

        assertTrue(ls2.containsLetterSet(ls1));
        assertTrue(ls3.containsLetterSet(ls1));
        assertFalse(ls2.containsLetterSet(ls3));
        assertFalse(ls3.containsLetterSet(ls2));

        assertFalse(ls4.containsLetterSet(ls1));
        assertFalse(ls4.containsLetterSet(ls2));
        assertFalse(ls4.containsLetterSet(ls3));

        assertFalse(ls2.containsLetterSet(ls4));
        assertFalse(ls3.containsLetterSet(ls4));

        LetterSet ls5 = new LetterSet('a', 'a', 'b', 'b', 'c');

        assertTrue(ls5.containsLetterSet(ls2));
        assertTrue(ls5.containsLetterSet(ls3));
        assertFalse(ls5.containsLetterSet(ls4));
    }

    @Test
    public void testToString() {
        LetterSet ls = new LetterSet('a', 'a', 'b', 'c');
        assertEquals(ls.toString(), "{'a': 2, 'b': 1, 'c': 1}");
    }

    @Test
    public void testHashCode() {
        LetterSet ls1 = new LetterSet('a', 'b', 'c');
        LetterSet ls2 = new LetterSet('a', 'b', 'c');
        LetterSet ls3 = new LetterSet('a', 'a', 'b', 'c');

        assertEquals(ls1.hashCode(), ls2.hashCode());
        assertNotEquals(ls1.hashCode(), ls3.hashCode());
    }

    @Test
    public void testEquality() {
        LetterSet ls1 = new LetterSet('a', 'b', 'c');
        LetterSet ls2 = new LetterSet('a', 'b', 'c');
        LetterSet ls3 = new LetterSet('b', 'c', 'a');

        LetterSet ls4 = new LetterSet('a', 'a', 'b', 'c');
        LetterSet ls5 = new LetterSet('a', 'b', 'a', 'c');

        assertEquals(ls1, ls2);
        assertEquals(ls1, ls3);
        assertEquals(ls4, ls5);
        assertNotEquals(ls1, ls4);

        assertEquals(ls1, new LetterSet("abc"));
        assertEquals(ls1, new LetterSet("cba"));

        assertNotEquals(ls1, new LetterSet("abcd"));
    }
}


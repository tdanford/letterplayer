package tdanford.letterplayer;

import java.util.*;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import static org.mockito.Mock.*;

/**
 * User: tdanford
 * Date: 7/22/13
 */
public class LetterPointTest {

    @Test
    public void testConstructor() {
        new LetterPoint(1, 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConstructorExceptionOnNegativeCoords() {
        new LetterPoint(-1, 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConstructorExceptionOnLargeCoords() {
        new LetterPoint(1, 5);
    }

    @Test
    public void testAccessors() {
        LetterPoint pt = new LetterPoint(2, 3);
        assertEquals(pt.getRow(), 2);
        assertEquals(pt.getCol(), 3);
    }

    @Test
    public void testNeighbors() {
        LetterPoint p1 = new LetterPoint(0, 0);

        Collection<LetterPoint> nbs = p1.neighbors();

        assertTrue(nbs.contains(p1), String.valueOf(nbs));
        assertTrue(nbs.contains(new LetterPoint(1, 0)));
        assertTrue(nbs.contains(new LetterPoint(0, 1)));
        assertEquals(nbs.size(), 3, String.valueOf(nbs));

        LetterPoint p2 = new LetterPoint(0, 2);
        Collection<LetterPoint> nbs2 = p2.neighbors();
        assertTrue(nbs2.contains(p2), String.valueOf(nbs2));
        assertTrue(nbs2.contains(new LetterPoint(0, 1)));
        assertTrue(nbs2.contains(new LetterPoint(0, 3)));
        assertTrue(nbs2.contains(new LetterPoint(1, 2)));
        assertEquals(nbs2.size(), 4, String.valueOf(nbs2));

        LetterPoint p3 = new LetterPoint(2, 2);
        Collection<LetterPoint> nbs3 = p3.neighbors();
        assertTrue(nbs3.contains(p3), String.valueOf(nbs3));
        assertTrue(nbs3.contains(new LetterPoint(1,2)));
        assertTrue(nbs3.contains(new LetterPoint(3,2)));
        assertTrue(nbs3.contains(new LetterPoint(2,1)));
        assertTrue(nbs3.contains(new LetterPoint(2,3)));
        assertEquals(nbs3.size(), 5, String.valueOf(nbs3));
    }

    @Test
    public void testString() {
        assertEquals((new LetterPoint(1, 3)).toString(), "<1,3>");
    }

    @Test
    public void testEquality() {
        LetterPoint pt1 = new LetterPoint(1, 1);
        LetterPoint pt2 = new LetterPoint(1, 1);

        LetterPoint pt3 = new LetterPoint(1, 3);
        LetterPoint pt4 = new LetterPoint(3, 1);

        assertEquals(pt1, pt2);
        assertNotEquals(pt1, pt3);
        assertNotEquals(pt1, pt4);
        assertNotEquals(pt3, pt4);
    }

}

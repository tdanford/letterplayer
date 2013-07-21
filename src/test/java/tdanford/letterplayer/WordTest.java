package tdanford.letterplayer;

import java.util.*;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import static org.mockito.Mock.*;

/**
 * User: tdanford
 * Date: 7/18/13
 */
public class WordTest {

    @Test
    public void testWordConstructor() {
        Word w = new Word("test");
        assertNotNull(w);
        assertEquals(w.toString(), "test");
    }

    @Test
    public void testWordLetterSet() {
        Word w1 = new Word("abc");

        LetterSet ls1 = w1.getLetters();

        assertEquals(ls1.count('a'), 1);
        assertEquals(ls1.count('b'), 1);
        assertEquals(ls1.count('c'), 1);

        Word w2 = new Word("bbac");
        LetterSet ls2 = w2.getLetters();

        assertEquals(ls2.count('a'), 1);
        assertEquals(ls2.count('b'), 2);
        assertEquals(ls2.count('c'), 1);

        assertTrue(ls2.containsLetterSet(ls1));
        assertFalse(ls1.containsLetterSet(ls2));
    }

    @Test
    public void testWordPrefix() {
        Word w1 = new Word("aabc");
        Word w2 = new Word("aa");
        Word w3 = new Word("abc");

        assertTrue(w2.isPrefixOf(w1));

        assertFalse(w1.isPrefixOf(w2));
        assertFalse(w2.isPrefixOf(w3));
        assertFalse(w3.isPrefixOf(w1));
    }

    @Test
    public void testWordComparator() {
        Word w1 = new Word("");
        Word w2 = new Word("test");
        Word w3 = new Word("testing");
        Word w4 = new Word("uh");

        Word[] words = new Word[] { w1, w2, w3, w4 };

        for(int i = 0; i < words.length; i++) {
            for(int j = i + 1; j < words.length; j++) {
                String wa = words[i].toString(), wb = words[j].toString();

                assertEquals(words[i].compareTo(words[j]), wa.compareTo(wb));
                assertEquals(words[j].compareTo(words[i]), wb.compareTo(wa));
            }
        }
    }

    @Test
    public void testWordEquality() {
        Word w1 = new Word("foo");
        Word w2 = new Word("foo");
        Word w3 = new Word("bar");

        assertEquals(w1, w1);
        assertEquals(w1, w2);
        assertEquals(w1.hashCode(), w2.hashCode());

        assertNotEquals(w1, w3);
        assertNotEquals(w1.hashCode(), w3.hashCode());
        assertNotEquals(w2, w3);
        assertNotEquals(w2.hashCode(), w3.hashCode());
    }

    @Test
    public void testWordLength() {
        Word w1 = new Word("foo");
        Word w2 = new Word("foo ");
        Word w3 = new Word("bar");

        assertEquals(w1.length(), 3);
        assertEquals(w2.length(), 4);
        assertEquals(w3.length(), 3);
    }
}

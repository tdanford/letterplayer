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
public class WordSetTest {

    @Test
    public void testNoThreeLetterWords() throws IOException {
        WordSet ws = new WordSet();

        int c = 0;
        for(Word w : ws) {
            assertTrue(w.length() >= 3, String.format("word \"%s\" had length < 3", w));
            c += 1;
        }

        assertEquals(ws.size(), c, "words.size() doesn't equal number of words");
        assertEquals(ws.size(), 109441);  // number of 3+ length words in wordsEn.txt
    }

}

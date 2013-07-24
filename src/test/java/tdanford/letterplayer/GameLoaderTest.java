package tdanford.letterplayer;

import java.io.*;
import java.util.*;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import static org.mockito.Mock.*;

/**
 * User: tdanford
 * Date: 7/21/13
 */
public class GameLoaderTest {


    @Test(expectedExceptions = IOException.class)
    public void testEmptyThrowsException() throws IOException {
        GameLoader loader = new GameLoader(new StringReader(""));
    }

    @Test(expectedExceptions = IOException.class)
    public void testPartialBoardThrowsException() throws IOException {
        GameLoader loader = new GameLoader(new StringReader(
                "abcdef\n" +
                "defgh"));
    }

    @Test
    public void testCompleteBoardLoad() throws IOException {
        GameLoader loader = new GameLoader(new StringReader(
                "abcde\n" +
                        "abcde\n" +
                        "abcde\n" +
                        "abcde\n" +
                        "abcde\n"));

        assertNotNull(loader.getBoard());
    }

    @Test(expectedExceptions = IOException.class)
    public void testExceptionOnBadBoardCharacter() throws IOException {
        GameLoader loader = new GameLoader(new StringReader(
                "abcde\n" +
                        "a.cde\n" +
                        "abcde\n" +
                        "abcde\n" +
                        "abcde\n"));
    }

    @Test
    public void testOneSimpleWord() throws IOException {
        GameLoader loader = new GameLoader(new StringReader(
                "abcde\n" +
                        "abcde\n" +
                        "abcde\n" +
                        "abcde\n" +
                        "abcde\n" +
                        "cab\n" +
                        "111"
        ));

    }

    @Test(expectedExceptions = IOException.class)
    public void testExceptionOnIncompleteWord() throws IOException {
        GameLoader loader = new GameLoader(new StringReader(
                "abcde\n" +
                        "abcde\n" +
                        "abcde\n" +
                        "abcde\n" +
                        "abcde\n" +
                        "cab\n"
        ));

    }
}

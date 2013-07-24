package tdanford.letterplayer;

import java.io.IOException;
import java.util.*;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import static tdanford.letterplayer.MaskUtils.*;

/**
 * User: tdanford
 * Date: 7/23/13
 */
public class MaskUtilsTest {

    @Test
    public void testBaseMask() {
        assertEquals(Integer.bitCount(bits), 25);
    }

    @Test
    public void testViewMask() {
        assertEquals(MaskUtils.viewMask(bits), "1111111111111111111111111");
    }

    @Test
    public void testPrintMask() {
        assertEquals(MaskUtils.printMask(bits), "  01234\n\n0 11111\n1 11111\n2 11111\n3 11111\n4 11111");
    }

    @Test
    public void testMaskingFunctions() {
        int mask = 0;

        assertEquals(isMasked(mask, 2, 2), false, "2,2 initial");
        assertEquals(isMasked(mask, 3, 4), false, "3,4 initial");

        mask = setMask(mask, 3, 4, true);

        assertEquals(isMasked(mask, 2, 2), false, "2,2 first");
        assertEquals(isMasked(mask, 3, 4), true, "3,4 first");

        mask = setMask(mask, 2, 2, true);
        mask = setMask(mask, 3, 4, false);

        assertEquals(isMasked(mask, 3, 4), false, "2,2 second");
        assertEquals(isMasked(mask, 2, 2), true, "3,4 second");
    }
}

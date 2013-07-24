package tdanford.letterplayer;

/**
 * User: tdanford
 * Date: 7/23/13
 */
public class MaskUtils {

    public static final int bits = (1 << 25) - 1;

    public static int countBits(int mask) {
        return Integer.bitCount(mask & bits);
    }

    public static boolean isMasked(int mask, int r, int c) {
        return ((1 << (r*5 + c)) & mask) != 0;
    }

    public static int setMask(int mask, int r, int c, boolean value) {
        int bit = 1 << (r*5+c);
        return value ? (mask | bit) : (mask & (-1 ^ bit));
    }

    public static String viewMask(int mask) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 25; i++) {
            if((1 & (mask >> i)) > 0) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        return sb.toString();
    }

    public static String printMask(int mask) {
        String str = viewMask(mask);
        StringBuilder sb = new StringBuilder();
        sb.append("  01234\n\n0 ");
        for(int i = 0; i < 25; i++) {
            if(i > 0 && i % 5 == 0) {
                sb.append(String.format("\n%d ", (i/5)));
            }
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
}

package memoization;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * GridTraveler
 *
 * O(x * y * max(x, y) * 2) time complexity
 * O((x + y) * max(x, y)) space complexity
 **/
public class GridTraveler {
    final BigInteger res;

    public GridTraveler(final short x, final short y) {
        res = travel(x, y);
    }

    private String makeKey(final short x, final short y) {
        return Arrays.toString(new int[]{ Math.min(x, y), Math.max(x, y) });
    }

    private BigInteger travel(final short x, final short y) {
        return travel(x, y, new HashMap<>());
    }

    private BigInteger travel(final short x,final short y, Map<String, BigInteger> memo) {
        if (x <= 0 || y <= 0) return BigInteger.ZERO;
        if (x == 1 || y == 1) return BigInteger.ONE;
        final var key = makeKey(x, y);
        var val = memo.get(key);
        if (val == null) {
            val = travel((short) (x - 1), y, memo).add(travel(x, (short) (y - 1), memo));
            memo.put(key, val);
        };
        return val;
    }

    @Override
    public String toString() {
        return res != null ? res.toString() : "null";
    }

    public static void main(final String []args) {
        final var start = new Date();
        final var x = args.length > 1 ? Short.parseShort(args[0]) : 2000;
        final var y = args.length > 2 ? Short.parseShort(args[1]) : 2000;
        System.out.printf("The number of unique paths on %dx%d grid is %s.%n", x, y, new GridTraveler(x, y));
        System.out.printf("For execution it took %d ms.%n", (new Date()).getTime() - start.getTime());
    }
}
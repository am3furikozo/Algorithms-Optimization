package tabulation;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

/**
 * GridTraveler
 *
 * O(x * y) time complexity
 * O(x * y) space complexity
 **/
public class GridTraveler {
    final BigInteger res;

    public GridTraveler(final short x, final short y) {
        res = travel(x, y);
    }

    private BigInteger travel(final short x,final short y) {
        final BigInteger[][] table = new BigInteger[x + 1][y + 1];
        short i, j;
        for (i = 0; i < table.length; i++) Arrays.fill(table[i], BigInteger.ZERO);
        table[1][1] = BigInteger.ONE;
        for (i = 0; i <= x; i++) for (j = 0; j <= y; j++) {
            final var cur = table[i][j];
            if (j + 1 <= x) table[i][j + 1] = table[i][j + 1].add(cur);
            if (i + 1 <= y) table[i + 1][j] = table[i + 1][j].add(cur);
        }
        return table[x][y];
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
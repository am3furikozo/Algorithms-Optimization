package tabulation;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

/**
 * Fibonacci
 *
 * O(n) time complexity
 * O(n) space complexity
 * ----------------------------
 * n - number
 **/
public class Fibonacci {
    final BigInteger res;

    public Fibonacci(final short num) {
        res = get(num);
    }

    private BigInteger get(final short num) {
        final BigInteger[] table = new BigInteger[num + 1];
        Arrays.fill(table, BigInteger.ZERO);
        table[1] = BigInteger.ONE;
        for (short i = 0; i < num - 1; i++) {
            var cur = table[i];
            table[i+1] = table[i+1].add(cur);
            table[i+2] = table[i+2].add(cur);
        }
        table[table.length - 1] = table[table.length - 1].add(table[table.length - 2]);
        return table[num];
    }

    @Override
    public String toString() {
        return res != null ? res.toString() : "null";
    }

    public static void main(final String []args) {
        final var start = new Date();
        final var value = args.length > 0  ? Short.parseShort(args[0]) : 3900;
        System.out.printf("Fibonacci for %d is %s.%n", value, new Fibonacci(value));
        System.out.printf("For execution it took %d ms.%n", (new Date()).getTime() - start.getTime());
    }
}
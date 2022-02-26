package memoization;

import java.math.BigInteger;
import java.util.*;

/**
 * Fibonacci
 *
 * O(n) time complexity
 * O(n) space complexity
 * ----------------------------
 * n - number
 **/
public class Fibonacci  {
    final BigInteger res;

    public Fibonacci(final short num) {
        res = get(num);
    }

    private BigInteger get(final short num) {
        return get(num, new HashMap<>());
    }

    private BigInteger get(final short num, final Map<Short, BigInteger> memo) {
        if (num <= 2) return BigInteger.ONE;
        var val = memo.get(num);
        if (val == null) {
            val = get((short) (num - 1), memo).add(get((short) (num - 2), memo));
            memo.put(num, val);
        };
        return val;
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
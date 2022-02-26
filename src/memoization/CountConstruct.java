package memoization;

import java.util.*;

/**
 * CanConstruct
 *
 * O(n * m^2) time complexity
 * O(m^2) space complexity
 * ----------------------------
 * m - target length, n - array length
 **/
public class CountConstruct {
    final Integer res;

    public CountConstruct(final String target, final String[] parts) {
        res = count(target, parts);
    }

    private Integer count(final String target, final String[] parts) {
        return count(target, parts, new HashMap<>());
    }

    private Integer count(final String target, final String[] parts, final Map<String, Integer> memo) {
        if (target.isEmpty()) return 1;
        var val = memo.get(target);
        if (val != null) return val;
        val = 0;
        for (final var part : parts) if (target.startsWith(part))
            val += count(target.replaceFirst(part, ""), parts, memo);
        memo.put(target, val);
        return val;
    }

    @Override
    public String toString() {
        return res != null ? res.toString() : "null";
    }

    public static void main(final String []args) {
        final var start = new Date();
        final var target = args.length > 1 ? args[0] : "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef";
        final String[] parts;
        if (args.length > 2) {
            parts = new String[args.length - 1];
            System.arraycopy(args, 1, parts, 0, args.length - 1);
        } else parts = new String[]{ "e", "ee", "eee", "eeee", "eeeee", "eeeeee" };
        System.out.printf(
                "Count of ways to create word \"%s\" from %s is %s.%n",
                target,
                Arrays.toString(parts),
                new CountConstruct(target, parts)
        );
        System.out.printf("For execution it took %d ms.%n", (new Date()).getTime() - start.getTime());
    }
}
package memoization;

import java.util.*;

/**
 * CanConstruct
 *
 * O(n*m^2) time complexity
 * O(m^2) space complexity
 * ----------------------------
 * m - target length, n - array length
 **/
public class CanConstruct {
    final Boolean res;

    public CanConstruct(final String target, String[] parts) {
        res = can(target, parts);
    }

    private Boolean can(final String target, String[] parts) {
        return can(target, parts, new HashSet<>());
    }

    private Boolean can(final String target, String[] parts, Set<String> memo) {
        if (target.isEmpty()) return true;
        if (memo.contains(target)) return false;
        for (final var part : parts)
            if (target.startsWith(part) && can(target.replaceFirst(part, ""), parts, memo)) return true;
        memo.add(target);
        return false;
    }

    @Override
    public String toString() {
        return res != null ? res.toString() : "null";
    }

    public static void main(String []args) {
        final var start = new Date();
        final var target = args.length > 1 ? args[0] : "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef";
        final String[] parts;
        if (args.length > 2) {
            parts = new String[args.length - 1];
            System.arraycopy(args, 1, parts, 0, args.length - 1);
        } else parts = new String[]{ "e", "ee", "eee", "eeee", "eeeee", "eeeeee" };
        System.out.printf(
                "Can we create word \"%s\" from %s: %s.%n",
                target,
                Arrays.toString(parts),
                new CanConstruct(target, parts)
        );
        System.out.printf("For execution it took %d ms.%n", (new Date()).getTime() - start.getTime());
    }
}
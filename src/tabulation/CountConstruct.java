package tabulation;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * CanConstruct
 *
 * O(n * m^2) time complexity
 * O(m) space complexity
 * ----------------------------
 * m - target length, n - array length
 **/
public class CountConstruct {
    final int res;

    public CountConstruct(final String target, final String[] parts) {
        res = count(target, parts);
    }

    private int count(final String target, final String[] parts) {
        final var table = new int[target.length() + 1];
        Arrays.fill(table, 0);
        table[0] = 1;
        for (var i = 0; i <= target.length(); i++) if (table[i] != 0)
            for (final var part : parts) if (target.startsWith(part, i)) table[part.length() + i] += 1;
        return table[target.length()];
    }

    @Override
    public String toString() {
        return String.valueOf(res);
    }

    public static void main(final String []args) {
        final var start = new Date();
        final var target = args.length > 1 ? args[0] : "hello";
        final String[] parts;
        if (args.length > 2) {
            parts = new String[args.length - 1];
            System.arraycopy(args, 1, parts, 0, args.length - 1);
        } else parts = new String[]{ "hel", "lo", "he", "llo", "he", "ll", "o" };
        System.out.printf(
                "Count of ways to create word \"%s\" from %s is %s.%n",
                target,
                Arrays.toString(parts),
                new CountConstruct(target, parts)
        );
        System.out.printf("For execution it took %d ms.%n", (new Date()).getTime() - start.getTime());
    }
}
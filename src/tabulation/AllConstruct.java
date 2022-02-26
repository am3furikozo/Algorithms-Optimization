package tabulation;

import java.util.*;
import java.util.stream.Collectors;

/**
 * AllConstruct
 *
 * O(n^m) time complexity
 * O(n^m) space complexity
 * ----------------------------
 * m - target length, n - array length
 **/
public class AllConstruct {
    final String[][] res;

    public AllConstruct(final String target, final String[] parts) {
        res = get(target, parts);
    }

    private String[][] get(final String target, final String[] parts) {
        final var table = new String[target.length() + 1][][];
        Arrays.fill(table, new String[][]{});
        table[0] = new String[][]{{}};
        for (var i = 0; i <= target.length(); i++) for (final var part : parts) if (target.startsWith(part, i))
            for (var j = 0; j < table[i].length; j++) {
                final var newCombinations = Arrays.copyOf(table[i][j], table[i][j].length + 1);
                newCombinations[newCombinations.length - 1] = part;
                final var combinations =  Arrays.copyOf(
                        table[i + part.length()],
                        table[i + part.length()].length + 1);
                combinations[combinations.length - 1] = newCombinations;
                table[i + part.length()] = combinations;
            }
        return table[target.length()];
    }

    @Override
    public String toString() {
        return Arrays.deepToString(res);
    }

    public static void main(final String []args) {
        final var start = new Date();
        final var target = args.length > 1 ? args[0] : "aaaaaaaaaaaaaa";
        final String[] parts;
        if (args.length > 2) {
            parts = new String[args.length - 1];
            System.arraycopy(args, 1, parts, 0, args.length - 1);
        } else parts = new String[]{ "a", "aa", "aaa", "aaaa", "aaaaa" };
        System.out.printf(
                "Ways to create word \"%s\" from %s are %s.%n",
                target,
                Arrays.toString(parts),
                new AllConstruct(target, parts)
        );
        System.out.printf("For execution it took %d ms.%n", (new Date()).getTime() - start.getTime());
    }
}
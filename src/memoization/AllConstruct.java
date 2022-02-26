package memoization;

import java.util.*;
import java.util.stream.Collectors;

/**
 * AllConstruct
 *
 * O(n^m) time complexity
 * O(m) space complexity
 * ----------------------------
 * m - target length, n - array length
 **/
public class AllConstruct {
    final Collection<List<String>> res;

    public AllConstruct(final String target, final String[] parts) {
        res = get(target, parts);
    }

    private Collection<List<String>> get(final String target, final String[] parts) {
        return get(target, parts, new HashMap<>());
    }

    private Collection<List<String>> get(
            final String target,
            final String[] parts,
            final Map<String, Collection<List<String>>> memo) {
        if (target.isEmpty()) return new ArrayList<>(1){{ add(new ArrayList<>(0)); }};
        var val = memo.get(target);
        if (val != null) return val;
        else val = new ArrayList<>();
        for (var part : parts) if (target.startsWith(part))
            val.addAll(get(target.replaceFirst(part, ""), parts, memo)
                    .stream()
                    .peek(way -> way.add(0, part))
                    .collect(Collectors.toList()));
        memo.put(target, val);
        return val;
    }

    @Override
    public String toString() {
        return res != null ? res.toString() : "null";
    }

    public static void main(final String []args) {
        final var start = new Date();
        final var target = args.length > 1 ? args[0] : "aaaaaaaaaaaaaaaaaaaaaaaaaaz";
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
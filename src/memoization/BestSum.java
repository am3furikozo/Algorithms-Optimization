package memoization;

import java.util.*;
import java.util.stream.Collectors;

/**
 * BestSum
 *
 * O(n*m^2) time complexity
 * O(m) space complexity
 * ----------------------------
 * m - target, n - array length
 **/
public class BestSum {
    final Collection<Integer> res;

    public BestSum(final int target, final List<Integer> nums) {
        res = find(target, nums);
    }

    private Collection<Integer> find(final int target, final List<Integer> nums) {
        return find(target, nums, new HashMap<>());
    }

    private Collection<Integer> find(
            final int target,
            final Collection<Integer> nums,
            final Map<Integer, Collection<Integer>> memo) {
        if (target == 0) return new ArrayList<>();
        if (target < 0) return null;
        Collection<Integer> shortest = null;
        if (memo.containsKey(target)) return memo.get(target);
        for (final var num : nums) {
            var combination = find(target - num, nums, memo);
            if (combination != null && (shortest == null || (combination.size() + 1) < shortest.size())) {
                shortest = new ArrayList<>(){{ addAll(combination); }};
                shortest.add(num);
            }
        }
        memo.put(target, shortest);
        return shortest;
    }

    @Override
    public String toString() {
        return res != null ? res.toString() : "null";
    }

    public static void main(final String []args) {
        final var start = new Date();
        final var target = args.length > 1 ? Integer.parseInt(args[0]) : 8;
        final List<Integer> nums;
        if (args.length > 2) {
            nums = new ArrayList<>(args.length - 1);
            for (var i = 1; i < args.length; i++) nums.add(Integer.parseInt(args[i]));
        } else nums = List.of(1, 4, 5);
        System.out.printf(
                "Optimal way to crate number %d from %s is sum of %s.%n",
                target,
                nums,
                new BestSum(target, nums)
        );
        System.out.printf("For execution it took %d ms.%n", (new Date()).getTime() - start.getTime());
    }
}
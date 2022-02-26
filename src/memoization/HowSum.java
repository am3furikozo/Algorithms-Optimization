package memoization;

import java.util.*;

/**
 * HowSum
 *
 * O(n * m^2) time complexity
 * O(m^2) space complexity
 * ----------------------------
 * m - target, n - array length
 **/
public class HowSum {
    final Collection<Integer> res;

    public HowSum(final int target, final Collection<Integer> nums) {
        res = how(target, nums);
    }

    private Collection<Integer> how(final int target, final Collection<Integer> nums) {
        return how(target, nums, new HashSet<>());
    }

    private List<Integer> how(final int target, final Collection<Integer> nums, final Set<Integer> memo) {
        if (target == 0) return new ArrayList<>();
        if (target < 0 || memo.contains(target)) return null;
        for (final var num : nums) {
            final var val = how(target - num, nums, memo);
            if (val != null) {
                val.add(num);
                return val;
            }
        }
        memo.add(target);
        return null;
    }

    @Override
    public String toString() {
        return res != null ? res.toString() : "null";
    }

    public static void main(final String []args) {
        final var start = new Date();
        final var target = args.length > 1 ? Integer.parseInt(args[0]) : 300;
        final Collection<Integer> nums;
        if (args.length > 2) {
            nums = new ArrayList<>(args.length - 1);
            for (var i = 1; i < args.length; i++) nums.add(Integer.parseInt(args[i]));
        } else nums = List.of(7, 14);
        System.out.printf(
                "We can create the number %d from %s with sum of %s.%n",
                target,
                nums,
                new HowSum(target, nums)
        );
        System.out.printf("For execution it took %d ms.%n", (new Date()).getTime() - start.getTime());
    }
}
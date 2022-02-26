package tabulation;

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
    final Integer[] res;

    public HowSum(final int target, final Integer[] nums) {
        res = how(target, nums);
    }

    private Integer[] how(final int target, final Integer[] nums) {
        final var table = new Integer[target + 1][];
        Arrays.fill(table, null);
        table[0] = new Integer[0];
        for (var i = 0; i <= target; i++) if (table[i] != null)
            for (final var num : nums) if (i + num <= target && table[i + num] == null) {
                var n = Arrays.copyOf(table[i], table[i].length + 1);
                n[n.length - 1] = num;
                table[i + num] = n;
            }
        return table[target];
    }

    @Override
    public String toString() {
        return res != null ? Arrays.toString(res) : "null";
    }

    public static void main(final String []args) {
        final var start = new Date();
        final var target = args.length > 1 ? Integer.parseInt(args[0]) : 301;
        final Integer[] nums;
        if (args.length > 2) {
            nums = new Integer[args.length - 1];
            for (var i = 1; i < args.length; i++) nums[i - 1] = Integer.parseInt(args[i]);
        } else nums = new Integer[]{7, 14};
        System.out.printf(
                "We can create the number %d from %s with sum of %s.%n",
                target,
                Arrays.toString(nums),
                new HowSum(target, nums)
        );
        System.out.printf("For execution it took %d ms.%n", (new Date()).getTime() - start.getTime());
    }
}
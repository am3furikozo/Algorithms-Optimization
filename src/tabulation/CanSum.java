package tabulation;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CanSum
 *
 * O(M * n) time complexity
 * O(m) space complexity
 * ----------------------------
 * m - target, n - array length
 **/
public class CanSum {
    final Boolean res;

    public CanSum(final int target, final int[] nums) {
        res = can(target, nums);
    }

    private boolean can(final int target, final int[] nums) {
        final var table = new boolean[target + 1];
        Arrays.fill(table, false);
        table[0] = true;
        for (var i = 0; i <= target; i++)
            if (table[i]) for (final var num : nums) if ((i + num) < table.length) table[i + num] = true;
        return table[target];
    }

    @Override
    public String toString() {
        return res != null ? res.toString() : "null";
    }

    public static void main(final String []args) {
        final var start = new Date();
        final var target = args.length > 1 ? Integer.parseInt(args[0]) : 301;
        final int[] nums;
        if (args.length > 2) {
            nums = new int[args.length - 1];
            for (var i = 1; i < args.length; i++) nums[i - 1] = Integer.parseInt(args[i]);
        } else nums = new int[]{7, 14};
        System.out.printf(
                "Can we create a number %d from %s? %s.%n",
                target,
                Arrays.toString(nums),
                new CanSum(target, nums)
        );
        System.out.printf("For execution it took %d ms.%n", (new Date()).getTime() - start.getTime());
    }
}
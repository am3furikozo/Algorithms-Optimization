package tabulation;

import java.util.*;

/**
 * BestSum
 *
 * O(n*m^2) time complexity
 * O(m^2) space complexity
 * ----------------------------
 * m - target, n - array length
 **/
public class BestSum {
    final Integer[] res;

    public BestSum(final int target, final int[] nums) {
        res = find(target, nums);
    }

    private Integer[] find(final int target, final int[] nums) {
        final var table = new Integer[target + 1][];
        Arrays.fill(table, null);
        table[0] = new Integer[0];
        for (var i = 0; i <= target; i++) if (table[i] != null)
            for (final var num : nums) if (i + num <= target)
                if (table[i + num] == null || table[i + num].length > (table[i].length + 1)) {
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
        final var target = args.length > 1 ? Integer.parseInt(args[0]) : 8;
        final int[] nums;
        if (args.length > 2) {
            nums = new int[args.length - 1];
            for (var i = 1; i < args.length; i++) nums[i - 1] = Integer.parseInt(args[i]);
        } else nums = new int[]{3, 2, 5, 8};
        System.out.printf(
                "Optimal way to crate number %d from %s is sum of %s.%n",
                target,
                Arrays.toString(nums),
                new BestSum(target, nums)
        );
        System.out.printf("For execution it took %d ms.%n", (new Date()).getTime() - start.getTime());
    }
}
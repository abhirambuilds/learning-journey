class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Option 1: Satisfy all of need1 using type 1 items, and all of need2 using type 2 items
        long a = (long) need1 * cost1 + (long) need2 * cost2;

        // Option 2: Satisfy the maximum of the two needs entirely using type 3 items
        long b = (long) costBoth * Math.max(need1, need2);

        // Option 3: Use type 3 items for the overlapping requirement (min(need1, need2)),
        // and cover the remaining deficit using type 1 or type 2 items respectively
        int mn = Math.min(need1, need2);
        long c = (long) costBoth * mn 
               + (long) (need1 - mn) * cost1 
               + (long) (need2 - mn) * cost2;

        return Math.min(a, Math.min(b, c));
    }
}

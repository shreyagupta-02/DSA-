class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long left = 1;
        long right = (long) Arrays.stream(coins).min().getAsInt() * k;

        while (left < right) {
            long mid = left + (right - left) / 2;

            if (count(coins, mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private long count(int[] coins, long x) {
        int n = coins.length;
        long cnt = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;
                    lcm = lcm(lcm, coins[i]);

                    if (lcm > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) continue;

            long add = x / lcm;

            if ((bits & 1) == 1)
                cnt += add;
            else
                cnt -= add;
        }

        return cnt;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}
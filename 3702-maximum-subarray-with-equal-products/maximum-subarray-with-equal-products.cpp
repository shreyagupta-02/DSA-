
class Solution {
public:
    int maxLength(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            long long product = 1;
            int g = 0;
            int l = 1;

            for (int j = i; j < n; j++) {
                product *= nums[j];

                g = gcd(g, nums[j]);

                l = (l / gcd(l, nums[j])) * nums[j];

                if (product == 1LL * g * l) {
                    ans = max(ans, j - i + 1);
                }

                // Maximum possible GCD * LCM is 25200
                if (product > 25200) {
                    break;
                }
            }
        }

        return ans;
    }
};

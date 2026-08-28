class Solution {
    private String target;
    private int n;
    private char mid;
    private int[] cnt;

    public String lexPalindromicPermutation(String s, String target) {
        this.target = target;
        n = s.length();

        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int odd = 0;
        mid = 0;

        cnt = new int[26];

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                odd++;
                mid = (char) ('a' + i);
            }
            cnt[i] = freq[i] / 2;
        }

        if (odd > 1) return "";

        StringBuilder left = new StringBuilder();
        int m = n / 2;

        for (int pos = 0; pos < m; pos++) {

            boolean found = false;

            for (int ch = 0; ch < 26; ch++) {
                if (cnt[ch] == 0) continue;

                cnt[ch]--;
                left.append((char) ('a' + ch));

                if (canFinish(left)) {
                    found = true;
                    break;
                }

                left.deleteCharAt(left.length() - 1);
                cnt[ch]++;
            }

            if (!found) return "";
        }

        String ans = buildPalindrome(left.toString(), cnt);
        return ans.compareTo(target) > 0 ? ans : "";
    }

    private boolean canFinish(StringBuilder prefix) {

        StringBuilder left = new StringBuilder(prefix);

        for (int ch = 25; ch >= 0; ch--) {
            while (cnt[ch]-- > 0) {
                left.append((char) ('a' + ch));
            }
        }

        String candidate = buildPalindrome(left.toString(), null);

        for (int ch = 0; ch < 26; ch++) {
            cnt[ch] = countChar(left.toString(), (char) ('a' + ch))
                    - countChar(prefix.toString(), (char) ('a' + ch));
        }

        return candidate.compareTo(target) > 0;
    }

    private String buildPalindrome(String leftHalf, int[] dummy) {
        StringBuilder res = new StringBuilder(leftHalf);

        if (mid != 0) res.append(mid);

        res.append(new StringBuilder(leftHalf).reverse());

        return res.toString();
    }

    private int countChar(String s, char c) {
        int ans = 0;
        for (char x : s.toCharArray()) {
            if (x == c) ans++;
        }
        return ans;
    }
}
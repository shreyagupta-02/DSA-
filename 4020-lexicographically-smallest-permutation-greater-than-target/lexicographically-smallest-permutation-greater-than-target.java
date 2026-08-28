class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        for (int i = 0; i < n; i++) {
            int ch = target.charAt(i) - 'a';
            if (cnt[ch] == 0) break;
            cnt[ch]--;
        }

        cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        for (int i = n - 1; i >= 0; i--) {
            int cur = target.charAt(i) - 'a';

            boolean prefixValid = true;
            int[] tmp = cnt.clone();

            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';
                if (tmp[c] == 0) {
                    prefixValid = false;
                    break;
                }
                tmp[c]--;
            }

            if (!prefixValid) continue;

            for (int bigger = cur + 1; bigger < 26; bigger++) {
                if (tmp[bigger] == 0) continue;

                tmp[bigger]--;

                StringBuilder ans = new StringBuilder();
                ans.append(target, 0, i);
                ans.append((char) ('a' + bigger));

                for (int c = 0; c < 26; c++) {
                    while (tmp[c]-- > 0) {
                        ans.append((char) ('a' + c));
                    }
                }

                return ans.toString();
            }
        }

        return "";
    }
}
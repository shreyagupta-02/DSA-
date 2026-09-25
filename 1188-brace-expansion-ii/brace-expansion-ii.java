class Solution {
    private String s;
    private int idx;

    private Set<String> product(Set<String> A, Set<String> B) {
        Set<String> res = new TreeSet<>();

        for (String a : A) {
            for (String b : B) {
                res.add(a + b);
            }
        }

        return res;
    }

    // expr := term (',' term)*
    private Set<String> parseExpr() {
        Set<String> res = parseTerm();

        while (idx < s.length() && s.charAt(idx) == ',') {
            idx++;
            Set<String> nxt = parseTerm();
            res.addAll(nxt);
        }

        return res;
    }

    // term := factor+
    private Set<String> parseTerm() {
        Set<String> res = new TreeSet<>();
        res.add("");

        while (idx < s.length()
                && s.charAt(idx) != '}'
                && s.charAt(idx) != ',') {

            Set<String> nxt = parseFactor();
            res = product(res, nxt);
        }

        return res;
    }

    // factor := letter | '{' expr '}'
    private Set<String> parseFactor() {
        Set<String> res = new TreeSet<>();

        char ch = s.charAt(idx);

        if (Character.isLowerCase(ch)) {
            res.add(String.valueOf(ch));
            idx++;
        } else {
            idx++; // skip '{'
            res = parseExpr();
            idx++; // skip '}'
        }

        return res;
    }

    public List<String> braceExpansionII(String expression) {
        s = expression;
        idx = 0;

        Set<String> ans = parseExpr();
        return new ArrayList<>(ans);
    }
}
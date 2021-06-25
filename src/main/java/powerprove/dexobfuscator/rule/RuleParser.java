package powerprove.dexobfuscator.rule;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class RuleParser {
    private TokenScanner scanner;

    private String curToken = "";

    public RuleParser(File ruleFile) {
        this.scanner = new TokenScanner(ruleFile);
    }

    private static Set<String> INIT_KEYWORDS = new HashSet<String>() {
            {
                add("public");
                add("private");
                add("interface");
                add("class");
                add("protected");
            }
    };

    private boolean isInitKeyword(String token) {
        if (true == INIT_KEYWORDS.contains(token)) {
            return true;
        }

        return false;
    }

    public boolean hasNext() {
        return scanner.hasNext();
    }

    public Rule next() {
        Rule rule = new Rule();

        boolean isFindClassToken = false;
        boolean isOpenBrace = false;
        boolean isOpenParenthesis = false;

        // 기존 token 정리
        if (false == curToken.isEmpty()) {
            if (curToken.equals("class")) {
                isFindClassToken = true;
            }
            rule.add(curToken);
            curToken = "";
        }

        while(scanner.hasNext()) {
            curToken += scanner.next();

            if (0 == rule.size()) {
                if (false == isInitKeyword(curToken)) {
                    throw new RuntimeException("wrong keyword \"" + curToken + "\" in line " + scanner.getLineNumber());
                }
            } else {
                if ((false == isOpenBrace)
                    && (true == isFindClassToken)
                    && (true == isInitKeyword(curToken))) {
                    break;
                }

                if (curToken.equals("{")) {
                    isOpenBrace = true;
                }

                if (curToken.equals("}")) {
                    rule.add(curToken);
                    curToken = "";
                    break;
                }

                if (true == curToken.contains("(")) {
                    isOpenParenthesis = true;
                }

                if (true == curToken.contains(")")) {
                    isOpenParenthesis = false;
                }

                if (true == isOpenParenthesis) {
                    // (...)을 전부 다 하나의 토큰으로 자름
                    continue;
                }
            }
            if (curToken.equals("class")) {
                isFindClassToken = true;
            }
            rule.add(curToken);
            curToken = "";
        }

        return rule;
    }
}

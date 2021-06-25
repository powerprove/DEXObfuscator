package powerprove.dexobfuscator.rule;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TokenScanner {
    private Scanner scanner;

    private String curLine;
    private int curLineOffset;

    private int lineNumber = 0;

    public TokenScanner(File file) {
        try {
            this.scanner = new Scanner(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public boolean hasNext() {
        if (null == curLine) {
            return scanner.hasNextLine();
        }
        if (curLine.length() <= curLineOffset) {
            if (false == scanner.hasNextLine()) {
                return false;
            }
        }
        return true;
    }

    public String next() {
        String token = "";

        while (true) {
            if (null == curLine) {
                curLine = scanner.nextLine();
                curLineOffset = 0;
                lineNumber++;
            }

            if (curLine.length() <= curLineOffset) {
                curLine = scanner.nextLine();
                curLineOffset = 0;
                lineNumber++;
            }

            for (; curLineOffset < curLine.length(); curLineOffset++) {
                char ch = curLine.charAt(curLineOffset);

                if (true == Character.isWhitespace(ch)) {
                    if (0 == token.length()) {
                        continue;
                    }

                    curLineOffset++;
                    return token;
                }

                token += ch;
            }
            if (0 != token.length()) {
                break;
            }
        }

        return token;
    }
}

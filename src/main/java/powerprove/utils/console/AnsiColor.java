package powerprove.utils.console;

public enum AnsiColor {
    RESET("\033[0m"),  // Text Reset

    // Regular Colors
    BLACK("\033[0;30m"),   // BLACK
    RED("\033[0;31m"),     // RED
    GREEN("\033[0;32m"),   // GREEN
    YELLOW("\033[0;33m"),  // YELLOW
    BLUE("\033[0;34m"),    // BLUE
    PURPLE("\033[0;35m"),  // PURPLE
    CYAN("\033[0;36m"),    // CYAN
    WHITE("\033[0;37m"),   // WHITE

    // Bold
    BLACK_BOLD("\033[1;30m"),  // BLACK
    RED_BOLD("\033[1;31m"),    // RED
    GREEN_BOLD("\033[1;32m"),  // GREEN
    YELLOW_BOLD("\033[1;33m"), // YELLOW
    BLUE_BOLD("\033[1;34m"),   // BLUE
    PURPLE_BOLD("\033[1;35m"), // PURPLE
    CYAN_BOLD("\033[1;36m"),   // CYAN
    WHITE_BOLD("\033[1;37m");  // WHITE

    public final String ansiCode;

    AnsiColor(String ansicode) {
        this.ansiCode = ansicode;
    }
}

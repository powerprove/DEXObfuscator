package powerprove.dexobfuscator.jar.lib;

import org.apache.commons.lang3.StringUtils;
import powerprove.utils.console.AnsiColor;

import java.io.PrintStream;

public class Logger {
    private static Logger logger = new Logger();

    private PrintStream printStream = null;
    private LogLevel logLevel = LogLevel.Verbose;

    public static Logger getLogger() {
        return logger;
    }

    public Logger() {
        setPrintStream(System.out);
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public void i(String message) {
        if (logLevel.ordinal() < LogLevel.Info.ordinal()) {
            return;
        }

        String prefix = getPrefix(LogLevel.Info);
        prefix = getStringWithAnsiColor(prefix, AnsiColor.GREEN);

        println(prefix, message);
    }

    public void v(String message) {
        if (logLevel.ordinal() < LogLevel.Verbose.ordinal()) {
            return;
        }

        String prefix = getPrefix(LogLevel.Verbose);
        prefix = getStringWithAnsiColor(prefix, AnsiColor.BLUE);

        println(prefix, message);
    }

    public void w(String message) {
        if (logLevel.ordinal() < LogLevel.Warning.ordinal()) {
            return;
        }

        String prefix = getPrefix(LogLevel.Warning);
        prefix = getStringWithAnsiColor(prefix, AnsiColor.PURPLE);

        println(prefix, message);
    }

    public void e(String message) {
        if (logLevel.ordinal() < LogLevel.Error.ordinal()) {
            return;
        }

        String prefix = getPrefix(LogLevel.Error);
        prefix = getStringWithAnsiColor(prefix, AnsiColor.RED);

        println(prefix, message);
    }

    private String getPrefix(LogLevel logLevel) {
        String prefix = logLevel.shortName + "/:";
        return prefix;
    }

    public static String getStringWithAnsiColor(String message, AnsiColor Color) {
//        final int SIZE_ANSI_COLOR = AnsiColor.BLACK.length();

        return Color.ansiCode + message + AnsiColor.RESET.ansiCode;
//        return new StringBuffer(message.length() + SIZE_ANSI_COLOR * 2)
//                .append(Color)
//                .append(message)
//                .append(AnsiColor.RESET)
//                .toString();
    }

    public void println() {
        printStream.println();
        printStream.flush();
    }

    public void println(String message) {
        printStream.println(message);
        printStream.flush();
    }

    public void println(String prefix, String message) {
        println(prefix + " " + message);
    }

    public enum LogLevel {
        Error("E"),
        Warning("W"),
        Info("I"),
        Verbose("V");

        private final String shortName;

        LogLevel(String shortName) {
            this.shortName = shortName;
        }
    }
}

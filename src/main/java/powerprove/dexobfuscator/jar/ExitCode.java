package powerprove.dexobfuscator.jar;

public enum ExitCode {
    SUCCESS(0),
    ERROR(1);

    private final int code;
    private ExitCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}

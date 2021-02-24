package powerprove.dexobfuscator.jar.lib;

public class Obfuscator {
    private final Configuration configuration;

    public Obfuscator(Configuration configuration) {
        this.configuration = configuration;
    }

    public void execute() {
        if (false == this.configuration.isApplyObfuscate()) {
            throw new RuntimeException("please setting obfuscate options.");
        }
    }
}

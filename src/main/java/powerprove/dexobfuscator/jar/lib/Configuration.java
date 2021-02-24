package powerprove.dexobfuscator.jar.lib;

import powerprove.utils.file.FileUtils;

import java.io.File;

public class Configuration {
    private final File inputFile;
    private final File outputFile;
    private final File ruleFile;

    public boolean verbose = false;

    /**
     * obfuscate options
     */
    public boolean applyObfuscateConstString = false;
    public boolean applyConvertReflection = false;


    // Required options
    public Configuration(String input, String output, String rule) throws Exception {
        inputFile = FileUtils.getAlreadyExistFile(input);
        outputFile = FileUtils.getNotExistFile(output);
        ruleFile = FileUtils.getAlreadyExistFile(rule);
    }

    public boolean isApplyObfuscate() {
        if ((true == applyObfuscateConstString)
            || (true == applyConvertReflection)) {
            return true;
        }

        return false;
    }

    public void print() {
        // TODO : use Logger
    }
}

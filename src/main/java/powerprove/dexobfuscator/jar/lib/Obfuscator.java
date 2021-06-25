package powerprove.dexobfuscator.jar.lib;

import powerprove.dexobfuscator.rule.RuleParser;

public class Obfuscator {
    private final Configuration configuration;

    public Obfuscator(Configuration configuration) {
        this.configuration = configuration;
    }

    public void printOptions() {
        Logger.getLogger().i("[Input File] " + configuration.inputFile.getAbsolutePath());
        Logger.getLogger().i("[Output File] " + configuration.outputFile.getAbsolutePath());
        Logger.getLogger().i("[Rule parser] " + configuration.ruleFile.getAbsolutePath());
    }
    public void execute() {
        if (false == this.configuration.isApplyObfuscate()) {
            throw new RuntimeException("please setting obfuscate options.");
        }

        RuleParser ruleParser = new RuleParser(this.configuration.ruleFile);
        while(ruleParser.hasNext()) {
            ruleParser.next().print();
        }
    }
}

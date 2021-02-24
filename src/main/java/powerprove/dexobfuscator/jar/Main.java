package powerprove.dexobfuscator.jar;

import org.apache.commons.cli.*;
import powerprove.dexobfuscator.jar.lib.Configuration;
import powerprove.dexobfuscator.jar.lib.Obfuscator;

import java.io.PrintStream;
import java.io.PrintWriter;

public class Main {
    private static PrintStream DEFAULT_OUT_STREAM = System.out;

    private static Options allOptions = new Options();

    public static void main (String[] args) {

        loadOptions();

        // Parse arguments
        CommandLineParser parser = new DefaultParser();
        CommandLine cmdLine = null;

        try {
            cmdLine = parser.parse(allOptions, args);
        } catch (ParseException e){
            e.printStackTrace();
            System.exit(ExitCode.ERROR.getCode());
        }

        // Print usage
        if (true == cmdLine.hasOption("help")) {
            usage();
            System.exit(ExitCode.ERROR.getCode());
        }

        // Print version
        if (true == cmdLine.hasOption("version")) {
            // TODO : print version
            System.exit(ExitCode.ERROR.getCode());
        }

        try {
            Configuration configuration = new Configuration(cmdLine.getOptionValue("input"),
                                                            cmdLine.getOptionValue("output"),
                                                            cmdLine.getOptionValue("rule"));

            configuration.verbose = cmdLine.hasOption("verbose");

            configuration.applyObfuscateConstString = cmdLine.hasOption("applyObfuscateConstString");
            configuration.applyConvertReflection = cmdLine.hasOption("applyConvertReflection");

            Obfuscator obfuscator = new Obfuscator(configuration);
            obfuscator.execute();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(ExitCode.ERROR.getCode());
        }

        System.exit(ExitCode.SUCCESS.getCode());
    }

    private static void loadOptions() {
        // input/output/rule
        Option inputOption = new Option(
                "i",
                "input",
                true,
                "target dex/apk file");

        Option outputOption = new Option(
                "o",
                "output",
                true,
                "output dex/apk file path");

        Option ruleOption = new Option(
                "r",
                "rule",
                true,
                "rule file path");

        Option helpOption = new Option(
                "h",
                "help",
                false,
                "print this help then exit");

        Option versionOption = new Option(
                "v",
                "version",
                false,
                "print the version then exit");

        Option verboseOption = new Option(
                null,
                "verbose",
                false,
                "print additional details");

        // obfuscate option
        Option convertReflectionOption = new Option(
                null,
                "applyConvertReflection",
                false,
                "apply convert reflection"
        );

        Option constStringObfuscateOption = new Option(
                null,
                "applyObfuscateConstString",
                false,
                "apply obfuscate const string"
        );

        allOptions.addOption(inputOption);
        allOptions.addOption(outputOption);
        allOptions.addOption(ruleOption);
        allOptions.addOption(helpOption);
        allOptions.addOption(versionOption);
        allOptions.addOption(verboseOption);
        allOptions.addOption(convertReflectionOption);
        allOptions.addOption(constStringObfuscateOption);
    }

    private static void usage() {
        PrintWriter printWriter = new PrintWriter(DEFAULT_OUT_STREAM);
        HelpFormatter formatter = new HelpFormatter();
        formatter.setWidth(120);

        // Print options
        printWriter.println("Options:");
        formatter.printOptions(
                printWriter,
                formatter.getWidth(),
                allOptions,
                formatter.getLeftPadding(),
                formatter.getDescPadding());
        printWriter.println();

        printWriter.flush();
    }
}
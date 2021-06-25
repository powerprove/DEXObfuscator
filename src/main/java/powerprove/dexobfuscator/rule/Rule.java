package powerprove.dexobfuscator.rule;

import org.apache.commons.lang3.StringUtils;
import powerprove.dexobfuscator.jar.lib.Logger;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    private List<String> tokens = new ArrayList<>();

    private String targetClassAccess = "";
    private String targetClass = "";

    private List<FieldElement> targetFields = new ArrayList<>();
    private List<MethodElement> targetMethods = new ArrayList<>();

    class FieldElement {
        public String access = "";
        public String name = "";

        public FieldElement(String access, String name) {
            this.access = access;
            this.name = name;
        }
    }

    class MethodElement {
        public String access = "";
        public String returnObject = "";
        public String name = "";
        public String parameters = "";
        public boolean isNative = false;

        public MethodElement(String access, String returnObject, String name, String parameters, boolean isNative) {
            this.access = access;
            this.returnObject = returnObject;
            this.name = name;
            this.parameters = parameters;
            this.isNative = isNative;
        }
    }

    public Rule() {

    }

    public int size() {
        return tokens.size();
    }

    public void add(String token) {
        tokens.add(token);

        if (true == targetClassAccess.isEmpty()) {
            targetClassAccess = token;
            return;
        }

        if ((false == targetClassAccess.isEmpty())
                && (false == token.equals("class"))
                && (true == targetClass.isEmpty())) {
            targetClass = token;
            return;
        }
    }

    public void print() {
        Logger.getLogger().i("[target class] " + targetClassAccess + " " + targetClass);
        Logger.getLogger().i(tokens.size() + ", " + StringUtils.join(tokens, " "));
    }
}

package powerprove.dexobfuscator.jar.lib;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static final String Tag = "[" + PropertiesUtil.class.getSimpleName() + "]";
    private static Properties sProps = null;

    public static String get(String key) {
        return get().getProperty(key).trim();
    }

    public static Properties get() {
        if (null == sProps) {
            sProps = new Properties();
            loadProperties("properties/dexobfuscator.properties");
        }
        return sProps;
    }

    public static void set(String key, String value) {
        sProps.setProperty(key, value);
    }

    private static void loadProperties(String pathProperties) {
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(pathProperties);

        try {
            sProps.load(in);
            in.close();
        } catch (Exception ex) {
            // 정상적인 상황에서 해당 Exception 이 발생하지 않을 것으로 가정합니다.
            Logger.getLogger().e(Tag + "Fail to load properties - " + pathProperties);
        }
    }
}


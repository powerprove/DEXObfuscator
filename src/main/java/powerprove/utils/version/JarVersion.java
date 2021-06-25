package powerprove.utils.version;

public final class JarVersion {
    public static void main(String... aArgs){
        JarVersion readVersion = new JarVersion();
        readVersion.readVersionInfoInManifest();
    }

    public void readVersionInfoInManifest(){
        Package objPackage = powerprove.dexobfuscator.jar.Main.class.getClass().getPackage();
        //examine the package object
        String name = objPackage.getSpecificationTitle();
        String version = objPackage.getSpecificationVersion();
        //some jars may use 'Implementation Version' entries in the manifest instead
        System.out.println("Package name: " + name);
        System.out.println("Package version: " + version);
    }
}

package ...;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class LibrariesDownloader {
    public void downloadLib(String group, String name, String version) {
        try {
            File libsDir = new File("libraries/" + group + "/" + name + "/" + version);
            if (!libsDir.exists()) libsDir.mkdirs();
            File jarFile = new File(libsDir, name + "-" + version + ".jar");
            if (jarFile.exists()) return;
            String url = "https://repo1.maven.org/maven2/" +
                    group.replace(".", "/") + "/" +
                    name + "/" +
                    version + "/" +
                    name + "-" + version + ".jar";
            System.out.println("[TestPlugin] Downloading " + name + "...");
            try (InputStream in = new URL(url).openStream()) {
                Files.copy(in, jarFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("[TestPlugin] Installed " + name + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
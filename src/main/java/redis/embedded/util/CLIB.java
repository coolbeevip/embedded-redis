package redis.embedded.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class CLIB {
    static Logger LOG = Logger.getLogger(CLIB.class.getName());

    public static String detectCLib() {
        String result = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/etc/os-release")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
            LOG.info("OS-Release: " + result);
            if (result.toLowerCase().contains("alpine")) {
                return "MUSL";
            } else {
                return "GLIBC";
            }
        } catch (Exception e) {
            return "";
        }
    }
}

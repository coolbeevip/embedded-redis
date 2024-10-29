package redis.embedded.model;

import redis.embedded.util.CLIB;

import static redis.embedded.model.Architecture.x86;
import static redis.embedded.model.Architecture.x86_64;
import static redis.embedded.model.Architecture.aarch64;
import static redis.embedded.model.OS.MAC_OS_X;
import static redis.embedded.model.OS.UNIX;
import static redis.embedded.model.OS.WINDOWS;

public class OsArchitecture {

    public static final OsArchitecture
        WINDOWS_x86 = new OsArchitecture(WINDOWS, x86, ""),
        WINDOWS_x86_64 = new OsArchitecture(WINDOWS, x86_64,""),
        UNIX_x86 = new OsArchitecture(UNIX, x86,"GLIBC"),
        UNIX_x86_64 = new OsArchitecture(UNIX, x86_64,"GLIBC"),
        UNIX_x86_64_MUSL = new OsArchitecture(UNIX, x86_64,"MUSL"),
        UNIX_AARCH64 = new OsArchitecture(UNIX, aarch64,"GLIBC"),
        UNIX_AARCH64_MUSL = new OsArchitecture(UNIX, aarch64,"MUSL"),
        MAC_OS_X_x86_64 = new OsArchitecture(MAC_OS_X, x86_64,""),
        MAC_OS_X_ARM64 = new OsArchitecture(MAC_OS_X, aarch64,"");

    public final OS os;
    public final Architecture arch;
    public final String cLib;

    public static OsArchitecture detectOSandArchitecture() {
        final OS os = OS.detectOS();
        final Architecture arch = os.detectArchitecture();
        final String cLib = CLIB.detectCLib();
        return new OsArchitecture(os, arch, cLib);
    }

    public OsArchitecture(final OS os, final Architecture arch, final String cLib) {
        this.os = os;
        this.arch = arch;
        this.cLib = cLib;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final OsArchitecture that = (OsArchitecture) o;
        return arch == that.arch && os == that.os && cLib.equals(that.cLib);
    }

    @Override
    public int hashCode() {
        int result = os.hashCode();
        result = 31 * result + arch.hashCode();
        result = 31 * result + cLib.hashCode();
        return result;
    }

}

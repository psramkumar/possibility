package com.mjs_svc.possibility.util;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class Version {
    private static final String version = "Dev Snapshot";
    private static final int build = Integer.parseInt(
            "$Rev$".replace("[\\$\\s]", "").replace("Rev:", ""));

    public static int getBuild() {
        return build;
    }
    public static String getVersion() {
        return version;
    }
}

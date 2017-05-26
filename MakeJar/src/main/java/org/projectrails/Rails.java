package org.projectrails;

import org.projectrainbow.ServerWrapper;
import org.projectrainbow._DiwUtils;

import net.md_5.bungee.config.Configuration;

/**
 * Project Rails.
 */
public class Rails {
    private static RailConfig config = new RailConfig("projectrails.yml");

    /**
     * Runs ProjectRails stuff. Called in
     * {@link org.projectrainbow._DiwUtils#Startup()}
     */
    public static void run() {
        _DiwUtils.version = "1.11.2";
        _DiwUtils.upstream_version = "169";
        config.saveDefaultConfig();
        ServerWrapper.getInstance().registerCommand(new CmdRails());
    }

    public static Configuration getConfig() {
        return config.getConfig();
    }
}

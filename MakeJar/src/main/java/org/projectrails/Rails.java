package org.projectrails;

import net.md_5.bungee.config.Configuration;

/**
 * Usefull utils for Project Rails.
 */
public class Rails {
    private static RailConfig config = new RailConfig("projectrails.yml");

    /**
     * Runs ProjectRails stuff. Called in
     * {@link org.projectrainbow._DiwUtils#Startup()}
     */
    public static void run() {
        config.saveDefaultConfig();
    }

    public static Configuration getConfig() {
        return config.getConfig();
    }
}

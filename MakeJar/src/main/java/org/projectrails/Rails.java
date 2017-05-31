package org.projectrails;

import java.util.jar.Attributes;

import org.projectrainbow.ServerWrapper;
import org.projectrainbow._DiwUtils;

import net.md_5.bungee.config.Configuration;

public class Rails {
    private static RailConfig config = new RailConfig("projectrails.yml");

    /**
     * Runs ProjectRails stuff. Called in
     * {@link org.projectrainbow._DiwUtils#Startup()}
     */
    public static void run() {
        Attributes a = Rail_Updater.getManifest(Rail_Updater.class).getMainAttributes();
        String hash = a.getValue("GitCommitHash");
        if (hash.endsWith("-dirty")) hash = hash.replace("-dirty", "");

        _DiwUtils.version = "git-ProjectRails-" + hash;
        _DiwUtils.upstream_version = "169"; // Change when updating upstream.
        config.saveDefaultConfig();
        ServerWrapper.getInstance().registerCommand(new CmdRails());
    }

    public static Configuration getConfig() {
        return config.getConfig();
    }
}

package org.projectrails;

import java.util.jar.Attributes;

import org.projectrails.commands.CmdAfk;
import org.projectrails.warps.CmdDelwarp;
import org.projectrails.warps.CmdSetwarp;
import org.projectrails.warps.CmdWarp;
import org.projectrails.warps.WarpConfiguration;
import org.projectrainbow.ServerWrapper;
import org.projectrainbow._DiwUtils;

import org.projectrails.commands.CmdRails;

import net.md_5.bungee.config.Configuration;

public class Rails {
    private static RailConfig config = new RailConfig("projectrails.yml");
    private static int upstream = 173; // Change when updating upstream.
    private static boolean useWarpsV2 = false;
    public static boolean displaynameafk = true;

    /**
     * ProjectRails startup. Runs in {@link _DiwUtils#Startup()}
     */
    public static void run() {
        Attributes a = Rail_Updater.getManifest(Rail_Updater.class).getMainAttributes();
        String hash = a.getValue("GitCommitHash");
        if (hash.endsWith("-dirty")) {
            System.out.println("ProjectRails DEBUG: This build is custom built!");
            hash = hash.replace("-dirty", "");
        }

        _DiwUtils.version = "git-ProjectRails-" + hash;
        _DiwUtils.upstream_version = String.valueOf(upstream);

        // Save default configuration files.
        config.saveDefaultConfig();
        WarpConfiguration.warps.saveDefaultConfig();
        WarpConfiguration.load();

        // Config values.
        config.addDefault("commands.afk.enable", true);
        config.addDefault("commands.afk.useDisplayNames", true);
        config.addDefault("files.use-new-warps-config", true);
        config.saveConfig();
        config.reloadConfig();
        
        // AFK command.
        displaynameafk = config.getConfig().getBoolean("commands.afk.useDisplayNames");
        if (config.getConfig().getBoolean("commands.afk.enable")) registerCommand(new CmdAfk());

        // Register commands.
        registerCommand(new CmdRails());
        useWarpsV2 = config.getConfig().getBoolean("files.use-new-warps-config");
        if (useWarpsV2) {
            registerCommand(new CmdWarp());
            registerCommand(new CmdSetwarp());
            registerCommand(new CmdDelwarp());
        }
    }

    public static Configuration getConfig() {
        return config.getConfig();
    }

    public static int getRainbowVersion() {
        return upstream;
    }

    private static void registerCommand(PluginReference.MC_Command cmd) {
        ServerWrapper.getInstance().registerCommand(cmd);
    }

    public static boolean useVersion2Warps() {
        return useWarpsV2;
    }
}
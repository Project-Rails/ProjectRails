package org.projectrails;

import java.util.jar.Attributes;

import org.projectrails.commands.CmdAfk;
import org.projectrails.commands.CmdClearlag;
import org.projectrails.commands.CmdLag;
import org.projectrails.commands.CmdRails;
import org.projectrails.commands.CmdSpawnMob;
import org.projectrails.warps.CmdDelwarp;
import org.projectrails.warps.CmdSetwarp;
import org.projectrails.warps.CmdWarp;
import org.projectrails.warps.WarpConfiguration;
import org.projectrainbow._DiwUtils;

import PluginReference.RainbowUtils;
import net.md_5.bungee.config.Configuration;

public class Rails {
    private static RailConfig config = new RailConfig("projectrails.yml");
    private static int upstream = 175; // Change when updating upstream.
    private static boolean useWarpsV2 = false;
    public static boolean displaynameafk = true;
    public static boolean broadcastclearlag = true;
    
    public static void run() {
        run(false);
    }

    public static void run(boolean registerCommands) {
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
        config.addDefault("commands.laginfo.enable", true);
        config.addDefault("commands.clearlag.enable", true);
        config.addDefault("commands.clearlag.broadcast", true);
        config.addDefault("commands.spawnmob.enable", true);
        config.addDefault("files.use-new-warps-config", true);
        config.saveConfig();
        config.reloadConfig();

        // commands.
        displaynameafk = config.getConfig().getBoolean("commands.afk.useDisplayNames");
        broadcastclearlag = config.getConfig().getBoolean("commands.clearlag.broadcast");

        if (registerCommands) registerCommands();
    }
    
    public static void registerCommands() {
        if (config.getConfig().getBoolean("commands.afk.enable")) registerCommand(new CmdAfk());
        if (config.getConfig().getBoolean("commands.laginfo.enable")) registerCommand(new CmdLag());
        if (config.getConfig().getBoolean("commands.clearlag.enable")) registerCommand(new CmdClearlag());
        broadcastclearlag = config.getConfig().getBoolean("commands.clearlag.broadcast");
        
        if (config.getConfig().getBoolean("commands.spawnmob.enable")) registerCommand(new CmdSpawnMob());

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
        RainbowUtils.getServer().registerCommand(cmd);
    }

    public static boolean useVersion2Warps() {
        return useWarpsV2;
    }
}
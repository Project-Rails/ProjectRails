package org.projectrails.warps;

import java.io.File;

import org.projectrails.RailConfig;
import org.projectrails.Rails;
import org.projectrainbow._DiwUtils;

import PluginReference.ChatColor;
import PluginReference.MC_Entity;
import PluginReference.MC_Location;
import PluginReference.MC_Player;

/**
 * A human readable replacement for Rainbow's built-in warp system.
 */
public class WarpConfiguration {
    public static RailConfig warps = new RailConfig(new File(_DiwUtils.RainbowDataDirectory, "warps.yml"));

    public static void load() {
        System.out.println("[ProjectRails] Warps v2 activated!");
        if (new File(_DiwUtils.RainbowDataDirectory, "warps.dat").exists()) {
            System.err.println("[ProjectRails] Warps version 1 data file found!");
            System.err.println("[ProjectRails] You will have to convert your old warps to the new format by yourself.");
        }
        warps.saveDefaultConfig();
        reload();
    }

    public static void reload() {
        warps.reloadConfig();
    }

    public static void save() {
        warps.saveConfig();
    }

    public static void tpPlayerToWarp(MC_Player p, String warpname) {
        tpPlayerToWarp(p, warpname.toLowerCase(), false);
    }

    public static void tpPlayerToWarp(MC_Player p, String warpname, boolean slient) {
        double x = warps.getConfig().getDouble("warps." + warpname.toLowerCase() + ".x");
        double y = warps.getConfig().getDouble("warps." + warpname.toLowerCase() + ".y");
        double z = warps.getConfig().getDouble("warps." + warpname.toLowerCase() + ".z");
        int d = warps.getConfig().getInt("warps." + warpname.toLowerCase() + ".world");
        float yaw = warps.getConfig().getFloat("warps." + warpname.toLowerCase() + ".yaw");
        float pitch = warps.getConfig().getFloat("warps." + warpname.toLowerCase() + ".pitch");

        MC_Location loc = new MC_Location(x, y, z, d, yaw, pitch);
        if (!slient) {
            p.sendMessage(ChatColor.GREEN + "Warping to " + warpname.toLowerCase());
        }
        p.teleport(loc);
    }

    public static void tpEntityToWarp(MC_Entity e, String warpname) {
        warpname = warpname.toLowerCase();
        double x = warps.getConfig().getDouble("warps." + warpname + ".x");
        double y = warps.getConfig().getDouble("warps." + warpname + ".y");
        double z = warps.getConfig().getDouble("warps." + warpname + ".z");
        int d = warps.getConfig().getInt("warps." + warpname + ".world");
        float yaw = warps.getConfig().getFloat("warps." + warpname + ".yaw");
        float pitch = warps.getConfig().getFloat("warps." + warpname + ".pitch");

        MC_Location loc = new MC_Location(x, y, z, d, yaw, pitch);
        e.teleport(loc);
    }

    public static void createWarp(MC_Location loc, String warpname) {
        warpname = warpname.toLowerCase();
        warps.getConfig().set("warps." + warpname + ".x", loc.x);
        warps.getConfig().set("warps." + warpname + ".y", (loc.y + 1));
        warps.getConfig().set("warps." + warpname + ".z", loc.z);
        warps.getConfig().set("warps." + warpname + ".world", loc.dimension);
        warps.getConfig().set("warps." + warpname + ".yaw", loc.yaw);
        warps.getConfig().set("warps." + warpname + ".pitch", loc.pitch);
        warps.saveConfig();
        warps.reloadConfig();
    }

    public static void removeWarp(String warpname) {
        warpname = warpname.toLowerCase();
        warps.getConfig().set("warps." + warpname, null);
        warps.saveConfig();
        warps.reloadConfig();
    }
}

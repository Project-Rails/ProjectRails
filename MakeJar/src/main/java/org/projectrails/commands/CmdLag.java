package org.projectrails.commands;

import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.time.DurationFormatUtils;

import PluginReference.ChatColor;
import PluginReference.MC_Player;
import PluginReference.MC_World;

public class CmdLag extends RailCommand {

    @Override
    public void handleCommand(MC_Player sender, String[] args) {   
        sendMessage(sender, ChatColor.GOLD + "Uptime: " + ChatColor.GREEN + formatTime(DurationFormatUtils.formatDurationHMS(ManagementFactory.getRuntimeMXBean().getUptime())));
        sendMessage(sender, ChatColor.GOLD + "Max RAM: " + ChatColor.GREEN + formatSize(Runtime.getRuntime().maxMemory()));
        sendMessage(sender, ChatColor.GOLD + "Total RAM: " + ChatColor.GREEN + formatSize(Runtime.getRuntime().totalMemory()));
        sendMessage(sender, ChatColor.GOLD + "Free RAM: " + ChatColor.GREEN + formatSize(Runtime.getRuntime().freeMemory()));

        List<MC_World> worlds = sender.getServer().getWorlds();
        sendMessage(sender, ChatColor.GOLD + "Worlds (" + sender.getServer().getWorlds().size() + "):");
        for (MC_World w : worlds) {
            sendMessage(sender, ChatColor.GOLD + "World: " + ChatColor.GREEN + w.getName() + ChatColor.GOLD + ": Chunks: " + ChatColor.GREEN + w.getLoadedChunks().size() + ChatColor.GOLD + ", Entities: " + ChatColor.GREEN + w.getEntities().size());
        }
    }

    private String formatTime(String time) {
        String[] strs = time.split(":");
        if (strs[0].startsWith("0")) {
            strs[0] = strs[0].substring(1);
        }
        String hours = strs[0] + " Hours, ";
        String min = strs[1] + " Minutes, ";
        String sec = strs[2].substring(0, strs[2].indexOf(".")) + " Seconds.";
        if (hours.startsWith("00")) return min + sec;
        else return hours + min + sec;
    }

    private String formatSize(long v) {
        int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
        return String.format("%.1f %sB", (double)v / (1L << (z*10)), " KMGTPE".charAt(z));
    }
    
    @Override
    public CommandInfo getInfo() {
        return new CommandInfo("laginfo", "Shows the lag info.", Arrays.asList("ram", "uptime", "lag"));
    }
}

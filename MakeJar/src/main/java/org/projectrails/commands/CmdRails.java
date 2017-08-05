package org.projectrails.commands;

import java.util.Arrays;
import java.util.jar.Attributes;

import org.projectrails.Rail_Updater;
import org.projectrails.Rails;

import PluginReference.ChatColor;
import PluginReference.MC_Player;
import PluginReference.RainbowUtils;

public class CmdRails extends RailCommand {
    @Override
    public void handleCommand(MC_Player p, String[] args) {
        if (args.length == 0) {
            sendMessage(p, ChatColor.RED + "Use /rails help to see all sub commands.");
            return;
        }

        if (args[0].equalsIgnoreCase("updatecheck")) {
            int i = Rail_Updater.check();
            if (i == 0) {
                sendMessage(p, "You are running the latest version.");
                return;
            } else {
                sendMessage(p, "You are running " + i + " versions behind.");
                return;
            }
        }

        if (args[0].equalsIgnoreCase("upstream")) {
            sendMessage(p, "ProjectRainbow b" + Rails.getRainbowVersion());
            int i = Rail_Updater.rainbowCheck();
            if (i == 0) {
                sendMessage(p, "Running the latest upstream build.");
                return;
            } else {
                sendMessage(p, "Running " + i + " builds behind the latest upstream build.");
                return;
            }
        }

        if (args[0].equalsIgnoreCase("info")) {
            Attributes a = Rail_Updater.getManifest(Rail_Updater.class).getMainAttributes();
            sendMessage(p, "ProjectRainbow version b" + Rails.getRainbowVersion());
            String hash = a.getValue("GitCommitHash");
            if (hash.endsWith("-dirty")) {
                sendMessage(p, "ProjectRails version: git-Rails-custom-" + hash);
                return;
            }
            sendMessage(p, "ProjectRails version git-Rails-" + hash);
            return;
        }

        if (args[0].equalsIgnoreCase("viewdistance")) {
            if (args.length == 1) {
                sendMessage(p, ChatColor.RED + "Usage: /rails viewdistance <number>");
                return;
            } else {
                try {
                    RainbowUtils.getServer().setViewDistance(Integer.valueOf(args[1]));
                } catch (NumberFormatException e) {
                    sendMessage(p, ChatColor.RED + args[1] + " is not a number.");
                }
            }
            return;
        }

        if (args[0].equalsIgnoreCase("help")) {
            sendMessage(p, ChatColor.AQUA + "Command Help:");
            sendMessage(p, ChatColor.GOLD + "/rails help " + ChatColor.ITALIC + "Displayes help for commands.");
            sendMessage(p, ChatColor.GOLD + "/rails updatecheck " + ChatColor.ITALIC + "Checks for updates.");
            sendMessage(p, ChatColor.GOLD + "/rails upstream " + ChatColor.ITALIC + "Checks for Rainbow updates.");
            sendMessage(p, ChatColor.GOLD + "/rails viewdistance " +  ChatColor.ITALIC + "Sets the server's view distance");
            return;
        }

        sendMessage(p, ChatColor.RED + "Usage: /rails help");
    }

    @Override
    public CommandInfo getInfo() {
        return new CommandInfo("rails", "Project-Rails main command.", Arrays.asList("projectrails"));
    }
}
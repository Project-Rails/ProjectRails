package org.projectrails.commands;

import java.util.ArrayList;
import java.util.Collection;
import org.projectrails.commands.CommandInfo;
import org.projectrails.commands.RailCommand;
import org.projectrails.Rails;
import org.projectrainbow.ServerWrapper;

import PluginReference.ChatColor;
import PluginReference.MC_Player;

public class CmdAfk extends RailCommand {
    private static Collection<String> afkmap = new ArrayList<>();
    
    @Override
    public void handleCommand(MC_Player plr, String[] args) {
        if (plr != null) {
            if (isAFK(plr)) {
                setAFK(plr, false);
            } else {
                setAFK(plr, true);
            }
        } else sendMessage(plr, "You're not a player.");
    }

    /**
     * Check if player is away from keybord.
     * 
     * @return true if player is afk, false if not.
     * @param target The player to check.
     */
    public static boolean isAFK(MC_Player target) {
        try {
            return afkmap.contains(target.getUUID().toString());
        } catch (NullPointerException ingore) {
            return false;
        }
    }
    
    public static void setAFK(MC_Player target, boolean status) {
        setAFK(target, status, true);
    }
    
    public static void setAFK(MC_Player target, boolean status, boolean broadcast) {
        target.setInvulnerable(status);
        if (status) {
            if (!isAFK(target)) {
                afkmap.add(target.getUUID().toString());
                if (broadcast) {
                    if (Rails.displaynameafk) {
                        ServerWrapper.getInstance().broadcastMessage(ChatColor.GRAY + "* " + target.getCustomName() + ChatColor.GRAY + " is now AFK!");
                    } else {
                        ServerWrapper.getInstance().broadcastMessage(ChatColor.GRAY + "* " + target.getName() + ChatColor.GRAY + " is now AFK!");
                    }
                }
            }
        } else {
            if (isAFK(target)) {
                afkmap.remove(target.getUUID().toString());
                if (broadcast) {
                    if (Rails.displaynameafk) {
                        ServerWrapper.getInstance().broadcastMessage(ChatColor.GRAY + "* " + target.getCustomName() + ChatColor.GRAY + " is no longer AFK!");
                    } else {
                        ServerWrapper.getInstance().broadcastMessage(ChatColor.GRAY + "* " + target.getName() + ChatColor.GRAY + " is no longer AFK!");
                    }
                }
            }
        }
    }

    @Override
    public CommandInfo getInfo() {
        return new CommandInfo("afk", "Go away-from-keybord");
    }
}

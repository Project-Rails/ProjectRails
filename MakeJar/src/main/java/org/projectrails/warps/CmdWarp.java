package org.projectrails.warps;

import org.projectrails.commands.CommandInfo;
import org.projectrails.commands.RailCommand;

import PluginReference.ChatColor;
import PluginReference.MC_Player;

public class CmdWarp extends RailCommand {
    @Override
    public void handleCommand(MC_Player plr, String[] args) {
        if (plr == null) {
            System.out.println("--- Only for players!");
        } else {
            if (args.length <= 0) {
                String warplist = "Warps: ";
                for (String s : WarpConfiguration.warps.getConfig().getSection("warps").getKeys()) {
                    warplist = warplist + ChatColor.GREEN + s + ChatColor.GRAY + ",";
                }
                if (ChatColor.StripColor(warplist).length() <= "Warps: ".length()) {
                    plr.sendMessage(ChatColor.RED + "No warps exist!");
                    return;
                }
                plr.sendMessage(warplist.substring(0, (warplist.length() - 1)));
            } else {
                WarpConfiguration.warps.reloadConfig();
                if (WarpConfiguration.warps.getConfig().get("warps." + args[0].toLowerCase() + ".x") == null) {
                    plr.sendMessage(ChatColor.RED + "Warp doesn't exist!");
                } else {
                    WarpConfiguration.tpPlayerToWarp(plr, args[0]);
                }
            }
        }
    }

    @Override
    public boolean hasPermissionToUse(MC_Player p) {
        return super.hasPermissionToUse(p) || p.hasPermission("rainbow.warp") || p.hasPermission("rails.warp")
                || p.isOp();
    }

    @Override
    public CommandInfo getInfo() {
        CommandInfo info = new CommandInfo();
        info.name = "warp";
        info.aliases = null;
        info.usage = "warp somewhere";
        return info;
    }
}
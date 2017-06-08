package org.projectrails.warps;

import org.projectrails.CommandInfo;
import org.projectrails.RailCommand;

import PluginReference.ChatColor;
import PluginReference.MC_Player;

public class CmdSetwarp extends RailCommand {
    @Override
    public void handleCommand(MC_Player p, String[] args) {
        if (args.length <= 0) {
            p.sendMessage(ChatColor.RED + "Usage: /setwarp <warpname>");
        } else {
            WarpConfiguration.createWarp(p.getLocation(), args[0].toLowerCase());
            String loc = "X:" + p.getLocation().x + ",Y:" + (p.getLocation().y + 1) + ",Z:" + p.getLocation().z;
            p.sendMessage(ChatColor.GREEN + "Set warp " + args[0].toLowerCase() + " at " + loc);
        }
    }

    @Override
    public boolean hasPermissionToUse(MC_Player p) {
        return super.hasPermissionToUse(p) || p.hasPermission("rainbow.setwarp") || p.hasPermission("rails.setwarp");
    }

    @Override
    public CommandInfo getInfo() {
        CommandInfo info = new CommandInfo();
        info.name = "setwarp";
        info.aliases = null;
        info.usage = "set a warp";
        return info;
    }
}

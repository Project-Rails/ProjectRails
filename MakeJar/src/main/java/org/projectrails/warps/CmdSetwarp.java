package org.projectrails.warps;

import org.projectrails.commands.CommandInfo;
import org.projectrails.commands.RailCommand;

import PluginReference.ChatColor;
import PluginReference.MC_Player;

public class CmdSetwarp extends RailCommand {
    @Override
    public void handleCommand(MC_Player p, String[] args) {
        if (args.length <= 0) {
            p.sendMessage(ChatColor.RED + "Usage: /setwarp <warpname>");
        } else {
            String x = String.valueOf(p.getLocation().x);
            String y = String.valueOf(p.getLocation().y + 1);
            String z = String.valueOf(p.getLocation().z);
            
            WarpConfiguration.createWarp(p.getLocation(), args[0].toLowerCase());
            String loc = "X:" + x.substring(0, x.indexOf(".") + 2) + ",Y:" + y.substring(0, y.indexOf(".") + 2) + ",Z:" + z.substring(0, z.indexOf(".") + 2);
            p.sendMessage(ChatColor.GREEN + "Set warp " + args[0].toLowerCase() + " at " + loc);
        }
    }

    @Override
    public boolean hasPermissionToUse(MC_Player p) {
        return super.hasPermissionToUse(p) || p.hasPermission("rainbow.setwarp");
    }

    @Override
    public CommandInfo getInfo() {
        return new CommandInfo("setwarp", "set a warp");
    }
}

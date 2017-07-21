package org.projectrails.warps;

import org.projectrails.commands.CommandInfo;
import org.projectrails.commands.RailCommand;

import PluginReference.ChatColor;
import PluginReference.MC_Player;

public class CmdDelwarp extends RailCommand {
    @Override
    public void handleCommand(MC_Player p, String[] args) {
        if (args.length <= 0) {
            p.sendMessage(ChatColor.RED + "Usage: /delwarp <warpname>");
        } else {
            WarpConfiguration.removeWarp(args[0].toLowerCase());
            p.sendMessage(ChatColor.GREEN + "Removed warp " + args[0].toLowerCase());
        }
    }

    @Override
    public boolean hasPermissionToUse(MC_Player p) {
        return super.hasPermissionToUse(p) || p.hasPermission("rainbow.delwarp");
    }

    @Override
    public CommandInfo getInfo() {
        CommandInfo info = new CommandInfo();
        info.name = "delwarp";
        info.aliases = null;
        info.usage = "remove a warp";
        return info;
    }
}
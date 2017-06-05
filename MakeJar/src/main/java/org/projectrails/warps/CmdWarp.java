package org.projectrails.warps;

import java.util.List;

import org.projectrails.RailCommand;

import PluginReference.ChatColor;
import PluginReference.MC_Player;

public class CmdWarp extends RailCommand {
    @Override
    public String getCommandName() {
        return "warp";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getHelpLine(MC_Player plr) {
        return ChatColor.AQUA + "/warp" + ChatColor.WHITE + " --- Warp somewhere";
    }

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
                plr.sendMessage(warplist);
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
        return super.hasPermissionToUse(p) || p.hasPermission("rainbow.warp") || p.hasPermission("rails.warp") || p.isOp();
    }

    @Override
    public List<String> getTabCompletionList(MC_Player plr, String[] args) {
        // TODO: Tab complete warp name.
        return null;
    }
}
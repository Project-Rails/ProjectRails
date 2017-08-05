package org.projectrails.commands;

import org.apache.commons.lang3.StringUtils;

import PluginReference.ChatColor;
import PluginReference.MC_EntityType;
import PluginReference.MC_Player;

public class CmdSpawnMob extends RailCommand {

    @Override
    public void handleCommand(MC_Player plr, String[] args) {
        if (plr == null) {
            System.out.println("/spawnmob is for players only");
            return;
        }
        if (args.length <= 0) {
            plr.sendMessage(ChatColor.GOLD + "Mobs: " + ChatColor.GREEN + StringUtils.join(MC_EntityType.values(), ChatColor.GOLD + "," + ChatColor.GREEN));
        } else {
            int amount = 1;
            if (args.length > 1) {
                amount = Integer.valueOf(args[1]);
            }
            MC_EntityType e = MC_EntityType.PIG;
            try {
                e = MC_EntityType.valueOf(args[0]);
            } catch (Exception ex) {
                plr.sendMessage(ChatColor.RED + "Unknown entity type.");
            }
            for (int z = 0; z < amount;) {
                plr.getWorld().spawnEntity(e, plr.getLocation(), "");
                z++;
            }
            plr.sendMessage("Summoning entity: " + e);
        }
    }

    @Override
    public CommandInfo getInfo() {
        return new CommandInfo("spawnmob", "Summon a entity!");
    }

}

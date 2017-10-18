package org.projectrails.commands;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.projectrails.Rails;

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
            plr.sendMessage(ChatColor.GOLD + "Mobs: " + ChatColor.GREEN + StringUtils.join(getSpawnable(), ChatColor.GOLD + ", " + ChatColor.GREEN));
        } else {
            int amount = 1;
            if (args.length > 1) amount = Integer.valueOf(args[1]);

            if (amount > Rails.mobSpawnLimit) {
                amount = Rails.mobSpawnLimit;
                plr.sendMessage(ChatColor.RED + "WARNNING: " + amount + " is two large. The max is " + Rails.mobSpawnLimit);
            }

            MC_EntityType e = MC_EntityType.PIG;
            try {
                e = MC_EntityType.valueOf(args[0].toUpperCase());
            } catch (Exception ex) {
                plr.sendMessage(ChatColor.RED + "Unknown entity type.");
            }
            for (int z = 0; z < amount;) {
                plr.getWorld().spawnEntity(e, plr.getLocation(), "");
                z++;
            }
            plr.sendMessage(ChatColor.GREEN + "Summoning " + amount + "entity(s): " + e);
        }
    }
    
    private ArrayList<MC_EntityType> getSpawnable() {
        ArrayList<MC_EntityType> list = new ArrayList<>();
        for (MC_EntityType e : MC_EntityType.values()) {
            if (!(e == MC_EntityType.PLAYER || e == MC_EntityType.ITEM || e == MC_EntityType.PAINTING || e == MC_EntityType.LEASH_KNOT || 
                    e == MC_EntityType.AREA_EFFECT_CLOUD || e == MC_EntityType.ITEM_FRAME)) 
                list.add(e);
        }
        return list;
    }

    @Override
    public CommandInfo getInfo() {
        return new CommandInfo("spawnmob", "Summon a entity!");
    }

}

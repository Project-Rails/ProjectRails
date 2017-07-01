package org.projectrails;

import PluginReference.ChatColor;
import PluginReference.MC_Player;

public abstract class RailCommand implements PluginReference.MC_Command {
    @Override
    public java.util.List<String> getAliases() {
        return getInfo().aliases;
    }

    @Override
    public String getCommandName() {
        return getInfo().name;
    }

    @Override
    public java.util.List<String> getTabCompletionList(MC_Player p, String[] args) {
        return null;
    }

    @Override
    public boolean hasPermissionToUse(MC_Player p) {
        return p == null || p.hasPermission("rails." + getCommandName()) || p.hasPermission("rails.admin");
    }

    @Override
    public String getHelpLine(MC_Player p) {
        return ChatColor.AQUA + getCommandName() + ChatColor.WHITE + " --- "
                + ChatColor.translateAlternateColorCodes('&', getInfo().usage);
    }

    /**
     * Replacement for {@link MC_Player#sendMessage(String)} that supports
     * sending messages to the console/server log.
     */
    public void sendMessage(MC_Player p, String msg) {
        if (p == null) {
            System.out.println(msg);
        } else {
            p.sendMessage(msg);
        }
    }

    public abstract CommandInfo getInfo();
}

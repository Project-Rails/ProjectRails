package org.projectrails;

import PluginReference.MC_Player;

public abstract class RailCommand implements PluginReference.MC_Command {
    @Override
    public java.util.List<String> getAliases() {
        return null;
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
    public String getHelpLine(MC_Player arg0) {
        return "A ProjectRails provided command.";
    }

    public void sendMessage(MC_Player p, String msg) {
        if (p == null) {
            System.out.println(msg);
        } else {
            p.sendMessage(msg);
        }
    }
}

package org.projectrails;

import java.util.Arrays;
import java.util.List;
import java.util.jar.Attributes;

import org.projectrainbow.commands._CmdVer;

import PluginReference.MC_Player;

public class CmdRails extends RailCommand {

    @Override
    public List<String> getAliases() {
        return Arrays.asList("projectrails");
    }

    @Override
    public String getCommandName() {
        return "rails";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return "A ProjectRails provided command.";
    }

    @Override
    public void handleCommand(MC_Player p, String[] args) {
        if (args.length <= 0) {
            Attributes a = Rail_Updater.getManifest(Rail_Updater.class).getMainAttributes();
            String hash = a.getValue("GitCommitHash");
            if (hash.endsWith("-dirty")) hash = hash.replace("-dirty", "");

            sendMessage(p, "ProjectRails version git-Rails-" + hash);
        }
        
        if (args[0].equalsIgnoreCase("updatecheck")) {
            int i = Rail_Updater.check();
            if (i == 0) {
                sendMessage(p, "You are running the latest version.");
                return;
            } else {
                sendMessage(p, "You are running " + i + " versions behind.");
                return;
            }
        }
    }

}

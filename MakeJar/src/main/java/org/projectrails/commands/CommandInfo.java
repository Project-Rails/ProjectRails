package org.projectrails.commands;

import java.util.List;

public class CommandInfo {
    public String name = "_null_";
    public List<String> aliases = null;
    public String usage = "A ProjectRails powered command";

    public CommandInfo() {
    }

    public CommandInfo(String name, String usage) {
        this.name = name;
        this.usage = usage;
    }

    public CommandInfo(String name, String usage, List<String> aliases) {
        this.name = name;
        this.usage = usage;
        this.aliases = aliases;
    }
}
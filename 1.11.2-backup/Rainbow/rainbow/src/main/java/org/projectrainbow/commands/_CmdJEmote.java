package org.projectrainbow.commands;
import PluginReference.ChatColor;import PluginReference.MC_Command;import PluginReference.MC_Player;import org.projectrainbow._EmoteUtils;import java.util.List;public class _CmdJEmote implements MC_Command{
public String getCommandName(){
return "jemote";
}@Overridepublic List<String> getAliases(){
return null;
}@Overridepublic String getHelpLine(MC_Player plr){
return ChatColor.AQUA + "/jemote" + ChatColor.WHITE + " --- Emotes!";
}@Overridepublic void handleCommand(MC_Player plr, String[] args){
_EmoteUtils.HandleCommand(plr, args);
}@Overridepublic boolean hasPermissionToUse(MC_Player plr){
return plr == null || plr.hasPermission("rainbow.jemote");
}@Overridepublic List<String> getTabCompletionList(MC_Player plr, String[] args){
return null;
}}
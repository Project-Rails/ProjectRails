package org.projectrainbow.commands;
import PluginReference.ChatColor;import PluginReference.MC_Command;import PluginReference.MC_Player;import net.minecraft.command.CommandBase;import org.projectrainbow._DiwUtils;import org.projectrainbow._JOT_OnlineTimeUtils;import java.util.List;public class _CmdJot implements MC_Command{
public String getCommandName(){
return "jot";
}@Overridepublic List<String> getAliases(){
return null;
}@Overridepublic String getHelpLine(MC_Player plr){
return ChatColor.AQUA + "/jot" + ChatColor.WHITE + " --- Online Time!";
}@Overridepublic void handleCommand(MC_Player plr, String[] args){
_JOT_OnlineTimeUtils.HandleCommand(plr, args);
}@Overridepublic boolean hasPermissionToUse(MC_Player plr){
return plr == null || plr.hasPermission("rainbow.jot");
}@Overridepublic List<String> getTabCompletionList(MC_Player plr, String[] args){
return args.length >= 1
? CommandBase.getListOfStringsMatchingLastWord(args,
_DiwUtils.getMinecraftServer().getAllUsernames())
: null;}}
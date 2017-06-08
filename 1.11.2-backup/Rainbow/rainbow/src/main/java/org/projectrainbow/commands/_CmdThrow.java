package org.projectrainbow.commands;
import PluginReference.ChatColor;import PluginReference.MC_Command;import PluginReference.MC_Player;import java.util.List;public class _CmdThrow implements MC_Command{
public _CmdThrow(){
}public String getCommandName(){
return "throw";
}@Overridepublic List<String> getAliases(){
return null;
}@Overridepublic String getHelpLine(MC_Player plr){
return ChatColor.AQUA + "/throw" + ChatColor.WHITE
+ " --- Use if something riding you!";
}@Overridepublic void handleCommand(MC_Player plr, String[] args){
if (plr == null){
System.out.println("Not from console!");
}else{if (plr.getRider() == null){
plr.sendMessage(ChatColor.RED + "You don\'t have a rider!");
}else{plr.sendMessage(
ChatColor.GREEN + "You eject rider: " + ChatColor.WHITE
+ plr.getRider().getName());
plr.setRider(null);
}}}@Overridepublic boolean hasPermissionToUse(MC_Player plr){
return plr == null || plr.hasPermission("rainbow.throw");
}@Overridepublic List<String> getTabCompletionList(MC_Player plr, String[] args){
return null;
}}
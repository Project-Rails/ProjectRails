package org.projectrainbow.commands;
import PluginReference.ChatColor;import PluginReference.MC_Command;import PluginReference.MC_ItemStack;import PluginReference.MC_Player;import org.projectrainbow._DiwUtils;import org.projectrainbow._EconomyManager;import java.util.List;public class _CmdWorth implements MC_Command{
public _CmdWorth(){}public String getCommandName(){
return "worth";
}@Overridepublic List<String> getAliases(){
return null;
}@Overridepublic String getHelpLine(MC_Player plr){
return ChatColor.AQUA + "/worth" + ChatColor.WHITE
+ " --- Check worth of item in hand";
}@Overridepublic void handleCommand(MC_Player plr, String[] args){
if (plr == null){
_DiwUtils.ConsoleMsg("Not from console!  Requires item in hand.");
}else{if (plr.getItemInHand() == null){
plr.sendMessage(ChatColor.RED + "You must have something in hand.");
}else{MC_ItemStack is = plr.getItemInHand();
Double val = _EconomyManager.GetItemWorth(is);
if (val.doubleValue() <= 0.0D){
plr.sendMessage(
ChatColor.RED
+ "That item is not sellable to the server.");
}else{int count = is.getCount();
String msg = String.format(
"%d x %.2f = " + ChatColor.BOLD + ChatColor.GREEN
+ "$%.2f",new Object[]{
Integer.valueOf(count), val,
Double.valueOf((double) count * val.doubleValue())});plr.sendMessage(
ChatColor.GOLD + is.getFriendlyName()
+ ChatColor.WHITE + ": " + msg);
}}}}@Overridepublic boolean hasPermissionToUse(MC_Player plr){
return plr == null || plr.hasPermission("rainbow.worth");
}@Overridepublic List<String> getTabCompletionList(MC_Player plr, String[] args){
return null;
}}
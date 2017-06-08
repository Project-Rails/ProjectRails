package org.projectrainbow.commands;
import PluginReference.MC_Command;import PluginReference.MC_Player;import net.minecraft.util.text.TextFormatting;import org.projectrainbow._Announcer;import java.util.List;public class _CmdAnnouncer implements MC_Command{
@Overridepublic String getCommandName(){
return "announcer";
}@Overridepublic List<String> getAliases(){
return null;
}@Overridepublic String getHelpLine(MC_Player plr){
return TextFormatting.LIGHT_PURPLE + "/announcer" + TextFormatting.WHITE
+ " --- Announcer!";
}@Overridepublic void handleCommand(MC_Player plr, String[] args){
_Announcer.HandleCommand(plr, args);
}@Overridepublic boolean hasPermissionToUse(MC_Player plr){
return plr == null || plr.isOp();
}@Overridepublic List<String> getTabCompletionList(MC_Player plr, String[] args){
return null;
}}
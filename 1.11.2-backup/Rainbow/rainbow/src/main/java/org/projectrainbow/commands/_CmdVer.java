package org.projectrainbow.commands;
import java.util.Collections;import java.util.List;import org.projectrainbow.Updater;import org.projectrainbow._ColorHelper;import org.projectrainbow._DiwUtils;import org.projectrainbow.launch.Bootstrap;import PluginReference.MC_Player;import net.minecraft.command.CommandBase;import net.minecraft.command.CommandException;import net.minecraft.command.ICommandSender;import net.minecraft.server.MinecraftServer;public class _CmdVer extends CommandBase{
@Overridepublic String getCommandName(){
return "ver";
}@Overridepublic List<String> getCommandAliases(){
return Collections.singletonList("version");
}@Overridepublic boolean checkPermission(MinecraftServer minecraftServer, ICommandSender sender){
return (!(sender instanceof MC_Player)) || ((MC_Player) sender).hasPermission("rainbow.version");
}@Overridepublic String getCommandUsage(ICommandSender sender){
return String.valueOf(_ColorHelper.AQUA) + "/ver" + _ColorHelper.WHITE + " --- Version Info";
}@Overridepublic void execute(MinecraftServer minecraftServer, ICommandSender cs, String[] strings) throws CommandException{
_DiwUtils.reply(cs, String.valueOf(_ColorHelper.AQUA) + "Rainbow " + _ColorHelper.LIGHT_PURPLE + _DiwUtils.MC_VERSION_STRING + " b" + Bootstrap.buildNumber);
_DiwUtils.reply(cs, "Checking for update, please wait...");
_DiwUtils.reply(cs, Updater.checkForUpdate());
}}
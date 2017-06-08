package org.projectrainbow.util;
import PluginReference.MC_Command;import PluginReference.MC_Player;import net.minecraft.command.CommandException;import net.minecraft.command.ICommand;import net.minecraft.command.ICommandSender;import org.projectrainbow._DiwUtils;import java.util.List;public class WrappedMinecraftCommand implements MC_Command{
public final ICommand delegate;
public WrappedMinecraftCommand(ICommand delegate){
this.delegate = delegate;
}@Overridepublic int hashCode(){
return delegate.hashCode();
}@Overridepublic boolean equals(Object obj){
return delegate.equals(obj);
}@Overridepublic String toString(){
return delegate.toString();
}@Overridepublic String getCommandName(){
return delegate.getCommandName();
}@Overridepublic List<String> getAliases(){
return delegate.getCommandAliases();
}@Overridepublic String getHelpLine(MC_Player plr){
return delegate.getCommandUsage((ICommandSender) plr);
}@Overridepublic void handleCommand(MC_Player plr, String[] args){
try{delegate.execute(_DiwUtils.getMinecraftServer(), (ICommandSender) plr, args);
}catch (CommandException e){Util.sneakyThrow(e);
}}@Overridepublic boolean hasPermissionToUse(MC_Player plr){
return delegate.checkPermission(_DiwUtils.getMinecraftServer(), (ICommandSender) plr);
}@Overridepublic List<String> getTabCompletionList(MC_Player plr, String[] args){
return delegate.getTabCompletionOptions(_DiwUtils.getMinecraftServer(), (ICommandSender) plr, args, null);
}}
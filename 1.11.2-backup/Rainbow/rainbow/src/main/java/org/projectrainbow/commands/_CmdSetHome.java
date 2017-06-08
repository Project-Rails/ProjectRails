package org.projectrainbow.commands;
import PluginReference.MC_Player;import net.minecraft.command.CommandBase;import net.minecraft.command.CommandException;import net.minecraft.command.ICommandSender;import net.minecraft.entity.player.EntityPlayer;import net.minecraft.server.MinecraftServer;import org.projectrainbow._ColorHelper;import org.projectrainbow._HomeUtils;import joebkt._SerializableLocation;import org.projectrainbow.interfaces.IMixinICommandSender;public class _CmdSetHome extends CommandBase
{@Overridepublic String getCommandName(){
return "sethome";
}@Overridepublic boolean checkPermission(MinecraftServer minecraftServer, ICommandSender iCommandSender){
return (!(iCommandSender instanceof MC_Player)) || ((MC_Player) iCommandSender).hasPermission("rainbow.sethome");
}@Overridepublic String getCommandUsage(ICommandSender iCommandSender){
return String.valueOf(_ColorHelper.AQUA) + "/sethome" + _ColorHelper.WHITE + " --- Set a home";
}@Overridepublic void execute(MinecraftServer minecraftServer, ICommandSender cs, String[] args) throws CommandException{
if (!(cs instanceof EntityPlayer)){
System.out.println("Sethome is for Players only!");
return;}final EntityPlayer p = (EntityPlayer)cs;
final String pName = p.getName();
final _SerializableLocation sloc = new _SerializableLocation(p.posX, p.posY, p.posZ, p.dimension, p.rotationYaw, p.rotationPitch);
_HomeUtils.setHome(p.getUniqueID(), sloc);
_HomeUtils.SaveHomes();
final String msg = String.format("Home Set for %s set to %s", pName, sloc.toString());
System.out.println(msg);
((IMixinICommandSender)p).sendMessage(String.valueOf(_ColorHelper.GREEN) + "Home set to " + _ColorHelper.WHITE + sloc.toString());
}}
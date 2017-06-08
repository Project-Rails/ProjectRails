package PluginExample.commands;
import PluginExample.CmdBase;import PluginReference.ChatColor;import PluginReference.MC_Player;public class CmdHero extends CmdBase{
public CmdHero(){
super("hero", "You're the best!");
}@Overrideprotected void execute(MC_Player plr, String[] args){
plr.setInvulnerable(!plr.isInvulnerable());
if (plr.isInvulnerable())
plr.sendMessage(ChatColor.GREEN + "You are now invulnerable.");
elseplr.sendMessage(ChatColor.DARK_GREEN + "You are no longer invulnerable.");
}}
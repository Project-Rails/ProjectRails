package org.projectrainbow.mixins;
import PluginReference.MC_EventInfo;import PluginReference.MC_ItemEntity;import PluginReference.MC_ItemStack;import PluginReference.MC_Player;import net.minecraft.entity.item.EntityItem;import net.minecraft.entity.player.EntityPlayer;import net.minecraft.item.ItemStack;import org.projectrainbow.Hooks;import org.projectrainbow.PluginHelper;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Shadow;import org.spongepowered.asm.mixin.injection.At;import org.spongepowered.asm.mixin.injection.Inject;import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;@Mixin(EntityItem.class)
public abstract class MixinEntityItem implements MC_ItemEntity{
@Shadowpublic abstract ItemStack getEntityItem();
@Shadowpublic abstract void setEntityItemStack(ItemStack var1);
@Shadowpublic abstract String getOwner();
@Shadowpublic abstract void setOwner(String var1);
@Shadowpublic abstract String getThrower();
@Shadowpublic abstract void setThrower(String var1);
@Inject(method = "onCollideWithPlayer", at = @At(value = "INVOKE", target = "net.minecraft.entity.player.InventoryPlayer.addItemStackToInventory(Lnet/minecraft/item/ItemStack;)Z"), cancellable = true)
private void onPickup(EntityPlayer player, CallbackInfo callbackInfo){
MC_EventInfo ei = new MC_EventInfo();
Hooks.onAttemptItemPickup((MC_Player) player, getItemStack(), false, ei);
if (ei.isCancelled){
callbackInfo.cancel();
}}@Overridepublic MC_ItemStack getItemStack(){
return (MC_ItemStack) (Object) getEntityItem();
}@Overridepublic void setItemStack(MC_ItemStack is){
setEntityItemStack(PluginHelper.getItemStack(is));
}@Overridepublic String getThrowerName(){
return getThrower();
}@Overridepublic void setThrowerName(String name){
setThrower(name);
}@Overridepublic String getOwnerName(){
return getOwner();
}@Overridepublic void setOwnerName(String name){
setOwner(name);
}}
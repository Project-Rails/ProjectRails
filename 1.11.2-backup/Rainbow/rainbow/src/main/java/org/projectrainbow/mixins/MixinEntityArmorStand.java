package org.projectrainbow.mixins;
import PluginReference.*;import com.google.common.base.Preconditions;import net.minecraft.entity.item.EntityArmorStand;import net.minecraft.entity.player.EntityPlayer;import net.minecraft.inventory.EntityEquipmentSlot;import net.minecraft.item.ItemStack;import net.minecraft.util.DamageSource;import net.minecraft.util.EnumHand;import net.minecraft.util.NonNullList;import net.minecraft.util.math.Rotations;import org.projectrainbow.Hooks;import org.projectrainbow.PluginHelper;import org.spongepowered.asm.mixin.Final;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Shadow;import org.spongepowered.asm.mixin.injection.At;import org.spongepowered.asm.mixin.injection.Inject;import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;import java.util.ArrayList;import java.util.List;@Mixin(EntityArmorStand.class)
public abstract class MixinEntityArmorStand extends MixinEntityLivingBase implements MC_ArmorStand{
@Shadow@Finalprivate NonNullList<ItemStack> handItems;
@Shadow@Finalprivate NonNullList<ItemStack> armorItems;
@Shadowprivate Rotations headRotation;
@Shadowprivate Rotations bodyRotation;
@Shadowprivate Rotations leftArmRotation;
@Shadowprivate Rotations rightArmRotation;
@Shadowprivate Rotations leftLegRotation;
@Shadowprivate Rotations rightLegRotation;
@Shadowabstract void setNoBasePlate(boolean var1);
@Shadowpublic abstract boolean hasNoBasePlate();
@Shadowabstract void setShowArms(boolean var1);
@Shadowpublic abstract boolean getShowArms();
@Shadowpublic abstract void setHeadRotation(Rotations var1);
@Shadowpublic abstract void setBodyRotation(Rotations var1);
@Shadowpublic abstract void setLeftArmRotation(Rotations var1);
@Shadowpublic abstract void setRightArmRotation(Rotations var1);
@Shadowpublic abstract void setLeftLegRotation(Rotations var1);
@Shadowpublic abstract void setRightLegRotation(Rotations var1);
@Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
private void onAttacked(DamageSource damageSource, float damage, CallbackInfoReturnable<Boolean> callbackInfo){
m_rainbowAdjustedDamage = damage;
damageModified = false;
attacker = (MC_Entity) damageSource.getEntity();
MC_EventInfo ei = new MC_EventInfo();
Hooks.onAttemptEntityDamage(this, PluginHelper.wrap(damageSource), damage, ei);
if (ei.isCancelled){
callbackInfo.cancel();
callbackInfo.setReturnValue(false);
}}@Inject(method = "swapItem", at = @At("HEAD"), cancellable = true)
private void onArmorStandInteract(EntityPlayer var1, EntityEquipmentSlot var2, ItemStack var3, EnumHand var4, CallbackInfo callbackInfo){
MC_ArmorStandActionType type = MC_ArmorStandActionType.UNSPECIFIED;
switch (var2){
case MAINHAND:
type = MC_ArmorStandActionType.HELD_ITEM;
break;case OFFHAND:
type = MC_ArmorStandActionType.OFFHAND_ITEM;
break;case FEET:type = MC_ArmorStandActionType.FEET;
break;case LEGS:type = MC_ArmorStandActionType.LEGS;
break;case CHEST:type = MC_ArmorStandActionType.BODY;
break;case HEAD:type = MC_ArmorStandActionType.HEAD;
break;}MC_EventInfo ei = new MC_EventInfo();
Hooks.onAttemptArmorStandInteract((MC_Player) var1, this, type, ei);
if (ei.isCancelled){
callbackInfo.cancel();
}}@Overridepublic List<MC_ItemStack> getArmor(){
return PluginHelper.copyInvList(armorItems);
}@Overridepublic void setArmor(List<MC_ItemStack> var1){
PluginHelper.updateInv(armorItems, var1);
}@Overridepublic boolean hasArms(){
return getShowArms();
}@Overridepublic boolean hasBase(){
return !hasNoBasePlate();
}@Overridepublic void setHasArms(boolean flag){
setShowArms(flag);
}@Overridepublic void setHasBase(boolean flag){
setNoBasePlate(!flag);
}@Overridepublic List<MC_FloatTriplet> getPose(){
ArrayList<MC_FloatTriplet> pose = new ArrayList<MC_FloatTriplet>();
pose.add(wrap(this.headRotation));
pose.add(wrap(this.bodyRotation));
pose.add(wrap(this.leftArmRotation));
pose.add(wrap(this.rightArmRotation));
pose.add(wrap(this.leftLegRotation));
pose.add(wrap(this.rightLegRotation));
return pose;
}@Overridepublic void setPose(List<MC_FloatTriplet> pose){
Preconditions.checkNotNull(pose, "pose");
Preconditions.checkArgument(pose.size() == 6, "size != 6");
setHeadRotation(unwrap(pose.get(0)));
setBodyRotation(unwrap(pose.get(1)));
setLeftArmRotation(unwrap(pose.get(2)));
setRightArmRotation(unwrap(pose.get(3)));
setLeftLegRotation(unwrap(pose.get(4)));
setRightLegRotation(unwrap(pose.get(5)));
}@Overridepublic MC_ItemStack getItemInHand(){
return (MC_ItemStack) (Object) handItems.get(0);
}@Overridepublic void setItemInHand(MC_ItemStack item){
handItems.set(0, PluginHelper.getItemStack(item));
}@Overridepublic MC_ItemStack getItemInOffHand(){
return (MC_ItemStack) (Object) handItems.get(1);
}@Overridepublic void setItemInOffHand(MC_ItemStack item){
handItems.set(1, PluginHelper.getItemStack(item));
}private static MC_FloatTriplet wrap(Rotations rotations){
return new MC_FloatTriplet(rotations.getX(), rotations.getY(), rotations.getZ());
}private static Rotations unwrap(MC_FloatTriplet floatTriplet){
return new Rotations(floatTriplet.x, floatTriplet.y, floatTriplet.z);
}}
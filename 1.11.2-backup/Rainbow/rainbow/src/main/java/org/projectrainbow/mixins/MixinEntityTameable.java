package org.projectrainbow.mixins;
import PluginReference.MC_AnimalTameable;import PluginReference.MC_Player;import net.minecraft.entity.EntityLivingBase;import net.minecraft.entity.passive.EntityTameable;import org.spongepowered.asm.mixin.Implements;import org.spongepowered.asm.mixin.Interface;import org.spongepowered.asm.mixin.Intrinsic;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Shadow;import java.util.UUID;@Mixin(EntityTameable.class)
@Implements(@Interface(iface = MC_AnimalTameable.class, prefix = "api$"))
public abstract class MixinEntityTameable extends MixinEntityAnimal implements MC_AnimalTameable{
@Shadowpublic abstract boolean isTamed();
@Shadowpublic abstract void setTamed(boolean var1);
@Shadowpublic abstract boolean isSitting();
@Shadowpublic abstract void setSitting(boolean var1);
@Shadowpublic abstract UUID getOwnerId();
@Shadowpublic abstract void setOwnerId(UUID var1);
@Shadowpublic abstract EntityLivingBase shadow$getOwner();
@Shadowprotected abstract void playTameEffect(boolean var1);
@Intrinsicpublic boolean api$isTamed(){
return isTamed();
}@Intrinsicpublic void api$setTamed(boolean flag){
setTamed(flag);
}@Overridepublic boolean getSitting(){
return isSitting();
}@Intrinsicpublic void api$setSitting(boolean flag){
setSitting(flag);
}@Overridepublic String getUUIDOfOwner(){
return getOwnerId().toString();
}@Overridepublic void setUUIDOfOwner(String uuid){
setOwnerId(UUID.fromString(uuid));
}@Overridepublic void setOwner(MC_Player plr){
setUUIDOfOwner(plr.getUUID().toString());
}@Overridepublic MC_Player getOwner(){
return (MC_Player) shadow$getOwner();
}@Overridepublic boolean isOwnedBy(MC_Player plr){
return getUUIDOfOwner().endsWith(plr.getUUID().toString());
}@Overridepublic void showLoveHateEffect(boolean flag){
playTameEffect(flag);
}}
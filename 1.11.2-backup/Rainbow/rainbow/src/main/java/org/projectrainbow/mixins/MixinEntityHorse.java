package org.projectrainbow.mixins;
import PluginReference.MC_Attribute;import PluginReference.MC_AttributeType;import PluginReference.MC_Horse;import PluginReference.MC_HorseType;import PluginReference.MC_HorseVariant;import PluginReference.MC_Player;import net.minecraft.entity.ai.attributes.IAttribute;import net.minecraft.entity.passive.*;import org.spongepowered.asm.mixin.Final;import org.spongepowered.asm.mixin.Implements;import org.spongepowered.asm.mixin.Interface;import org.spongepowered.asm.mixin.Intrinsic;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Shadow;import java.util.UUID;@Mixin(AbstractHorse.class)
@Implements(@Interface(iface = MC_Horse.class, prefix = "api$"))
public abstract class MixinEntityHorse extends MixinEntityAnimal{
@Shadowprotected abstract boolean isTame();
@Shadowpublic abstract void setHorseTamed(boolean var1);
@Shadowpublic abstract int getTemper();
@Shadowpublic abstract void setTemper(int var1);
@Shadowpublic abstract UUID getOwnerUniqueId();
@Shadowpublic abstract void setOwnerUniqueId(UUID var1);
@Shadow@Finalprivate static IAttribute JUMP_STRENGTH;
public void setOwner(MC_Player plr){
if (plr == null){
setOwnerUniqueId(null);
return;}setOwnerUniqueId(plr.getUUID());
}public MC_HorseType api$getHorseType(){
AbstractHorse handle = (EntityHorse) (Object) this;
if (handle instanceof EntityHorse)
return MC_HorseType.HORSE;
if (handle instanceof EntityDonkey)
return MC_HorseType.DONKEY;
if (handle instanceof EntityMule)
return MC_HorseType.MULE;
if (handle instanceof EntityZombieHorse)
return MC_HorseType.ZOMBIE;
if (handle instanceof EntitySkeletonHorse)
return MC_HorseType.SKELETON;
if (handle instanceof EntityLlama)
return MC_HorseType.LLAMA;
return MC_HorseType.UNKNOWN;
}public void setHorseType(MC_HorseType arg){
throw new UnsupportedOperationException("setHorseType no longer works");
}public MC_HorseVariant getHorseVariant(){
AbstractHorse handle = (EntityHorse) (Object) this;
if (handle instanceof EntityHorse){
return MC_HorseVariant.values()[((EntityHorse) handle).getHorseVariant()];
}return MC_HorseVariant.UNKNOWN;
}public void setHorseVariant(MC_HorseVariant arg){
AbstractHorse handle = (EntityHorse) (Object) this;
if (handle instanceof EntityHorse){
((EntityHorse) handle).setHorseVariant(arg.ordinal());
}}public boolean hasChest(){
AbstractHorse handle = (EntityHorse) (Object) this;
return handle instanceof AbstractChestHorse && ((AbstractChestHorse) handle).dh();
}public void setHasChest(boolean flag){
AbstractHorse handle = (EntityHorse) (Object) this;
if (handle instanceof AbstractChestHorse){
((AbstractChestHorse) handle).setChested(flag);
}else{throw new UnsupportedOperationException("setHasChest not supported for this entity type");
}}public void api$setTamed(boolean flag){
setHorseTamed(flag);
}@Intrinsicpublic boolean api$isTame(){
return isTame();
}@Intrinsicpublic int api$getTemper(){
return getTemper();
}@Intrinsicpublic void api$setTemper(int val){
setTemper(val);
}public String getOwnerUUID(){
return getOwnerUniqueId().toString();
}public void setOwnerUUID(String strUUID){
setOwnerUniqueId(UUID.fromString(strUUID));
}@Overridepublic MC_Attribute getAttribute(MC_AttributeType type){
if (type == MC_AttributeType.HORSE_JUMP_STRENGTH){
return (MC_Attribute) getEntityAttribute(JUMP_STRENGTH);
}return super.getAttribute(type);
}}
package org.projectrainbow.mixins;
import PluginReference.MC_Ocelot;import PluginReference.MC_OcelotType;import net.minecraft.entity.passive.EntityOcelot;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Shadow;@Mixin(EntityOcelot.class)
public abstract class MixinEntityOcelot implements MC_Ocelot{
@Shadowpublic abstract int getTameSkin();
@Shadowpublic abstract void setTameSkin(int var1);
@Overridepublic MC_OcelotType getCatType(){
int idx = getTameSkin();
return idx == 0
? MC_OcelotType.WILD
: (idx == 1? MC_OcelotType.BLACK
: (idx == 2? MC_OcelotType.RED
: (idx == 3? MC_OcelotType.SIAMESE
: MC_OcelotType.UNKNOWN)));
}@Overridepublic void setCatType(MC_OcelotType catType){
if (catType == MC_OcelotType.WILD){
setTameSkin(0);
}if (catType == MC_OcelotType.BLACK){
setTameSkin(1);
}if (catType == MC_OcelotType.RED){
setTameSkin(2);
}if (catType == MC_OcelotType.SIAMESE){
setTameSkin(3);
}}}
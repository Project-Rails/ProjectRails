package org.projectrainbow.mixins;
import PluginReference.MC_EntityAgeable;import net.minecraft.entity.EntityAgeable;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Shadow;@Mixin(EntityAgeable.class)
public abstract class MixinEntityAgeable extends MixinEntityLiving implements MC_EntityAgeable{
@Shadowpublic abstract void setGrowingAge(int var1);
@Shadowpublic abstract int getGrowingAge();
@Overridepublic int getAge(){
return getGrowingAge();
}@Overridepublic void setAge(int idx){
setGrowingAge(idx);
}}
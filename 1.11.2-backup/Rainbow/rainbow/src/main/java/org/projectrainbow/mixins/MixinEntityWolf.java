package org.projectrainbow.mixins;
import PluginReference.MC_Wolf;import net.minecraft.entity.passive.EntityWolf;import org.spongepowered.asm.mixin.Implements;import org.spongepowered.asm.mixin.Interface;import org.spongepowered.asm.mixin.Intrinsic;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Shadow;@Mixin(EntityWolf.class)
@Implements(@Interface(iface = MC_Wolf.class, prefix = "api$"))
public abstract class MixinEntityWolf{
@Shadowpublic abstract boolean isAngry();
@Shadowpublic abstract void setAngry(boolean var1);
/*** Check if angry
** @return True if angry, False otherwise
*/@Intrinsicpublic boolean api$getAngry(){
return isAngry();
}/*** Set angry flag
** @param flag True if angry, false otherwise
*/@Intrinsicpublic void api$setAngry(boolean flag){
setAngry(flag);
}}
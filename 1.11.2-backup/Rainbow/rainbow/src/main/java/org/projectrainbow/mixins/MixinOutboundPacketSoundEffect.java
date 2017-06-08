package org.projectrainbow.mixins;
import net.minecraft.network.play.server.SPacketSoundEffect;import net.minecraft.util.SoundEvent;import org.projectrainbow.interfaces.IMixinOutboundPacketSoundEffect;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Shadow;@Mixin(SPacketSoundEffect.class)
public class MixinOutboundPacketSoundEffect implements IMixinOutboundPacketSoundEffect{
@Shadowprivate SoundEvent sound;
@Shadowprivate int posX;
@Shadowprivate int posY;
@Shadowprivate int posZ;
@Overridepublic int getX(){
return posX;
}@Overridepublic int getY(){
return posY;
}@Overridepublic int getZ(){
return posZ;
}@Overridepublic String getSoundName(){
return SoundEvent.REGISTRY.getNameForObject(sound).getResourcePath();
}}
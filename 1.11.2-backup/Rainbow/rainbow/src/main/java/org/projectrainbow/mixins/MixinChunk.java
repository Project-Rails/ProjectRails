package org.projectrainbow.mixins;
import PluginReference.MC_Chunk;import net.minecraft.world.chunk.Chunk;import org.spongepowered.asm.mixin.Final;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Shadow;@Mixin(Chunk.class)
public class MixinChunk implements MC_Chunk{
@Shadow@Finalpublic int xPosition;
@Shadow@Finalpublic int zPosition;
@Overridepublic int getCX(){
return xPosition;
}@Overridepublic int getCZ(){
return zPosition;
}}
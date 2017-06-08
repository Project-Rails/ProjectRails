package org.projectrainbow.mixins;
import PluginReference.MC_Instrument;import PluginReference.MC_NoteBlock;import net.minecraft.init.Blocks;import net.minecraft.tileentity.TileEntity;import net.minecraft.tileentity.TileEntityNote;import net.minecraft.util.math.BlockPos;import net.minecraft.world.World;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Shadow;@Mixin(TileEntityNote.class)
public abstract class MixinTileEntityNote extends TileEntity implements MC_NoteBlock{
@Shadowpublic byte note;
@Shadowpublic abstract void triggerNote(World var1, BlockPos var2);
@Overridepublic int getNote(){
return note;
}@Overridepublic void setNote(int note){
if (note < 0 || note > 24){
throw new IllegalArgumentException("Note must be between 0 and 24");
}this.note = (byte) note;
markDirty();
}@Overridepublic void play(){
triggerNote(getWorld(), getPos());
}@Overridepublic void play(MC_Instrument instrument, int note){
if (note < 0 || note > 24){
throw new IllegalArgumentException("Note must be between 0 and 24");
}getWorld().addBlockEvent(getPos(), Blocks.NOTEBLOCK, instrument.ordinal(), note);
}}
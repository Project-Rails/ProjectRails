package org.projectrainbow.mixins;
import PluginReference.MC_Chest;import PluginReference.MC_DirectionNESWUD;import PluginReference.MC_ItemStack;import net.minecraft.block.Block;import net.minecraft.item.ItemStack;import net.minecraft.tileentity.TileEntity;import net.minecraft.tileentity.TileEntityChest;import net.minecraft.util.NonNullList;import org.projectrainbow.PluginHelper;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Shadow;import java.util.List;@Mixin(TileEntityChest.class)
public abstract class MixinTileEntityChest extends TileEntity implements MC_Chest{
@Shadowprivate NonNullList<ItemStack> chestContents;
@Shadowpublic TileEntityChest adjacentChestZNeg;
@Shadowpublic TileEntityChest adjacentChestXPos;
@Shadowpublic TileEntityChest adjacentChestXNeg;
@Shadowpublic TileEntityChest adjacentChestZPos;
@Overridepublic List<MC_ItemStack> getInventory(){
return PluginHelper.copyInvList(chestContents);
}@Overridepublic void setInventory(List<MC_ItemStack> var1){
PluginHelper.updateInv(chestContents, var1);
}@Overridepublic MC_Chest GetLinkedChestAt(MC_DirectionNESWUD var1){
if (adjacentChestXNeg != null)
return (MC_Chest) adjacentChestXNeg;
else if (adjacentChestXPos != null)
return (MC_Chest) adjacentChestXPos;
else if (adjacentChestZNeg != null)
return (MC_Chest) adjacentChestZNeg;
elsereturn (MC_Chest) adjacentChestZPos;
}@Overridepublic int getBlockId(){
return Block.getIdFromBlock(getBlockType());
}}
package org.projectrainbow.mixins;
import net.minecraft.entity.player.EntityPlayerMP;import net.minecraft.inventory.Container;import net.minecraft.inventory.ContainerWorkbench;import net.minecraft.inventory.IInventory;import net.minecraft.inventory.InventoryCrafting;import net.minecraft.item.ItemStack;import net.minecraft.item.crafting.CraftingManager;import net.minecraft.network.play.server.SPacketSetSlot;import net.minecraft.world.World;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Overwrite;import org.spongepowered.asm.mixin.Shadow;@Mixin(ContainerWorkbench.class)
public abstract class MixinContainerWorkbench extends Container{
@Shadowpublic InventoryCrafting craftMatrix;
@Shadowpublic IInventory craftResult;
@Shadowprivate World worldObj;
@Overwritepublic void onCraftMatrixChanged(IInventory var1){
ItemStack itemStack = CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj);
this.craftResult.setInventorySlotContents(0, itemStack);
if (super.listeners.size() < 1){
return;}EntityPlayerMP player = (EntityPlayerMP) super.listeners.get(0);
player.connection.sendPacket(new SPacketSetSlot(player.openContainer.windowId, 0, itemStack));
}}
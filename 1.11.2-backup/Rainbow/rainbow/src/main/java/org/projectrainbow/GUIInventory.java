package org.projectrainbow;
import PluginReference.MC_InventoryGUI;import PluginReference.MC_ItemStack;import net.minecraft.entity.player.EntityPlayer;import net.minecraft.entity.player.InventoryPlayer;import net.minecraft.inventory.Container;import net.minecraft.inventory.InventoryBasic;import net.minecraft.item.ItemStack;import net.minecraft.world.IInteractionObject;public class GUIInventory extends InventoryBasic implements MC_InventoryGUI, IInteractionObject{
private ClickHandler[] clickHandlers;
public GUIInventory(String title, int size){
super(title, true, size);
clickHandlers = new ClickHandler[size];
}@Overridepublic int getSize(){
return getSizeInventory();
}@Overridepublic MC_ItemStack getItemStackAt(int index){
return ((MC_ItemStack) (Object) getStackInSlot(index));
}@Overridepublic void setItemStackAt(int index, MC_ItemStack itemStack){
setInventorySlotContents(index, PluginHelper.getItemStack(itemStack));
}public String getTitle(){
return getName();
}@Overridepublic void setClickHandler(int slotIndex, ClickHandler clickHandler){
this.clickHandlers[slotIndex] = clickHandler;
}@Overridepublic ClickHandler getClickHandler(int slotIndex){
return clickHandlers[slotIndex];
}@Overridepublic Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer entityPlayer){
return new GUIContainer(inventoryPlayer, this, entityPlayer);
}@Overridepublic String getGuiID(){
return "minecraft:container";
}}
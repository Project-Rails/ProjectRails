package org.projectrainbow.mixins;
import net.minecraft.block.Block;import net.minecraft.item.ItemBlockSpecial;import org.projectrainbow.interfaces.IMixinItemReed;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Shadow;@Mixin(ItemBlockSpecial.class)
public class MixinItemReed implements IMixinItemReed{
@Shadowprivate Block block;
@Overridepublic Block getBlock(){
return block;
}}
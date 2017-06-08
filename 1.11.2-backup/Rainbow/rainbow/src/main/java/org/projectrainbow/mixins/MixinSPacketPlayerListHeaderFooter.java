package org.projectrainbow.mixins;
import net.minecraft.network.play.server.SPacketPlayerListHeaderFooter;import net.minecraft.util.text.ITextComponent;import org.projectrainbow.interfaces.IMixinSPacketPlayerListHeaderFooter;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Shadow;@Mixin(SPacketPlayerListHeaderFooter.class)
public class MixinSPacketPlayerListHeaderFooter implements IMixinSPacketPlayerListHeaderFooter{
@Shadowprivate ITextComponent header;
@Shadowprivate ITextComponent footer;
@Overridepublic void setHeader(ITextComponent header){
this.header = header;
}@Overridepublic void setFooter(ITextComponent footer){
this.footer = footer;
}}
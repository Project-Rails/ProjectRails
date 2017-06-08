package org.projectrainbow.mixins;
import net.minecraft.entity.player.PlayerCapabilities;import org.projectrainbow.interfaces.IMixinPlayerCapabilities;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Shadow;@Mixin(PlayerCapabilities.class)
public class MixinPlayerCapabilities implements IMixinPlayerCapabilities{
@Shadowprivate float flySpeed;
@Shadowprivate float walkSpeed;
@Overridepublic void setFlySpeed(float flySpeed){
this.flySpeed = flySpeed;
}@Overridepublic void setWalkSpeed(float walkSpeed){
this.walkSpeed = walkSpeed;
}}
package org.projectrainbow.mixins;
import PluginReference.MC_AttributeModifier;import net.minecraft.entity.ai.attributes.AttributeModifier;import org.projectrainbow.PluginHelper;import org.spongepowered.asm.mixin.Final;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Shadow;import java.util.UUID;@Mixin(AttributeModifier.class)
public class MixinAttributeModifier implements MC_AttributeModifier{
@Shadow@Finalprivate double amount;
@Shadow@Finalprivate int operation;
@Shadow@Finalprivate String name;
@Shadow@Finalprivate UUID id;
/*** Get the operator used by this attribute modifier.
** @return the operator
*/@Overridepublic Operator getOperator(){
return PluginHelper.operatorMap.inverse().get(operation);
}/*** Get the value of this attribute modifier.
** @return the value
*/@Overridepublic double getValue(){
return amount;
}/*** Get the name of this attribute modifier.
** @return the name
*/@Overridepublic String getName(){
return name;
}/*** Get the uuid of the attribute modifier. Each attribute modifier must have
* a unique UUID
** @return the UUID
*/@Overridepublic UUID getUUID(){
return id;}}
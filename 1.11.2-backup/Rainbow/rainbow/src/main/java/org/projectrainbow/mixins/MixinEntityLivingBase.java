package org.projectrainbow.mixins;
import PluginReference.MC_Attribute;import PluginReference.MC_AttributeType;import PluginReference.MC_DamageType;import PluginReference.MC_Entity;import PluginReference.MC_EventInfo;import PluginReference.MC_LivingEntity;import PluginReference.MC_Player;import PluginReference.MC_PotionEffect;import net.minecraft.entity.EntityLivingBase;import net.minecraft.entity.SharedMonsterAttributes;import net.minecraft.entity.ai.attributes.IAttribute;import net.minecraft.entity.ai.attributes.IAttributeInstance;import net.minecraft.entity.player.EntityPlayer;import net.minecraft.potion.Potion;import net.minecraft.potion.PotionEffect;import net.minecraft.util.CombatRules;import net.minecraft.util.DamageSource;import org.projectrainbow.Hooks;import org.projectrainbow.PluginHelper;import org.spongepowered.asm.mixin.Final;import org.spongepowered.asm.mixin.Implements;import org.spongepowered.asm.mixin.Interface;import org.spongepowered.asm.mixin.Intrinsic;import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.Shadow;import org.spongepowered.asm.mixin.injection.At;import org.spongepowered.asm.mixin.injection.Inject;import org.spongepowered.asm.mixin.injection.ModifyArg;import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;import java.util.ArrayList;import java.util.Collection;import java.util.List;import java.util.Map;@Mixin(EntityLivingBase.class)
@Implements(@Interface(iface = MC_Entity.class, prefix = "api$"))
public abstract class MixinEntityLivingBase extends MixinEntity implements MC_LivingEntity{
@Shadowprotected EntityPlayer attackingPlayer;
@Shadowprivate EntityLivingBase lastAttacker;
@Shadow@Finalprivate Map<Potion, PotionEffect> activePotionsMap;
@Shadowprotected abstract void onNewPotionEffect(PotionEffect var1);
@Shadowprotected abstract void onChangedPotionEffect(PotionEffect var1, boolean var2);
@Shadow@Finalpublic abstract float getHealth();
@Shadowpublic abstract void setHealth(float var1);
@Shadow@Finalpublic abstract float getMaxHealth();
@Shadowpublic abstract float getAbsorptionAmount();
@Shadowpublic abstract void setAbsorptionAmount(float var1);
@Shadow@Finalpublic abstract void setArrowCountInEntity(int var1);
@Shadowpublic abstract Collection<PotionEffect> getActivePotionEffects();
@Shadowpublic abstract void clearActivePotions();
@Shadowprotected abstract float applyPotionDamageCalculations(DamageSource var1, float var2);
@Shadowpublic abstract IAttributeInstance getEntityAttribute(IAttribute var1);
@Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
private void onAttacked(DamageSource damageSource, float damage, CallbackInfoReturnable<Boolean> callbackInfo){
m_rainbowAdjustedDamage = damage;
damageModified = false;
attacker = (MC_Entity) damageSource.getEntity();
MC_EventInfo ei = new MC_EventInfo();
Hooks.onAttemptEntityDamage(this, PluginHelper.wrap(damageSource), damage, ei);
if (ei.isCancelled){
callbackInfo.cancel();
callbackInfo.setReturnValue(false);
}}@ModifyArg(method = "attackEntityFrom", at = @At(value = "INVOKE", target = "net.minecraft.entity.EntityLivingBase.damageEntity(Lnet/minecraft/util/DamageSource;F)V"))
private float applyAdjustedDamage(float oldDamage){
return damageModified ? m_rainbowAdjustedDamage : oldDamage;
}@Inject(method = "attackEntityFrom", at = @At(value = "INVOKE", target = "net.minecraft.entity.EntityLivingBase.onDeath(Lnet/minecraft/util/DamageSource;)V"))
private void hookOnDeath(DamageSource var1, float var2, CallbackInfoReturnable<Boolean> callbackInfo){
Hooks.onAttemptDeath(this, (MC_Entity) var1.getEntity(), PluginHelper.wrap(var1), var2);
if (!(this instanceof MC_Player)){
Hooks.onNonPlayerEntityDeath(this, (MC_Entity) var1.getEntity(), PluginHelper.wrap(var1));
}}@Inject(method = "addPotionEffect", at = @At("HEAD"), cancellable = true)
private void onAddPotionEffect(PotionEffect var1, CallbackInfo callbackInfo){
if (this instanceof MC_Player){
MC_EventInfo ei = new MC_EventInfo();
Hooks.onAttemptPotionEffect((MC_Player) this, PluginHelper.potionMap.get(var1.getPotion()), ei);
if (ei.isCancelled){
callbackInfo.cancel();
}}}@Intrinsicpublic float api$getHealth(){
return getHealth();
}@Intrinsicpublic void api$setHealth(float var1){
setHealth(var1);
}@Intrinsicpublic float api$getMaxHealth(){
return getMaxHealth();
}@Overridepublic void setMaxHealth(float var1){
((EntityLivingBase) (Object) this).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(var1);
}@Overridepublic int getBaseArmorScore(){
return ((EntityLivingBase) (Object) this).getTotalArmorValue();
}@Overridepublic float getArmorAdjustedDamage(MC_DamageType var1, float var2){
return CombatRules.getDamageAfterMagicAbsorb(var2, (float) ((EntityLivingBase) (Object) this).getTotalArmorValue());
}@Overridepublic float getTotalAdjustedDamage(MC_DamageType var1, float var2){
var2 = getArmorAdjustedDamage(var1, var2);
var2 = applyPotionDamageCalculations(PluginHelper.unwrap(var1), var2);
return Math.max(var2 - getAbsorptionAmount(), 0);
}@Intrinsicpublic float api$getAbsorptionAmount(){
return getAbsorptionAmount();
}@Intrinsicpublic void api$setAbsorptionAmount(float var1){
setAbsorptionAmount(var1);
}@Overridepublic void setNumberOfArrowsHitWith(int var1){
setArrowCountInEntity(var1);
}@Overridepublic List<MC_PotionEffect> getPotionEffects(){
List<MC_PotionEffect> potionEffects = new ArrayList<MC_PotionEffect>();
for (PotionEffect potionEffect:getActivePotionEffects()){
potionEffects.add(PluginHelper.wrap(potionEffect));
}return potionEffects;
}@Overridepublic void setPotionEffects(List<MC_PotionEffect> var1){
clearActivePotions();
for (MC_PotionEffect mc_potionEffect:var1){
PotionEffect potionEffect = PluginHelper.unwrap(mc_potionEffect);
PotionEffect var2 = this.activePotionsMap.get(potionEffect.getPotion());
if (var2 == null){
this.activePotionsMap.put(potionEffect.getPotion(), potionEffect);
this.onNewPotionEffect(potionEffect);
}else{var2.combine(potionEffect);
this.onChangedPotionEffect(var2, true);
}}}@Overridepublic MC_Attribute getAttribute(MC_AttributeType type){
IAttribute attribute = PluginHelper.attributeMap.get(type);
return attribute == null ? null : (MC_Attribute) getEntityAttribute(attribute);
}}
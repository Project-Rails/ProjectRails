package org.projectrainbow.mixins;
import PluginReference.MC_DamageType;import PluginReference.MC_Entity;import PluginReference.MC_EntityType;import PluginReference.MC_EventInfo;import PluginReference.MC_FloatTriplet;import PluginReference.MC_ItemStack;import PluginReference.MC_Location;import PluginReference.MC_MotionData;import PluginReference.MC_PotionEffect;import PluginReference.MC_World;import com.google.common.base.Objects;import com.google.common.base.Preconditions;import com.google.common.base.Predicates;import net.minecraft.block.state.IBlockState;import net.minecraft.entity.Entity;import net.minecraft.entity.EntityList;import net.minecraft.entity.player.EntityPlayerMP;import net.minecraft.nbt.NBTTagCompound;import net.minecraft.util.DamageSource;import net.minecraft.util.math.AxisAlignedBB;import net.minecraft.util.math.BlockPos;import net.minecraft.world.World;import net.minecraft.world.WorldServer;import org.projectrainbow.Hooks;import org.projectrainbow.PluginHelper;import org.projectrainbow._DiwUtils;import org.projectrainbow.interfaces.IMixinNBTBase;import org.spongepowered.asm.mixin.*;import org.spongepowered.asm.mixin.injection.*;import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;import java.io.ByteArrayOutputStream;import java.io.DataOutputStream;import java.util.Collections;import java.util.List;import java.util.UUID;@Mixin(Entity.class)
@Implements(@Interface(iface = MC_Entity.class, prefix = "api$"))
public abstract class MixinEntity implements MC_Entity{
protected float m_rainbowAdjustedDamage = 0;
protected boolean damageModified = false;
@Shadowpublic float rotationYaw;
@Shadowpublic float rotationPitch;
@Shadowpublic int dimension;
@Shadowpublic World world;
@Shadowpublic boolean isDead;
@Shadowpublic double posX;
@Shadowpublic double posY;
@Shadowpublic double posZ;
@Shadowpublic double motionX;
@Shadowpublic double motionY;
@Shadowpublic double motionZ;
@Shadowpublic boolean onGround;
@Shadowpublic float fallDistance;
@Shadowprotected boolean inWater;
@Shadowprivate boolean invulnerable;
@Shadowprivate int fire;
protected MC_Entity attacker = null;
@Shadowpublic abstract List<Entity> getPassengers();
@Shadowpublic abstract void setWorld(World w);
@Shadowpublic abstract void setPositionAndRotation(double x, double y, double z, float yaw, float pitch);
@Shadowpublic abstract AxisAlignedBB getEntityBoundingBox();
@Shadowpublic abstract void setSneaking(boolean sneaking);
@Shadowpublic abstract UUID getUniqueID();
@Shadowpublic abstract void onKillCommand();
@Shadowpublic abstract void setCustomNameTag(String name);
@Shadowpublic abstract String getCustomNameTag();
@Shadowpublic abstract void setDead();
@Shadowpublic abstract boolean isEntityInvulnerable(DamageSource var1);
@Shadowpublic abstract boolean shadow$isSneaking();
@Shadowpublic abstract boolean shadow$isInvisible();
@Shadowpublic abstract boolean shadow$startRiding(Entity var1);
@Shadowpublic abstract String getName();
@Shadowpublic abstract boolean isSprinting();
@Shadowpublic abstract void setInvisible(boolean var1);
@Shadowpublic abstract boolean hasCustomName();
@Shadowpublic abstract int getEntityId();
@Shadowpublic abstract Entity getRidingEntity();
@Shadowpublic abstract void setPosition(double var1, double var3, double var5);
@Shadowpublic abstract void dismountRidingEntity();
protected void setInvulnerable(boolean value){
invulnerable = value;
}private float waterFallDistance = 0;
@Inject(method = "handleWaterMovement", at = @At(value = "FIELD", target = "net.minecraft.entity.Entity.fallDistance:F"))
private void onWaterEntered(CallbackInfoReturnable<Boolean> callbackInfo){
waterFallDistance = fallDistance;
}@Inject(method = "updateFallState", at = @At("HEAD"))
protected void a(double var1, boolean onGround, IBlockState var4, BlockPos var5, CallbackInfo callbackInfo){
if (onGround && fallDistance > 0){
Hooks.onFallComplete(this, this.fallDistance, new MC_Location(var5.getX(), var5.getY(), var5.getZ(), dimension), inWater);
}else if (inWater && waterFallDistance > 0){Hooks.onFallComplete(this, this.waterFallDistance, new MC_Location(var5.getX(), var5.getY(), var5.getZ(), dimension), inWater);
waterFallDistance = 0;
}}@Redirect(method = "applyEntityCollision", at = @At(value = "INVOKE", target = "net.minecraft.entity.Entity.addVelocity(DDD)V"))
void onEntityPushed(Entity pushedEntity, double xVelocity, double yVelocity, double zVelocity, Entity other){
MC_Entity entity = pushedEntity == other ? this : (MC_Entity) other;
MC_EventInfo ei = new MC_EventInfo();
MC_FloatTriplet velocity = new MC_FloatTriplet((float) xVelocity, (float) yVelocity, (float) zVelocity);
Hooks.onEntityPushed(entity, (MC_Entity) pushedEntity, velocity, ei);
if (!ei.isCancelled){
pushedEntity.addVelocity(velocity.x, velocity.y, velocity.z);
}}@ModifyConstant(method = "changeDimension", constant = @Constant(doubleValue = 8.0D))
private double injectNetherDistanceRatio(double ignored){
return _DiwUtils.netherDistanceRatio;
}@Overridepublic MC_Location getLocation(){
return new MC_Location(posX, posY, posZ, dimension, rotationYaw, rotationPitch);
}@Overridepublic MC_World getWorld(){
return (MC_World) world;
}@Overridepublic MC_EntityType getType(){
MC_EntityType type = PluginHelper.getEntityType((Class<? extends Entity>) (Object) getClass());
return Objects.firstNonNull(type, MC_EntityType.UNSPECIFIED);
}@Overridepublic boolean isDead(){
return isDead;
}@Overridepublic void kill(){
onKillCommand();
}@Overridepublic MC_Entity getVehicle(){
return (MC_Entity) getRidingEntity();
}@Overridepublic MC_Entity getRider(){
return getPassengers().isEmpty() ? null : (MC_Entity) getPassengers().get(0);
}@Overridepublic void setRider(MC_Entity var1){
if (var1 == null){
MC_Entity rider = getRider();
if (rider != null){
((Entity) rider).dismountRidingEntity();
}return;}((Entity) var1).startRiding((Entity) (Object) this);
}@Overridepublic void setVehicle(MC_Entity var1){
if (var1 == null){
((Entity) (Object) this).dismountRidingEntity();
return;}shadow$startRiding((Entity) var1);
}@Overridepublic List<MC_ItemStack> getArmor(){
throw new UnsupportedOperationException("This entity has no armor.");
}@Overridepublic void setArmor(List<MC_ItemStack> var1){
throw new UnsupportedOperationException("This entity has no armor.");
}@Overridepublic String internalInfo(){
return EntityList.getEntityString((Entity) (Object) this) + ": " + toString();
}@Overridepublic int getFireTicks(){
return fire;
}@Overridepublic void setFireTicks(int var1){
fire = var1;
}@Overridepublic MC_MotionData getMotionData(){
MC_MotionData motionData = new MC_MotionData();
motionData.xMotion = motionX;
motionData.yMotion = motionY;
motionData.zMotion = motionZ;
motionData.onGround = onGround;
motionData.fallDistance = fallDistance;
motionData.inWater = inWater;
return motionData;
}@Overridepublic void setMotionData(MC_MotionData motionData){
Preconditions.checkNotNull(motionData, "motionData");
motionX = motionData.xMotion;
motionY = motionData.yMotion;
motionZ = motionData.zMotion;
onGround = motionData.onGround;
fallDistance = (float) motionData.fallDistance;
inWater = motionData.inWater;
}@Overridepublic void setCustomName(String var1){
setCustomNameTag(var1);
}@Overridepublic String getCustomName(){
return getCustomNameTag();
}@Overridepublic float getHealth(){
return 1;}@Overridepublic void setHealth(float var1){
}@Overridepublic float getMaxHealth(){
return 1;}@Overridepublic void setMaxHealth(float var1){
}@Overridepublic int getBaseArmorScore(){
return 0;}@Overridepublic float getArmorAdjustedDamage(MC_DamageType var1, float var2){
return 0;}@Overridepublic float getTotalAdjustedDamage(MC_DamageType var1, float var2){
return 0;}@Overridepublic float getAbsorptionAmount(){
return 0;}@Overridepublic void setAbsorptionAmount(float var1){
}@Overridepublic void setNumberOfArrowsHitWith(int var1){
}@Overridepublic MC_Entity getAttacker(){
return attacker;
}@Overridepublic List<MC_PotionEffect> getPotionEffects(){
return Collections.emptyList();
}@Overridepublic void setPotionEffects(List<MC_PotionEffect> var1){
}@Overridepublic void setAdjustedIncomingDamage(float var1){
m_rainbowAdjustedDamage = var1;
damageModified = true;
}@Overridepublic List getNearbyEntities(float var1){
return world.getEntitiesInAABBexcluding((Entity) (Object) this, new AxisAlignedBB(posX - var1, posY - var1, posZ - var1, posX + var1, posY + var1, posZ + var1), Predicates.<Entity>alwaysTrue());
}@Overridepublic void removeEntity(){
setDead();}@Intrinsicpublic boolean api$isSneaking(){
return shadow$isSneaking();
}@Intrinsicpublic boolean api$isInvisible(){
return shadow$isInvisible();
}@Intrinsicpublic String api$getName(){
return getName();
}@Intrinsicpublic boolean api$isSprinting(){
return isSprinting();
}@Intrinsicpublic void api$setInvisible(boolean var1){
setInvisible(var1);
}@Intrinsicpublic boolean api$hasCustomName(){
return hasCustomName();
}@Intrinsicpublic int api$getEntityId(){
return getEntityId();
}@Overridepublic List<MC_Entity> getRiders(){
return (List<MC_Entity>) (Object) getPassengers();
}@Overridepublic void addRider(MC_Entity ent){
((Entity) ent).startRiding((Entity) (Object) this);
}@Overridepublic void removeRider(MC_Entity ent){
((Entity)ent).dismountRidingEntity();
}@Overridepublic UUID getUUID(){
return getUniqueID();
}@Overridepublic void teleport(MC_Location loc){
teleport(loc, true);
}@Overridepublic void teleport(MC_Location loc, boolean safe){
teleport(_DiwUtils.getMinecraftServer().worldServerForDimension(loc.dimension), loc.x, loc.y, loc.z, loc.yaw, loc.pitch, safe);
}@Overridepublic byte[] serialize(){
NBTTagCompound data = new NBTTagCompound();
((Entity) (Object) this).writeToNBT(data);
data.setString("id", EntityList.getEntityString((Entity) (Object) this));
data.removeTag("UUIDMost");
data.removeTag("UUIDLeast");
data.removeTag("Dimension");
try{ByteArrayOutputStream os = new ByteArrayOutputStream();
DataOutputStream dos = new DataOutputStream(os);
((IMixinNBTBase) data).write1(dos);
dos.flush();
dos.close();
return os.toByteArray();
}catch (Exception var5){var5.printStackTrace();
return new byte[0];
}}public void teleport(WorldServer world, double x, double y, double z){
teleport(world, x, y, z, rotationYaw, rotationPitch);
}public void teleport(WorldServer world, double x, double y, double z, float yaw, float pitch){
teleport(world, x, y, z, yaw, pitch, true);
}public void teleport(WorldServer world, double x, double y, double z, float yaw, float pitch, boolean safe){
dismountRidingEntity();
for (Entity passenger:getPassengers()){
passenger.dismountRidingEntity();
}if (world != this.world){
final WorldServer fromWorld = (WorldServer) this.world;
final WorldServer toWorld = world;
fromWorld.removeEntityDangerously((EntityPlayerMP) (Object) this);
dimension = ((MC_World) toWorld).getDimension();
setPositionAndRotation(x, y, z, yaw, pitch);
toWorld.getChunkProvider().loadChunk((int) posX >> 4, (int) posZ >> 4);
while(safe && !toWorld.getCollisionBoxes((Entity) (Object) this, this.getEntityBoundingBox()).isEmpty() && this.posY < 255.0D){
this.setPosition(this.posX, this.posY + 1.0D, this.posZ);
}this.setWorld(toWorld);
toWorld.spawnEntityInWorld((Entity) (Object) this);
fromWorld.resetUpdateEntityTick();
toWorld.resetUpdateEntityTick();
}else{setPositionAndRotation(x, y, z, yaw, pitch);
}}}
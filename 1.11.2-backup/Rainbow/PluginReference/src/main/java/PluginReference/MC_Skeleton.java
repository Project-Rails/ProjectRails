package PluginReference;
/*** A Skeleton
*/public interface MC_Skeleton extends MC_LivingEntity{
/*** Get type of skeleton (regular / wither)
** @return Skeleton type enum
*/@DeprecatedMC_SkeletonType getSkeletonType();
/*** Set type of skeleton (regular, wither)
** @param skellyType Skeleton type
*/@Deprecatedvoid setSkeletonType(MC_SkeletonType skellyType);
}
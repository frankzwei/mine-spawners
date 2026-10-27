package me.katanya04.minespawners.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTypes;

/**
 * Mixin that allows non-creative players to place a spawner without it's NBT resetting
 */
@Mixin(BlockEntityType.class)
public abstract class SpawnerBlockPermissionsMixin {

    @Inject(method = "onlyOpCanSetNbt", at = @At("HEAD"), cancellable = true)
    private void minSpawners$allowSpawnerData(CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this == BlockEntityTypes.MOB_SPAWNER || (Object) this == BlockEntityTypes.TRIAL_SPAWNER) {
            cir.setReturnValue(false);
        }
    }
}
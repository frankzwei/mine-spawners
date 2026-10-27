package me.katanya04.minespawners.mixins;

import net.minecraft.references.BlockItemIds;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

/**
 * Make trial spawners drop only when mined with required tool (pickaxe)
 */
@Mixin(Blocks.class)
public class TrialSpawnerCorrectToolMixin {

    @ModifyArgs(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/Blocks;register(Lnet/minecraft/references/BlockItemId;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;"
            )
    )
    private static void mineSpawners$requireCorrectTool(Args args) {
        if (BlockItemIds.TRIAL_SPAWNER.equals(args.get(0))) {
            BlockBehaviour.Properties properties = args.get(2);
            args.set(2, properties.requiresCorrectToolForDrops());
        }
    }
}
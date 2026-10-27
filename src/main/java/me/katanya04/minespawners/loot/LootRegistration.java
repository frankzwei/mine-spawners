package me.katanya04.minespawners.loot;

import com.mojang.serialization.MapCodec;

import me.katanya04.minespawners.MineSpawnersMod;
import me.katanya04.minespawners.loot.functions.CopyDataComponentFunction;
import me.katanya04.minespawners.loot.functions.SetDataComponentFunction;
import me.katanya04.minespawners.loot.conditions.MatchToolWithDynamicTag;
import me.katanya04.minespawners.loot.lootnbtprovider.ContextAndBlockEntityLootNbtProvider;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

/**
 * Registration of the {@link SetDataComponentFunction} and {@link CopyDataComponentFunction} loot functions, and
 * {@link MatchToolWithDynamicTag} loot condition
 */
public class LootRegistration {

    public static MapCodec<SetDataComponentFunction> setDataComponentFunctionType;
    public static MapCodec<CopyDataComponentFunction> copyDataComponentFunctionType;
    public static MapCodec<MatchToolWithDynamicTag> matchToolWithDynamicTagType;
    public static MapCodec<ContextAndBlockEntityLootNbtProvider> ContextAndBlockEntityLootNbtProviderType;

    public static void register() {
        setDataComponentFunctionType = Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, Identifier.fromNamespaceAndPath(MineSpawnersMod.MOD_ID, "set_data_component"), SetDataComponentFunction.CODEC);
        copyDataComponentFunctionType = Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, Identifier.fromNamespaceAndPath(MineSpawnersMod.MOD_ID, "copy_data_components"), CopyDataComponentFunction.CODEC);
        matchToolWithDynamicTagType = Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, Identifier.fromNamespaceAndPath(MineSpawnersMod.MOD_ID, "match_tool_with_dynamic_tag"), MatchToolWithDynamicTag.CODEC);
        ContextAndBlockEntityLootNbtProviderType = Registry.register(BuiltInRegistries.LOOT_NBT_PROVIDER_TYPE, Identifier.fromNamespaceAndPath(MineSpawnersMod.MOD_ID, "context_and_block_entity"), ContextAndBlockEntityLootNbtProvider.MAP_CODEC);
    }
}
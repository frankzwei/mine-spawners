package me.katanya04.minespawners.tags;

import me.katanya04.minespawners.MineSpawnersMod;
import me.katanya04.minespawners.config.SimpleConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * A dynamic tag, is not registered anywhere, it is just a map tag-supplier of elements
 */
public class DynamicTags {

    public static final TagKey<Item> BLACKLISTED = TagKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(MineSpawnersMod.MOD_ID, "blacklisted"));
    private static final Map<TagKey<Item>, Supplier<Set<Item>>> DYNAMIC_TAGS = new HashMap<>();

    static {
        DYNAMIC_TAGS.put(BLACKLISTED, () -> SimpleConfig.BLACKLISTED_PICKAXES.getValue().stream()
                .map(p -> BuiltInRegistries.ITEM.getValue(Identifier.tryParse(p))).collect(Collectors.toSet()));
    }

    public static boolean isInTag(ItemInstance stack, TagKey<Item> tag) {
        return stack.is(tag) || DYNAMIC_TAGS.getOrDefault(tag, Collections::emptySet).get().contains(stack.typeHolder().value());
    }
}
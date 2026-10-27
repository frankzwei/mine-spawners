package me.katanya04.minespawners.config.valuetypes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.katanya04.minespawners.MineSpawnersMod;
import me.katanya04.minespawners.config.SimpleConfig;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProvider;

import org.jetbrains.annotations.NotNull;

/**
 * Config value that holds a float as its value
 */
public class FloatConfigValue extends ConfigValue<Float> implements ContextFloatProvider {

    private static final MapCodec<FloatConfigValue> LOOT_CODEC = RecordCodecBuilder
            .mapCodec(instance -> instance.group(Codec.STRING.fieldOf("key").forGetter(ConfigValue::getKey))
            .apply(instance, path -> (FloatConfigValue) SimpleConfig.values.get(path)));

    static {
        Registry.register(BuiltInRegistries.CONTEXT_FLOAT_PROVIDER_TYPE, Identifier.fromNamespaceAndPath(MineSpawnersMod.MOD_ID, "from_config"), LOOT_CODEC);
    }

    public FloatConfigValue(String key, Float defValue, float min, float max) {
        super(key, defValue, value -> value >= min && value <= max);
    }

    public void setValue(double value) {
        this.setValue(Float.valueOf((float) value));
    }

    @Override
    public float getFloat(@NotNull LootContext context) {
        return this.getValue();
    }

    @Override
    public Codec<Float> getCodec() {
        return Codec.FLOAT;
    }

    @Override
    public void validate(ValidationContext context) {
        // No loot context parameters are required
    }

    @Override
    public MapCodec<? extends ContextFloatProvider> codec() {
        return LOOT_CODEC;
    }

    @Override
    public float getFloatUnsafe(LootContext context) throws ArithmeticException {
        return this.getValue();
    }
}
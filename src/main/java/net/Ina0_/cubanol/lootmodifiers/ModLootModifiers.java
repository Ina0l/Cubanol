package net.Ina0_.cubanol.lootmodifiers;

import com.mojang.serialization.MapCodec;
import net.Ina0_.cubanol.Cubanol;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Cubanol.MOD_ID);

    public static final Supplier<MapCodec<NoAppleLootModifier>> NO_APPLE_LOOT_MODIFIER =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("no_apple_loot_modifier", () -> NoAppleLootModifier.CODEC);



    public static void register(IEventBus eventBus){
        GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}

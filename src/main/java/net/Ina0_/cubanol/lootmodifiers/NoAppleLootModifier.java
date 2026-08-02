package net.Ina0_.cubanol.lootmodifiers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class NoAppleLootModifier extends LootModifier {
    public static final MapCodec<NoAppleLootModifier> CODEC = RecordCodecBuilder.mapCodec(
            instance -> LootModifier.codecStart(instance).apply(instance, NoAppleLootModifier::new)
    );

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    public NoAppleLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(@NotNull ObjectArrayList<ItemStack> generatedLoot, @NotNull LootContext context) {
        ObjectArrayList<ItemStack> modifiedLoot = new ObjectArrayList<>();
        for(ItemStack stack: generatedLoot){
            if(!stack.is(Items.APPLE)){
                modifiedLoot.add(stack);
            }
        }
        return modifiedLoot;
    }

    @Override
    public @NotNull MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}

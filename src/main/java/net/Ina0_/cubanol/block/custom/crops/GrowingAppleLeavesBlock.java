package net.Ina0_.cubanol.block.custom.crops;

import net.Ina0_.cubanol.block.properties.ModBlockStateProperties;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class GrowingAppleLeavesBlock extends FruitGrowingLeavesBlock {

    public static final IntegerProperty AGE = ModBlockStateProperties.AGE_8;

    public GrowingAppleLeavesBlock(Properties properties) {
        super(properties, () -> Items.APPLE);
    }

    @Override
    public int getMaxAge() {
        return 8;
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }
}

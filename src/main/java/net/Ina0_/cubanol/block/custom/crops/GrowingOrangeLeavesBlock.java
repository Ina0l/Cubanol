package net.Ina0_.cubanol.block.custom.crops;

import net.Ina0_.cubanol.block.properties.ModBlockStateProperties;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class GrowingOrangeLeavesBlock extends FruitGrowingLeavesBlock {

    public static final IntegerProperty AGE = ModBlockStateProperties.AGE_8;

    public GrowingOrangeLeavesBlock(Properties properties) {
        super(properties, ModItems.ORANGE);
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

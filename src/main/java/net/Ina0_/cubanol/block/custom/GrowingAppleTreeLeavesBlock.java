package net.Ina0_.cubanol.block.custom;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class GrowingAppleTreeLeavesBlock extends FruitGrowingLeavesBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 8);

    public GrowingAppleTreeLeavesBlock(Properties properties) {
        super(properties, Items.APPLE);
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

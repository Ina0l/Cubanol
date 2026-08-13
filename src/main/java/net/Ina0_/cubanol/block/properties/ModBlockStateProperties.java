package net.Ina0_.cubanol.block.properties;


import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockStateProperties {
    public static final BooleanProperty CUT = BooleanProperty.create("cut");
    public static final BooleanProperty DRIED = BooleanProperty.create("dried");

    public static final BooleanProperty SUPPORTING = BooleanProperty.create("supporting");

    public static final BooleanProperty WHITE = BooleanProperty.create("white");

    public static final IntegerProperty WIRE_COUNT = IntegerProperty.create("wire_count", 0, 4);

    public static final IntegerProperty AGE_8 = IntegerProperty.create("age", 0, 8);
    public static final IntegerProperty AGE_6 = IntegerProperty.create("age", 0, 6);
    public static final IntegerProperty AGE_12 = IntegerProperty.create("age", 0, 12);
}

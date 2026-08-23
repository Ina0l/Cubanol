package net.Ina0_.cubanol.block.fluids;

import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.item.ModItems;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public abstract class TrodBlackGrapeFluid {
    public static final BaseFlowingFluid.Properties FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluids.TROD_BLACK_GRAPE_TYPE,
            ModFluids.TROD_BLACK_GRAPE,
            ModFluids.FLOWING_TROD_BLACK_GRAPE
    )
            .block(ModBlocks.TROD_BLACK_GRAPE)
            .bucket(ModItems.TROD_BLACK_GRAPE_BUCKET)
            .levelDecreasePerBlock(3)
            .tickRate(10)
            .slopeFindDistance(1)
            .explosionResistance(100.0f);


    public static class Flowing extends BaseFlowingFluid.Flowing{
        protected Flowing() {
            super(FLUID_PROPERTIES);
        }
    }

    public static class Source extends BaseFlowingFluid.Source{
        protected Source() {
            super(FLUID_PROPERTIES);
        }
    }
}

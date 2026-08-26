package net.Ina0_.cubanol.fluids;

import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.item.ModItems;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public class DestemmedBlackGrapeFluid {
    public static final BaseFlowingFluid.Properties FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluids.DESTEMMED_BLACK_GRAPE_TYPE,
            ModFluids.DESTEMMED_BLACK_GRAPE,
            ModFluids.FLOWING_DESTEMMED_BLACK_GRAPE
    )
            .bucket(ModItems.DESTEMMED_BLACK_GRAPE_BUCKET)
            .levelDecreasePerBlock(3)
            .tickRate(10)
            .slopeFindDistance(1)
            .explosionResistance(100.0f)
            //this is only here to get the liquid's translation component
            .block(ModBlocks.DESTEMMED_BLACK_GRAPE);

    static class Source extends BaseFlowingFluid.Source {
        public Source() {
            super(FLUID_PROPERTIES);
        }
    }

    static class Flowing extends BaseFlowingFluid.Flowing {
        public Flowing() {
            super(FLUID_PROPERTIES);
        }
    }
}

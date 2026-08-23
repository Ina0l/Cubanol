package net.Ina0_.cubanol.block.fluids;

import net.Ina0_.cubanol.Cubanol;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, Cubanol.MOD_ID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, Cubanol.MOD_ID);


    public static final DeferredHolder<FluidType, FluidType> TROD_BLACK_GRAPE_TYPE = FLUID_TYPES.register("trod_black_grape_type", () -> new FluidType(
            FluidType.Properties.create()
                    .descriptionId("block.cubanol.trod_black_grape_type")
                    .fallDistanceModifier(0F)
                    .canExtinguish(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
    ));

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> TROD_BLACK_GRAPE = FLUIDS.register("trod_black_grape", TrodBlackGrapeFluid.Source::new);
    public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> FLOWING_TROD_BLACK_GRAPE = FLUIDS.register("flowing_trod_black_grape", TrodBlackGrapeFluid.Flowing::new);

    public static void register(IEventBus eventBus){
        FLUIDS.register(eventBus);
        FLUID_TYPES.register(eventBus);
    }
}

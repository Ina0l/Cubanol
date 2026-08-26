package net.Ina0_.cubanol;

import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.block.entity.ModBlockEntities;
import net.Ina0_.cubanol.fluids.ModFluids;
import net.Ina0_.cubanol.item.ModCreativeModTabs;
import net.Ina0_.cubanol.item.ModItems;
import net.Ina0_.cubanol.item.properties.ModDataComponentTypes;
import net.Ina0_.cubanol.lootmodifiers.ModLootModifiers;
import net.Ina0_.cubanol.sound.ModSoundEvents;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Cubanol.MOD_ID)
public class Cubanol {
    public static final String MOD_ID = "cubanol";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Cubanol(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for mod loading
        modEventBus.addListener(this::commonSetup);

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModFluids.register(modEventBus);

        ModLootModifiers.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        ModDataComponentTypes.register(modEventBus);
        ModSoundEvents.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }
}

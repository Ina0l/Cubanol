package net.Ina0_.cubanol;

import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Cubanol.MOD_ID)
public class Cubanol {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "cubanol";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Cubanol(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for mod loading
        modEventBus.addListener(this::commonSetup);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the mod items to the creative blocks tabs
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(ModItems.BLACK_GRAPE);
            event.accept(ModItems.WHITE_GRAPE);
            event.accept(ModItems.BLACK_GRAPE_SEEDS);
            event.accept(ModItems.WHITE_GRAPE_SEEDS);
            event.accept(ModItems.AGAVE_SYRUP);
            event.accept(ModItems.AGAVE_SEEDS);
            event.accept(ModItems.RICE);
            event.accept(ModItems.RICE_PANICLE);
        }
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.STAR_ANISE);
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.OAK_TABLE);
            event.accept(ModBlocks.SPRUCE_TABLE);
            event.accept(ModBlocks.BIRCH_TABLE);
            event.accept(ModBlocks.JUNGLE_TABLE);
            event.accept(ModBlocks.ACACIA_TABLE);
            event.accept(ModBlocks.DARK_OAK_TABLE);
            event.accept(ModBlocks.MANGROVE_TABLE);
            event.accept(ModBlocks.CHERRY_TABLE);
            event.accept(ModBlocks.BAMBOO_TABLE);
            event.accept(ModBlocks.CRIMSON_TABLE);
            event.accept(ModBlocks.WARPED_TABLE);
            event.accept(ModBlocks.FAKE_WINE_BOTTLE);
        }
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS){
            event.accept(ModBlocks.CROP_SUPPORT);
            event.accept(ModItems.WIRE);
        }
    }
}

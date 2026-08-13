package net.Ina0_.cubanol.item;

import com.google.gson.JsonObject;
import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.item.custom.AgaveSyrupItem;
import net.Ina0_.cubanol.item.custom.GrapeSeedsItem;
import net.Ina0_.cubanol.item.custom.WireItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

//this is there to access the vanilla examples
import net.minecraft.world.item.Items;

import java.io.IOException;
import java.io.Reader;
import java.util.Optional;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Cubanol.MOD_ID);

    public static final DeferredItem<Item> BLACK_GRAPE = ITEMS.register("black_grape", () -> new Item(
            new Item.Properties().food(
                    (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.1F).build()
            )
    ));

    public static final DeferredItem<Item> WHITE_GRAPE = ITEMS.register("white_grape", () -> new Item(
            new Item.Properties().food(
                    (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.1F).build()
            )
    ));

    public static final DeferredItem<ItemNameBlockItem> STAR_ANISE = ITEMS.register("star_anise", () -> new ItemNameBlockItem(
            ModBlocks.STAR_ANISE_CROP.get(),
            new Item.Properties()
    ));

    public static final DeferredItem<ItemNameBlockItem> AGAVE_SEEDS = ITEMS.register("agave_seeds", () -> new ItemNameBlockItem(
            ModBlocks.AGAVE_CROP.get(),
            new Item.Properties()
    ));

    public static final DeferredItem<AgaveSyrupItem> AGAVE_SYRUP = ITEMS.register("agave_syrup", () -> new AgaveSyrupItem(
            new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.1F).build())
    ));

    public static final DeferredItem<Item> WIRE = ITEMS.register("wire", () -> new WireItem(
            new Item.Properties()
    ));

    public static final DeferredItem<GrapeSeedsItem> BLACK_GRAPE_SEEDS = ITEMS.register("black_grape_seeds", () -> new GrapeSeedsItem(
            new Item.Properties(),
            false
    ));

    public static final DeferredItem<GrapeSeedsItem> WHITE_GRAPE_SEEDS = ITEMS.register("white_grape_seeds", () -> new GrapeSeedsItem(
            new Item.Properties(),
            true
    ));

    public static final DeferredItem<ItemNameBlockItem> RICE = ITEMS.register("rice", () -> new ItemNameBlockItem(
            ModBlocks.RICE_CROP.get(),
            new Item.Properties()
    ));

    public static final DeferredItem<Item> RICE_PANICLE = ITEMS.register("rice_panicle", () -> new Item(
            new Item.Properties()
    ));

    public static final DeferredItem<BlockItem> APPLE_SEEDS = ITEMS.register("apple_seeds", () -> new BlockItem(
            ModBlocks.APPLE_SAPLING_CROP.get(),
            new Item.Properties()
    ));

    public static final DeferredItem<SignItem> APPLE_SIGN = ITEMS.register("apple_sign", () -> new SignItem(
            new Item.Properties().stacksTo(16),
            ModBlocks.APPLE_SIGN.get(),
            ModBlocks.APPLE_WALL_SIGN.get()
    ));

    public static final DeferredItem<HangingSignItem> APPLE_HANGING_SIGN = ITEMS.register("apple_hanging_sign", () -> new HangingSignItem(
            ModBlocks.APPLE_HANGING_SIGN.get(),
            ModBlocks.APPLE_WALL_HANGING_SIGN.get(),
            new Item.Properties().stacksTo(16)
    ));

    public static final DeferredItem<Item> ORANGE = ITEMS.register("orange", () -> new Item(
            new Item.Properties()
    ));

    public static final DeferredItem<BlockItem> ORANGE_SEEDS = ITEMS.register("orange_seeds", () -> new BlockItem(
            ModBlocks.ORANGE_SAPLING_CROP.get(),
            new Item.Properties()
    ));

    public static final DeferredItem<SignItem> ORANGE_SIGN = ITEMS.register("orange_sign", () -> new SignItem(
            new Item.Properties().stacksTo(16),
            ModBlocks.ORANGE_SIGN.get(),
            ModBlocks.ORANGE_WALL_SIGN.get()
    ));

    public static final DeferredItem<HangingSignItem> ORANGE_HANGING_SIGN = ITEMS.register("orange_hanging_sign", () -> new HangingSignItem(
            ModBlocks.ORANGE_HANGING_SIGN.get(),
            ModBlocks.ORANGE_WALL_HANGING_SIGN.get(),
            new Item.Properties().stacksTo(16)
    ));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

    public static boolean isItemModelGenerated(Item item){
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
        ResourceLocation current_file = BuiltInRegistries.ITEM.getKey(item).withPrefix("item/");
        while(true) {
            if (current_file.equals(ResourceLocation.withDefaultNamespace("builtin/generated"))) {
                return true;
            }

            ResourceLocation jsonPath = current_file.withPath(path -> "models/" + path + ".json");
            Optional<Resource> resource = resourceManager.getResource(jsonPath);
            if (resource.isEmpty()) {
                return false;
            }

            Reader reader;
            try {
                reader = resource.get().openAsReader();
            } catch (IOException e) {
                return false;
            }
            JsonObject jsonObject = GsonHelper.parse(reader);
            if(!jsonObject.has("parent")){
                return false;
            }
            current_file = ResourceLocation.parse(GsonHelper.getAsString(jsonObject, "parent"));
        }
    }
}

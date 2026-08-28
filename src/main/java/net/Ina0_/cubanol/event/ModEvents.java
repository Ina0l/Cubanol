package net.Ina0_.cubanol.event;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.block.custom.crops.CropSupportBlock;
import net.Ina0_.cubanol.block.entity.CaskBlockEntity;
import net.Ina0_.cubanol.block.entity.ModBlockEntities;
import net.Ina0_.cubanol.block.entity.renderer.CaskBlockEntityRenderer;
import net.Ina0_.cubanol.block.entity.renderer.TableBlockEntityRenderer;
import net.Ina0_.cubanol.fluids.ModFluids;
import net.Ina0_.cubanol.item.ModItems;
import net.Ina0_.cubanol.item.properties.ModDataComponentTypes;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.fluids.capability.templates.FluidHandlerItemStack;

import javax.annotation.Nullable;

@EventBusSubscriber(modid = Cubanol.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void entityTickEvent(EntityTickEvent.Post event){
        Entity entity = event.getEntity();
        if(entity instanceof Player player){
            if(player.getPersistentData().contains(Cubanol.MOD_ID + ":wire_selected_block")){
                if (!player.getMainHandItem().is(ModItems.WIRE)) {
                    player.getPersistentData().remove(Cubanol.MOD_ID + ":wire_selected_block");
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBlockRightClicked(BlockEvent.BlockToolModificationEvent event){
        LevelAccessor level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        @Nullable Player player = event.getPlayer();
        @Nullable ItemStack stack = player == null? null: player.getMainHandItem();

        if(stack != null && stack.is(ItemTags.HOES)){
            if (state.is(Blocks.DIRT) || state.is(Blocks.GRASS_BLOCK)){
                if (level.getBlockState(pos.above()).is(ModBlocks.CROP_SUPPORT)){
                    event.setFinalState(Blocks.FARMLAND.defaultBlockState());
                }
                if(level.getBlockState(pos.above()).is(Blocks.WATER)){
                    event.setFinalState(Blocks.FARMLAND.defaultBlockState());
                }
            }
        }
    }

    @SubscribeEvent
    public static void onItemUsedOnBlock(UseItemOnBlockEvent event){
        LevelAccessor level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        @Nullable Player player = event.getPlayer();
        @Nullable ItemStack stack = player == null? null: player.getMainHandItem();

        if(state.is(ModBlocks.GRAPE_CROP)){
            if(stack != null && stack.getItem() instanceof BlockItem){
                if(ModBlocks.GRAPE_CROP.get().isMaxAge(state)){
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBlockDestroyed(BlockEvent.BreakEvent event){
        LevelAccessor level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        @Nullable Player player = event.getPlayer();

        if(state.is(ModBlocks.GRAPE_CROP)){
            event.setCanceled(true);
            if(!level.isClientSide()){
                ModBlocks.dropItemsFromState((ServerLevel) level, state, pos, player);
                level.setBlock(pos, CropSupportBlock.getBlockStateFromGrapeCropState(state), 3);
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterBlockColorHandlers(RegisterColorHandlersEvent.Block event){
        event.register(
                (state, level, pos, tintIndex) ->
                        level != null && pos != null?
                                BiomeColors.getAverageFoliageColor(level, pos):
                                FoliageColor.getDefaultColor(),
                ModBlocks.APPLE_LEAVES.get(),
                ModBlocks.GROWING_APPLE_LEAVES.get(),
                ModBlocks.ORANGE_LEAVES.get(),
                ModBlocks.GROWING_ORANGE_LEAVES.get()
        );
    }

    @SubscribeEvent
    public static void onRegisterItemColorHandlers(RegisterColorHandlersEvent.Item event){
        event.register(
                (stack, tintIndex) -> {
                    BlockState blockstate = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
                    return event.getBlockColors().getColor(blockstate, null, null, tintIndex);
                },
                ModBlocks.APPLE_LEAVES.asItem(),
                ModBlocks.GROWING_APPLE_LEAVES.asItem(),
                ModBlocks.ORANGE_LEAVES.asItem(),
                ModBlocks.GROWING_ORANGE_LEAVES.asItem()
        );
    }

    @SubscribeEvent
    public static void onBlockEntityTypeAddBlock(BlockEntityTypeAddBlocksEvent event){
        event.modify(
                BlockEntityType.SIGN,
                ModBlocks.APPLE_SIGN.get(),
                ModBlocks.APPLE_WALL_SIGN.get(),
                ModBlocks.ORANGE_SIGN.get(),
                ModBlocks.ORANGE_WALL_SIGN.get()
        );
        event.modify(
                BlockEntityType.HANGING_SIGN,
                ModBlocks.APPLE_HANGING_SIGN.get(),
                ModBlocks.APPLE_WALL_HANGING_SIGN.get(),
                ModBlocks.ORANGE_HANGING_SIGN.get(),
                ModBlocks.ORANGE_WALL_HANGING_SIGN.get()
        );
    }

    @SubscribeEvent
    public static void onRegisterBlockEntityRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlockEntities.TABLE_BLOCK_ENTITY.get(), TableBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CASK_BLOCK_ENTITY.get(), CaskBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event){
        event.registerFluidType(
                new IClientFluidTypeExtensions() {
                    private static final ResourceLocation TROD_BLACK_GRAPE_STILL = ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "block/trod_black_grape_still");
                    private static final ResourceLocation TROD_BLACK_GRAPE_FLOW = ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "block/trod_black_grape_flow");
                    private static final ResourceLocation TROD_BLACK_GRAPE_OVERLAY = ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "block/trod_black_grape_overlay");

                    @Override
                    public int getTintColor() {
                        return 0xFF351FA4;
                    }

                    @Override
                    public ResourceLocation getStillTexture() {
                        return TROD_BLACK_GRAPE_STILL;
                    }

                    @Override
                    public ResourceLocation getFlowingTexture() {
                        return TROD_BLACK_GRAPE_FLOW;
                    }

                    @Override
                    public ResourceLocation getOverlayTexture() {
                        return TROD_BLACK_GRAPE_OVERLAY;
                    }

                },
                ModFluids.TROD_BLACK_GRAPE_TYPE
        );
        event.registerFluidType(
                new IClientFluidTypeExtensions() {
                    private static final ResourceLocation DESTEMMED_BLACK_GRAPE_STILL = ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "block/destemmed_black_grape_still");
                    private static final ResourceLocation DESTEMMED_BLACK_GRAPE_FLOWING = ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "block/destemmed_black_grape_flowing");

                    @Override
                    public ResourceLocation getStillTexture() {
                        return DESTEMMED_BLACK_GRAPE_STILL;
                    }

                    @Override
                    public ResourceLocation getFlowingTexture() {
                        return DESTEMMED_BLACK_GRAPE_FLOWING;
                    }
                },
                ModFluids.DESTEMMED_BLACK_GRAPE_TYPE
        );
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.registerItem(
                Capabilities.FluidHandler.ITEM,
                (stack, ctx) -> new FluidHandlerItemStack(ModDataComponentTypes.SIMPLE_FLUID_CONTENT, stack, CaskBlockEntity.TANK_CAPACITY),
                ModItems.CASK.get()
        );
        event.registerBlock(
                Capabilities.FluidHandler.BLOCK,
                (level, pos, state, blockEntity, context) -> {
                    if(level.getBlockEntity(pos) instanceof CaskBlockEntity caskBlockEntity) {
                        return caskBlockEntity.tank;
                    }
                    throw new RuntimeException();
                },
                ModBlocks.CASK.get()
        );
    }
}

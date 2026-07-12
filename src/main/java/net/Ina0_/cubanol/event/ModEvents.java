package net.Ina0_.cubanol.event;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.block.custom.CropSupportBlock;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

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
            }
        }
    }
}

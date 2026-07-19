package net.Ina0_.cubanol.item.custom;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.block.custom.CropSupportBlock;
import net.Ina0_.cubanol.block.custom.GrapeCropBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class WireItem extends Item {
    public WireItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        if(!level.isClientSide()){
            if (player != null) {
                if (state.is(ModBlocks.CROP_SUPPORT) || state.is(ModBlocks.GRAPE_CROP)) {
                    if(state.is(ModBlocks.GRAPE_CROP) && state.getValue(GrapeCropBlock.AGE) == ((GrapeCropBlock)state.getBlock()).getMaxAge()){
                        return InteractionResult.PASS;
                    }
                    if (player.getPersistentData().contains(Cubanol.MOD_ID + ":wire_selected_block")) {
                        int[] intArray = ((IntArrayTag) Objects.requireNonNull(player.getPersistentData().get(Cubanol.MOD_ID + ":wire_selected_block"))).getAsIntArray();
                        BlockPos previouslySelectedPos = new BlockPos(intArray[0], intArray[1], intArray[2]);
                        BlockState previouslySelectedState = level.getBlockState(previouslySelectedPos);
                        if (previouslySelectedPos.getY() == pos.getY()) {
                            for (Direction direction : Direction.values()) {
                                if (direction == Direction.UP || direction == Direction.DOWN) {
                                    continue;
                                }
                                if (previouslySelectedPos.getX() + direction.getStepX() == pos.getX() && previouslySelectedPos.getZ() + direction.getStepZ() == pos.getZ()) {
                                    if (previouslySelectedState.getValue(CropSupportBlock.getPropertyFromDirection(direction))) {
                                        if(!player.getPersistentData().contains(Cubanol.MOD_ID + ":wire_selected_by_fast_selection")){
                                            level.setBlock(pos, state.setValue(CropSupportBlock.getPropertyFromDirection(direction.getOpposite()), false), 3);
                                            level.setBlock(previouslySelectedPos, previouslySelectedState.setValue(CropSupportBlock.getPropertyFromDirection(direction), false), 3);
                                            CropSupportBlock.setWireCountProperty(level, pos, level.getBlockState(pos));
                                            CropSupportBlock.setWireCountProperty(level, previouslySelectedPos, level.getBlockState(previouslySelectedPos));
                                            player.getPersistentData().remove(Cubanol.MOD_ID + ":wire_selected_block");
                                            if (!player.hasInfiniteMaterials()) {
                                                stack.setCount(stack.getCount() + 1);
                                            }
                                        } else {
                                            player.getPersistentData().put(Cubanol.MOD_ID + ":wire_selected_block", new IntArrayTag(List.of(pos.getX(), pos.getY(), pos.getZ())));
                                            player.getPersistentData().remove(Cubanol.MOD_ID + ":wire_selected_by_fast_selection");
                                            return InteractionResult.SUCCESS;
                                        }
                                    } else {
                                        level.setBlock(pos, state.setValue(CropSupportBlock.getPropertyFromDirection(direction.getOpposite()), true), 3);
                                        level.setBlock(previouslySelectedPos, previouslySelectedState.setValue(CropSupportBlock.getPropertyFromDirection(direction), true), 3);
                                        CropSupportBlock.setWireCountProperty(level, pos, level.getBlockState(pos));
                                        CropSupportBlock.setWireCountProperty(level, previouslySelectedPos, level.getBlockState(previouslySelectedPos));

                                        player.getPersistentData().put(Cubanol.MOD_ID + ":wire_selected_block", new IntArrayTag(List.of(pos.getX(), pos.getY(), pos.getZ())));
                                        player.getPersistentData().put(Cubanol.MOD_ID + ":wire_selected_by_fast_selection", IntTag.valueOf(0));
                                        stack.consume(1, player);
                                    }
                                    level.playSound(player, pos, SoundEvents.CHAIN_PLACE, SoundSource.BLOCKS);
                                    return InteractionResult.CONSUME;
                                }
                            }
                        }
                    }
                    level.playSound(player, pos, SoundEvents.CHAIN_PLACE, SoundSource.BLOCKS);
                    player.getPersistentData().put(Cubanol.MOD_ID + ":wire_selected_block", new IntArrayTag(List.of(pos.getX(), pos.getY(), pos.getZ())));
                    player.getPersistentData().remove(Cubanol.MOD_ID + ":wire_selected_by_fast_selection");
                    return InteractionResult.SUCCESS;
                }
            }
        } else {
            if (player != null) {
                if (state.is(ModBlocks.CROP_SUPPORT) || state.is(ModBlocks.GRAPE_CROP)) {
                    if(state.is(ModBlocks.GRAPE_CROP) && state.getValue(GrapeCropBlock.AGE) == ((GrapeCropBlock)state.getBlock()).getMaxAge()){
                        return InteractionResult.PASS;
                    }
                    level.playSound(player, pos, SoundEvents.CHAIN_PLACE, SoundSource.BLOCKS);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.useOn(context);
    }
}

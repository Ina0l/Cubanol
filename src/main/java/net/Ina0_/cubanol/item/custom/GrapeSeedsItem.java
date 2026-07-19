package net.Ina0_.cubanol.item.custom;

import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.block.custom.CropSupportBlock;
import net.Ina0_.cubanol.block.custom.GrapeCropBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class GrapeSeedsItem extends Item {
    public final Boolean isGrapeWhite;

    public GrapeSeedsItem(Properties properties, Boolean isGrapeWhite) {
        super(properties);
        this.isGrapeWhite = isGrapeWhite;
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        if(state.is(ModBlocks.CROP_SUPPORT) || (state.is(Blocks.FARMLAND) && level.getBlockState(pos.above()).is(ModBlocks.CROP_SUPPORT))){
            if(state.is(Blocks.FARMLAND)){
                pos = pos.above();
                state = level.getBlockState(pos);
            }
            if(GrapeCropBlock.getFacingPropertiesCount(state) > 0){
                int cableNumber = GrapeCropBlock.getFacingPropertiesCount(state);
                double random = ModBlocks.getLocalizedRandom(pos);
                Direction vineHangingSide = null;
                Function<Direction[], Integer> findFirstNull = array -> {
                    for(int i = 0; i < array.length; i += 1){
                        if(array[i] == null){
                            return i;
                        }
                    }
                    return -1;
                };

                Direction[] stateProperties = new Direction[cableNumber];
                if(state.getValue(CropSupportBlock.NORTH)){
                    stateProperties[findFirstNull.apply(stateProperties)] = Direction.NORTH;
                }
                if(state.getValue(CropSupportBlock.SOUTH)){
                    stateProperties[findFirstNull.apply(stateProperties)] = Direction.SOUTH;
                }
                if(state.getValue(CropSupportBlock.EAST)){
                    stateProperties[findFirstNull.apply(stateProperties)] = Direction.EAST;
                }
                if(state.getValue(CropSupportBlock.WEST)){
                    stateProperties[findFirstNull.apply(stateProperties)] = Direction.WEST;
                }

                for(double i = 1; i <= cableNumber; i += 1){
                    if(random <  i / (double) cableNumber){
                        vineHangingSide = stateProperties[(int) i - 1];
                        break;
                    }
                }
                if(vineHangingSide == null){
                    throw new NullPointerException("vineHangingSide is null");
                }
                BlockState newState = GrapeCropBlock.getBlockStateFromCropSupportState(state)
                        .setValue(GrapeCropBlock.VINE_HANGING_SIDE, vineHangingSide)
                        .setValue(GrapeCropBlock.WHITE, this.isGrapeWhite);
                if(newState.canSurvive(level, pos)){
                    if(!level.isClientSide()){
                        level.setBlock(pos, newState, 3);
                        stack.consume(1, player);
                    }
                    level.playSound(player, pos, SoundEvents.CROP_PLANTED, SoundSource.BLOCKS);
                    return InteractionResult.sidedSuccess(level.isClientSide());
                }
            }
        }
        return InteractionResult.PASS;
    }
}

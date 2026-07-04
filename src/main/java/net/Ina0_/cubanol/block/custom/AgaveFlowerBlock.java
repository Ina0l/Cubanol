package net.Ina0_.cubanol.block.custom;

import net.Ina0_.cubanol.block.ModBlockStateProperties;
import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.ItemAbilities;
import org.jetbrains.annotations.NotNull;

public class AgaveFlowerBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    public static final BooleanProperty CUT = ModBlockStateProperties.CUT;
    public static final BooleanProperty DRIED = ModBlockStateProperties.DRIED;

    public static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(6.0, 0.0, 6.0, 10.0, 2.0, 10.0),
            Block.box(6.0, 0.0, 6.0, 10.0, 6.0, 10.0),
            Block.box(4.0, 0.0, 4.0, 12.0, 10.0, 12.0),
            Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0),
            Block.box(1.0, 0.0, 1.0, 15.0, 13.0, 15.0),
            Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 15.0),
            Block.box(1.0, 0.0, 1.0, 15.0, 15.0, 15.0),
            Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0),
    };

    public AgaveFlowerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.defaultBlockState()
                        .setValue(AGE, 0)
                        .setValue(CUT, false)
                        .setValue(DRIED, false)
        );
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(CUT);
        builder.add(DRIED);
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    protected @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(ModBlocks.AGAVE_STEM.get()) || level.getBlockState(pos.below()).is(ModBlocks.AGAVE_CROP.get());
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(DRIED);
    }

    @Override
    protected void randomTick(BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if(state.getValue(DRIED)){
            return;
        }
        if(state.getValue(CUT)){
            if(level.random.nextDouble() < 0.25){
                AgaveCropBlock.setDried(level, state, pos);
                return;
            }
        }
        super.randomTick(state, level, pos, random);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader level, @NotNull BlockPos pos, BlockState state) {
        if(state.getValue(CUT)){
            return false;
        }
        return super.isValidBonemealTarget(level, pos, state);
    }


    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (!stack.canPerformAction(ItemAbilities.SHEARS_TRIM) && !stack.is(Items.GLASS_BOTTLE)) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        } else if (level.isClientSide()) {
            if (stack.canPerformAction(ItemAbilities.SHEARS_TRIM)) {
                if(!state.getValue(CUT) && state.getValue(AGE) < this.getMaxAge()){
                    level.playSound(player, pos, SoundEvents.GROWING_PLANT_CROP, SoundSource.BLOCKS);
                }
            }
            if (stack.is(Items.GLASS_BOTTLE) && state.getValue(CUT)) {
                level.playSound(player, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS);
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        } else {
            if (stack.canPerformAction(ItemAbilities.SHEARS_TRIM)) {
                if(!state.getValue(CUT) && state.getValue(AGE) < this.getMaxAge()){
                    level.playSound(player, pos, SoundEvents.GROWING_PLANT_CROP, SoundSource.BLOCKS);
                    AgaveCropBlock.setCut(level, state, pos);
                    stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                } else {
                    return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
                }
            }
            if (stack.is(Items.GLASS_BOTTLE) && state.getValue(CUT)) {
                level.playSound(player, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS);
                AgaveCropBlock.setDried(level, state, pos);
                if(!player.hasInfiniteMaterials()){
                    stack.setCount(stack.getCount() - 1);
                    if (player.getInventory().findSlotMatchingItem(stack) != -1 || player.getInventory().getFreeSlot() != -1) {
                        player.getInventory().add(new ItemStack(ModItems.AGAVE_SYRUP.get()));
                    } else {
                        level.addFreshEntity(new ItemEntity(
                                level,
                                player.position().x(),
                                player.position().y(),
                                player.position().z(),
                                new ItemStack(ModItems.AGAVE_SYRUP.get())
                        ));
                    }
                }
            }
            stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            level.gameEvent(player, GameEvent.SHEAR, pos);
            player.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        }
    }
}

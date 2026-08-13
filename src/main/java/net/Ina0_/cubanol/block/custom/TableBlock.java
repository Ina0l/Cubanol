package net.Ina0_.cubanol.block.custom;

import com.mojang.serialization.MapCodec;
import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.block.entity.TableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class TableBlock extends BaseEntityBlock {
    private static final VoxelShape LEG_1;
    private static final VoxelShape LEG_2;
    private static final VoxelShape LEG_3;
    private static final VoxelShape LEG_4;
    private static final VoxelShape TABLE_TOP;
    private static final VoxelShape AABB;

    public static final MapCodec<TableBlock> CODEC = simpleCodec(TableBlock::new);

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;


    public TableBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return AABB;
    }

    @Override
    protected void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean movedByPiston) {
        if(state.getBlock() != newState.getBlock()){
            if(level.getBlockEntity(pos) instanceof TableBlockEntity tableBlockEntity){
                tableBlockEntity.dropContent();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if(player.isShiftKeyDown()){
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        if(level.getBlockEntity(pos) instanceof TableBlockEntity tableBlockEntity){
            if(!stack.isEmpty()){
                if(!stack.is(tableBlockEntity.getStack().getItem())){
                    if (tableBlockEntity.hasItem() && !level.isClientSide()) {
                        ModBlocks.collectOrDropItems((ServerLevel) level, player, pos, List.of(tableBlockEntity.getStack()));
                    }
                    level.setBlock(pos, this.defaultBlockState().setValue(FACING, player.getDirection()), 3);
                    tableBlockEntity.setItem(stack.getItem());
                    if(!player.hasInfiniteMaterials()){
                        stack.shrink(1);
                    }
                    level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS);

                } else if(stack.getCount() < stack.getItem().getMaxStackSize(stack)){
                    tableBlockEntity.clearInventory();
                    if(!player.hasInfiniteMaterials()) {
                        stack.setCount(stack.getCount() + 1);
                    }
                    level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);

                } else {
                    return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
                }
            } else if(tableBlockEntity.hasItem()){
                if(!player.hasInfiniteMaterials()) {
                    player.setItemInHand(hand, tableBlockEntity.getStack());
                }
                tableBlockEntity.clearInventory();
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);

            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new TableBlockEntity(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    static {
        LEG_1 = box(1.0, 0.0, 1.0, 3.0, 14.0, 3.0);
        LEG_2 = box(1.0, 0.0, 13.0, 3.0, 14.0, 15.0);
        LEG_3 = box(13.0, 0.0, 1.0, 15.0, 14.0, 3.0);
        LEG_4 = box(13.0, 0.0, 13.0, 15.0, 14.0, 15.0);
        TABLE_TOP = box(0.0, 14.0, 0.0, 16.0, 16.0, 16.0);
        AABB = Shapes.or(TABLE_TOP, LEG_1, LEG_2, LEG_3, LEG_4);
    }
}

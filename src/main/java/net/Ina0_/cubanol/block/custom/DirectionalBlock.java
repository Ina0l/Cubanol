package net.Ina0_.cubanol.block.custom;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;

public class DirectionalBlock extends Block {

    public DirectionalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getClockWise());
    }

    protected @NotNull BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(BlockStateProperties.HORIZONTAL_FACING, rot.rotate(state.getValue(BlockStateProperties.HORIZONTAL_FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
    }
}

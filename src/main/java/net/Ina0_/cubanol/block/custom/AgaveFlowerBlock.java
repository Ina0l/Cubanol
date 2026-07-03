package net.Ina0_.cubanol.block.custom;

import net.Ina0_.cubanol.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class AgaveFlowerBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
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
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        builder.add(AGE);
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
}

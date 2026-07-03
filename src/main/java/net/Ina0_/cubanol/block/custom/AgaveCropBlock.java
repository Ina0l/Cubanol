package net.Ina0_.cubanol.block.custom;

import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class AgaveCropBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(6.0, 0.0, 6.0, 10.0, 2.0, 10.0),
            Block.box(5.0, 0.0, 5.0, 11.0, 8.0, 11.0),
            Block.box(2.0, 0.0, 2.0, 14.0, 9.0, 14.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    };

    public AgaveCropBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 5;
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return ModItems.AGAVE_SEEDS.get();
    }

    @Override
    protected void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        super.randomTick(state, level, pos, random);
        if(this.isMaxAge(level.getBlockState(pos))){
            if(level.getBlockState(pos.above()).is(Blocks.AIR)){
                if(Math.random() < 0.25){
                    level.setBlock(pos.above(), ModBlocks.AGAVE_FLOWER.get().defaultBlockState(), 2);
                } else {
                    level.setBlock(pos.above(), ModBlocks.AGAVE_STEM.get().defaultBlockState(), 2);
                }
            } else if(!level.getBlockState(pos.above()).is(ModBlocks.AGAVE_STEM)){
                level.setBlock(pos, this.defaultBlockState().setValue(AGE, this.getMaxAge() - 1), 2);
            }
        }
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        super.performBonemeal(level, random, pos, state);
        if (this.isMaxAge(level.getBlockState(pos))) {
            if (level.getBlockState(pos.above()).is(Blocks.AIR)) {
                if(Math.random() < 0.25){
                    level.setBlock(pos.above(), ModBlocks.AGAVE_FLOWER.get().defaultBlockState(), 2);
                } else {
                    level.setBlock(pos.above(), ModBlocks.AGAVE_STEM.get().defaultBlockState(), 2);
                }
            } else if(!level.getBlockState(pos.above()).is(ModBlocks.AGAVE_STEM)){
                level.setBlock(pos, this.defaultBlockState().setValue(AGE, this.getMaxAge() - 1), 2);
            }
        }
    }

    @Override
    protected void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Block neighborBlock, @NotNull BlockPos neighborPos, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
        if(!level.isClientSide()){
            if (!level.getBlockState(pos.above()).is(ModBlocks.AGAVE_STEM) && !level.getBlockState(pos.above()).is(ModBlocks.AGAVE_STEM)) {
                if(this.isMaxAge(level.getBlockState(pos))){
                    level.setBlock(pos, this.defaultBlockState().setValue(AGE, this.getMaxAge() - 1), 2);
                }
            }
        }
    }
}

package net.Ina0_.cubanol.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class SaplingCropBlock extends CropBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;

    public static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.box(0, 0, 0, 16, 3, 16),
            Block.box(0, 0, 0, 16, 5, 16),
            Block.box(0, 0, 0, 16, 6, 16),
            Block.box(0, 0, 0, 16, 8, 16),
            Block.box(0, 0, 0, 16, 11, 16),
            Block.box(0, 0, 0, 16, 16, 16),
    };

    public final Supplier<? extends SaplingBlock> saplingBlock;
    public final Supplier<? extends Item> seedItem;

    public SaplingCropBlock(Properties properties, Supplier<? extends SaplingBlock> saplingBlock, Supplier<? extends Item> seedItem) {
        super(properties);
        this.saplingBlock = saplingBlock;
        this.seedItem = seedItem;
    }

    @Override
    protected void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        super.randomTick(state, level, pos, random);
        if(this.isMaxAge(level.getBlockState(pos))){
            level.setBlock(pos, this.saplingBlock.get().defaultBlockState(), 3);
            level.setBlock(pos.below(), Blocks.DIRT.defaultBlockState(), 3);
        }
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPES[this.getAge(state)];
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(this.getAgeProperty());
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return this.seedItem.get();
    }

    @Override
    public int getMaxAge() {
        return 5;
    }

    @Override
    protected @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }
}

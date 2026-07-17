package net.Ina0_.cubanol.block.custom;

import net.Ina0_.cubanol.block.ModBlockStateProperties;
import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class AgaveStemBlock extends CropBlock {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 6);
    public static final BooleanProperty CUT = ModBlockStateProperties.CUT;
    public static final BooleanProperty DRIED = ModBlockStateProperties.DRIED;

    public static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(6.0, 0.0, 6.0, 10.0, 2.0, 10.0),
            Block.box(6.0, 0.0, 6.0, 10.0, 7.0, 10.0),
            Block.box(6.0, 0.0, 6.0, 10.0, 10.0, 10.0),
            Block.box(6.0, 0.0, 6.0, 10.0, 12.0, 10.0),
            Block.box(6.0, 0.0, 6.0, 10.0, 14.0, 10.0),
            Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0),
            Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0)
    };

    public AgaveStemBlock(Properties properties) {
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
        builder.add(AGE, CUT, DRIED);
    }

    @Override
    protected @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 6;
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
    protected boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(DRIED);
    }

    @Override
    protected void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
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
        if (this.isMaxAge(level.getBlockState(pos))) {
            if (level.getBlockState(pos.above()).is(Blocks.AIR)) {
                int stemCount = 0;
                BlockPos posUnder = pos.below();
                while (level.getBlockState(posUnder).is(ModBlocks.AGAVE_STEM.get())) {
                    stemCount += 1;
                    posUnder = posUnder.below();
                }
                double probabilityOfFlower = switch (stemCount) {
                    case 0 -> 0.5;
                    case 1 -> 0.75;
                    default -> 1.0;
                };
                if (level.random.nextDouble() < probabilityOfFlower) {
                    level.setBlock(pos.above(), ModBlocks.AGAVE_FLOWER.get().defaultBlockState(), 2);
                } else {
                    level.setBlock(pos.above(), ModBlocks.AGAVE_STEM.get().defaultBlockState(), 2);
                }
            } else if(!level.getBlockState(pos.above()).is(ModBlocks.AGAVE_STEM) && !level.getBlockState(pos.above()).is(ModBlocks.AGAVE_FLOWER)){
                level.setBlock(pos, state.setValue(AGE, this.getMaxAge() - 1), 2);
            }
        }
    }

    @Override
    protected void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Block neighborBlock, @NotNull BlockPos neighborPos, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
        if(!level.isClientSide()){
            if (!level.getBlockState(pos.above()).is(ModBlocks.AGAVE_STEM.get()) && !level.getBlockState(pos.above()).is(ModBlocks.AGAVE_FLOWER.get())){
                if(state.getValue(DRIED)){
                    level.destroyBlock(pos, false);
                } else if(this.isMaxAge(level.getBlockState(pos))){
                    level.setBlock(pos, state.setValue(AGE, this.getMaxAge() - 1), 2);
                }
            }
        }
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        super.performBonemeal(level, random, pos, state);
        if (this.isMaxAge(level.getBlockState(pos))) {
            if (level.getBlockState(pos.above()).is(Blocks.AIR)) {
                int stemCount = 0;
                BlockPos posUnder = pos.below();
                while(level.getBlockState(posUnder).is(ModBlocks.AGAVE_STEM.get())){
                    stemCount += 1;
                    posUnder = posUnder.below();
                }
                double probabilityOfFlower = Math.min(0.25 * (stemCount + 1), 1.0);

                if(level.random.nextDouble() < probabilityOfFlower){
                    level.setBlock(pos.above(), ModBlocks.AGAVE_FLOWER.get().defaultBlockState(), 2);
                } else {
                    level.setBlock(pos.above(), ModBlocks.AGAVE_STEM.get().defaultBlockState(), 2);
                }
            } else {
                level.setBlock(pos, this.defaultBlockState().setValue(AGE, this.getMaxAge() - 1), 2);
            }
        }
    }

    @Override
    protected boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(ModBlocks.AGAVE_STEM.get()) || level.getBlockState(pos.below()).is(ModBlocks.AGAVE_CROP.get());
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader level, @NotNull BlockPos pos, BlockState state) {
        if(state.getValue(CUT)){
            return false;
        }
        return super.isValidBonemealTarget(level, pos, state);
    }
}

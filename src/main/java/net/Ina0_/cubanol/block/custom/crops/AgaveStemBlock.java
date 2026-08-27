package net.Ina0_.cubanol.block.custom.crops;

import net.Ina0_.cubanol.block.properties.ModBlockStateProperties;
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

public class AgaveStemBlock extends CropBlock {
    public static final IntegerProperty AGE = ModBlockStateProperties.AGE_6;
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, CUT, DRIED);
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 6;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.AGAVE_SEEDS.get();
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(DRIED);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
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
                    level.setBlock(pos.above(), ModBlocks.AGAVE_FLOWER.get().defaultBlockState(), 3);
                } else {
                    level.setBlock(pos.above(), ModBlocks.AGAVE_STEM.get().defaultBlockState(), 3);
                }
            } else if(!level.getBlockState(pos.above()).is(ModBlocks.AGAVE_STEM) && !level.getBlockState(pos.above()).is(ModBlocks.AGAVE_FLOWER)){
                level.setBlock(pos, state.setValue(AGE, this.getMaxAge() - 1), 3);
            }
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
        if(!level.isClientSide()){
            if (!level.getBlockState(pos.above()).is(ModBlocks.AGAVE_STEM.get()) && !level.getBlockState(pos.above()).is(ModBlocks.AGAVE_FLOWER.get())){
                if(state.getValue(DRIED)){
                    level.destroyBlock(pos, false);
                } else if(this.isMaxAge(level.getBlockState(pos))){
                    level.setBlock(pos, state.setValue(AGE, this.getMaxAge() - 1), 3);
                }
            }
        }
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
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
                    level.setBlock(pos.above(), ModBlocks.AGAVE_FLOWER.get().defaultBlockState(), 3);
                } else {
                    level.setBlock(pos.above(), ModBlocks.AGAVE_STEM.get().defaultBlockState(), 3);
                }
            } else {
                level.setBlock(pos, this.defaultBlockState().setValue(AGE, this.getMaxAge() - 1), 3);
            }
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(ModBlocks.AGAVE_STEM.get()) || level.getBlockState(pos.below()).is(ModBlocks.AGAVE_CROP.get());
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        if(state.getValue(CUT)){
            return false;
        }
        return super.isValidBonemealTarget(level, pos, state);
    }
}

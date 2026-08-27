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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AgaveCropBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    public static final BooleanProperty CUT = ModBlockStateProperties.CUT;
    public static final BooleanProperty DRIED = ModBlockStateProperties.DRIED;

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
        return 5;
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
        if(this.isMaxAge(level.getBlockState(pos))){
            if(level.getBlockState(pos.above()).is(Blocks.AIR)){
                if(level.random.nextDouble() < 0.25){
                    level.setBlock(pos.above(), ModBlocks.AGAVE_FLOWER.get().defaultBlockState(), 3);
                } else {
                    level.setBlock(pos.above(), ModBlocks.AGAVE_STEM.get().defaultBlockState(), 3);
                }
            } else if(!level.getBlockState(pos.above()).is(ModBlocks.AGAVE_STEM) && !level.getBlockState(pos.above()).is(ModBlocks.AGAVE_FLOWER)){
                level.setBlock(pos, this.defaultBlockState().setValue(AGE, this.getMaxAge() - 1), 3);
            }
        }
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        super.performBonemeal(level, random, pos, state);
        if (this.isMaxAge(level.getBlockState(pos))) {
            if (level.getBlockState(pos.above()).is(Blocks.AIR)) {
                if(level.random.nextDouble() < 0.25){
                    level.setBlock(pos.above(), ModBlocks.AGAVE_FLOWER.get().defaultBlockState(), 3);
                } else {
                    level.setBlock(pos.above(), ModBlocks.AGAVE_STEM.get().defaultBlockState(), 3);
                }
            } else if(!level.getBlockState(pos.above()).is(ModBlocks.AGAVE_STEM)){
                level.setBlock(pos, this.defaultBlockState().setValue(AGE, this.getMaxAge() - 1), 3);
            }
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
        if(!level.isClientSide()){
            if (!level.getBlockState(pos.above()).is(ModBlocks.AGAVE_STEM) && !level.getBlockState(pos.above()).is(ModBlocks.AGAVE_FLOWER)) {
                if(this.isMaxAge(level.getBlockState(pos))){
                    level.setBlock(
                            pos,
                            this.defaultBlockState()
                                    .setValue(AGE, state.getValue(CUT)? this.getMaxAge() - 2: this.getMaxAge() - 1)
                                    .setValue(CUT, false)
                                    .setValue(DRIED, false),
                            2
                    );
                }
            }
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        if(state.getValue(DRIED)){
            return false;
        }
        return super.isValidBonemealTarget(level, pos, state);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(DRIED) && super.isRandomlyTicking(state);
    }

    /**
     * Marks a whole agave with the cut blockState by only designating one of its blocks
     * @param level The minecraft world in which the agave is
     * @param state The agave part's blockState
     * @param pos The position of the agave
     */
    public static void setCut(Level level, BlockState state, BlockPos pos){
        level.setBlock(pos, state.setValue(CUT, true), 3);
        BlockState stateAbove = level.getBlockState(pos.above());
        if(
                stateAbove.is(ModBlocks.AGAVE_STEM)
                || stateAbove.is(ModBlocks.AGAVE_CROP)
                || stateAbove.is(ModBlocks.AGAVE_FLOWER)
        ){
            if (!stateAbove.getValue(CUT)) {
                setCut(level, stateAbove, pos.above());
            }
        }
        BlockState stateBelow = level.getBlockState(pos.below());
        if(
                stateBelow.is(ModBlocks.AGAVE_STEM)
                || stateBelow.is(ModBlocks.AGAVE_CROP)
                || stateBelow.is(ModBlocks.AGAVE_FLOWER)
        ){
            if (!stateBelow.getValue(CUT)) {
                setCut(level, stateBelow, pos.below());
            }
        }
    }

    /**
     * Marks a whole agave with the dried blockState by only designating one of its blocks
     * @param level The minecraft world in which the agave is
     * @param state The agave part's blockState
     * @param pos The position of the agave
     */
    public static void setDried(Level level, BlockState state, BlockPos pos){
        level.setBlock(pos, state.setValue(DRIED, true), 3);
        BlockState stateAbove = level.getBlockState(pos.above());
        if(
                stateAbove.is(ModBlocks.AGAVE_STEM)
                        || stateAbove.is(ModBlocks.AGAVE_CROP)
                        || stateAbove.is(ModBlocks.AGAVE_FLOWER)
        ){
            if (!stateAbove.getValue(DRIED)) {
                setDried(level, stateAbove, pos.above());
            }
        }
        BlockState stateBelow = level.getBlockState(pos.below());
        if(
                stateBelow.is(ModBlocks.AGAVE_STEM)
                        || stateBelow.is(ModBlocks.AGAVE_CROP)
                        || stateBelow.is(ModBlocks.AGAVE_FLOWER)
        ){
            if (!stateBelow.getValue(DRIED)) {
                setDried(level, stateBelow, pos.below());
            }
        }
    }
}

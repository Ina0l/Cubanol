package net.Ina0_.cubanol.block.custom;

import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RiceCropBlock extends CropBlock implements SimpleWaterloggedBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
    public static final BooleanProperty SUPPORTING = BooleanProperty.create("supporting");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 9.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 15.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    };

    public RiceCropBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0).setValue(SUPPORTING, false).setValue(WATERLOGGED, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, SUPPORTING, WATERLOGGED);
    }

    @Override
    public int getMaxAge() {
        return 4;
    }

    @Override
    protected @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return ModItems.RICE;
    }

    @Override
    protected boolean isRandomlyTicking(@NotNull BlockState state) {
        return super.isRandomlyTicking(state) || !state.getValue(SUPPORTING);
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE_BY_AGE[this.getAge(state) + (state.getValue(SUPPORTING) ? 1: 0)];
    }

    @Override
    protected void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if(!level.isClientSide()) {
            if(this.isMaxAge(state) && level.getBlockState(pos.above()).is(Blocks.AIR)) {
                float f = getGrowthSpeed(state, level, pos);
                if (CommonHooks.canCropGrow(level, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0)) {
                    level.setBlock(pos.above(), ModBlocks.RICE_PANICLES.get().defaultBlockState(), 3);
                    level.setBlock(pos, state.setValue(SUPPORTING, true), 3);
                    return;
                }
            }
        }
        super.randomTick(state, level, pos, random);
    }

    @Override
    protected void neighborChanged(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Block neighborBlock, @NotNull BlockPos neighborPos, boolean movedByPiston) {
        if(!level.isClientSide()){
            if (!level.getBlockState(pos.above()).is(ModBlocks.RICE_PANICLES)) {
                level.setBlock(pos, state.setValue(SUPPORTING, false), 3);
            }
        }
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
    }

    @Override
    protected boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(Blocks.FARMLAND) && state.getValue(WATERLOGGED);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Boolean isWaterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState().setValue(WATERLOGGED, isWaterlogged);
    }

    @Override
    protected @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    protected static float getGrowthSpeed(BlockState state, Level level, BlockPos pos){
        float f = 1.0F;
        BlockPos blockpos = pos.below();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                float f1 = 0.0F;
                BlockState blockstate = level.getBlockState(blockpos.offset(i, 0, j));
                net.neoforged.neoforge.common.util.TriState soilDecision = blockstate.canSustainPlant(level, blockpos.offset(i, 0, j), net.minecraft.core.Direction.UP, state);
                if (soilDecision.isDefault() ? blockstate.getBlock() instanceof net.minecraft.world.level.block.FarmBlock : soilDecision.isTrue()) {
                    f1 = 1.0F;
                    if (blockstate.isFertile(level, pos.offset(i, 0, j))) {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }
        return f;
    }
}

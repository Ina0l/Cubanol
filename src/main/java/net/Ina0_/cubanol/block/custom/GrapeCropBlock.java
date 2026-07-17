package net.Ina0_.cubanol.block.custom;

import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import org.jetbrains.annotations.NotNull;

public class GrapeCropBlock extends CropBlock{
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 12);
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final DirectionProperty VINE_HANGING_SIDE = BlockStateProperties.HORIZONTAL_FACING;

    public static final BooleanProperty WHITE = BooleanProperty.create("white");


    private static final VoxelShape BASE_SHAPE;
    private static final VoxelShape NORTH_CABLE;
    private static final VoxelShape SOUTH_CABLE;
    private static final VoxelShape EAST_CABLE;
    private static final VoxelShape WEST_CABLE;
    public static final VoxelShape[] SHAPES;

    public GrapeCropBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(NORTH, false)
                .setValue(SOUTH, false)
                .setValue(EAST, false)
                .setValue(WEST, false)
                .setValue(VINE_HANGING_SIDE, Direction.NORTH)
                .setValue(WHITE, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, NORTH, SOUTH, EAST, WEST, VINE_HANGING_SIDE, WHITE);
    }

    @Override
    public int getMaxAge() {
        return 12;
    }

    @Override
    protected @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(@NotNull LevelReader level, @NotNull BlockPos pos, BlockState state) {
        return new ItemStack(state.getValue(WHITE)? ModItems.WHITE_GRAPE_SEEDS.get(): ModItems.BLACK_GRAPE_SEEDS.get());
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        VoxelShape shape = BASE_SHAPE;

        if(state.getValue(NORTH)){
            shape = Shapes.or(shape, NORTH_CABLE);
        }
        if(state.getValue(SOUTH)){
            shape = Shapes.or(shape, SOUTH_CABLE);
        }
        if(state.getValue(EAST)){
            shape = Shapes.or(shape, EAST_CABLE);
        }
        if(state.getValue(WEST)){
            shape = Shapes.or(shape, WEST_CABLE);
        }
        return Shapes.or(shape, SHAPES[state.getValue(VINE_HANGING_SIDE).get3DDataValue() - 2]);
    }

    @Override
    protected void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Block neighborBlock, @NotNull BlockPos neighborPos, boolean movedByPiston) {
        if(!level.isClientSide()){
            if(pos.getY() == neighborPos.getY()){
                Direction neighborDirection = null;
                for (Direction direction : Direction.values()) {
                    if (direction == Direction.DOWN || direction == Direction.UP) {
                        continue;
                    }
                    if (pos.getX() + direction.getStepX() == neighborPos.getX() && pos.getZ() + direction.getStepZ() == neighborPos.getZ()) {
                        neighborDirection = direction;
                    }
                }
                if (neighborDirection == null) {
                    throw new NullPointerException("neighborDirection is null");
                }
                if (level.getBlockState(neighborPos).getBlock() instanceof CropSupportBlock || level.getBlockState(neighborPos).getBlock() instanceof GrapeCropBlock) {
                    if (!state.getValue(CropSupportBlock.getPropertyFromDirection(neighborDirection)) && level.getBlockState(neighborPos).getValue(CropSupportBlock.getPropertyFromDirection(neighborDirection.getOpposite()))) {
                        level.playSound(null, pos, SoundEvents.CHAIN_BREAK, SoundSource.BLOCKS);
                        BlockState newState = state.setValue(CropSupportBlock.getPropertyFromDirection(neighborDirection.getOpposite()), false);
                        if(newState.is(ModBlocks.GRAPE_CROP) && !newState.getValue(CropSupportBlock.getPropertyFromDirection(newState.getValue(VINE_HANGING_SIDE)))){
                            this.destroy(level, neighborPos, newState);
                        } else {
                            level.setBlock(neighborPos, newState, 3);
                        }
                    }
                    if (state.getValue(CropSupportBlock.getPropertyFromDirection(neighborDirection)) && !level.getBlockState(neighborPos).getValue(CropSupportBlock.getPropertyFromDirection(neighborDirection.getOpposite()))) {
                        level.playSound(null, pos, SoundEvents.CHAIN_BREAK, SoundSource.BLOCKS);
                        BlockState newState = state.setValue(CropSupportBlock.getPropertyFromDirection(neighborDirection), false);
                        if(newState.is(ModBlocks.GRAPE_CROP) && !newState.getValue(CropSupportBlock.getPropertyFromDirection(newState.getValue(VINE_HANGING_SIDE)))){
                            this.destroy(level, pos, newState);
                        } else {
                            level.setBlock(pos, newState, 3);
                        }
                    }
                } else {
                    if (state.getValue(CropSupportBlock.getPropertyFromDirection(neighborDirection))) {
                        level.playSound(null, pos, SoundEvents.CHAIN_BREAK, SoundSource.BLOCKS);
                        BlockState newState = state.setValue(CropSupportBlock.getPropertyFromDirection(neighborDirection), false);
                        if(newState.is(ModBlocks.GRAPE_CROP) && !newState.getValue(CropSupportBlock.getPropertyFromDirection(newState.getValue(VINE_HANGING_SIDE)))){
                            this.destroy(level, pos, newState);
                        } else {
                            level.setBlock(pos, newState, 3);
                        }
                    }
                }
            }
        }
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        if(!level.isClientSide()){
            if(state.getValue(AGE) == this.getMaxAge()){
                if(ModBlocks.collectOrDropItemsFromState((ServerLevel) level, player, state, pos) > 0 || player.hasInfiniteMaterials()){
                    level.setBlock(pos, state.setValue(AGE, 8), 3);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    public void destroy(@NotNull LevelAccessor levelAccessor, @NotNull BlockPos pos, @NotNull BlockState state) {
        if(!levelAccessor.isClientSide()) {
            ModBlocks.dropItemsFromState((ServerLevel) levelAccessor, state, pos, null);
            levelAccessor.setBlock(pos, CropSupportBlock.getBlockStateFromGrapeCropState(state), 3);
        }
    }

    @Override
    protected void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (level.getRawBrightness(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                float f = getGrowthSpeed(state, level, pos);
                if (CommonHooks.canCropGrow(level, pos, state, random.nextInt((int)(25.0F / f) + 1) == 0)) {
                    level.setBlock(pos, state.setValue(AGE, i + 1), 2);
                    CommonHooks.fireCropGrowPost(level, pos, state);
                }
            }
        }
    }

    @Override
    public void growCrops(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state) {
        int i;
        if(state.getValue(AGE) < 8){
            i = this.getAge(state) + this.getBonemealAgeIncrease(level);
            int j = 8;
            if (i > j) {
                i = j;
            }
        } else {
            i = this.getAge(state) + 1;
            int j = this.getMaxAge();
            if (i > j) {
                i = j;
            }
        }
        level.setBlock(pos, state.setValue(AGE, i), 2);

    }

    @Override
    protected boolean isRandomlyTicking(@NotNull BlockState state) {
        if(getFacingPropertiesCount(state) > 1) {
            return super.isRandomlyTicking(state);
        } else {
            return false;
        }
    }

    public static int getFacingPropertiesCount(@NotNull BlockState state){
        int toReturn = 0;
        if(state.getValue(NORTH)){
            toReturn += 1;
        }
        if(state.getValue(SOUTH)){
            toReturn += 1;
        }
        if(state.getValue(EAST)){
            toReturn += 1;
        }
        if(state.getValue(WEST)){
            toReturn += 1;
        }
        return toReturn;
    }

    public static float getGrowthSpeed(BlockState state, BlockGetter blockGetter, BlockPos pos){
        return CropBlock.getGrowthSpeed(state, blockGetter, pos) * ((getFacingPropertiesCount(state) - 2) * 0.25f + 1) / (state.getValue(AGE) < 9? 1: 2);
    }

    public static BlockState getBlockStateFromCropSupportState(@NotNull BlockState state){
        if(!state.is(ModBlocks.CROP_SUPPORT)){
            throw new IllegalArgumentException("state argument should be a grape crop state, got "+state.getBlock().getName()+" instead");
        }
        return ModBlocks.GRAPE_CROP.get().defaultBlockState()
                .setValue(NORTH, state.getValue(NORTH))
                .setValue(SOUTH, state.getValue(SOUTH))
                .setValue(EAST, state.getValue(EAST))
                .setValue(WEST, state.getValue(WEST));
    }

    static {
        BASE_SHAPE = Block.box(7.0, 0.0, 7.0, 9.0, 16.0, 9.0);
        NORTH_CABLE = Block.box(7.0, 11.0, 0.0, 9.0, 13.0, 8.0);
        SOUTH_CABLE = Block.box(7.0, 11.0, 8.0, 9.0, 13.0, 16.0);
        EAST_CABLE = Block.box(8.0, 11.0, 7.0, 16.0, 13.0, 9.0);
        WEST_CABLE = Block.box(0.0, 11.0, 7.0, 8.0, 13.0, 9.0);
        SHAPES = new VoxelShape[]{
                Block.box(5.0, 0.0, 0.0, 15.0, 14.0, 16.0),
                Block.box(1.0, 0.0, 0.0, 11.0, 14.0, 16.0),
                Block.box(0.0, 0.0, 1.0, 16.0, 14.0, 11.0),
                Block.box(0.0, 0.0, 5.0, 16.0, 14.0, 15.0)
        };
    }
}

package net.Ina0_.cubanol.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class CropSupportBlock extends Block{
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;

    private static final VoxelShape BASE_SHAPE = Block.box(7.0, 0.0, 7.0, 9.0, 16.0, 9.0);
    private static final VoxelShape NORTH_CABLE = Block.box(7.0, 11.0, 0.0, 9.0, 13.0, 8.0);
    private static final VoxelShape SOUTH_CABLE = Block.box(7.0, 11.0, 8.0, 9.0, 13.0, 16.0);
    private static final VoxelShape EAST_CABLE = Block.box(8.0, 11.0, 7.0, 16.0, 13.0, 9.0);
    private static final VoxelShape WEST_CABLE = Block.box(0.0, 11.0, 7.0, 8.0, 13.0, 9.0);


    public CropSupportBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(NORTH, false)
                .setValue(SOUTH, false)
                .setValue(EAST, false)
                .setValue(WEST, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH)
                .add(SOUTH)
                .add(EAST)
                .add(WEST);
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
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
        return shape;
    }

    @Override
    protected void neighborChanged(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Block neighborBlock, @NotNull BlockPos neighborPos, boolean movedByPiston) {
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
                if (level.getBlockState(neighborPos).getBlock() instanceof CropSupportBlock || level.getBlockState(neighborPos).getBlock() instanceof  GrapeCropBlock) {
                    if(!state.getValue(getPropertyFromDirection(neighborDirection)) && level.getBlockState(neighborPos).getValue(getPropertyFromDirection(neighborDirection.getOpposite()))) {
                        level.playSound(null, pos, SoundEvents.CHAIN_BREAK, SoundSource.BLOCKS);
                        level.setBlock(neighborPos, level.getBlockState(neighborPos).setValue(getPropertyFromDirection(neighborDirection.getOpposite()), false), 2);
                    }
                    if(state.getValue(getPropertyFromDirection(neighborDirection)) && !level.getBlockState(neighborPos).getValue(getPropertyFromDirection(neighborDirection.getOpposite()))) {
                        level.playSound(null, pos, SoundEvents.CHAIN_BREAK, SoundSource.BLOCKS);
                        level.setBlock(pos, state.setValue(getPropertyFromDirection(neighborDirection), false), 2);
                    }
                } else {
                    if(state.getValue(getPropertyFromDirection(neighborDirection))) {
                        level.playSound(null, pos, SoundEvents.CHAIN_BREAK, SoundSource.BLOCKS);
                        level.setBlock(pos, state.setValue(getPropertyFromDirection(neighborDirection), false), 2);
                    }
                }
            }
        }
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
    }

    public static BooleanProperty getPropertyFromDirection(@NotNull Direction direction){
        return switch (direction) {
            case Direction.EAST -> BlockStateProperties.EAST;
            case Direction.WEST -> BlockStateProperties.WEST;
            case Direction.NORTH -> BlockStateProperties.NORTH;
            case Direction.SOUTH -> BlockStateProperties.SOUTH;
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }
}

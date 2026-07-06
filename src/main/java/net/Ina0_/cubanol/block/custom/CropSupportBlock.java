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
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class CropSupportBlock extends Block {
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.box(7.0, 0.0, 7.0, 9.0, 16.0, 9.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    };

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
        if(state.getValue(NORTH) || state.getValue(SOUTH) || state.getValue(EAST) || state.getValue(WEST)){
            return SHAPES[1];
        } else {
            return SHAPES[0];
        }
    }

    @Override
    protected void neighborChanged(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Block neighborBlock, @NotNull BlockPos neighborPos, boolean movedByPiston) {
        if(!level.isClientSide()){
            Direction neighborDirection = null;
            for (Direction direction: Direction.values()){
                if(direction == Direction.DOWN || direction == Direction.UP){
                    continue;
                }
                if(pos.getX() + direction.getStepX() == neighborPos.getX() && pos.getZ() + direction.getStepZ() == neighborPos.getZ()){
                    neighborDirection = direction;
                }
            }
            assert neighborDirection != null;
            if (level.getBlockState(neighborPos).getBlock() instanceof CropSupportBlock) {
                if(!state.getValue(getPropertyFromDirection(neighborDirection)) && level.getBlockState(neighborPos).getValue(getPropertyFromDirection(neighborDirection.getOpposite()))){
                    level.playSound(null, pos, SoundEvents.CHAIN_BREAK, SoundSource.BLOCKS);
                    level.setBlock(neighborPos, level.getBlockState(neighborPos).setValue(getPropertyFromDirection(neighborDirection.getOpposite()), false), 2);
                }
                if(state.getValue(getPropertyFromDirection(neighborDirection)) && !level.getBlockState(neighborPos).getValue(getPropertyFromDirection(neighborDirection.getOpposite()))){
                    level.playSound(null, pos, SoundEvents.CHAIN_BREAK, SoundSource.BLOCKS);
                    level.setBlock(pos, state.setValue(getPropertyFromDirection(neighborDirection), false), 2);
                }
            } else {
                if(state.getValue(getPropertyFromDirection(neighborDirection))){
                    level.playSound(null, pos, SoundEvents.CHAIN_BREAK, SoundSource.BLOCKS);
                    level.setBlock(pos, state.setValue(getPropertyFromDirection(neighborDirection), false), 2);
                }
            }
        }
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
    }

    public static BooleanProperty getPropertyFromDirection(Direction direction){
        return switch (direction) {
            case Direction.EAST -> BlockStateProperties.EAST;
            case Direction.WEST -> BlockStateProperties.WEST;
            case Direction.NORTH -> BlockStateProperties.NORTH;
            case Direction.SOUTH -> BlockStateProperties.SOUTH;
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }
}

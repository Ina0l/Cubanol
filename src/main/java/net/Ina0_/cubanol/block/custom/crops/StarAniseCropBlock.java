package net.Ina0_.cubanol.block.custom.crops;

import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.block.properties.ModBlockStateProperties;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StarAniseCropBlock extends CropBlock {
    public static final IntegerProperty AGE = ModBlockStateProperties.AGE_6;

    public static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.box(7.0, 0.0, 7.0, 9.0, 1.0, 9.0),
            Block.box(5.0, 0.0, 5.0, 11.0, 5.0, 11.0),
            Block.box(3.0, 0.0, 3.0, 13.0, 8.0, 13.0),
            Block.box(3.0, 0.0, 3.0, 13.0, 9.0, 13.0),
            Block.box(2.0, 0.0, 2.0, 14.0, 10.0, 14.0),
            Block.box(2.0, 0.0, 2.0, 14.0, 10.0, 14.0),
            Block.box(2.0, 0.0, 2.0, 14.0, 10.0, 14.0),
    };

    public StarAniseCropBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0));
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.STAR_ANISE;
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES[state.getValue(AGE)];
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(this.isMaxAge(state)){
            if(!level.isClientSide()){
                ModBlocks.collectOrDropItemsFromState((ServerLevel) level, player, state, pos);
                level.setBlock(pos, state.setValue(AGE, this.getMaxAge() - 2), 3);
            }
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return InteractionResult.PASS;
    }
}

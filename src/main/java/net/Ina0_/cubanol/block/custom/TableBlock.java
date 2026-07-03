package net.Ina0_.cubanol.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;


public class TableBlock extends Block {
    private static final VoxelShape LEG_1;
    private static final VoxelShape LEG_2;
    private static final VoxelShape LEG_3;
    private static final VoxelShape LEG_4;
    private static final VoxelShape TABLE_TOP;
    private static final VoxelShape AABB;


    public TableBlock(Properties properties) {
        super(properties);
    }

    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return AABB;
    }

    static {
        LEG_1 = box(1.0, 0.0, 1.0, 3.0, 14.0, 3.0);
        LEG_2 = box(1.0, 0.0, 13.0, 3.0, 14.0, 15.0);
        LEG_3 = box(13.0, 0.0, 1.0, 15.0, 14.0, 3.0);
        LEG_4 = box(13.0, 0.0, 13.0, 15.0, 14.0, 15.0);
        TABLE_TOP = box(0.0, 14.0, 0.0, 16.0, 16.0, 16.0);
        AABB = Shapes.or(TABLE_TOP, LEG_1, LEG_2, LEG_3, LEG_4);
    }
}

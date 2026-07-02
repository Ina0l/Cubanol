package net.Ina0_.cubanol.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class FakeBottle extends DirectionalBlock{
    private final Supplier<VoxelShape> fullBottleShape;

    public FakeBottle(Properties properties, Supplier<VoxelShape> fullBottleShape) {
        super(properties);
        this.fullBottleShape = fullBottleShape;
    }

    protected @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        Vec3 vec3 = state.getOffset(level, pos);
        Direction direction = state.getValue(FACING);
        if(direction == Direction.NORTH){
            vec3 = vec3.add((double) 1 /16, 0, (double) 1 /16);
        }
        if(direction == Direction.EAST){
            vec3 = vec3.add(0, 0, (double) 1 /16);
        }
        if(direction == Direction.WEST){
            vec3 = vec3.add((double) 1 /16, 0, 0);
        }
        return this.fullBottleShape.get().move(vec3.x, vec3.y, vec3.z);
    }
}

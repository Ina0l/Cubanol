package net.Ina0_.cubanol.block.custom;

import com.mojang.serialization.MapCodec;
import net.Ina0_.cubanol.block.entity.CaskBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CaskBlock extends BaseEntityBlock implements BucketPickup, LiquidBlockContainer {
    public static final VoxelShape SHAPE;

    public static final MapCodec<CaskBlock> CODEC = simpleCodec(CaskBlock::new);

    public CaskBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new CaskBlockEntity(pos, state);
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @NotNull ItemStack pickupBlock(@Nullable Player player, LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockState state) {
        if(level.getBlockEntity(pos) instanceof CaskBlockEntity caskBlockEntity){
            if(caskBlockEntity.tank.getFluidAmount() >= FluidType.BUCKET_VOLUME){
                ItemStack bucket = new ItemStack(caskBlockEntity.getFluid().getBucket());

                caskBlockEntity.getFluid().getPickupSound().ifPresent(
                        soundEvent -> level.playSound(player, pos, soundEvent, SoundSource.BLOCKS)
                );

                caskBlockEntity.tank.drain(FluidType.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);

                return bucket;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    @Deprecated
    public @NotNull Optional<SoundEvent> getPickupSound() {
        return Optional.empty();
    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player player, BlockGetter level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Fluid fluid) {
        if(level.getBlockEntity(pos) instanceof CaskBlockEntity caskBlockEntity){
            return caskBlockEntity.tank.fill(new FluidStack(fluid, FluidType.BUCKET_VOLUME), IFluidHandler.FluidAction.SIMULATE) == FluidType.BUCKET_VOLUME;
        }
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull FluidState fluidState) {
        if(level.getBlockEntity(pos) instanceof CaskBlockEntity caskBlockEntity){
            if(caskBlockEntity.tank.fill(new FluidStack(fluidState.getType(), FluidType.BUCKET_VOLUME), IFluidHandler.FluidAction.SIMULATE) == FluidType.BUCKET_VOLUME){
                caskBlockEntity.tank.fill(new FluidStack(fluidState.getType(), FluidType.BUCKET_VOLUME), IFluidHandler.FluidAction.EXECUTE);
                return true;
            }
        }
        return false;
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if(level.getBlockEntity(pos) instanceof CaskBlockEntity caskBlockEntity){
            boolean isFluidWater = caskBlockEntity.getFluid().isSame(Fluids.WATER);
            boolean canAcceptWaterBottle = (isFluidWater && caskBlockEntity.tank.getSpace() >= 250) || caskBlockEntity.getFluid().isSame(Fluids.EMPTY);
            boolean canExtractWaterBottle = isFluidWater && caskBlockEntity.tank.getFluidAmount() >= 250;

            if (stack.is(Items.GLASS_BOTTLE) && canExtractWaterBottle) {
                if(!player.hasInfiniteMaterials()){
                    stack.consume(1, player);
                    ItemStack waterBottle = PotionContents.createItemStack(Items.POTION, Potions.WATER);
                    if (player.getItemInHand(hand).isEmpty()) {
                        player.setItemInHand(hand, waterBottle);
                    } else {
                        player.getInventory().add(waterBottle);
                    }
                }
                caskBlockEntity.tank.drain(250, IFluidHandler.FluidAction.EXECUTE);
                level.playSound(player, pos, SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS);
                return ItemInteractionResult.sidedSuccess(level.isClientSide());

            } else if (stack.is(Items.POTION) && canAcceptWaterBottle) {
                PotionContents potioncontents = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);

                if(potioncontents.is(Potions.WATER)){
                    if(!player.hasInfiniteMaterials()){
                        stack.consume(1, player);
                        player.setItemInHand(hand, new ItemStack(Items.GLASS_BOTTLE));
                    }
                    caskBlockEntity.tank.fill(new FluidStack(Fluids.WATER, 250), IFluidHandler.FluidAction.EXECUTE);
                    level.playSound(player, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.PLAYERS);
                    return ItemInteractionResult.sidedSuccess(level.isClientSide());
                }
            }
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public boolean onDestroyedByPlayer(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, boolean willHarvest, @NotNull FluidState fluid) {
        if(!player.hasInfiniteMaterials()){
            ItemStack stack = new ItemStack(this);
            if (level.getBlockEntity(pos) instanceof CaskBlockEntity caskBlockEntity) {
                if(!caskBlockEntity.tank.isEmpty()){
                    FluidUtil.getFluidHandler(stack).ifPresent(
                            fluidHandler -> fluidHandler.fill(caskBlockEntity.tank.getFluid(), IFluidHandler.FluidAction.EXECUTE)
                    );
                }
            }
            popResource(level, pos, stack);
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(@NotNull BlockState state, @NotNull HitResult target, @NotNull LevelReader level, @NotNull BlockPos pos, @NotNull Player player) {
        ItemStack stack = new ItemStack(this);
        if(level.getBlockEntity(pos) instanceof CaskBlockEntity caskBlockEntity){
            if(!caskBlockEntity.tank.isEmpty()){
                FluidUtil.getFluidHandler(stack).ifPresent(
                        fluidHandler -> fluidHandler.fill(caskBlockEntity.tank.getFluid(), IFluidHandler.FluidAction.EXECUTE)
                );
            }
        }
        return stack;
    }

    static {
        SHAPE = Shapes.or(
                Block.box(2, 0, 5, 14, 1, 11),
                Block.box(3, 0, 11, 13, 1, 12),
                Block.box(4, 0, 12, 12, 1, 13),
                Block.box(5, 0, 13, 11, 1, 14),
                Block.box(5, 0, 2, 11, 1, 3),
                Block.box(4, 0, 3, 12, 1, 4),
                Block.box(3, 0, 4, 13, 1, 5),
                Block.box(5, 3, 0, 11, 13, 1),
                Block.box(5, 3, 15, 11, 13, 16),
                Block.box(15, 3, 5, 16, 13, 11),
                Block.box(0, 3, 5, 1, 13, 11),
                Block.box(14, 3, 11, 15, 13, 13),
                Block.box(1, 3, 11, 2, 13, 13),
                Block.box(14, 3, 3, 15, 13, 5),
                Block.box(1, 3, 3, 2, 13, 5),
                Block.box(11, 3, 14, 13, 13, 15),
                Block.box(11, 3, 1, 13, 13, 2),
                Block.box(3, 3, 14, 5, 13, 15),
                Block.box(3, 3, 1, 5, 13, 2),
                Block.box(2, 3, 2, 3, 13, 3),
                Block.box(2, 3, 13, 3, 13, 14),
                Block.box(13, 3, 2, 14, 13, 3),
                Block.box(13, 3, 13, 14, 13, 14),
                Block.box(1, 1, 5, 2, 3, 11),
                Block.box(14, 1, 5, 15, 3, 11),
                Block.box(5, 1, 14, 11, 3, 15),
                Block.box(5, 1, 1, 11, 3, 2),
                Block.box(11, 1, 2, 13, 3, 3),
                Block.box(11, 1, 13, 13, 3, 14),
                Block.box(3, 1, 13, 5, 3, 14),
                Block.box(3, 1, 2, 5, 3, 3),
                Block.box(13, 1, 3, 14, 3, 5),
                Block.box(2, 1, 3, 3, 3, 5),
                Block.box(13, 1, 11, 14, 3, 13),
                Block.box(2, 1, 11, 3, 3, 13),
                Block.box(3, 1, 12, 4, 3, 13),
                Block.box(12, 1, 12, 13, 3, 13),
                Block.box(3, 1, 3, 4, 3, 4),
                Block.box(12, 1, 3, 13, 3, 4),
                Block.box(1, 13, 5, 2, 15, 11),
                Block.box(14, 13, 5, 15, 15, 11),
                Block.box(5, 13, 14, 11, 15, 15),
                Block.box(5, 13, 1, 11, 15, 2),
                Block.box(11, 13, 2, 13, 15, 3),
                Block.box(11, 13, 13, 13, 15, 14),
                Block.box(3, 13, 13, 5, 15, 14),
                Block.box(3, 13, 2, 5, 15, 3),
                Block.box(13, 13, 3, 14, 15, 5),
                Block.box(2, 13, 3, 3, 15, 5),
                Block.box(13, 13, 11, 14, 15, 13),
                Block.box(2, 13, 11, 3, 15, 13),
                Block.box(3, 13, 12, 4, 15, 13),
                Block.box(12, 13, 12, 13, 15, 13),
                Block.box(3, 13, 3, 4, 15, 4),
                Block.box(12, 13, 3, 13, 15, 4)
        ).optimize();
    }
}

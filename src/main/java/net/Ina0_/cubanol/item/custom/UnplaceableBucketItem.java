package net.Ina0_.cubanol.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class UnplaceableBucketItem extends BucketItem {
    public UnplaceableBucketItem(Fluid content, Properties properties) {
        super(content, properties);
    }

    @Override
    public boolean emptyContents(@Nullable Player player, Level level, BlockPos pos, @Nullable BlockHitResult result, @Nullable ItemStack container) {
        // that's just the BucketItem.emptyContents but without the last if block containing the level.setBlock
        if (!(this.content instanceof FlowingFluid flowingfluid)) {
            return false;
        } else {
            Block $$7;
            boolean $$8;
            BlockState blockstate;
            boolean flag2;
            label82: {
                blockstate = level.getBlockState(pos);
                $$7 = blockstate.getBlock();
                $$8 = blockstate.canBeReplaced(this.content);
                label70:
                if (!blockstate.isAir() && !$$8) {
                    if ($$7 instanceof LiquidBlockContainer liquidblockcontainer
                            && liquidblockcontainer.canPlaceLiquid(player, level, pos, blockstate, this.content)) {
                        break label70;
                    }

                    flag2 = false;
                    break label82;
                }

                flag2 = true;
            }

            boolean flag1 = flag2;
            java.util.Optional<net.neoforged.neoforge.fluids.FluidStack> containedFluidStack = java.util.Optional.ofNullable(container).flatMap(net.neoforged.neoforge.fluids.FluidUtil::getFluidContained);
            if (!flag1) {
                return result != null && this.emptyContents(player, level, result.getBlockPos().relative(result.getDirection()), null, container);
            } else if (containedFluidStack.isPresent() && this.content.getFluidType().isVaporizedOnPlacement(level, pos, containedFluidStack.get())) {
                this.content.getFluidType().onVaporize(player, level, pos, containedFluidStack.get());
                return true;
            } else if (level.dimensionType().ultraWarm() && this.content.defaultFluidState().is(FluidTags.WATER)) {
                int l = pos.getX();
                int i = pos.getY();
                int j = pos.getZ();
                level.playSound(
                        player,
                        pos,
                        SoundEvents.FIRE_EXTINGUISH,
                        SoundSource.BLOCKS,
                        0.5F,
                        2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F
                );

                for (int k = 0; k < 8; k++) {
                    level.addParticle(
                            ParticleTypes.LARGE_SMOKE, (double)l + Math.random(), (double)i + Math.random(), (double)j + Math.random(), 0.0, 0.0, 0.0
                    );
                }

                return true;
            } else {
                if ($$7 instanceof LiquidBlockContainer liquidBlockContainer1 && liquidBlockContainer1.canPlaceLiquid(player, level, pos, blockstate,content)) {
                    liquidBlockContainer1.placeLiquid(level, pos, blockstate, flowingfluid.getSource(false));
                    this.playEmptySound(player, level, pos);
                    return true;
                }
                return false;
            }
        }
    }
}

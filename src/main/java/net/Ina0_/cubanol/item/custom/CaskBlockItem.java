package net.Ina0_.cubanol.item.custom;

import net.Ina0_.cubanol.block.entity.CaskBlockEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.templates.FluidHandlerItemStack;

import java.util.List;

public class CaskBlockItem extends BlockItem {
    public CaskBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        FluidStack fluidStack = FluidUtil.getFluidHandler(stack).map(
                fluidHandler -> ((FluidHandlerItemStack) fluidHandler).getFluid()
        ).orElse(FluidStack.EMPTY);
        if(!fluidStack.isEmpty()){
            return 1;
        }
        return super.getMaxStackSize(stack);
    }

    @Override
    protected boolean placeBlock(BlockPlaceContext context, BlockState state) {
        boolean toReturn = super.placeBlock(context, state);
        if(context.getLevel().getBlockEntity(context.getClickedPos()) instanceof CaskBlockEntity caskBlockEntity) {
            FluidUtil.getFluidHandler(context.getItemInHand()).ifPresent(
                    fluidHandler -> caskBlockEntity.tank.setFluid(((FluidHandlerItemStack) fluidHandler).getFluid())
            );
        }
        return toReturn;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        FluidStack fluidStack = FluidUtil.getFluidHandler(stack).map(
                fluidHandler -> ((FluidHandlerItemStack) fluidHandler).getFluid()
        ).orElse(FluidStack.EMPTY);

        tooltipComponents.add(Component.translatable("tooltip.cubanol.cask_content_tooltip"));
        tooltipComponents.add(
                fluidStack.isEmpty()?
                        Component.translatable("tooltip.cubanol.empty"):
                        Component.translatable(fluidStack.getFluid().defaultFluidState().createLegacyBlock().getBlock().getDescriptionId())
                                .append(Component.literal(" §e" + fluidStack.getAmount() + "§r/" + CaskBlockEntity.TANK_CAPACITY + "mB"))
        );
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}

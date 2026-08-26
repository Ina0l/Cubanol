package net.Ina0_.cubanol.item.custom;

import net.Ina0_.cubanol.block.entity.CaskBlockEntity;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CaskBlockItem extends BlockItem {
    public CaskBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    protected boolean placeBlock(@NotNull BlockPlaceContext context, @NotNull BlockState state) {
        boolean toReturn = super.placeBlock(context, state);
        if(context.getLevel().getBlockEntity(context.getClickedPos()) instanceof CaskBlockEntity caskBlockEntity){
            CustomData data = context.getItemInHand().get(DataComponents.CUSTOM_DATA);
            CompoundTag tag;
            if(data == null){
                tag = new CompoundTag();
            } else {
                tag = data.copyTag();
            }
            caskBlockEntity.tank.readFromNBT(context.getLevel().registryAccess(), tag);
        }
        return toReturn;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        CustomData data = stack.get(DataComponents.CUSTOM_DATA);
        CompoundTag tag;
        if (data == null) {
            tag = new CompoundTag();
        } else {
            tag = data.copyTag();
        }
        try (Level level = Objects.requireNonNull(context.level())) {
            FluidStack fluidStack = FluidStack.parseOptional(level.registryAccess(), tag.getCompound("Fluid"));
            tooltipComponents.add(Component.translatable("tooltip.cubanol.cask_content_tooltip"));
            tooltipComponents.add(
                    fluidStack.isEmpty()?
                            Component.translatable("tooltip.cubanol.empty"):
                            Component.translatable(fluidStack.getFluid().defaultFluidState().createLegacyBlock().getBlock().getDescriptionId())
                                    .append(Component.literal(" §e" + fluidStack.getAmount() + "§r/" + CaskBlockEntity.TANK_CAPACITY + "mB"))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException ignored){}

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}

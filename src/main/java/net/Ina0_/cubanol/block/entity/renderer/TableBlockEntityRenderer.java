package net.Ina0_.cubanol.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.Ina0_.cubanol.block.custom.TableBlock;
import net.Ina0_.cubanol.block.entity.TableBlockEntity;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

import java.util.Objects;

public record TableBlockEntityRenderer(BlockEntityRendererProvider.Context context) implements BlockEntityRenderer<TableBlockEntity> {

    @Override
    public void render(TableBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = blockEntity.getStack();
        Direction facing = blockEntity.getBlockState().getValue(TableBlock.FACING);

        poseStack.pushPose();
        if (ModItems.isItemModelGenerated(stack.getItem())) {
            poseStack.translate(0.5f, 1f, 0.5f);
            poseStack.translate(facing.getStepX() * -0.2, 0, facing.getStepZ() * -0.2);
            poseStack.mulPose(facing.getRotation());
        } else {
            poseStack.translate(0.5f, 0.91f, 0.5f);
        }
        poseStack.scale(1.5f, 1.5f, 1.5f);

        itemRenderer.renderStatic(stack, ItemDisplayContext.GROUND, getLightLevel(Objects.requireNonNull(blockEntity.getLevel()), blockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, poseStack, bufferSource, blockEntity.getLevel(), 1);

        poseStack.popPose();
    }

    public static int getLightLevel(Level level, BlockPos pos) {
        int blockLight = level.getBrightness(LightLayer.BLOCK, pos);
        int skyLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(blockLight, skyLight);
    }
}

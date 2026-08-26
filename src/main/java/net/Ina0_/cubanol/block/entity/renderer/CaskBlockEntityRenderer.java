package net.Ina0_.cubanol.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.Ina0_.cubanol.block.entity.CaskBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.inventory.InventoryMenu;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

public record CaskBlockEntityRenderer(BlockEntityRendererProvider.Context context) implements BlockEntityRenderer<CaskBlockEntity> {

    @Override
    public void render(@NotNull CaskBlockEntity blockEntity, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        FluidStack stack = blockEntity.tank.getFluid();
        float pixelWidth = 1.0f / 16;

        if(!stack.isEmpty()){
            VertexConsumer buffer = bufferSource.getBuffer(RenderType.TRANSLUCENT);
            IClientFluidTypeExtensions clientFluidType = IClientFluidTypeExtensions.of(stack.getFluid());
            TextureAtlasSprite sprite = Minecraft.getInstance().getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS).getSprite(clientFluidType.getStillTexture());

            double waterPixelLevel = Math.ceil(13.0 * stack.getAmount() / CaskBlockEntity.TANK_CAPACITY);
            float yLevel = (pixelWidth * 13 * stack.getAmount() / CaskBlockEntity.TANK_CAPACITY) + pixelWidth;

            if(waterPixelLevel <= 2 || waterPixelLevel >= 13){
                squareWithinBlockWithSize(buffer, poseStack.last(), pixelWidth * 5, pixelWidth * 11, yLevel, pixelWidth * 2,  pixelWidth * 3,  clientFluidType.getTintColor(), sprite, packedLight);
                squareWithinBlockWithSize(buffer, poseStack.last(), pixelWidth * 4, pixelWidth * 12, yLevel, pixelWidth * 3,  pixelWidth * 4,  clientFluidType.getTintColor(), sprite, packedLight);
                squareWithinBlockWithSize(buffer, poseStack.last(), pixelWidth * 3, pixelWidth * 13, yLevel, pixelWidth * 4,  pixelWidth * 5,  clientFluidType.getTintColor(), sprite, packedLight);
                squareWithinBlockWithSize(buffer, poseStack.last(), pixelWidth * 2, pixelWidth * 14, yLevel, pixelWidth * 5,  pixelWidth * 11, clientFluidType.getTintColor(), sprite, packedLight);
                squareWithinBlockWithSize(buffer, poseStack.last(), pixelWidth * 3, pixelWidth * 13, yLevel, pixelWidth * 11, pixelWidth * 12, clientFluidType.getTintColor(), sprite, packedLight);
                squareWithinBlockWithSize(buffer, poseStack.last(), pixelWidth * 4, pixelWidth * 12, yLevel, pixelWidth * 12, pixelWidth * 13, clientFluidType.getTintColor(), sprite, packedLight);
                squareWithinBlockWithSize(buffer, poseStack.last(), pixelWidth * 5, pixelWidth * 11, yLevel, pixelWidth * 13, pixelWidth * 14, clientFluidType.getTintColor(), sprite, packedLight);
            } else {
                squareWithinBlockWithSize(buffer, poseStack.last(), pixelWidth * 5, pixelWidth * 11, yLevel, pixelWidth * 1,  pixelWidth * 2,  clientFluidType.getTintColor(), sprite, packedLight);
                squareWithinBlockWithSize(buffer, poseStack.last(), pixelWidth * 3, pixelWidth * 13, yLevel, pixelWidth * 2,  pixelWidth * 3,  clientFluidType.getTintColor(), sprite, packedLight);
                squareWithinBlockWithSize(buffer, poseStack.last(), pixelWidth * 2, pixelWidth * 14, yLevel, pixelWidth * 3,  pixelWidth * 5,  clientFluidType.getTintColor(), sprite, packedLight);
                squareWithinBlockWithSize(buffer, poseStack.last(), pixelWidth * 1, pixelWidth * 15, yLevel, pixelWidth * 5,  pixelWidth * 11, clientFluidType.getTintColor(), sprite, packedLight);
                squareWithinBlockWithSize(buffer, poseStack.last(), pixelWidth * 2, pixelWidth * 14, yLevel, pixelWidth * 11, pixelWidth * 13, clientFluidType.getTintColor(), sprite, packedLight);
                squareWithinBlockWithSize(buffer, poseStack.last(), pixelWidth * 3, pixelWidth * 13, yLevel, pixelWidth * 13, pixelWidth * 14, clientFluidType.getTintColor(), sprite, packedLight);
                squareWithinBlockWithSize(buffer, poseStack.last(), pixelWidth * 5, pixelWidth * 11, yLevel, pixelWidth * 14, pixelWidth * 15,  clientFluidType.getTintColor(), sprite, packedLight);
            }
        }
    }

    private void squareWithinBlockWithSize(VertexConsumer buffer, PoseStack.Pose poseStack, float minX, float maxX, float y, float minZ, float maxZ, int tint, TextureAtlasSprite sprite, int light){
        float textureUSize = sprite.getU1() - sprite.getU0();
        float textureVSize = sprite.getV1() - sprite.getV0();

        float u0 = sprite.getU0() + textureUSize * minX;
        float u1 = sprite.getU0() + textureUSize * maxX;
        float v0 = sprite.getV0() + textureVSize * minZ;
        float v1 = sprite.getV0() + textureVSize * maxZ;

        square(buffer, poseStack, minX, maxX, y, minZ, maxZ, tint, u0, u1, v0, v1, light);
    }

    private void square(VertexConsumer buffer, PoseStack.Pose poseStack, float minX, float maxX, float y, float minZ, float maxZ, int tint, float u0, float u1, float v0, float v1, int light){
        vertex(buffer, poseStack, minX, y, minZ, tint, u0, v0, light, 0, 1, 0);
        vertex(buffer, poseStack, minX, y, maxZ, tint, u0, v1, light, 0, 1, 0);
        vertex(buffer, poseStack, maxX, y, maxZ, tint, u1, v1, light, 0, 1, 0);
        vertex(buffer, poseStack, maxX, y, minZ, tint, u1, v0, light, 0, 1, 0);
    }
    
    private void vertex(VertexConsumer buffer, PoseStack.Pose pose, float x, float y, float z, int tint, float u, float v, int light, float nx, float ny, float nz){
        buffer.addVertex(pose, x, y, z)
                .setColor(tint)
                .setUv(u, v)
                .setLight(light)
                .setNormal(pose, nx, ny, nz);
    }
}

package net.Ina0_.cubanol.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nullable;
import java.util.Objects;

public class CaskBlockEntity extends BlockEntity {

    public static final int TANK_CAPACITY = FluidType.BUCKET_VOLUME * 4;

    public final FluidTank tank = new FluidTank(TANK_CAPACITY, stack -> true){
        @Override
        protected void onContentsChanged() {
            setChanged();
            if(!Objects.requireNonNull(level).isClientSide()){
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public CaskBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.CASK_BLOCK_ENTITY.get(), pos, blockState);
    }

    public Fluid getFluid(){
        return tank.getFluid().getFluid();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("tank", new CompoundTag(1));
        tank.writeToNBT(registries, tag.getCompound("tank"));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        tank.readFromNBT(registries, tag.getCompound("tank"));
    }

    @Override
    @Nullable
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}

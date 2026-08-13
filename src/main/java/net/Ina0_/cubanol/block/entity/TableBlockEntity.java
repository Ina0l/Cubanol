package net.Ina0_.cubanol.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class TableBlockEntity extends BlockEntity {

    public final ItemStackHandler inventory = new ItemStackHandler(1){
        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!Objects.requireNonNull(level).isClientSide()){
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public TableBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.TABLE_BLOCK_ENTITY.get(), pos, blockState);
    }

    public void clearInventory(){
        inventory.extractItem(0, 1, false);
        inventory.insertItem(0, ItemStack.EMPTY, false);
    }

    public ItemStack getStack(){
        return inventory.getStackInSlot(0).copy();
    }

    public boolean hasItem(){
        return !this.getStack().isEmpty();
    }

    public void setItem(Item item){
        clearInventory();
        inventory.insertItem(0, new ItemStack(item), false);
    }

    public void dropContent(){
         SimpleContainer inv = new SimpleContainer(1);
         inv.setItem(0, inventory.getStackInSlot(0));
        Containers.dropContents(Objects.requireNonNull(this.level), this.getBlockPos(), inv);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
        return saveWithoutMetadata(registries);
    }
}

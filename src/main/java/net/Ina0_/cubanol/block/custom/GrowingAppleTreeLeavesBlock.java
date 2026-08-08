package net.Ina0_.cubanol.block.custom;

import net.Ina0_.cubanol.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.CommonHooks;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GrowingAppleTreeLeavesBlock extends LeavesBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    
    public GrowingAppleTreeLeavesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(AGE));
    }

    @Override
    protected void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (level.isAreaLoaded(pos, 1)) {
            if (level.getRawBrightness(pos, 0) >= 9) {
                int i = this.getAge(state);
                if (i < this.getMaxAge()) {
                    if (CommonHooks.canCropGrow(level, pos, state, random.nextInt((int)(25.0F / 10) + 1) == 0)) {
                        level.setBlock(pos, this.getStateForAge(i + 1), 2);
                        CommonHooks.fireCropGrowPost(level, pos, state);
                    }
                }
            }

        }
    }

    public void growCrops(Level level, BlockPos pos, BlockState state) {
        int i = this.getAge(state) + this.getBoneMealAgeIncrease(level);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        level.setBlock(pos, this.getStateForAge(i), 2);
    }

    public BlockState getStateForAge(int age) {
        return this.defaultBlockState().setValue(this.getAgeProperty(), age);
    }

    protected int getBoneMealAgeIncrease(Level level) {
        return Mth.nextInt(level.random, 2, 5);
    }

    @Override
    protected boolean isRandomlyTicking(@NotNull BlockState state) {
        return !this.isMaxAge(state);
    }
    
    public int getMaxAge(){
        return 7;
    }
    
    public IntegerProperty getAgeProperty(){
        return AGE;
    }

    public boolean isMaxAge(BlockState state){
        return this.getAge(state) >= this.getMaxAge();
    }
    
    public int getAge(BlockState state){
        return state.getValue(this.getAgeProperty());
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state) {
        return !this.isMaxAge(state);
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        this.growCrops(level, pos, state);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        if(this.isMaxAge(state)){
            if(!level.isClientSide()) {
                List<ItemStack> drops = state.getDrops(
                        new LootParams.Builder((ServerLevel) level)
                                .withParameter(LootContextParams.TOOL, player.getItemInHand(player.getUsedItemHand()))
                                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                );
                for(ItemStack drop: drops){
                    if(drop.is(Items.APPLE)){
                        ModBlocks.collectOrDropItems((ServerLevel) level, player, pos, List.of(drop));
                    }
                }
            }
            level.playLocalSound(player, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0f, 1.0f);
            level.setBlock(pos, state.setValue(AGE, 0), 3);
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }
}

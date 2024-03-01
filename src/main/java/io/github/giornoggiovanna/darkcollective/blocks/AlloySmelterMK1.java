package io.github.giornoggiovanna.darkcollective.blocks;

import io.github.giornoggiovanna.darkcollective.blockentity.AlloySmelterMK1Entity;
import io.github.giornoggiovanna.darkcollective.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AlloySmelterMK1 extends BaseEntityBlock {
    public AlloySmelterMK1(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return BlockEntityInit.ALLOY_SMELTERMK1_ENTITY.get().create(blockPos, blockState);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof AlloySmelterMK1Entity){
                ((AlloySmelterMK1Entity) blockEntity).drops();
            }
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if(level.isClientSide()) {
            return null;
        }


        return createTickerHelper(type, BlockEntityInit.ALLOY_SMELTERMK1_ENTITY.get(), (level1, pos, state1, blockEntity) -> blockEntity.tick(level1, pos, state1));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> GameEventListener getListener(ServerLevel p_221121_, T p_221122_) {
        return super.getListener(p_221121_, p_221122_);
    }

    @Override
    public boolean isEnabled(FeatureFlagSet p_249172_) {
        return super.isEnabled(p_249172_);
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HorizontalDirectionalBlock.FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult result) {
        if(!level.isClientSide())
        {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof AlloySmelterMK1Entity)
            {
                NetworkHooks.openScreen(((ServerPlayer)player), (AlloySmelterMK1Entity)entity, pos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}

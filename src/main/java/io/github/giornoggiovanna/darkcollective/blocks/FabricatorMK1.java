package io.github.giornoggiovanna.darkcollective.blocks;

import io.github.giornoggiovanna.darkcollective.blockentity.FabricatorMK1Entity;
import io.github.giornoggiovanna.darkcollective.blockentity.OreRefineryMK1Entity;
import io.github.giornoggiovanna.darkcollective.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class FabricatorMK1 extends BaseEntityBlock {
    private static final VoxelShape SHAPE = makeShape();

    public FabricatorMK1(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityInit.FABRICATORMK1_ENTITY.get().create(pos, state);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof FabricatorMK1Entity){
                ((FabricatorMK1Entity) blockEntity).drops();
            }
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
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
            if (entity instanceof FabricatorMK1Entity)
            {
                NetworkHooks.openScreen(((ServerPlayer)player), (FabricatorMK1Entity)entity, pos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if(level.isClientSide()) {
            return null;
        }


        return createTickerHelper(type, BlockEntityInit.FABRICATORMK1_ENTITY.get(), (level1, pos, state1, blockEntity) -> blockEntity.tick(level1, pos, state1));
    }

    public static VoxelShape makeShape(){
        VoxelShape SHAPE = Shapes.empty();
        SHAPE = Shapes.join(SHAPE, Shapes.box(0.0625, 0.5, -0.375, 0.125, 1, -0.3125), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Shapes.box(0, 0.5, 0, 1, 1, 1), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Shapes.box(0.0625, 0, 0, 0.9375, 0.5, 1), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Shapes.box(0.9375, 0, 0.25, 1, 0.4375, 0.75), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Shapes.box(0, 0, 0.25, 0.0625, 0.4375, 0.75), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Shapes.box(0.0625, 1, -0.875, 0.125, 1.0625, 0.125), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Shapes.box(0, 1, 0.1875, 1, 1.75, 0.3125), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Shapes.box(0.1875, 1.0625, 0.125, 0.8125, 1.6875, 0.1875), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Shapes.box(0.875, 1, -0.875, 0.9375, 1.0625, 0.125), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Shapes.box(0.875, 0.5, -0.375, 0.9375, 1, -0.3125), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Shapes.box(0.4375, 1, -0.875, 0.5625, 1.0625, 0.125), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Shapes.box(0.4375, 0.5, -0.375, 0.5625, 1, -0.3125), BooleanOp.OR);

        return SHAPE;
    }

}

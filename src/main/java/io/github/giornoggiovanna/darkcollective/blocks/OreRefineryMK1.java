package io.github.giornoggiovanna.darkcollective.blocks;

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
import net.minecraft.world.level.block.*;
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

    public class OreRefineryMK1 extends BaseEntityBlock{



        private static final VoxelShape SHAPE = makeShape();

        public OreRefineryMK1(BlockBehaviour.Properties properties) {
            super(properties);
        }

        @Override
        public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
            return SHAPE;
        }

        public static VoxelShape makeShape(){
            VoxelShape shape = Shapes.empty();
            shape = Shapes.join(shape, Shapes.box(-1, -0.4375, -1, 2, 0.1875, 2), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.1875, 0.1875, -1, 0.5, 1.375, 2), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.22283849461570304, 0.017284764347397008, -0.96875, 0.34783849461570304, 1.8125, 1.96875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0, 1.375, -1, 0.5, 1.5625, 2), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.34783849461570304, 0.07978476434739701, -0.96875, 1.160338494615703, 1.5, 1.96875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.5, 0.1875, -0.75, 2, 1.25, 1.75), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.75, 0.1875, 1.75, 1.625, 0.9375, 1.875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.75, 0.1875, -0.875, 1.625, 0.9375, -0.75), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.09783849461570304, 0.517284764347397, -0.59375, 0.22283849461570304, 1.0625, -0.28125), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.09783849461570304, 0.517284764347397, 1.28125, 0.22283849461570304, 1.0625, 1.59375), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.09783849461570304, 0.517284764347397, 0.34375, 0.22283849461570304, 1.0625, 0.65625), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.22283849461570304, 0.017284764347397008, -0.96875, 0.34783849461570304, 1.8125, 1.96875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(1.078125, 0.125, 0, 1.328125, 1.265625, 0.25), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(1.078125, 0.625, 0.75, 1.328125, 1.265625, 1), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.953125, 1.265625, -0.125, 1.453125, 1.34375, 0.375), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.953125, 1.265625, 0.59375, 1.453125, 1.34375, 1.09375), BooleanOp.OR);

            return shape;
        }


        @Nullable
        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return BlockEntityInit.ORE_REFINERYMK1_ENTITY.get().create(pos, state);
        }

        @Override
        public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
            if(state.getBlock() != newState.getBlock()){
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof OreRefineryMK1Entity){
                    ((OreRefineryMK1Entity) blockEntity).drops();
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
                if (entity instanceof OreRefineryMK1Entity)
                {
                    NetworkHooks.openScreen(((ServerPlayer)player), (OreRefineryMK1Entity)entity, pos);
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


            return createTickerHelper(type, BlockEntityInit.ORE_REFINERYMK1_ENTITY.get(), (level1, pos, state1, blockEntity) -> blockEntity.tick(level1, pos, state1));
        }
    }

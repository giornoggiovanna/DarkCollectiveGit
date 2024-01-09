package io.github.giornoggiovanna.darkcollective.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlackStandBlock extends HorizontalDirectionalBlock {

    private static final VoxelShape SHAPE = makeShape();


    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public BlackStandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return SHAPE;
    }

    public static VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.875, 0.625, 0.375, 0.9375, 0.8125, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0, 0.375, 0.9375, 0.1875, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0.875, 0.375, 0.8125, 0.9375, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.875, 0.375, 0.1875, 0.9375, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.4375, 0.3125, 0.4375, 0.75, 0.375, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.9375, 0, 0.375, 1, 0.9375, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.9375, 0.375, 1, 1, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.75, 0.4375, 0.3125, 0.8125, 0.5625), BooleanOp.OR);

        return shape;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

}

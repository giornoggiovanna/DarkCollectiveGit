package io.github.giornoggiovanna.darkcollective.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlackStandBlock extends Block {

    private static final VoxelShape NORTHSHAPE = makeNorthShape();
    public static final VoxelShape EASTSHAPE = makeEastShape();
    public static final VoxelShape SOUTHSHAPE = makeSouthShape();
    public static final VoxelShape WESTSHAPE = makeWestShape();

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public BlackStandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return NORTHSHAPE;
    }

    public static VoxelShape makeNorthShape(){
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

    public static VoxelShape makeEastShape(){
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

    public static VoxelShape makeSouthShape(){
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

    public static VoxelShape makeWestShape(){
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
}

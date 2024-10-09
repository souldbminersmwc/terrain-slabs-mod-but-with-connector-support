package net.countered.terrainslabs.block.customslabs.soilslabs;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;

public class MyceliumSlab extends SlabBlock {
    public MyceliumSlab(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(SlabBlock.TYPE, SlabType.BOTTOM)
                .with(SNOWY, false)
                .with(WATERLOGGED, Boolean.valueOf(false)));
    }

    public static final MapCodec<MyceliumSlab> CODEC = createCodec(MyceliumSlab::new);
    public static final BooleanProperty SNOWY = Properties.SNOWY;

    @Override
    public MapCodec<MyceliumSlab> getCodec() {
        return CODEC;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        // Handle snowy logic
        if (direction == Direction.UP) {
            state = state.with(SNOWY, isSnow(neighborState));
        }

        // Handle waterlogging logic
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        // Return the modified state based on the slab's existing logic
        return state;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        BlockState currentState = ctx.getWorld().getBlockState(blockPos);

        // Check if the block at the position is already a slab (and it's not a double slab yet)
        if (currentState.isOf(this) && currentState.get(TYPE) != SlabType.DOUBLE) {
            // Convert to a double slab if a slab is placed on an existing slab
            return currentState.with(TYPE, SlabType.DOUBLE).with(WATERLOGGED, false);
        }

        // Determine the snowy state based on the block above
        BlockState blockAbove = ctx.getWorld().getBlockState(blockPos.up());
        BlockState defaultState = this.getDefaultState().with(SNOWY, isSnow(blockAbove));

        // Handle slab type and waterlogging for single slab placement
        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
        BlockState slabState = defaultState.with(TYPE, SlabType.BOTTOM)
                .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);

        Direction direction = ctx.getSide();

        // Adjust the slab type based on the placement context (whether it's a top or bottom slab)
        if (direction != Direction.DOWN && (direction == Direction.UP || !(ctx.getHitPos().y - (double) blockPos.getY() > 0.5))) {
            return slabState;
        } else {
            return slabState.with(TYPE, SlabType.TOP);
        }
    }

    private static boolean isSnow(BlockState state) {
        return state.isIn(BlockTags.SNOW);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SNOWY, TYPE, WATERLOGGED);
    }

    private static boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.SNOW) && (Integer)blockState.get(SnowBlock.LAYERS) == 1) {
            return true;
        } else if (blockState.getFluidState().getLevel() == 8) {
            return false;
        } else {
            int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
            return i < world.getMaxLightLevel();
        }
    }

    private static boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return canSurvive(state, world, pos) && !world.getFluidState(blockPos).isIn(FluidTags.WATER);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if (random.nextInt(10) == 0) {
            world.addParticle(
                    ParticleTypes.MYCELIUM, (double)pos.getX() + random.nextDouble(), (double)pos.getY() + 1.1, (double)pos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0
            );
        }
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!canSurvive(state, world, pos)) {
            world.setBlockState(pos, Blocks.DIRT.getDefaultState());
        } else {
            if (world.getLightLevel(pos.up()) >= 9) {
                BlockState blockState = this.getDefaultState();

                for (int i = 0; i < 4; i++) {
                    BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (world.getBlockState(blockPos).isOf(Blocks.DIRT) && canSpread(blockState, world, blockPos)) {
                        world.setBlockState(blockPos, blockState.with(SNOWY, Boolean.valueOf(world.getBlockState(blockPos.up()).isOf(Blocks.SNOW))));
                    }
                }
            }
        }
    }
}
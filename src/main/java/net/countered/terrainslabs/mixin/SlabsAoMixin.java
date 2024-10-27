package net.countered.terrainslabs.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public class SlabsAoMixin {

    //@Inject(
    //        method = "getAmbientOcclusionLightLevel",
    //        at = @At("HEAD"), cancellable = true
    //)
    //private void adjustSlabAmbientOcclusion(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Float> ci) {
    //    if (state.getBlock() instanceof SlabBlock) {
//
    //        ci.setReturnValue(0.2F);
    //    }
    //}
}

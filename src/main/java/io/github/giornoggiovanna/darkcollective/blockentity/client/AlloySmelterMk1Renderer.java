package io.github.giornoggiovanna.darkcollective.blockentity.client;

import io.github.giornoggiovanna.darkcollective.blockentity.AlloySmelterMK1Entity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class AlloySmelterMk1Renderer extends GeoBlockRenderer<AlloySmelterMK1Entity> {

    public AlloySmelterMk1Renderer(BlockEntityRendererProvider.Context context) {
        super(new AlloySmelterMk1Model());
    }
}

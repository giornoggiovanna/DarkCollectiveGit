package io.github.giornoggiovanna.darkcollective.blockentity.client;

import io.github.giornoggiovanna.darkcollective.DarkCollective;
import io.github.giornoggiovanna.darkcollective.blockentity.AlloySmelterMK1Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AlloySmelterMk1Model extends GeoModel<AlloySmelterMK1Entity> {
    @Override
    public ResourceLocation getModelResource(AlloySmelterMK1Entity alloySmelterMK1Entity) {
        return new ResourceLocation(DarkCollective.ModID, "geo/alloy_smeltermk1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AlloySmelterMK1Entity alloySmelterMK1Entity) {
        return new ResourceLocation(DarkCollective.ModID, "textures/block/alloy_smeltermk1.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AlloySmelterMK1Entity alloySmelterMK1Entity) {
        return new ResourceLocation(DarkCollective.ModID, "animations/alloy_smeltermk1.animation.json");
    }
}

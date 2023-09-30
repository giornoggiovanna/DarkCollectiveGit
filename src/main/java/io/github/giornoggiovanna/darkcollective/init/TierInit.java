package io.github.giornoggiovanna.darkcollective.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class TierInit {
    public static final ForgeTier AERNIUM = new ForgeTier(
            6,
            3000,
            1.5f,
            2,
            40,
            TagInit.NEEDS_AERNIUM_TOOL,
            () -> Ingredient.of(ItemInit.AERNIUM_INGOT::get)
    );
}

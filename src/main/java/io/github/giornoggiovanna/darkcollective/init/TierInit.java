package io.github.giornoggiovanna.darkcollective.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class TierInit {

    public static final ForgeTier PLASMAMK1 = new ForgeTier(
            7,
            4000,
            2.0f,
            4,
            30,
            TagInit.NEEDS_PLASMA_TOOL,
            ()-> Ingredient.of(ItemInit.BASIC_ENERGY_CORE::get)
    );
}

package io.github.giornoggiovanna.darkcollective.recipe;

import io.github.giornoggiovanna.darkcollective.DarkCollective;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeInit {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create
            (ForgeRegistries.RECIPE_SERIALIZERS, DarkCollective.ModID);

    public static final RegistryObject<RecipeSerializer<OreRefineryRecipe>> ORE_REFINERY_SERIALIZER = SERIALIZERS.register
            ("ore_refining",
            ()-> OreRefineryRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<FabricatorRecipe>> FABRICATOR_SERIALIZER = SERIALIZERS.register
            ("fabricating",
            ()-> FabricatorRecipe.Serializer.INSTANCE);



}

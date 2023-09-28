package io.github.giornoggiovanna.darkcollective.init;

import io.github.giornoggiovanna.darkcollective.DarkCollective;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DarkCollective.ModID);

    public static final RegistryObject<Item> BASIC_ENERGY_CORE = ITEMS.register("basic_energy_core",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.COMMON)
            ));

    public static final RegistryObject<Item> ADVANCED_ENERGY_CORE = ITEMS.register("advanced_energy_core",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.UNCOMMON)
            ));

    public static final RegistryObject<Item> COMPLEX_ENERGY_CORE = ITEMS.register("complex_energy_core",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.RARE)
            ));

    public static final RegistryObject<Item> ULTIMATE_ENERGY_CORE = ITEMS.register("ultimate_energy_core",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.EPIC)
            ));

    public static final RegistryObject<Item> MOTHERSHIP_ENERGY_CORE = ITEMS.register("mothership_energy_core",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.EPIC)
            ));




    public static final RegistryObject<Item> PLASMA_BREAD = ITEMS.register("plasma_bread",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationMod(0.4f)
                            .effect(()-> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 2), 1)
                            .build()
                    )
            ));
}

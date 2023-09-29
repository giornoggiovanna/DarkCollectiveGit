package io.github.giornoggiovanna.darkcollective.init;

import io.github.giornoggiovanna.darkcollective.DarkCollective;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static io.github.giornoggiovanna.darkcollective.init.CreativeTabInit.addToTab;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DarkCollective.ModID);

    //Cores
    public static final RegistryObject<Item> BASIC_ENERGY_CORE = addToTab(ITEMS.register("basic_energy_core",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.COMMON)
            )));

    public static final RegistryObject<Item> ADVANCED_ENERGY_CORE = addToTab(ITEMS.register("advanced_energy_core",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.UNCOMMON)
            )));

    public static final RegistryObject<Item> COMPLEX_ENERGY_CORE = addToTab(ITEMS.register("complex_energy_core",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.RARE)
            )));

    public static final RegistryObject<Item> ULTIMATE_ENERGY_CORE = addToTab(ITEMS.register("ultimate_energy_core",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.EPIC)
            )));

    public static final RegistryObject<Item> MOTHERSHIP_ENERGY_CORE = addToTab(ITEMS.register("mothership_energy_core",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.EPIC)
            )));



    //Foods
    public static final RegistryObject<Item> PLASMA_BREAD = addToTab(ITEMS.register("plasma_bread",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationMod(0.4f)
                            .effect(()-> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 2), 1)
                            .build()
                    )
            )));

    //Ingots
    public static final RegistryObject<Item> AERNIUM_INGOT = addToTab(ITEMS.register("aernium_ingot",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
                    .fireResistant()
            )));

    //Generation Blocks
    public static final RegistryObject<BlockItem> METEORITE_STONE = addToTab(ITEMS.register("meteorite_stone",
            ()-> new BlockItem(BlockInit.METEORITE_STONE.get(), new Item.Properties()
                    .fireResistant()
            )));

    //Building Blocks
    public static final RegistryObject<BlockItem> METEORITE_COBBLESTONE = addToTab(ITEMS.register("meteorite_cobblestone",
            ()-> new BlockItem(BlockInit.METEORITE_COBBLESTONE.get(), new Item.Properties()
                    .fireResistant()
            )));

    //Ores
    public static final RegistryObject<BlockItem> AERNIUM_ORE = addToTab(ITEMS.register("aernium_ore",
            ()-> new BlockItem(BlockInit.AERNIUM_ORE.get(), new Item.Properties()
                    .fireResistant()
            )));
}
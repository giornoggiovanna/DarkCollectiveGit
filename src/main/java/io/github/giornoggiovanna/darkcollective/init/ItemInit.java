package io.github.giornoggiovanna.darkcollective.init;

import io.github.giornoggiovanna.darkcollective.DarkCollective;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
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

    public static final RegistryObject<Item> ADVINTINIUM_GEMS = addToTab(ITEMS.register("advintinium_gems",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
                    .fireResistant()
            )));

    public static final RegistryObject<Item> ATRIVIUM_DUST = addToTab(ITEMS.register("atrivium_dust",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
            )));

    public static final RegistryObject<BottleItem> BOTTLE_ACTRIVIUM = addToTab(ITEMS.register("bottle_actrivium",
            ()-> new BottleItem(new Item.Properties()
                    .stacksTo(16)
            )));

    public static final RegistryObject<Item> TRIVINDIUM_CRYSTAL = addToTab(ITEMS.register("trivindium_crystal",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
                    .fireResistant()
            )));

    //Weapon Materials
    public static final RegistryObject<Item> METEORITE_GUARD = addToTab(ITEMS.register("meteorite_guard",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
                    .fireResistant()
            )));

    public static final RegistryObject<Item> METEORITE_HANDLE = addToTab(ITEMS.register("meteorite_handle",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
                    .fireResistant()
            )));

    public static final RegistryObject<Item> METEORITE_STOCK = addToTab(ITEMS.register("meteorite_stock",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
                    .fireResistant()
            )));

    public static final RegistryObject<Item> PLASMA_CONCENTRATOR = addToTab(ITEMS.register("plasma_concentrator",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
                    .fireResistant()
            )));

    public static final RegistryObject<Item> PLASMA_EMISSION_BLADE = addToTab(ITEMS.register("plasma_emission_blade",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
                    .fireResistant()
            )));

    public static final RegistryObject<Item> PLASMA_EMISSION_HEAD = addToTab(ITEMS.register("plasma_emission_head",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
                    .fireResistant()
            )));

    public static final RegistryObject<Item> PLASMA_EMITTER = addToTab(ITEMS.register("plasma_emitter",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
                    .fireResistant()
            )));

    //Swords
    public static final RegistryObject<SwordItem> AERNIUM_SWORD = addToTab(ITEMS.register("aernium_sword",
            ()-> new SwordItem(
                    TierInit.AERNIUM,
                    13,
                    2.5f,
                     new Item.Properties()
            )));

    //Pickaxes
    public static final RegistryObject<PickaxeItem> AERNIUM_PICKAXE = addToTab(ITEMS.register("aernium_pickaxe",
            ()-> new PickaxeItem(
                    TierInit.AERNIUM,
                    5,
                    2.5f,
                     new Item.Properties()
            )));

    //Axes
    public static final RegistryObject<AxeItem> AERNIUM_AXE = addToTab(ITEMS.register("aernium_axe",
            ()-> new AxeItem(
                    TierInit.AERNIUM,
                    16,
                    2.5f,
                     new Item.Properties()
            )));

    //Hoes
    public static final RegistryObject<HoeItem> AERNIUM_HOE = addToTab(ITEMS.register("aernium_hoe",
            ()-> new HoeItem(
                    TierInit.AERNIUM,
                    5,
                    2.5f,
                     new Item.Properties()
            )));

    //Shovels
    public static final RegistryObject<ShovelItem> AERNIUM_SHOVEL = addToTab(ITEMS.register("aernium_shovel",
            ()-> new ShovelItem(
                    TierInit.AERNIUM,
                    5,
                    2.5f,
                     new Item.Properties()
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

    public static final RegistryObject<BlockItem> ACTRIVIUM_ORE = addToTab(ITEMS.register("actrivium_ore",
            ()-> new BlockItem(BlockInit.AERNIUM_ORE.get(), new Item.Properties()
                    .fireResistant()
            )));

    public static final RegistryObject<BlockItem> ATRIVIUM_ORE = addToTab(ITEMS.register("atrivium_ore",
            ()-> new BlockItem(BlockInit.AERNIUM_ORE.get(), new Item.Properties()
                    .fireResistant()
            )));

    public static final RegistryObject<BlockItem> TRIVINDIUM_ORE = addToTab(ITEMS.register("trivindium_ore",
            ()-> new BlockItem(BlockInit.AERNIUM_ORE.get(), new Item.Properties()
                    .fireResistant()
            )));

    public static final RegistryObject<BlockItem> AERNIUM_BLOCK = addToTab(ITEMS.register("aernium_block",
            ()-> new BlockItem(BlockInit.AERNIUM_ORE.get(), new Item.Properties()
                    .fireResistant()
            )));
}

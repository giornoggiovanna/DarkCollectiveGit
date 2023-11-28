package io.github.giornoggiovanna.darkcollective.init;

import io.github.giornoggiovanna.darkcollective.DarkCollective;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
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

    //Misc
    public static final RegistryObject<Item> COMPACT_COAL = addToTab(ITEMS.register("compact_coal",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
            )));

    public static final RegistryObject<Item> RIFTING_FLUX = addToTab(ITEMS.register("rifting_flux",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
                    .rarity(Rarity.EPIC)
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

    public static final RegistryObject<Item> RAW_TITANITE = addToTab(ITEMS.register("raw_titanite",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
            )));

    public static final RegistryObject<Item> TITANIUM_INGOT = addToTab(ITEMS.register("titanium_ingot",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
            )));

    public static final RegistryObject<Item> STEEL_INGOT = addToTab(ITEMS.register("steel_ingot",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
            )));

    public static final RegistryObject<Item> TITANIUM_NUGGET = addToTab(ITEMS.register("titanium_nugget",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
            )));

    public static final RegistryObject<Item> STEEL_NUGGET = addToTab(ITEMS.register("steel_nugget",
            ()-> new Item(new Item.Properties()
                    .stacksTo(64)
            )));

    //Canisters
    public static final RegistryObject<Item> BIOMASS_CANISTER = addToTab(ITEMS.register("biomass_canister",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.COMMON)
            )));

    public static final RegistryObject<Item> LAVA_CANISTER = addToTab(ITEMS.register("lava_canister",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.COMMON)
            )));

    public static final RegistryObject<Item> WATER_CANISTER = addToTab(ITEMS.register("water_canister",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.COMMON)
            )));

    public static final RegistryObject<Item> XP_CANISTER = addToTab(ITEMS.register("xp_canister",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.COMMON)
            )));

    public static final RegistryObject<Item> PLASMA_CANISTER = addToTab(ITEMS.register("plasma_canister",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.COMMON)
            )));

    public static final RegistryObject<Item> EMPTY_CANISTER = addToTab(ITEMS.register("empty_canister",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.COMMON)
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

    public static final RegistryObject<SwordItem> PLASMA_SWORDMK1 = addToTab(ITEMS.register("plasma_swordmk1",
            ()-> new SwordItem(
                    TierInit.PLASMAMK1,
                    12,
                    2.5f,
                    new Item.Properties()
            )));
    //Pickaxes

    public static final RegistryObject<PickaxeItem> PLASMA_PICKAXEMK1 = addToTab(ITEMS.register("plasma_pickaxemk1",
            ()-> new PickaxeItem(
                    TierInit.PLASMAMK1,
                    4,
                    2.5f,
                    new Item.Properties()
            )));

    //Axes

    public static final RegistryObject<AxeItem> PLASMA_AXEMK1 = addToTab(ITEMS.register("plasma_axemk1",
            ()-> new AxeItem(
                    TierInit.PLASMAMK1,
                    14,
                    0.9f,
                    new Item.Properties()
            )));

    //Hoes

    public static final RegistryObject<HoeItem> PLASMA_HOEMK1 = addToTab(ITEMS.register("plasma_hoemk1",
            ()-> new HoeItem(
                    TierInit.PLASMAMK1,
                    4,
                    2.5f,
                    new Item.Properties()
            )));

    //Shovels

    public static final RegistryObject<ShovelItem> PLASMA_SHOVELMK1 = addToTab(ITEMS.register("plasma_shovelmk1",
            ()-> new ShovelItem(
                    TierInit.PLASMAMK1,
                    4,
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

    public static final RegistryObject<BlockItem> BLACK_STAND = addToTab(ITEMS.register("black_stand",
            ()-> new BlockItem(BlockInit.BLACK_STAND.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> ORE_REFINERYMK1 = addToTab(ITEMS.register("ore_refinerymk1",
            ()-> new BlockItem(BlockInit.ORE_REFINERYMK1.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> MOTHERSHIP_DARKTILES = addToTab(ITEMS.register("mothership_darktiles",
            ()-> new BlockItem(BlockInit.MOTHERSHIP_DARKTILES.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> MOTHERSHIP_DARKTILES2 = addToTab(ITEMS.register("mothership_darktiles2",
            ()-> new BlockItem(BlockInit.MOTHERSHIP_DARKTILES2.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> MOTHERSHIP_WHITEBRICKS = addToTab(ITEMS.register("mothership_whitebricks",
            ()-> new BlockItem(BlockInit.MOTHERSHIP_WHITEBRICKS .get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> WHITE_BRICK_LAMP = addToTab(ITEMS.register("white_brick_lamp",
            ()-> new BlockItem(BlockInit.WHITE_BRICK_LAMP.get(), new Item.Properties())));




    //Ores
    public static final RegistryObject<BlockItem> AERNIUM_ORE = addToTab(ITEMS.register("aernium_ore",
            ()-> new BlockItem(BlockInit.AERNIUM_ORE.get(), new Item.Properties()
                    .fireResistant()
            )));

    public static final RegistryObject<BlockItem> ACTRIVIUM_ORE = addToTab(ITEMS.register("actrivium_ore",
            ()-> new BlockItem(BlockInit.ACTRIVIUM_ORE.get(), new Item.Properties()
                    .fireResistant()
            )));

    public static final RegistryObject<BlockItem> ATRIVIUM_ORE = addToTab(ITEMS.register("atrivium_ore",
            ()-> new BlockItem(BlockInit.ATRIVIUM_ORE.get(), new Item.Properties()
                    .fireResistant()
            )));

    public static final RegistryObject<BlockItem> TRIVINDIUM_ORE = addToTab(ITEMS.register("trivindium_ore",
            ()-> new BlockItem(BlockInit.TRIVINDIUM_ORE.get(), new Item.Properties()
                    .fireResistant()
            )));

    public static final RegistryObject<BlockItem> AERNIUM_BLOCK = addToTab(ITEMS.register("aernium_block",
            ()-> new BlockItem(BlockInit.AERNIUM_BLOCK.get(), new Item.Properties()
                    .fireResistant()
            )));

    public static final RegistryObject<BlockItem> TITANIUM_ORE = addToTab(ITEMS.register("titanium_ore",
            ()-> new BlockItem(BlockInit.TITANIUM_ORE.get(), new Item.Properties()
            )));

    public static final RegistryObject<BlockItem> TITANIUM_BLOCK = addToTab(ITEMS.register("titanium_block",
            ()-> new BlockItem(BlockInit.TITANIUM_BLOCK.get(), new Item.Properties()
            )));

    public static final RegistryObject<BlockItem> STEEL_BLOCK = addToTab(ITEMS.register("steel_block",
            ()-> new BlockItem(BlockInit.STEEL_BLOCK.get(), new Item.Properties()
            )));


}

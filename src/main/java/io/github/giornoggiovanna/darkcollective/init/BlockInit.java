package io.github.giornoggiovanna.darkcollective.init;

import io.github.giornoggiovanna.darkcollective.DarkCollective;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DarkCollective.ModID);

    public static final RegistryObject<Block> METEORITE_STONE = BLOCKS.register("meteorite_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
            .mapColor(MapColor.COLOR_GRAY)
            .strength(70f, 2400f)
            .instrument(NoteBlockInstrument.BASS)
            .requiresCorrectToolForDrops()
            .pushReaction(PushReaction.IGNORE)
    ));

    public static final RegistryObject<Block> METEORITE_COBBLESTONE = BLOCKS.register("meteorite_cobblestone", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GRAY)
            .strength(40f, 1200f)
            .instrument(NoteBlockInstrument.BASS)
            .requiresCorrectToolForDrops()
            .pushReaction(PushReaction.IGNORE)
    ));

    public static final RegistryObject<Block> AERNIUM_ORE = BLOCKS.register("aernium_ore", ()-> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GRAY)
            .strength(55f, 3000f)
            .requiresCorrectToolForDrops()
            .pushReaction(PushReaction.IGNORE)
    ));

    public static final RegistryObject<Block> ACTRIVIUM_ORE = BLOCKS.register("actrivium_ore", ()-> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GRAY)
            .strength(30f, 1200f)
            .requiresCorrectToolForDrops()
            .pushReaction(PushReaction.IGNORE)
    ));

    public static final RegistryObject<Block> ADVINTINIUM_ORE = BLOCKS.register("advintinium_ore", ()-> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GRAY)
            .strength(70f, 4000f)
            .requiresCorrectToolForDrops()
            .pushReaction(PushReaction.IGNORE)
    ));

    public static final RegistryObject<Block> ATRIVIUM_ORE = BLOCKS.register("atrivium_ore", ()-> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GRAY)
            .strength(35f, 2500f)
            .requiresCorrectToolForDrops()
            .pushReaction(PushReaction.IGNORE)
    ));

    public static final RegistryObject<Block> TRIVINDIUM_ORE = BLOCKS.register("trivindium_ore", ()-> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GRAY)
            .strength(60f, 3500f)
            .requiresCorrectToolForDrops()
            .pushReaction(PushReaction.IGNORE)
    ));

    public static final RegistryObject<Block> AERNIUM_BLOCK = BLOCKS.register("aernium_block", ()-> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GRAY)
            .strength(30f, 1200f)
            .requiresCorrectToolForDrops()
            .pushReaction(PushReaction.IGNORE)
    ));
}

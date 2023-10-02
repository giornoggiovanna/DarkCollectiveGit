package io.github.giornoggiovanna.darkcollective.init;

import io.github.giornoggiovanna.darkcollective.DarkCollective;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;


public class TagInit {

    public static final TagKey<Block> NEEDS_AERNIUM_TOOL = tag("needs_aernium_tool");
    public static final TagKey<Block> NEEDS_PLASMA_TOOL = tag("needs_plasma_tool");

    private static TagKey<Block> tag(String name)
    {
        return BlockTags.create(new ResourceLocation(DarkCollective.ModID, name));
    }

}

package io.github.giornoggiovanna.darkcollective.init;

import io.github.giornoggiovanna.darkcollective.DarkCollective;
import io.github.giornoggiovanna.darkcollective.blockentity.FabricatorMK1Entity;
import io.github.giornoggiovanna.darkcollective.blockentity.OreRefineryMK1Entity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DarkCollective.ModID);

    public static final RegistryObject<BlockEntityType<OreRefineryMK1Entity>> ORE_REFINERYMK1_ENTITY = BLOCK_ENTITIES.register("ore_refinerymk1_entity",
            ()-> BlockEntityType.Builder.of(OreRefineryMK1Entity::new, BlockInit.ORE_REFINERYMK1.get())
            .build(null)
    );

    public static final RegistryObject<BlockEntityType<FabricatorMK1Entity>> FABRICATORMK1_ENTITY = BLOCK_ENTITIES.register("fabricatormk1_entity",
            ()-> BlockEntityType.Builder.of(FabricatorMK1Entity::new, BlockInit.FABRICATORMK1.get())
                    .build(null)
    );
}

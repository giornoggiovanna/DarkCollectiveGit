package io.github.giornoggiovanna.darkcollective.init;

import io.github.giornoggiovanna.darkcollective.DarkCollective;
import io.github.giornoggiovanna.darkcollective.entity.Harvester;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DarkCollective.ModID);

    public static final RegistryObject<EntityType<Harvester>> HARVESTER = ENTITIES.register("harvester",
            () -> EntityType.Builder.<Harvester>of(Harvester::new, MobCategory.CREATURE)
                    .canSpawnFarFromPlayer()
                    .sized(1.5f, 3.0f)
                    .build(new ResourceLocation(DarkCollective.ModID, "harvester").toString())

    );

}

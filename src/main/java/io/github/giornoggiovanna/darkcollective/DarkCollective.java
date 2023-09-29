package io.github.giornoggiovanna.darkcollective;

import io.github.giornoggiovanna.darkcollective.init.BlockInit;
import io.github.giornoggiovanna.darkcollective.init.CreativeTabInit;
import io.github.giornoggiovanna.darkcollective.init.ItemInit;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DarkCollective.ModID)
public class DarkCollective {
    public static final String ModID = "darkcollective";

    public DarkCollective()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        CreativeTabInit.TABS.register(bus);
    }
}

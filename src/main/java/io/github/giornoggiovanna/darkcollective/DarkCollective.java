package io.github.giornoggiovanna.darkcollective;

import io.github.giornoggiovanna.darkcollective.init.BlockEntityInit;
import io.github.giornoggiovanna.darkcollective.init.BlockInit;
import io.github.giornoggiovanna.darkcollective.init.CreativeTabInit;
import io.github.giornoggiovanna.darkcollective.init.ItemInit;
import io.github.giornoggiovanna.darkcollective.screen.MenuTypes;
import io.github.giornoggiovanna.darkcollective.screen.OreRefineryScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.awt.image.BufferStrategy;

@Mod(DarkCollective.ModID)
public class DarkCollective {
    public static final String ModID = "darkcollective";

    public DarkCollective()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        CreativeTabInit.TABS.register(bus);
        BlockEntityInit.BLOCK_ENTITIES.register(bus);
        MenuTypes.MENUS.register(bus);
    }

    @Mod.EventBusSubscriber(modid = ModID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {


            MenuScreens.register(MenuTypes.OR_MENU.get(), OreRefineryScreen::new);
        }
    }
}

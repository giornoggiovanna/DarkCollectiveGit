package io.github.giornoggiovanna.darkcollective.screen;

import io.github.giornoggiovanna.darkcollective.DarkCollective;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;


public class MenuTypes{
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, DarkCollective.ModID);

    public static final RegistryObject<MenuType> OR_MENU = MENUS.register("or_menu", () -> IForgeMenuType.create(OreRefineryMenu::new));
}

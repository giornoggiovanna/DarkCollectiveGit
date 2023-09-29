package io.github.giornoggiovanna.darkcollective.init;

import io.github.giornoggiovanna.darkcollective.DarkCollective;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CreativeTabInit  {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DarkCollective.ModID);

    public static final List<Supplier<? extends ItemLike>> DARK_COLLECTIVE_TAB_ITEMS = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> DARK_COLLECTIVE_TAB = TABS.register("dark_collective_tab",
            ()-> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.dark_collective_tab"))
                    .icon(ItemInit.BASIC_ENERGY_CORE.get()::getDefaultInstance)
                    .displayItems((displayParams, output) -> {
                        DARK_COLLECTIVE_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get()));

                    })
                    .build()
    );

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> itemLike) {
        DARK_COLLECTIVE_TAB_ITEMS.add(itemLike);
        return itemLike;
    }

}

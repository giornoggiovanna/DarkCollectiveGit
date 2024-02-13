package io.github.giornoggiovanna.darkcollective.screen.slot;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class AlloySmelterFuelSlot extends SlotItemHandler {

    private final Predicate<ItemStack> fuelPredicate;
    public AlloySmelterFuelSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, Predicate<ItemStack> fuelPredicate) {
        super(itemHandler, index, xPosition, yPosition);
        this.fuelPredicate = fuelPredicate;
    }

    public AlloySmelterFuelSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition){
        this(itemHandler, index, xPosition, yPosition, itemStack -> ForgeHooks.getBurnTime(itemStack, RecipeType.BLASTING) > 0);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return stack.is(Items.LAVA_BUCKET);
    }
}

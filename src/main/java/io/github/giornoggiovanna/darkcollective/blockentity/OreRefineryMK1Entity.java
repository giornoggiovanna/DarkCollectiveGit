package io.github.giornoggiovanna.darkcollective.blockentity;

import com.sun.jna.platform.win32.Psapi;
import io.github.giornoggiovanna.darkcollective.DarkCollective;
import io.github.giornoggiovanna.darkcollective.blockentity.util.TickableBlockEntity;
import io.github.giornoggiovanna.darkcollective.init.BlockEntityInit;
import io.github.giornoggiovanna.darkcollective.init.ItemInit;
import io.github.giornoggiovanna.darkcollective.screen.OreRefineryMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.plaf.basic.BasicComboBoxUI;

public class OreRefineryMK1Entity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(3);

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    public static final int ENERGY_SLOT = 2;

    private LazyOptional<IItemHandler> handlerLazyOptional = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public OreRefineryMK1Entity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.ORE_REFINERYMK1_ENTITY.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                 return switch (index){
                     case 0 -> OreRefineryMK1Entity.this.progress;
                     case 1 -> OreRefineryMK1Entity.this.maxProgress;
                     default -> 0;
                 };
            }

            @Override
            public void set(int index, int value) {
                switch (index)
                {
                    case 0 -> OreRefineryMK1Entity.this.progress = value;
                    case 1 -> OreRefineryMK1Entity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return handlerLazyOptional.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        handlerLazyOptional.invalidate();
    }

    public void drops()
    {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.darkcollective.ore_refinerymk1");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new OreRefineryMenu(id, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("ore_refinerymk1.progress", progress);


        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
        progress = tag.getInt("ore_refinerymk1.progress");
    }


    public void tick(Level level, BlockPos pos, BlockState state) {
        if (hasRecipe()) {
            increaseCraftingProgress();
            setChanged(level, pos, state);

            if (hasProgressedFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        ItemStack result = new ItemStack(ItemInit.AERNIUM_INGOT.get(), 1);
        this.itemHandler.extractItem(INPUT_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasProgressedFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        boolean hasCraftingItem = this.itemHandler.getStackInSlot(INPUT_SLOT).getItem() == ItemInit.AERNIUM_ORE.get();
        ItemStack result = new ItemStack(ItemInit.AERNIUM_ORE.get());

        return hasCraftingItem && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
}
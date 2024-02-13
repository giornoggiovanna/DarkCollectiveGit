package io.github.giornoggiovanna.darkcollective.blockentity;

import io.github.giornoggiovanna.darkcollective.DarkCollective;
import io.github.giornoggiovanna.darkcollective.init.BlockEntityInit;
import io.github.giornoggiovanna.darkcollective.recipe.AlloySmelterRecipe;
import io.github.giornoggiovanna.darkcollective.screen.AlloySmelterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AlloySmelterMK1Entity extends BlockEntity implements MenuProvider {

    public static final int INPUT_SLOT1 = 0;
    public static final int INPUT_SLOT2 = 1;
    public static final int INPUT_SLOT3 = 2;
    public static final int OUTPUT_SLOT = 3;
    public static final int FUEL_SLOT = 4;

    private final ItemStackHandler itemHandler = new ItemStackHandler(5){
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            AlloySmelterMK1Entity.this.setChanged();
        }
    };

    private final LazyOptional<ItemStackHandler> inventoryOptional = LazyOptional.of(() -> this.itemHandler);

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;
    private int burnTime = 0, maxBurnTime = 0;


    public AlloySmelterMK1Entity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.ALLOY_SMELTERMK1_ENTITY.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index)
                {
                    case 0 -> AlloySmelterMK1Entity.this.progress;
                    case 1 -> AlloySmelterMK1Entity.this.maxProgress;
                    case 2 -> AlloySmelterMK1Entity.this.burnTime;
                    case 3 -> AlloySmelterMK1Entity.this.maxBurnTime;
                    default -> throw new UnsupportedOperationException("Unexpected Value!" + index);
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> AlloySmelterMK1Entity.this.progress = value;
                    case 1 -> AlloySmelterMK1Entity.this.maxProgress = value;
                    case 2 -> AlloySmelterMK1Entity.this.burnTime = value;
                    case 3 -> AlloySmelterMK1Entity.this.maxBurnTime = value;
                };

            }
            @Override
            public int getCount() {
                return 4;
            }
        };

    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return inventoryOptional.cast();
        }else {
            return super.getCapability(cap, side);
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        inventoryOptional.invalidate();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.darkcollective.alloysmeltermk1");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new AlloySmelterMenu(i, inventory, this, this.data);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);

    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);

        var darkCollectiveData = new CompoundTag();
        darkCollectiveData.put("Inventory", itemHandler.serializeNBT());
        darkCollectiveData.putInt("Alloy_smeltermk1.progress", progress);
        darkCollectiveData.putInt("BurnTime", this.burnTime);
        darkCollectiveData.putInt("MaxBurnTime", this.maxBurnTime);
        nbt.put(DarkCollective.ModID, darkCollectiveData);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);

        CompoundTag darkCollectiveData = nbt.getCompound(DarkCollective.ModID);
        if (darkCollectiveData.isEmpty()) return;

        if (darkCollectiveData.contains("Inventory", Tag.TAG_COMPOUND)){
            itemHandler.deserializeNBT(darkCollectiveData.getCompound("inventory"));
        }
        if (darkCollectiveData.contains("Alloy_smeltermk1.progress")) {
            progress = darkCollectiveData.getInt("Alloy_smeltermk1.progress");
        }
        if (darkCollectiveData.contains("BurnTime", Tag.TAG_INT)){
            this.burnTime = darkCollectiveData.getInt("BurnTime");
        }
        if (darkCollectiveData.contains("MaxBurnTime", Tag.TAG_INT)){
            this.maxBurnTime = darkCollectiveData.getInt("MaxBurnTime");
        }

    }

    public void tick(Level level1, BlockPos pos, BlockState state1) {

        if (hasRecipe() && this.burnTime > 0) {
            increaseCraftingProgress();
            setChanged(level, pos, state1);

            if (hasProgressedFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
        System.out.println("This is the burntime " +this.burnTime);
            if(this.burnTime <= 0){
                if (canBurn(itemHandler.getStackInSlot(FUEL_SLOT))){
                    this.maxBurnTime = getBurnTime(this.itemHandler.getStackInSlot(FUEL_SLOT));
                    this.burnTime = this.maxBurnTime;
                    this.itemHandler.getStackInSlot(FUEL_SLOT).shrink(1);
                    sendUpdate();
                }
            }else{
                this.burnTime--;

            }
        }

    private void sendUpdate() {
        setChanged();

        if(this.level != null){
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    public LazyOptional<ItemStackHandler> getInventoryOptional() {
        return this.inventoryOptional;
    }

    protected int getBurnTime(ItemStack stack){
        return ForgeHooks.getBurnTime(stack, RecipeType.SMELTING);
    }

    private boolean canBurn(ItemStack stackInSlot) {
        return getBurnTime(stackInSlot) > 0;
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        Optional<AlloySmelterRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());

        this.itemHandler.extractItem(INPUT_SLOT1, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT2, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT3, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));

    }

    private Optional<AlloySmelterRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(AlloySmelterRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean hasProgressedFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
        System.out.println("This is the current progress" + progress);
    }

    private boolean hasRecipe() {
        Optional<AlloySmelterRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()){
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());

        System.out.println(canInsertAmountIntoOutputSlot(result.getCount()));
        System.out.println(canInsertItemIntoOutputSlot(result.getItem()));

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
}

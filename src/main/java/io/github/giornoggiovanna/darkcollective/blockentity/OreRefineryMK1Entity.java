package io.github.giornoggiovanna.darkcollective.blockentity;

import io.github.giornoggiovanna.darkcollective.DarkCollective;
import io.github.giornoggiovanna.darkcollective.blockentity.util.CustomEnergyStorage;
import io.github.giornoggiovanna.darkcollective.init.BlockEntityInit;
import io.github.giornoggiovanna.darkcollective.recipe.OreRefineryRecipe;
import io.github.giornoggiovanna.darkcollective.screen.OreRefineryMenu;
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

public class OreRefineryMK1Entity extends BlockEntity implements MenuProvider {


    //Properties
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    public static final int ENERGY_SLOT = 2;

    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            OreRefineryMK1Entity.this.setChanged();
        }
    };

    private final LazyOptional<ItemStackHandler> inventoryOptional = LazyOptional.of(() -> this.itemHandler);

    private final CustomEnergyStorage energyStorage = new CustomEnergyStorage(10000, 100, 100, 0);
    private final LazyOptional<CustomEnergyStorage> energyOptional = LazyOptional.of(() -> this.energyStorage);

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;
    private int burnTime = 0, maxBurnTime = 0;
    private int energyReductionRate = 5;

    public OreRefineryMK1Entity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.ORE_REFINERYMK1_ENTITY.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                 return switch (index){
                     case 0 -> OreRefineryMK1Entity.this.progress;
                     case 1 -> OreRefineryMK1Entity.this.maxProgress;
                     case 2 -> OreRefineryMK1Entity.this.energyStorage.getEnergyStored();
                     case 3 -> OreRefineryMK1Entity.this.energyStorage.getMaxEnergyStored();
                     case 4 -> OreRefineryMK1Entity.this.burnTime;
                     case 5 -> OreRefineryMK1Entity.this.maxBurnTime;
                     default -> throw new UnsupportedOperationException("Unexpected Value!" + index);
                 };
            }

            @Override
            public void set(int index, int value) {
                switch (index)
                {
                    case 0 -> OreRefineryMK1Entity.this.progress = value;
                    case 1 -> OreRefineryMK1Entity.this.maxProgress = value;
                    case 2 -> OreRefineryMK1Entity.this.energyStorage.setEnergy(value);
                    case 4 -> OreRefineryMK1Entity.this.burnTime = value;
                    case 5 -> OreRefineryMK1Entity.this.maxBurnTime = value;
                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return inventoryOptional.cast();
        }else if (cap == ForgeCapabilities.ENERGY){
            return energyOptional.cast();
        }else {
            return super.getCapability(cap, side);
        }
    }

    //Override Methods
    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        inventoryOptional.invalidate();
        this.energyOptional.invalidate();
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
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new OreRefineryMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);

        var darkCollectiveData = new CompoundTag();
        darkCollectiveData.put("Inventory", itemHandler.serializeNBT());
        darkCollectiveData.putInt("Ore_refinerymk1.progress", progress);
        darkCollectiveData.put("Energy", this.energyStorage.serializeNBT());
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
        if (darkCollectiveData.contains("Ore_refinerymk1.progress")) {
            progress = darkCollectiveData.getInt("ore_refinerymk1.progress");
        }
        if (darkCollectiveData.contains("Energy", Tag.TAG_INT)){
            this.energyStorage.deserializeNBT(darkCollectiveData.get("Energy"));
        }
        if (darkCollectiveData.contains("BurnTime", Tag.TAG_INT)){
            this.burnTime = darkCollectiveData.getInt("BurnTime");
        }
        if (darkCollectiveData.contains("MaxBurnTime", Tag.TAG_INT)){
            this.maxBurnTime = darkCollectiveData.getInt("MaxBurnTime");
        }

    }


    public void tick(Level level, BlockPos pos, BlockState state) {
        if (hasRecipe() && hasEnergyToCraft(energyReductionRate, this.energyStorage.getEnergyStored())) {
            increaseCraftingProgress();
            setChanged(level, pos, state);
            this.energyStorage.removeEnergy(energyReductionRate);

            if (hasProgressedFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
        System.out.println("This is the burntime " +this.burnTime);
        if (this.energyStorage.getEnergyStored() < this.energyStorage.getMaxEnergyStored()){
            if(this.burnTime <= 0){
                if (canBurn(itemHandler.getStackInSlot(ENERGY_SLOT))){
                    this.maxBurnTime = getBurnTime(this.itemHandler.getStackInSlot(ENERGY_SLOT));
                    this.burnTime = this.maxBurnTime;
                    this.itemHandler.getStackInSlot(ENERGY_SLOT).shrink(1);
                    sendUpdate();
                }
            }else{
                this.burnTime--;
                this.energyStorage.addEnergy(5);

            }
        }
    }

    //Crafting
    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        Optional<OreRefineryRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());

        this.itemHandler.extractItem(INPUT_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasProgressedFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
        System.out.println("This is working");
    }

    private boolean hasRecipe() {
        Optional<OreRefineryRecipe> recipe = getCurrentRecipe();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<OreRefineryRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(OreRefineryRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    public LazyOptional<ItemStackHandler> getInventoryOptional() {
        return this.inventoryOptional;
    }

    //Energy
    public LazyOptional<CustomEnergyStorage> getEnergyOptional(){
        return this.energyOptional;
    }

    public CustomEnergyStorage getEnergyStorage()
    {
        return this.energyStorage;
    }

    private void sendUpdate(){
        setChanged();

        if(this.level != null){
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    protected int getBurnTime(ItemStack stack){
        return ForgeHooks.getBurnTime(stack, RecipeType.SMELTING);
    }

    protected boolean canBurn(ItemStack stackInSlot) {
        return getBurnTime(stackInSlot) > 0;
    }

    protected boolean hasEnergyToCraft(int eReducRate, int currentEnergy){
        return (currentEnergy >= eReducRate) ? true : false;
    }

}

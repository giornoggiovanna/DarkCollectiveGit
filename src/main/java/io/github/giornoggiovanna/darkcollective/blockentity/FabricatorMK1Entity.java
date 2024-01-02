package io.github.giornoggiovanna.darkcollective.blockentity;

import io.github.giornoggiovanna.darkcollective.DarkCollective;
import io.github.giornoggiovanna.darkcollective.blockentity.util.CustomEnergyStorage;
import io.github.giornoggiovanna.darkcollective.blocks.FabricatorMK1;
import io.github.giornoggiovanna.darkcollective.init.BlockEntityInit;
import io.github.giornoggiovanna.darkcollective.recipe.OreRefineryRecipe;
import io.github.giornoggiovanna.darkcollective.screen.FabricatorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
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
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FabricatorMK1Entity extends BlockEntity implements MenuProvider {
    

    //Properties
    public static final int INPUT_SLOT1 = 0;
    public static final int INPUT_SLOT2 = 1;
    public static final int INPUT_SLOT3 = 2;
    public static final int INPUT_SLOT4 = 3;
    public static final int INPUT_SLOT5 = 4;

    public static final int OUTPUT_SLOT = 5;
    public static final int ENERGY_SLOT = 6;

    private final ItemStackHandler itemHandler = new ItemStackHandler(7) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            FabricatorMK1Entity.this.setChanged();
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

    public FabricatorMK1Entity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityInit.FABRICATORMK1_ENTITY.get(), blockPos, blockState);

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> FabricatorMK1Entity.this.progress;
                    case 1 -> FabricatorMK1Entity.this.maxProgress;
                    case 2 -> FabricatorMK1Entity.this.energyStorage.getEnergyStored();
                    case 3 -> FabricatorMK1Entity.this.energyStorage.getMaxEnergyStored();
                    case 4 -> FabricatorMK1Entity.this.burnTime;
                    case 5 -> FabricatorMK1Entity.this.maxBurnTime;
                    default -> throw new UnsupportedOperationException("Unexpected Value!" + index);
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index)
                {
                    case 0 -> FabricatorMK1Entity.this.progress = value;
                    case 1 -> FabricatorMK1Entity.this.maxProgress = value;
                    case 2 -> FabricatorMK1Entity.this.energyStorage.setEnergy(value);
                    case 4 -> FabricatorMK1Entity.this.burnTime = value;
                    case 5 -> FabricatorMK1Entity.this.maxBurnTime = value;
                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }
    
    //Overrides

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

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        inventoryOptional.invalidate();
        this.energyOptional.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);

        var darkCollectiveData = new CompoundTag();
        darkCollectiveData.put("Fab1.Inventory", itemHandler.serializeNBT());
        darkCollectiveData.putInt("Fab1.Progress", progress);
        darkCollectiveData.put("Fab1.Energy", this.energyStorage.serializeNBT());
        darkCollectiveData.putInt("Fab1.BurnTime", this.burnTime);
        darkCollectiveData.putInt("Fab1.MaxBurnTime", this.maxBurnTime);
        nbt.put(DarkCollective.ModID, darkCollectiveData);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);

        CompoundTag darkCollectiveData = nbt.getCompound(DarkCollective.ModID);
        if (darkCollectiveData.isEmpty()) return;

        if (darkCollectiveData.contains("Fab1.Inventory", Tag.TAG_COMPOUND)){
            itemHandler.deserializeNBT(darkCollectiveData.getCompound("Fab1.Inventory"));
        }
        if (darkCollectiveData.contains("Fab1.Progress")) {
            progress = darkCollectiveData.getInt("Fab1.Progress");
        }
        if (darkCollectiveData.contains("Fab1.Energy", Tag.TAG_INT)){
            this.energyStorage.deserializeNBT(darkCollectiveData.get("Fab1.Energy"));
        }
        if (darkCollectiveData.contains("Fab1.BurnTime", Tag.TAG_INT)){
            this.burnTime = darkCollectiveData.getInt("Fab1.BurnTime");
        }
        if (darkCollectiveData.contains("Fab1.MaxBurnTime", Tag.TAG_INT)){
            this.maxBurnTime = darkCollectiveData.getInt("Fab1.MaxBurnTime");
        }

    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("Fabricator Mk1");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new FabricatorMenu(containerId, inventory, this, this.data);
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }


    public void drops()
    {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
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

        this.itemHandler.extractItem(INPUT_SLOT1, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT2, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT3, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT4, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT5, 1, false);

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

package io.github.giornoggiovanna.darkcollective.screen;

    import io.github.giornoggiovanna.darkcollective.blockentity.OreRefineryMK1Entity;
    import io.github.giornoggiovanna.darkcollective.init.BlockInit;
    import io.github.giornoggiovanna.darkcollective.screen.slot.EnergyFuelSlot;
    import net.minecraft.network.FriendlyByteBuf;
    import net.minecraft.world.entity.player.Inventory;
    import net.minecraft.world.entity.player.Player;
    import net.minecraft.world.inventory.*;
    import net.minecraft.world.item.ItemStack;
    import net.minecraft.world.level.Level;
    import net.minecraft.world.level.block.entity.BlockEntity;
    import net.minecraftforge.items.SlotItemHandler;

public class OreRefineryMenu extends AbstractContainerMenu {
    public OreRefineryMK1Entity blockEntity;
    private Level level;
    private ContainerData data;

    public OreRefineryMenu(int ContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(MenuTypes.OR_MENU.get(), ContainerId);
        checkContainerSize(inv, 3);
        blockEntity = ((OreRefineryMK1Entity) entity);
        this.level = inv.player.level();
        this.data = data;

        if(entity instanceof OreRefineryMK1Entity be) {
            this.blockEntity = be;
        } else {
            throw new IllegalStateException("Incorrect block entity class (%s) passed into ExampleMenu!"
                    .formatted(blockEntity.getClass().getCanonicalName()));
        }

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        createBlockEntityInventory(be);

        addDataSlots(data);
    }


    private void createBlockEntityInventory(OreRefineryMK1Entity be) {
        be.getInventoryOptional().ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler, 0, 60, 17));
            this.addSlot(new SlotItemHandler(iItemHandler, 1, 116, 35));
            this.addSlot(new EnergyFuelSlot(iItemHandler, 2, 60, 53));
        });
    }


    public OreRefineryMenu(int id, Inventory inv, FriendlyByteBuf extraData){
        this(id, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(6));
    }



    public boolean isCrafting(){
        return data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 38; // This is the height in pixels of your arrow


        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 3;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, BlockInit.ORE_REFINERYMK1.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public int getEnergy(){
        return this.data.get(2);
    }

    public int getMaxEnergy(){
        return this.data.get(3);
    }

    public int getBurnTime(){
        return this.data.get(4);
    }

    public int getMaxBurnTime(){
        return this.data.get(5);
    }

    public int getEnergyStoredScale(){
        return (int) (((float)getEnergy()/(float)getMaxEnergy()) * 15);
    }
}



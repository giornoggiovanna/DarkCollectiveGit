package io.github.giornoggiovanna.darkcollective.blockentity.util;

import net.minecraftforge.energy.EnergyStorage;

public class CustomEnergyStorage extends EnergyStorage {
    public CustomEnergyStorage(int capacity) {
        super(capacity);
    }

    public CustomEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    public void setEnergy(int energy){
        if(energy < 0)
            energy = 0;
        if(energy > this.capacity)
            this.energy = capacity;

        this.energy = energy;
    }

    public void addEnergy(int energy){
        setEnergy(this.energy + energy);
    }

    public void removeEnergy(int energy){
        setEnergy((this.energy - energy));
    }
}

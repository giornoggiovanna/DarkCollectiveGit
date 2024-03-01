package io.github.giornoggiovanna.darkcollective.entity;

import io.github.giornoggiovanna.darkcollective.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Harvester extends Monster implements NeutralMob {
    public Harvester(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public Harvester(Level level, double x, double y, double z){
        this(EntityInit.HARVESTER.get(), level);
        setPos(x, y, z);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return 0;
    }

    @Override
    public void setRemainingPersistentAngerTime(int i) {

    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return null;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID uuid) {

    }

    @Override
    public void startPersistentAngerTimer() {

    }

    @Nullable
    @Override
    public LivingEntity getLastHurtByMob() {
        return null;
    }

    @Override
    public void setLastHurtByMob(@Nullable LivingEntity livingEntity) {

    }

    @Override
    public void setLastHurtByPlayer(@Nullable Player player) {

    }

    @Override
    public void setTarget(@Nullable LivingEntity livingEntity) {

    }

    @Override
    public boolean canAttack(LivingEntity livingEntity) {
        return false;
    }

    @Nullable
    @Override
    public LivingEntity getTarget() {
        return null;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 10.0f, 5.0f, 5.0f));
        /*this.goalSelector.addGoal(3, new MoveToBlockGoal(this, ) {
            @Override
            protected boolean isValidTarget(LevelReader levelReader, BlockPos blockPos) {
                return false;
            }
        });

         */
    }
}

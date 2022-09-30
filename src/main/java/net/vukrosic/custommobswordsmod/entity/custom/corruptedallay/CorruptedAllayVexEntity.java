package net.vukrosic.custommobswordsmod.entity.custom.corruptedallay;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.entity.ModEntities;

public class CorruptedAllayVexEntity extends VexEntity {
    boolean visible = false;
    int xp = 0;
    public CorruptedAllayVexEntity(EntityType<? extends VexEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        if(visible){
            CorruptedAllayAllayEntity allay = new CorruptedAllayAllayEntity(ModEntities.CORRUPTED_ALLAY_ALLAY, world);
            allay.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0, 0);
            world.spawnEntity(allay);
            this.discard();
        }
        checkIfHuntersSee();
        super.tick();
    }

    void checkIfHuntersSee() {
        if (SetHunterCommand.hunters != null) {
            SetHunterCommand.hunters.forEach(hunter -> {
                if(hunter.canSee(this)) {
                    visible = true;
                }
            });
        }
    }

    //if this entity scores a kill
    //spawn a new allay entity


    @Override
    public boolean onKilledOther(ServerWorld world, LivingEntity other) {
        xp += other.getXpToDrop();
        if(xp >= 10){
            //send message into chat
            world.getPlayers().get(0).sendMessage(Text.of("You gained " + other.getXpToDrop() + " xp corrupted allay entities!"), false);
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1000000, 0, false, false));
        }
        return super.onKilledOther(world, other);
    }


}

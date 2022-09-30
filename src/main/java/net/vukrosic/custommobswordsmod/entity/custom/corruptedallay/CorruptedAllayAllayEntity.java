package net.vukrosic.custommobswordsmod.entity.custom.corruptedallay;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.entity.ModEntities;

public class CorruptedAllayAllayEntity extends AllayEntity {
    boolean isAllay = false;
    int xp = 0;
    public CorruptedAllayAllayEntity(EntityType<? extends AllayEntity> entityType, World world) {
        super(entityType, world);
    }


    boolean visible = true;


    @Override
    public void tick() {
        if(!visible){
            CorruptedAllayVexEntity vex = new CorruptedAllayVexEntity(ModEntities.CORRUPTED_ALLAY_VEX, world);
            vex.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0, 0);
            world.spawnEntity(vex);
            this.discard();
        }
        checkIfHuntersSee();
        super.tick();
    }

    void checkIfHuntersSee() {
        if (SetHunterCommand.hunters != null) {
            for (int i = 0; i < SetHunterCommand.hunters.size(); i++) {
                if(SetHunterCommand.hunters.get(i).canSee(this)) {
                    isAllay = true;
                    break;
                }
                else {
                    isAllay = false;
                }
            }
        }
    }


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

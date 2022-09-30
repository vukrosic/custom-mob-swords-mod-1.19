package net.vukrosic.custommobswordsmod.entity.custom;

import net.vukrosic.custommobswordsmod.entity.custom.frogking.FrogKingEntity;

public interface PlayerEntityExt {
    boolean fireInfected = false;
    FrogKingEntity kingFrogEntity = null;
    ShieldingShulkerEntity shieldingShulkerEntity = null;

    void setShieldingShulkerEntity(ShieldingShulkerEntity shieldingShulkerEntity);

    void setFrogKingEntity(FrogKingEntity frogKingEntity);
    FrogKingEntity getFrogKingEntity();


    void setFireInfected(boolean fireInfected);

    boolean getFireInfected();
}

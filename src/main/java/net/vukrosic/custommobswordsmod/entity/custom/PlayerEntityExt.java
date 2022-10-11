package net.vukrosic.custommobswordsmod.entity.custom;

import net.vukrosic.custommobswordsmod.entity.custom.frogking.FrogKingEntity;
import net.vukrosic.custommobswordsmod.entity.custom.summoner.SummonerEntityGL;

public interface PlayerEntityExt {

    SummonerEntityGL summonerEntityGL = null;
    boolean fireInfected = false;
    FrogKingEntity kingFrogEntity = null;
    ShieldingShulkerEntity shieldingShulkerEntity = null;
    public boolean hasChickenEffect = true;
    boolean isInChickenDimention = false;

    void setSummonerEntityGL(SummonerEntityGL summonerEntityGL);
    SummonerEntityGL getSummonerEntityGL();

    void setInChickenDimention(boolean isInChickenDimention);
    boolean isInChickenDimention();

    void setShieldingShulkerEntity(ShieldingShulkerEntity shieldingShulkerEntity);
    ShieldingShulkerEntity getShieldingShulkerEntity();

    void SummonShieldingShulker();

    void setFrogKingEntity(FrogKingEntity frogKingEntity);
    FrogKingEntity getFrogKingEntity();

    boolean hasChickenEffect();


    void setFireInfected(boolean fireInfected);

    boolean getFireInfected();
}

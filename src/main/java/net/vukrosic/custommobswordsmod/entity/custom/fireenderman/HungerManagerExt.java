package net.vukrosic.custommobswordsmod.entity.custom.fireenderman;

import net.minecraft.entity.player.PlayerEntity;

public interface HungerManagerExt {
    boolean hasCombusometerEffect();
    void setCombusometerEffect(boolean hasCombusometerEffect);

    void setOwner();

    void setOwnerName(String ownerName);
}

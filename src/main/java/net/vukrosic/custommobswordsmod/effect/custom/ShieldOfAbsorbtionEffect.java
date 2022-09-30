package net.vukrosic.custommobswordsmod.effect.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.text.Text;
import net.minecraft.util.MetricsData;

public class ShieldOfAbsorbtionEffect extends StatusEffect {
    public ShieldOfAbsorbtionEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    // draw bar on screen


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        MinecraftClient client = MinecraftClient.getInstance();
        // get clien't matrixstack
        // get client's vertexconsumerprovider
        // render boss bar

        // send message
        client.player.sendMessage(Text.of("You have Shield of Absorbtion!"), false);
/*
        BossBar bossBar = new BossBar(client.player.getUuid(), Text.of ("text.fabric_mod_development_kit.boss_bar") , BossBar.Color.BLUE, BossBar.Style.PROGRESS);
        client.inGameHud.getBossBarHud().

        bossBar.setPercent(0.5f);

        client.inGameHud.getBossBarHud().addBoss(bossBar);

        client.inGameHud.getBossBarHud().render(0, 0, 0);
        client.setScreen(null);

        client.inGameHud.getBossBarHud();*/
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}

package net.vukrosic.custommobswordsmod.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.vukrosic.custommobswordsmod.item.ModItems;
import net.vukrosic.custommobswordsmod.util.FireInfectedPlayers;
import net.vukrosic.custommobswordsmod.util.GettingEatenByChunkenManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {
    @Shadow
    private ItemRenderer itemRenderer;
    @Shadow
    private MinecraftClient client;


    @Inject(method = "renderHotbarItem", at = @At("HEAD"))
    private void renderHotbar(int x, int y, float tickDelta, PlayerEntity player, ItemStack stack, int seed, CallbackInfo ci) {

        // black screen if player is being eaten by frog
        if(GettingEatenByChunkenManager.player != null && GettingEatenByChunkenManager.player.equals(player)) {
            Screen screen = MinecraftClient.getInstance().currentScreen;
            MatrixStack matrixStack = new MatrixStack();
            this.fill(matrixStack, 0, 0, this.client.getWindow().getScaledWidth(), this.client.getWindow().getScaledHeight(), 0x000000);
            this.client.currentScreen.drawTexture(new MatrixStack(), 0, 0, 0, 0, screen.width, screen.height);
        }


        // if player is burning
        if (FireInfectedPlayers.isPlayerInList(player)) {
            this.itemRenderer.renderInGuiWithOverrides(player, ModItems.FIRE_ITEM.getDefaultStack(), x, y, seed);
            //this.itemRenderer.renderGuiItemOverlay(this.client.textRenderer, ModItems.FIRE_ITEM.getDefaultStack(), x, y);
        }
    }
}

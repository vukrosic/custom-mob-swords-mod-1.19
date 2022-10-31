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
import net.minecraft.util.math.random.Random;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.item.ModItems;
import net.vukrosic.custommobswordsmod.util.FireInfectedPlayers;
import net.vukrosic.custommobswordsmod.util.GettingEatenByChunkenManager;
import net.vukrosic.custommobswordsmod.util.custom.InGameHudMixinExt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper implements InGameHudMixinExt {

    public boolean showPreyHealthbar = false;

    private static float preyHealth = 20;

    @Override
    public void setShowPreyHealthbar(boolean showPreyHealthbar) {
        this.showPreyHealthbar = showPreyHealthbar;
    }

    @Override
    public boolean getShowPreyHealthbar() {
        return showPreyHealthbar;
    }
    @Override
    public void setPreyHealth(float preyHealth) {
        this.preyHealth = preyHealth;
    }

    @Shadow
    private ItemRenderer itemRenderer;
    @Shadow
    private MinecraftClient client;
    @Shadow private final Random random = Random.create();



    // render hotbar
    @Shadow protected abstract void renderHealthBar(MatrixStack matrices, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking);

    // inject into renderHealthBar

    @Inject(method = "renderHealthBar", at = @At("HEAD"), cancellable = true)
    private void renderHealthBar(MatrixStack matrices, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        if(SetHunterCommand.pray != null) {
            //PreyHealthbarHudManager.setRenderHealthBar(new MatrixStack(), player, x, y, lines, regeneratingHeartIndex, maxHealth, lastHealth, health, absorption, blinking);
            // create a new buf
            // write the matrices to the buf

            /*
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
            // write matrices to buf
            buf.writeText(Text.Serializer.toJson(matrices));

            ClientPlayNetworking.send();*/
        }
    }


    //inject into renderExperienceBar
    @Inject(method = "renderExperienceBar", at = @At("HEAD"), cancellable = true)
    public void renderExperienceBar(MatrixStack matrices, int x, CallbackInfo ci) {
        if(showPreyHealthbar) {

            int incrementedHealth = (int) preyHealth + 1;

            if(incrementedHealth == 1) {
                incrementedHealth = 0;
            }
            renderHealthBar(new MatrixStack(), this.client.player, 15, 15, 500, 0, 20, incrementedHealth, (int)preyHealth, 0, false);


        }
    }

    @Inject(method = "renderHotbarItem", at = @At("HEAD"))
    private void renderHotbar(int x, int y, float tickDelta, PlayerEntity player, ItemStack stack, int seed, CallbackInfo ci) {


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

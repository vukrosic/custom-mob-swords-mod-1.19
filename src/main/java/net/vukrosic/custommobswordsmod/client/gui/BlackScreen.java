package net.vukrosic.custommobswordsmod.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public class BlackScreen extends Screen {
    protected BlackScreen(Text title) {
        super(title);
    }

    public void setBlackScreen() {
        this.client.setScreen(new BlackScreen(Text.of("")));
    }



    private static final Text TEXT = Text.translatable("multiplayer.downloadingTerrain");
    private static final long MIN_LOAD_TIME_MS = 2000L;
    private boolean ready = false;
    private boolean closeOnNextTick = false;
    private final long loadStartTime = System.currentTimeMillis();



    public boolean shouldCloseOnEsc() {
        return false;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackgroundTexture(0);
        //drawCenteredText(matrices, this.textRenderer, TEXT, this.width / 2, this.height / 2 - 50, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }

    public void tick() {
        boolean bl = this.closeOnNextTick || System.currentTimeMillis() > this.loadStartTime + 2000L;
        if (bl && this.client != null && this.client.player != null) {
            BlockPos blockPos = this.client.player.getBlockPos();
            boolean bl2 = this.client.world != null && this.client.world.isOutOfHeightLimit(blockPos.getY());
            if (bl2 || this.client.worldRenderer.isRenderingReady(blockPos)) {
                this.close();
            }

            if (this.ready) {
                this.closeOnNextTick = true;
            }

        }
    }

    public void setReady() {
        this.ready = true;
    }

    public boolean shouldPause() {
        return false;
    }
}

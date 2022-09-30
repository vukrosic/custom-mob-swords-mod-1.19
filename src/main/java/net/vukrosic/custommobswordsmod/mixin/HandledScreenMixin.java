package net.vukrosic.custommobswordsmod.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;
import net.vukrosic.custommobswordsmod.item.ModItems;
import net.vukrosic.custommobswordsmod.util.FireInfectedPlayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin<T extends ScreenHandler> extends Screen implements ScreenHandlerProvider<T> {

    protected HandledScreenMixin(Text title) {
        super(title);
    }
    @Shadow ItemStack touchDragStack;
    @Shadow private Slot touchDragSlotStart;
    @Shadow protected T handler;
    @Shadow boolean touchIsRightClickDrag;
    @Shadow
    Set<Slot> cursorDragSlots;
    @Shadow boolean cursorDragging;
    @Shadow int heldButtonType;
    @Shadow private void calculateOffset() {}
    @Shadow int backgroundWidth = 176;

/*
    @Inject(method = "drawItem", at = @At("HEAD"), cancellable = true)
    public void drawItem(ItemStack stack, int x, int y, String amountText, CallbackInfo ci){


            if(((PlayerEntityExt)this.client.player).getFireInfected()) {
                System.out.println("CASEEEEEEEEEEEEE1 1qqqqq+++++++++++++++++++++++++++++++++++++++q");
                MatrixStack matrixStack = RenderSystem.getModelViewStack();
                matrixStack.translate(0.0, 0.0, 32.0);
                RenderSystem.applyModelViewMatrix();
                this.setZOffset(200);
                this.itemRenderer.zOffset = 200.0F;
                this.itemRenderer.renderInGuiWithOverrides(ModItems.FIRE_ITEM.getDefaultStack(), x, y);
                this.itemRenderer.renderGuiItemOverlay(this.textRenderer, stack, x, y - (this.touchDragStack.isEmpty() ? 0 : 8), amountText);
                this.setZOffset(0);
                this.itemRenderer.zOffset = 0.0F;
            }

    }*/

    @Inject(method = "drawSlot", at = @At("HEAD"), cancellable = true)
    public void drawSlot(MatrixStack matrices, Slot slot, CallbackInfo ci) {
        PlayerEntity player = this.client.player;
        if (FireInfectedPlayers.isPlayerInList(player)) {
                int i = slot.x;
                int j = slot.y;
                ItemStack itemStack = ModItems.FIRE_ITEM.getDefaultStack();
                boolean bl = false;
                boolean bl2 = slot == this.touchDragSlotStart && !this.touchDragStack.isEmpty() && !this.touchIsRightClickDrag;
                ItemStack itemStack2 = this.handler.getCursorStack();
                String string = null;
                if (slot == this.touchDragSlotStart && !this.touchDragStack.isEmpty() && this.touchIsRightClickDrag && !itemStack.isEmpty()) {
                    itemStack = itemStack.copy();
                    itemStack.setCount(itemStack.getCount() / 2);
                } else if (this.cursorDragging && this.cursorDragSlots.contains(slot) && !itemStack2.isEmpty()) {
                    if (this.cursorDragSlots.size() == 1) {
                        return;
                    }

                    if (ScreenHandler.canInsertItemIntoSlot(slot, itemStack2, true) && this.handler.canInsertIntoSlot(slot)) {
                        itemStack = itemStack2.copy();
                        bl = true;
                        ScreenHandler.calculateStackSize(this.cursorDragSlots, this.heldButtonType, itemStack, slot.getStack().isEmpty() ? 0 : slot.getStack().getCount());
                        int k = Math.min(itemStack.getMaxCount(), slot.getMaxItemCount(itemStack));
                        if (itemStack.getCount() > k) {
                            String var10000 = Formatting.YELLOW.toString();
                            string = var10000 + k;
                            itemStack.setCount(k);
                        }
                    } else {
                        this.cursorDragSlots.remove(slot);
                        this.calculateOffset();
                    }
                }

                this.setZOffset(100);
                this.itemRenderer.zOffset = 100.0F;
                if (itemStack.isEmpty() && slot.isEnabled()) {
                    Pair<Identifier, Identifier> pair = slot.getBackgroundSprite();
                    if (pair != null) {
                        Sprite sprite = (Sprite) this.client.getSpriteAtlas((Identifier) pair.getFirst()).apply((Identifier) pair.getSecond());
                        RenderSystem.setShaderTexture(0, sprite.getAtlas().getId());
                        drawSprite(matrices, i, j, this.getZOffset(), 16, 16, sprite);
                        bl2 = true;
                    }
                }

                if (!bl2) {
                    if (bl) {
                        fill(matrices, i, j, i + 16, j + 16, -2130706433);
                    }

                    RenderSystem.enableDepthTest();
                    this.itemRenderer.renderInGuiWithOverrides(this.client.player, itemStack, i, j, slot.x + slot.y * this.backgroundWidth);
                    this.itemRenderer.renderGuiItemOverlay(this.textRenderer, itemStack, i, j, string);
                }

                this.itemRenderer.zOffset = 0.0F;
                this.setZOffset(0);
            }
        }



}

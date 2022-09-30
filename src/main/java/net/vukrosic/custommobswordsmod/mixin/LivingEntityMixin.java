package net.vukrosic.custommobswordsmod.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityLike;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.util.IZombieEntityMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Nameable, EntityLike, CommandOutput {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    protected abstract float getJumpVelocity();

    @Shadow
    public abstract double getJumpBoostVelocityModifier();

    @Overwrite
    public void jump() {
        double d = (double)this.getJumpVelocity() + this.getJumpBoostVelocityModifier();

        Iterable<ItemStack> itemStack = this.getArmorItems();

        for (ItemStack stack : itemStack) {
            if(stack.getTranslationKey().equals("item.custommobswordsmod.rabbit_boots"))
            {
                System.out.println("JUMP INTO OBLIVION ");
                d *= 8;
            }
        }

        Vec3d vec3d = this.getVelocity();
        this.setVelocity(vec3d.x, d, vec3d.z);
        if (this.isSprinting()) {
            float f = this.getYaw() * ((float)Math.PI / 180);
            this.setVelocity(this.getVelocity().add(-MathHelper.sin(f) * 0.2f, 0.0, MathHelper.cos(f) * 0.2f));
        }
        this.velocityDirty = true;
    }

    /*
    @Inject(at = @At("HEAD"), method = "jump", cancellable = true)
    public void useUndyingSword(CallbackInfo callbackInfo) {



        Iterable<ItemStack> itemStack = this.getArmorItems();
        if(this.getArmorItems().iterator().
        for (ItemStack stack : itemStack) {
            if(stack.getTranslationKey().equals("item.minecraft.golden_boots"))
            {
                System.out.println("JUMP INTO OBLIVION ");

            }
        }
    }*/
}

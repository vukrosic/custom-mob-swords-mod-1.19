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
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityLike;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.entity.custom.LivingEntityExt;
import net.vukrosic.custommobswordsmod.util.IZombieEntityMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Nameable, EntityLike, CommandOutput, LivingEntityExt {

    boolean beingShotFromFrogKing = false;
    public boolean getBeingShotFromFrogKing() {
        return beingShotFromFrogKing;
    }
    public void setBeingShotFromFrogKing(boolean beingShotFromFrogKing) {
        this.beingShotFromFrogKing = beingShotFromFrogKing;
    }
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    protected abstract float getJumpVelocity();

    @Shadow
    public abstract double getJumpBoostVelocityModifier();



    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void tick(CallbackInfo ci) {
        if(beingShotFromFrogKing){
            // get velocity
            Vec3d velocity = this.getVelocity();
            float magnitude = (float) Math.sqrt(velocity.x * velocity.x + velocity.y * velocity.y + velocity.z * velocity.z);
            if(magnitude < 0.3f){
                beingShotFromFrogKing = false;
            }
            world.getOtherEntities(this, this.getBoundingBox().expand(2), (entity) -> {
                return entity instanceof LivingEntity;
            }).forEach((entity) -> {
                entity.damage(DamageSource.MAGIC, 4);
            });
        }
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

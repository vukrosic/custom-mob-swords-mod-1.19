package net.vukrosic.custommobswordsmod.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.util.IZombieEntityMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements IZombieEntityMixin {

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public Packet<?> createSpawnPacket() {
        return null;
    }

/*
    public Boolean eviscereting = false;

    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void tickMovement(CallbackInfo ci) {
        //entityEntity entity = (entityEntity) (Object) this;

        if(eviscereting) {


            LivingEntity entity = (LivingEntity) (Object) this;

            if (entity.world.isClient) return;

            int evisceratingRadius = 6;
            BlockPos pos = entity.getBlockPos();
            for (int x = -evisceratingRadius; x <= evisceratingRadius; x++) {
                for (int y = -evisceratingRadius; y <= evisceratingRadius; y++) {
                    for (int z = -evisceratingRadius; z <= evisceratingRadius; z++) {
                        BlockPos newPos = pos.add(x, y, z);
                        BlockState state = entity.world.getBlockState(newPos);
                        Block block = state.getBlock();
                        if (block != Blocks.BEDROCK) {
                            entity.world.breakBlock(newPos, false);
                        }
                    }
                }
            }
        }
    }*/
/*
    @Override
    public void setEviscereting(boolean value) {
        eviscereting = value;
    }*/



    /*

    @Shadow
    public  native ItemStack getStackInHand(Hand hand_1);

    @Shadow
    public native LivingEntity getAttacker();

    @Shadow
    public native boolean hasStatusEffect(StatusEffect effect);

    @Shadow public native void setHealth(float health);

    @Shadow public native boolean clearStatusEffects();

    @Shadow public native boolean addStatusEffect(StatusEffectInstance statusEffectInstance_1);

    @Shadow public native void heal(float amount);

    @Shadow public native EntityGroup getGroup();

    @Shadow public native boolean isUndead();


    @Inject(at = @At("HEAD"), method = "tryUseTotem", cancellable = true)
    public void useUndyingSword(DamageSource damageSource_1, CallbackInfoReturnable<Boolean> callback) {
        /*inits PlayerEntity entity, which is a copy of this casted to Living Entity and then PlayerEntity*//*
        Entity entity =  this;



        /*ItemStack object that is set to the offhand item that entity is carrying*//*
        ItemStack offhand_stack = ((LivingEntityMixin) entity).getStackInHand(Hand.OFF_HAND);
        ItemStack offhand_stack_copy;

        ItemStack mainhand_stack = ((LivingEntityMixin) entity).getStackInHand(Hand.MAIN_HAND);

        //Executes if the item in offhand_stack is equal to the explosive totem of Undying
        if ((offhand_stack.getItem() == ModItems.UNDYING_SWORD) || (mainhand_stack.getItem() == ModItems.UNDYING_SWORD) ) {

            /*If the damagesource is something that could kill a player in creative mode, the totem does not work*//*
            if (damageSource_1.isOutOfWorld()) {

                callback.setReturnValue(false);
            }
            else {
                /*sets copy to offhand_stack*//*
                offhand_stack_copy = offhand_stack;
                /*deletes explosive totem from offhand*//*

                if((offhand_stack.getItem() == ModItems.UNDYING_SWORD)) {
                    offhand_stack.decrement(1);
                }
                else if((mainhand_stack.getItem() == ModItems.UNDYING_SWORD)){

                    mainhand_stack.decrement(1);

                }


                /*if the offhand_stack_copy is not empty, then execute*/


                /*totem saves player from an untimely death*//*
                this.setHealth(1.0F);
                this.clearStatusEffects();
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 125, 2));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 350, 4));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, evisceratingRadius0, 2));
                this.world.sendEntityStatus(this, (byte)35);

                /*Spawns a tntEntity on the player upon use of Explosive Totem*//*

                TntEntity tntEntity = EntityType.TNT.create(world);
                tntEntity.setFuse(5);
                tntEntity.refreshPositionAndAngles(this.getX() , this.getY() , this.getZ(), 0, 0);
                world.spawnEntity(tntEntity);

                callback.setReturnValue(true);




            }

        }


    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public Packet<?> createSpawnPacket() {
        return null;
    }*/
//}
